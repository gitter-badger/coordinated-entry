package com.hserv.coordinatedentry.authorization;

import com.servinglynk.hmis.warehouse.client.exception.RestClientHttpException;
import com.servinglynk.hmis.warehouse.client.model.ApiMethodAuthorizationCheck;




public interface IAuthorizationClient {

	public ApiMethodAuthorizationCheck checkApiAuthorization(ApiMethodAuthorizationCheck authCheck) throws RestClientHttpException;
	
	
}
