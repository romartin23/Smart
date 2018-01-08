package com.stratio.cct.servicestatus.api.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceStatusLauncherView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("name")
	private String serviceName;
	
	@JsonProperty("stratio")
	private Boolean isStratioApp;
	
	@JsonProperty("instances")
	private List<ServiceStatusInstances> serviceInstances;
	
}
