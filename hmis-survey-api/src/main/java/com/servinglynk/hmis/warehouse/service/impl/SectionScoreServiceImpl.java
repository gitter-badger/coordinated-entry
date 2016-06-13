package com.servinglynk.hmis.warehouse.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.SectionScores;
import com.servinglynk.hmis.warehouse.core.model.SortedPagination;
import com.servinglynk.hmis.warehouse.model.QuestionEntity;
import com.servinglynk.hmis.warehouse.model.ResponseEntity;
import com.servinglynk.hmis.warehouse.model.SectionScoreEntity;
import com.servinglynk.hmis.warehouse.model.SurveyEntity;
import com.servinglynk.hmis.warehouse.model.SurveySectionEntity;
import com.servinglynk.hmis.warehouse.service.SectionScoreService;
import com.servinglynk.hmis.warehouse.service.converter.SectionScoreConverter;
import com.servinglynk.hmis.warehouse.service.exception.SurveyNotFoundException;

@Component
public class SectionScoreServiceImpl extends ServiceBase implements SectionScoreService {

	@Transactional
	public SectionScores getAllSectionScores(UUID clientId,UUID surveyId,UUID sectionId,Integer startIndex,Integer maxItems){
		SectionScores scores = new SectionScores();
		
		List<SectionScoreEntity> entities = daoFactory.getSectionScoreDao().getAllSectionScores(surveyId, sectionId, startIndex, maxItems);
		for(SectionScoreEntity entity : entities){
			scores.addSectionScore(SectionScoreConverter.entityToModel(entity));
		}
		
        SortedPagination pagination = new SortedPagination();
        long count = daoFactory.getSectionScoreDao().getAllSectionScoresCount(surveyId, sectionId);
        pagination.setFrom(startIndex);
        pagination.setReturned(scores.getSectionScores().size());
        pagination.setTotal((int)count);
        scores.setPagination(pagination);
		return scores;
	}
	
	@Transactional
	public void deleteSectionScores(UUID clientId,UUID surveyId,UUID sectionId){
		
		List<SectionScoreEntity> entities = daoFactory.getSectionScoreDao().getAllSectionScores(surveyId, sectionId, 0, 0);
		
		for(SectionScoreEntity entity : entities ){
			daoFactory.getSectionScoreDao().deleteSectionScore(entity);
		}
	}
	
	@Transactional
	public void updateSectionScores(UUID clientId,UUID surveyId,UUID sectionId){
		deleteSectionScores(clientId, surveyId, sectionId);
		this.calculateSectionScore(surveyId, clientId);
		
	}
	
	@Transactional
	public void calculateSectionScore(UUID surveyId,UUID clientid){
			SurveyEntity surveyEntity = daoFactory.getSurveyDao().getSurveyById(surveyId);
			if(surveyEntity ==null) throw new SurveyNotFoundException();
			for(SurveySectionEntity sectionEntity :   surveyEntity.getSurveySectionEntities()){
				List<ResponseEntity> responseEntities = daoFactory.getResponseEntityDao().getAllSurveyResponses(surveyId, sectionEntity.getId(),0, 0);
				int score = 0;
				
				for(ResponseEntity entity : responseEntities){
					score = score + entity.getQuestionScore();
				}
				SectionScoreEntity scoreEntity = new SectionScoreEntity();
				scoreEntity.setSectionEntity(sectionEntity);
				scoreEntity.setSectionScore(score);
				scoreEntity.setSurveyEntity(surveyEntity);
				scoreEntity.setClientId(clientid);
				daoFactory.getSectionScoreDao().createSectionScore(scoreEntity);
			}
			
	}
	
	
	public int calculateQuestionScore(QuestionEntity questionEntity,ResponseEntity responseEntity)  {
		int score=0;
		boolean result;
		EvaluationContext context = new StandardEvaluationContext(responseEntity);
		ExpressionParser parser = new SpelExpressionParser();
			Expression expression = parser.parseExpression(questionEntity.getCorrectValueForAssessment());
			 result =  expression.getValue(context,boolean.class);
			if(result) {
					score = questionEntity.getQuestionWeight();
			}

		return score;		
	}
	
}