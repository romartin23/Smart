package com.stratio.cct.servicestatus.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MesosTaskModel {

	String name;
		
	List<MesosStatusModel> statuses;
	
	@JsonProperty("labels")
	ArrayList<Map<String,String>> labels;
	
	@JsonProperty("container")
	MesosContainerInfo container;
	
}
