package com.hserv.coordinatedentry.housingmatching.util;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {
	
	private static RestTemplate restTemplate;
	
    static {
        restTemplate = new RestTemplate();
    }
    
	public Object get(String url , Class responseType) {
		//RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, responseType);
	}
}
