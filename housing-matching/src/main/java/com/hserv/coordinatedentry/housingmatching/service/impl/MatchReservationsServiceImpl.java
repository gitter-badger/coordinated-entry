package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hserv.coordinatedentry.housingmatching.dao.RepositoryFactory;
import com.hserv.coordinatedentry.housingmatching.entity.EligibilityRequirement;
import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.entity.HousingInventory;
import com.hserv.coordinatedentry.housingmatching.entity.Match;
import com.hserv.coordinatedentry.housingmatching.entity.MatchStatus;
import com.hserv.coordinatedentry.housingmatching.external.HousingUnitService;
import com.hserv.coordinatedentry.housingmatching.external.NotificationService;
import com.hserv.coordinatedentry.housingmatching.external.ProjectService;
import com.hserv.coordinatedentry.housingmatching.helper.InvalidMatchStatus;
import com.hserv.coordinatedentry.housingmatching.model.MatchReservationModel;
import com.hserv.coordinatedentry.housingmatching.model.MatchStatusModel;
import com.hserv.coordinatedentry.housingmatching.model.Project;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;
import com.hserv.coordinatedentry.housingmatching.service.MatchReservationsService;
import com.hserv.coordinatedentry.housingmatching.service.ServiceFactory;
import com.hserv.coordinatedentry.housingmatching.translator.MatchReservationTranslator;
import com.servinglynk.hmis.warehouse.client.model.SearchRequest;
import com.servinglynk.hmis.warehouse.client.search.ISearchServiceClient;
import com.servinglynk.hmis.warehouse.core.model.BaseClient;
import com.servinglynk.hmis.warehouse.core.model.Session;

@Service
public class MatchReservationsServiceImpl implements MatchReservationsService {
	
	
	@Autowired
	RepositoryFactory repositoryFactory;
	
	@Autowired
	ServiceFactory serviceFactory;

	@Autowired
	MatchReservationTranslator matchReservationTranslator;

	@Autowired
	NotificationService notificationService;
	
	@Autowired
	EligibleClientService eligibleClientService;
	
	@Autowired
	HousingUnitService housingUnitService;
		
	@Autowired
	CommunityServiceLocator communityServiceLocator;
	
	@Autowired
	EligibilityValidator eligibilityValidator;
	
	
	@Autowired
	ISearchServiceClient searchServiceClient;
	
	Map<Integer, Integer[]> statusMap =new HashMap<Integer,Integer[]>();
	
	@PostConstruct
	public void init(){
		statusMap.put(0, new Integer[]{1,10});
		statusMap.put(1, new Integer[]{2,5,10});
		statusMap.put(2, new Integer[]{3,10});
		statusMap.put(3, new Integer[]{4,10});
		statusMap.put(4, new Integer[]{5,10});
		statusMap.put(5, new Integer[]{6,10});
		statusMap.put(6, new Integer[]{7,10});
		statusMap.put(7, new Integer[]{10});	
	}
		
	@Override
	public boolean createMatchReservation(MatchReservationModel matchReservationModel) {
	    repositoryFactory.getMatchReservationsRepository().saveAndFlush(matchReservationTranslator.translate(matchReservationModel));
		return true;
	}

	@Override
	public boolean createMatchReservation(List<MatchReservationModel> matchReservationModels) {
		Set<Match> matchReservations = matchReservationTranslator
				.translates(new HashSet<MatchReservationModel>(matchReservationModels));
		for (Match matchReservation : matchReservations) {
			repositoryFactory.getMatchReservationsRepository().saveAndFlush(matchReservation);
		}
		return true;
	}

	@Override
	public boolean deleteAll() {
		repositoryFactory.getMatchReservationsRepository().deleteAll();
		return true;
	}

	@Override
	public Page<Match> findAll(String q,Pageable pageable,String projectGroupCode) {
		String[] status ={};
		if(q!=null)
			status=	q.split(",");
		
		if(status.length>0){
			return repositoryFactory.getMatchReservationsRepository().findByProgramTypeAndMatchStatusIn(projectGroupCode,status,pageable);
		}else{
		return repositoryFactory.getMatchReservationsRepository().findByProgramType(projectGroupCode,pageable);
		}
	}

	@Override
	public boolean deleteByClientId(UUID clientId) {
		if (repositoryFactory.getMatchReservationsRepository().exists(clientId)) {
			repositoryFactory.getMatchReservationsRepository().deleteByEligibleClientClientId(clientId);
			return true;
		}
		return false;
	}

