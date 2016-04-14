package com.hserv.coordinatedentry.housinginventory.repository;

import com.hserv.coordinatedentry.housinginventory.domain.EligibleClient;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the EligibleClient entity.
 */
public interface EligibleClientRepository extends JpaRepository<EligibleClient,Long> {

}
