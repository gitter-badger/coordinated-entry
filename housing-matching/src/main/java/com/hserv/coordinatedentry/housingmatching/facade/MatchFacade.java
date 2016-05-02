package com.hserv.coordinatedentry.housingmatching.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.helper.MatchAlgorithmRequestHelper;
import com.hserv.coordinatedentry.housingmatching.model.MatchAlgorithmModel;
import com.hserv.coordinatedentry.housingmatching.model.MatchReservationModel;
import com.hserv.coordinatedentry.housingmatching.service.AutoMatchAlgorithmService;

@Component
public class MatchFacade {

	@Autowired
	MatchAlgorithmRequestHelper matchAlgorithmRequestHelper;

	@Autowired
	AutoMatchAlgorithmService autoMatchAlgorithmService;

	public void createMatch(String userId) {
		// TODO validate user
		
		
		MatchAlgorithmModel matchAlgoModel = matchAlgorithmRequestHelper.getMatchAlgorithmRequest(userId);

		// 6. call auto-matching algorithm to get right match
		List<MatchReservationModel> matchedReservationModels = autoMatchAlgorithmService.execute(matchAlgoModel);

		// 7. check the county

		// 8. if county is monterey -
		// 9. call DB layer to update match_reservation table with match_status
		// as success
		// 10. Call Notification service for Admin to approve or reject

		// 11. if county is non monterey
		// 12. call DB layer to update match_reservation table with match_status
		// as approve
		// 13. Call Notification service to notify housing unit and client
		// return null; // TODO Autogenerated Method Stub. Implement me please.
	}

}