	@Override
	public MatchReservationModel findByClientId(UUID clientId) {
		EligibleClient eligibleClient = repositoryFactory.getEligibleClientsRepository().findOne(clientId);
		if (eligibleClient!=null) 
			return matchReservationTranslator
					.translate(repositoryFactory.getMatchReservationsRepository().findByEligibleClient(eligibleClient).get(0));
		
		return null;
	}

	@Override
	public boolean updateByClientId(UUID clientId, MatchReservationModel matchReservationModel) {
	
			EligibleClient eligibleClient = repositoryFactory.getEligibleClientsRepository().findOne(clientId);
			if(eligibleClient==null)
				return false;
			
		Match matchReservations = matchReservationTranslator.translate(matchReservationModel);
			matchReservations.setEligibleClient(eligibleClient);
		repositoryFactory.getMatchReservationsRepository().saveAndFlush(matchReservations);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Async
	public void create(Session session,String trustedAppId) throws Exception {
		List<Match> matches = new ArrayList<Match>();
		List<EligibleClient> matchedEligibleClients = new ArrayList<EligibleClient>();
		List<EligibleClient> eligibleClients = repositoryFactory.getEligibleClientsRepository().findByProgramTypeAndMatched(session.getAccount().getProjectGroup().getProjectGroupCode(), false);
		
		List<EligibilityRequirement> eligibilityRequirements=repositoryFactory.getEligibilityRequirementRepository().findByProjectGroupCode(session.getAccount().getProjectGroup().getProjectGroupCode());
		
		for(EligibleClient eligibleClient : eligibleClients){
			
			SearchRequest request = new SearchRequest();
			request.setTrustedAppId(trustedAppId);
			request.setSearchEntity("clients");
			request.setSessionToken(session.getToken());
			request.addSearchParam("q", eligibleClient.getClientId());
			List<BaseClient> clients = (List<BaseClient>) searchServiceClient.search(request);
			BaseClient client = clients.get(0);
			UUID projectId =null;
					eligibilityValidator.validateProjectEligibility(client, eligibilityRequirements);		
			if(projectId!=null) {
				List<HousingInventory> housingInventories = repositoryFactory.getHousingUnitsRepository().findByProjectId(projectId+"");
					for(HousingInventory housingInventory : housingInventories){
							if(housingInventory.getBedsCapacity() != housingInventory.getBedsCurrent()) {
									Match match = new Match();
									match.setDateCreated(new Date());
									match.setEligibleClient(eligibleClient);
									match.setHousingUnitId(housingInventory.getHousingInventoryId());
									match.setManualMatch(false);
									match.setMatchDate(new Date());
									match.setMatchStatus(0);
									matches.add(match);
									matchedEligibleClients.add(eligibleClient);
									housingInventory.setBedsCurrent(housingInventory.getBedsCurrent()-1);
						}
					}
			}
		} 
		
		for(Match match : matches){
            repositoryFactory.getMatchReservationsRepository().saveAndFlush(match);
            EligibleClient eligibleClient = match.getEligibleClient();
            eligibleClient.setMatched(true);
            repositoryFactory.getEligibleClientsRepository().saveAndFlush(eligibleClient);
		}
		
	}
	
	@Override
	public void updateMatchStatus(UUID clientId, MatchStatusModel statusModel,String auditUser) throws Exception {
		EligibleClient client = new EligibleClient();
		client.setClientId(clientId);
		List<Match> matches = (List<Match>) repositoryFactory.getMatchReservationsRepository().findByEligibleClient(client);
		if(matches.isEmpty()) throw new ResourceNotFoundException("No match reservation found for this client");
		Match match = matches.get(0);
		Integer[] newStatus =  statusMap.get(match.getMatchStatus());
		System.out.println(statusModel.getStatus());
		if(!Arrays.asList(newStatus).contains(statusModel.getStatus())){
			throw new InvalidMatchStatus("Invalid status. Current status "+match.getMatchStatus());
		}
		
		
		MatchStatus matchStatus = new MatchStatus();
		BeanUtils.copyProperties(statusModel, matchStatus,"recipients");
		if(!statusModel.getRecipients().getToRecipients().isEmpty())
			matchStatus.setRecipients(statusModel.getRecipients().toJSONString());
		matchStatus.setClientId(clientId);
		matchStatus.setUserId(auditUser);
		repositoryFactory.getMatchStatusRepository().save(matchStatus);
		match.setMatchStatus(statusModel.getStatus());
		repositoryFactory.getMatchReservationsRepository().save(match);
		if(!statusModel.getRecipients().getToRecipients().isEmpty())
			notificationService.notifyStatusUpdate(match, statusModel.getRecipients());
	}
	
	public List<MatchStatusModel> getMatchStatusHistory(UUID clientId) throws Exception {
		List<MatchStatusModel> history = new ArrayList<MatchStatusModel>();
		List<MatchStatus> statusHistory = repositoryFactory.getMatchStatusRepository().findByClientId(clientId);
		for(MatchStatus matchStatus : statusHistory){
			MatchStatusModel matchStatusModel = new MatchStatusModel();
			BeanUtils.copyProperties(matchStatus, matchStatusModel,"recipients");
			//matchStatusModel.setStatusCode(MatchStatusUpdateEnum.lookupEnum(matchStatus.getStatus()+"").getValue());
		//	if(matchStatus.getRecipients()!=null)
		//			matchStatusModel.setRecipients(objectMapper.readValue(matchStatus.getRecipients(), Recipients.class));
			history.add(matchStatusModel);
			
		}
		return history;
	}
	
	public MatchStatusModel getMatchStatusById(UUID id) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		MatchStatusModel matchStatusModel = new MatchStatusModel();
		MatchStatus matchStatus =	repositoryFactory.getMatchStatusRepository().findOne(id);
		BeanUtils.copyProperties(matchStatus, matchStatusModel,"recipients");
	/*	if(matchStatus.getRecipients()!=null)
				matchStatusModel.setRecipients(objectMapper.readValue(matchStatus.getRecipients(), Recipients.class));*/
		return matchStatusModel;
	}
	
