package com.stratio.cct.servicestatus.api.services.marathon;

import com.stratio.cct.servicestatus.api.exceptions.MarathonNotAvariableException;

public interface MarathonService {

	String getApps(String dcosSecurityToken, String dcosEntrypoint) throws MarathonNotAvariableException;

}
