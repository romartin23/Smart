package com.stratio.cct.servicestatus.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MesosFrameworkModel {

	@JsonProperty("name")
    String name; //elasticsearchstratio",
    
	@JsonProperty("tasks")
	List<MesosTaskModel> tasks;
	
	@JsonProperty("webui_url")
	String webui_url;
	
}