	@Autowired
	ProjectService projectService;
	
	
	@Async
	public void matchingProcess(Integer maxClients,Session session , String trustedAppId) {

		String projectGroup = session.getAccount().getProjectGroup().getProjectGroupCode();
		List<HousingInventory> housingInventories = repositoryFactory.getHousingUnitsRepository().findByProjectGroupCode(projectGroup);

		for(HousingInventory housingInventory : housingInventories ){
			Integer matchCount =0;
			Project project = projectService.getProjectInfo(housingInventory.getProjectId(), session, trustedAppId);
			if(project!=null) {
					List<EligibleClient> clients= new ArrayList<>();
						if(housingInventory.getFamilyUnit()){
							  clients = eligibleClientService.getEligibleClients(project.getProjectType(),projectGroup, "FAMILY");
						}else{
							 clients = eligibleClientService.getEligibleClients(project.getProjectType(),projectGroup, "SINGLE_ADULT");					
						}
						
						List<EligibilityRequirement> requirements = repositoryFactory.getEligibilityRequirementRepository().findByProjectId(housingInventory.getProjectId());
							
						for(EligibleClient client : clients) {
							System.out.println("  Project id "+project.getProjectId() +" housing unit id "+housingInventory.getHousingInventoryId() +" client id "+client.getClientId());
							boolean validClient= false;
							BaseClient baseClient = eligibleClientService.getClientInfo(client.getClientId(), trustedAppId, session.getToken());
							if(baseClient!=null){
										Integer bedsRequired = eligibilityValidator.validateBedsAvailability(baseClient.getClientId(), housingInventory.getBedsCurrent());
										if(bedsRequired!=0) validClient = eligibilityValidator.validateProjectEligibility(baseClient, requirements);						
										if(validClient){
											this.matchClient(client, housingInventory);
											matchCount++;
												break;
										}
							}
						}
						if(matchCount==maxClients) break;
			}
		}
	}
	
	public void matchClient(EligibleClient client,HousingInventory housingInventory){
		Match match = new Match();
		match.setEligibleClient(client);
		match.setHousingUnitId(housingInventory.getHousingInventoryId());
		match.setManualMatch(false);
		match.setMatchDate(new Date());
		match.setMatchStatus(0);
		repositoryFactory.getMatchReservationsRepository().save(match);
		client.setMatched(true);
		repositoryFactory.getEligibleClientsRepository().save(client);
		housingInventory.setVacant(false);
//		housingInventory.setBedsCurrent(housingInventory.getBedsCurrent()-1);							
		repositoryFactory.getHousingUnitsRepository().save(housingInventory);
	}
}