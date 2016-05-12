package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hserv.coordinatedentry.housingmatching.entity.Match;

@Repository
public interface MatchReservationsRepository extends JpaRepository<Match, Serializable>{

	@Modifying(clearAutomatically=true)
	@Query("update Match ms set ms.matchStatus = ?1 where ms.reservationId = ?2")
	int updateMatchStatus(String matchStatus, UUID reservationId);
	
	@Modifying(clearAutomatically=true)
	@Query("update Match ms set ms.matchStatus = ?1, ms.manualMatch = ?2 where ms.reservationId = ?3")
	int updateMatchStatusAndManualMatch(String matchStatus, boolean manualMatch, UUID reservationId);
	
	void deleteByEligibleClientClientId(UUID clientId);
	
	Match findByEligibleClientClientId(UUID clientId);
	
}
