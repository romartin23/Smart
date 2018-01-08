package com.stratio.cct.servicestatus.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LauncherInstallServiceModel {
	
	@JsonProperty("name")
	private String serviceName;
	
	@JsonProperty("version")
	private String version;
	
	@JsonProperty("nfsAddress")
	private String nfsAdress;
	
	@JsonProperty("subpath")
	private String instanceSubpath;
	
	@JsonProperty("virtualHost")
	private String virtualHost;
	
	@JsonProperty("module")
	private String moduleName;
	
	@JsonProperty("url")
	private String url;
	
}
