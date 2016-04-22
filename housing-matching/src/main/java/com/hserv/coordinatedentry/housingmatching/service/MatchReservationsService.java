package com.hserv.coordinatedentry.housingmatching.service;

import java.util.List;

import com.hserv.coordinatedentry.housingmatching.model.MatchReservationModel;

public interface MatchReservationsService {

	public boolean createMatchReservation(MatchReservationModel matchReservationModel);
	
	public boolean createMatchReservation(List<MatchReservationModel> matchReservationModels);
	
	public int updateMatchStatus(String matchStatus, String reservationId);
	
	public int updateMatchStatusAndMAnualMatch(String matchStatus, boolean manualMatch, String reservationId);
	
	public void deleteAll();
	
	public List<MatchReservationModel> findAll();
	
	public boolean deleteByClientId(String clientId);
	
	public MatchReservationModel findByClientId(String clientId);
	
	public boolean updateByClientId(MatchReservationModel matchReservationModel);
 }
