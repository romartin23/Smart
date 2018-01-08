package com.stratio.cct.servicestatus.api.services.sso;

public interface GosecAuthenticator {

	boolean authenticate(
	    		String dcosEntryPoint,
	    		String userName,
	    		String dcosAppId,
	    		String pass);

	String getDcosToken();
	
}
