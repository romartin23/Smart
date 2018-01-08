package com.stratio.cct.servicestatus.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceStatusInstances implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("instance")
	private String instanceName;
	
	@JsonProperty("url")
	private String url;
	
	@JsonProperty("deploy")
	private Long deployTime;
	
	@JsonProperty("labels")
	private ArrayList<Map<String, String>> labels;
	
	@JsonProperty("version")
	private String version;
	
	@JsonProperty("isWeb")
	private Boolean isWeb;
	
	
}
