package com.stratio.cct.servicestatus.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MesosFrameworksStatusModel {

	List<MesosFrameworkModel> frameworks;
	
}
