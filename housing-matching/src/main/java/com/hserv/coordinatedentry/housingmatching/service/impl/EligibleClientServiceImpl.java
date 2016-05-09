package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hserv.coordinatedentry.housingmatching.dao.EligibleClientsRepository;
import com.hserv.coordinatedentry.housingmatching.entity.EligibleClients;
import com.hserv.coordinatedentry.housingmatching.model.EligibleClientModel;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;
import com.hserv.coordinatedentry.housingmatching.translator.EligibleClientsTranslator;

@Service
public class EligibleClientServiceImpl implements EligibleClientService {

	@Autowired
	EligibleClientsRepository eligibleClientsRepository;

	@Autowired
	private EligibleClientsTranslator eligibleClientsTranslator;

	@Override
	public List<EligibleClientModel> getEligibleClients(int num , String programType) {
		List<EligibleClientModel> eligibleClientModels = new ArrayList<>();
		List<EligibleClients> eligibleClients = eligibleClientsRepository
				.findTopEligibleClients(programType ,new PageRequest(0, num, eligibleClientSortClause()));
		for (EligibleClients eligibleClient : eligibleClients) {
			eligibleClientModels.add(eligibleClientsTranslator.translate(eligibleClient));
		}

		return eligibleClientModels;
	}

	@Override
	public EligibleClientModel getEligibleClientDetail(UUID clientID) {
		EligibleClients eligibleClient = eligibleClientsRepository.findByClientId(clientID);
		EligibleClientModel eligibleClientModel=  eligibleClientsTranslator.translate(eligibleClient);
		return eligibleClientModel;
	}
	
	@Override
	public List<EligibleClientModel> getEligibleClients() {
		List<EligibleClients> clients = eligibleClientsRepository.findAll(eligibleClientSortClause());
		List<EligibleClientModel> clientsModels = new ArrayList<EligibleClientModel>();
		for(EligibleClients client : clients) {
			clientsModels.add(eligibleClientsTranslator.translate(client));
		}
		return clientsModels;
	}

	@Override
	public boolean updateEligibleClient(UUID clientID, EligibleClientModel eligibleClientModel) {
		boolean status = false;
		if(!StringUtils.isEmpty(eligibleClientModel.getClientId())&&
				eligibleClientsRepository.exists(clientID)){
			eligibleClientsRepository.saveAndFlush(eligibleClientsTranslator.translate(eligibleClientModel));
			status = true;
		}
		return status;
	}

	@Override
	public boolean deleteEligibleClientById(UUID clientID) {
		boolean status = false;
		if(!StringUtils.isEmpty(clientID)&& eligibleClientsRepository.exists(clientID)){
			eligibleClientsRepository.deleteByClientId(clientID);
			status = true;
		}
		return status;
	}

	@Override
	public boolean createEligibleClient(EligibleClientModel eligibleClientModel) {
		boolean status = false;
		if(!StringUtils.isEmpty(eligibleClientModel.getClientId()) &&
				!eligibleClientsRepository.exists(eligibleClientModel.getClientId())){
			eligibleClientsRepository.saveAndFlush(eligibleClientsTranslator.translate(eligibleClientModel));
			status = true;
		}
		return status;
	}

	@Override
	public boolean deleteAll() {
		eligibleClientsRepository.deleteAll();
		return true;
	}

	@Override
	public boolean updateEligibleClients(List<EligibleClientModel> eligibleClientModels) {
		if (eligibleClientModels != null && !eligibleClientModels.isEmpty()) {
			for (EligibleClientModel clientModel : eligibleClientModels) {
				updateEligibleClient(clientModel.getClientId(), clientModel);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean createEligibleClients(List<EligibleClientModel> eligibleClientModels) {
		if (eligibleClientModels != null && !eligibleClientModels.isEmpty()) {
			for (EligibleClientModel clientModel : eligibleClientModels) {
				createEligibleClient(clientModel);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean updateEligibleClientScore(UUID clientID, int scoreTotal) {
		boolean status = false;
		if(!StringUtils.isEmpty(clientID)&&
				eligibleClientsRepository.exists(clientID)){
			eligibleClientsRepository.updateScoreByClientId(scoreTotal, clientID);
			status = true;
		}
		return status;	
	}
	
	private Sort eligibleClientSortClause() {
		return sortBySurveyDate().and(sortByScore());
	}

	private Sort sortByScore() {
		return new Sort(Sort.Direction.DESC, "surveyScore");
	}

	private Sort sortBySurveyDate() {
		return new Sort(Sort.Direction.ASC, "surveyDate");
	}
	
}
