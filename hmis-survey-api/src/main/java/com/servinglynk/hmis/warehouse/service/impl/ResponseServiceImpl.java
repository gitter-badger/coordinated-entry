package com.servinglynk.hmis.warehouse.service.impl; 

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.Response;
import com.servinglynk.hmis.warehouse.core.model.Responses;
import com.servinglynk.hmis.warehouse.core.model.SortedPagination;
import com.servinglynk.hmis.warehouse.model.QuestionEntity;
import com.servinglynk.hmis.warehouse.model.ResponseEntity;
import com.servinglynk.hmis.warehouse.model.SurveyEntity;
import com.servinglynk.hmis.warehouse.model.SurveySectionEntity;
import com.servinglynk.hmis.warehouse.service.ResponseService;
import com.servinglynk.hmis.warehouse.service.converter.ResponseConverter;
import com.servinglynk.hmis.warehouse.service.exception.QuestionNotFoundException;
import com.servinglynk.hmis.warehouse.service.exception.ResponseNotFoundException;
import com.servinglynk.hmis.warehouse.service.exception.SurveyNotFoundException;
import com.servinglynk.hmis.warehouse.service.exception.SurveySectionNotFoundException;


@Component
public class ResponseServiceImpl extends ServiceBase implements ResponseService  {

   @Transactional
   public Responses createResponse(UUID clientId,UUID surveyId,Responses responses,String caller){
	   Responses returnResponses = new Responses();
	   
	   SurveyEntity surveyEntity = daoFactory.getSurveyEntityDao().getSurveyEntityById(surveyId);
	   if(surveyEntity==null) throw new SurveyNotFoundException();
	   
	   
	   for(Response response : responses.getResponses()){
	   
	   QuestionEntity questionEntity = daoFactory.getQuestionEntityDao().getQuestionEntityById(response.getQuestionId());
	   if(questionEntity==null) throw new QuestionNotFoundException();
	   
	   SurveySectionEntity sectionEntity = daoFactory.getSurveySectionEntityDao().getSurveySectionEntityById(response.getSectionId());
	   if(sectionEntity==null) throw new SurveySectionNotFoundException();
	   
       ResponseEntity pResponse = ResponseConverter.modelToEntity(response, null);
       pResponse.setSurveyEntity(surveyEntity);
       pResponse.setSurveySectionEntity(sectionEntity);
       pResponse.setQuestionEntity(questionEntity);
       pResponse.setCreatedAt(LocalDateTime.now());
       pResponse.setUser(caller);
//       pResponse.setQuestionScore(serviceFactory.getSectionScoreService().calculateQuestionScore(questionEntity, response.getResponseText()));
       daoFactory.getResponseEntityDao().createResponseEntity(pResponse);
    //   pResponse.setQuestionScore(serviceFactory.getSectionScoreService().calculateQuestionScore(questionEntity, pResponse));
       daoFactory.getResponseEntityDao().updateResponseEntity(pResponse);
       returnResponses.addResponse(new Response(pResponse.getId()));
	   }
	   
	 //  serviceFactory.getSectionScoreService().updateSectionScores(clientId, surveyId, null);
       return returnResponses;
   }


   @Transactional
   public Response updateResponse(Response response,String caller){
       ResponseEntity pResponse = daoFactory.getResponseEntityDao().getResponseEntityById(response.getResponseId());
       if(pResponse==null) throw new ResponseNotFoundException();

       ResponseConverter.modelToEntity(response, pResponse);
       pResponse.setUpdatedAt(LocalDateTime.now());
       pResponse.setUser(caller);
   //    pResponse.setQuestionScore(serviceFactory.getSectionScoreService().calculateQuestionScore(pResponse.getQuestionEntity(), pResponse));
       daoFactory.getResponseEntityDao().updateResponseEntity(pResponse);
       response.setResponseId(pResponse.getId());
	   serviceFactory.getSectionScoreService().updateSectionScores(pResponse.getClientId(), pResponse.getSurveyEntity().getId(), null);
       return response;
   }


   @Transactional
   public Response deleteResponse(UUID ResponseId,String caller){
       ResponseEntity pResponse = daoFactory.getResponseEntityDao().getResponseEntityById(ResponseId);
       if(pResponse==null) throw new ResponseNotFoundException();

       pResponse.setUser(caller);
       daoFactory.getResponseEntityDao().deleteResponseEntity(pResponse);
       return new Response();
   }


   @Transactional
   public Response getResponseById(UUID ResponseId){
       ResponseEntity pResponse = daoFactory.getResponseEntityDao().getResponseEntityById(ResponseId);
       if(pResponse==null) throw new ResponseNotFoundException();

       return ResponseConverter.entityToModel( pResponse );
   }


   @Transactional
   public Responses getAllSurveyResponses(UUID surveyid, Integer startIndex, Integer maxItems){
       Responses Responses = new Responses();
        List<ResponseEntity> entities = daoFactory.getResponseEntityDao().getAllSurveyResponses(surveyid,null,startIndex,maxItems);
        for(ResponseEntity entity : entities){
           Responses.addResponse(ResponseConverter.entityToModel(entity));
        }
        long count = daoFactory.getResponseEntityDao().getSurveyResponsesCount(surveyid);
        SortedPagination pagination = new SortedPagination();
 
        pagination.setFrom(startIndex);
        pagination.setReturned(Responses.getResponses().size());
        pagination.setTotal((int)count);
        Responses.setPagination(pagination);
        return Responses; 
   }
}