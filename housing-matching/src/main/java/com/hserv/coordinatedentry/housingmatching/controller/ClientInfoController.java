package com.hserv.coordinatedentry.housingmatching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hserv.coordinatedentry.housingmatching.dao.ClientInfoRepository;
import com.hserv.coordinatedentry.housingmatching.entity.ClientInfo;

@RestController
public class ClientInfoController {
    
	@Autowired
	ClientInfoRepository clientInfoRepository;
	
    @RequestMapping("/clients")
    @Transactional(readOnly = true)
    public String getAllClients() {
    	System.out.println("Total clients = " +clientInfoRepository.count());
    	//System.out.println("Total clients = " +
    	//((ClientInfo) clientInfoRepository.findOne("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")));
    	
    	
    	Iterable<ClientInfo> clientInfos =  clientInfoRepository.findAll();
    	for(ClientInfo clientInfo : clientInfos){
    		System.out.println(clientInfo.toString());
    	}
    	
    	
    	RestTemplate clientInfoService = new RestTemplate();
    	String url = "http://localhost:8181/pathToClient";
    	//used entity object for now, haven't tested though, but will remove and make it some DTO
    	//entity objects shoudn't be used. Just a sample how to make call to survey/inventory services.
    	//url will be their anypoint mock services.
    	//ClientInfo clientInfo = clientInfoService.getForObject(url, ClientInfo.class,"client_id");
        return "Greetings from Spring Boot!";
    }
    
}
