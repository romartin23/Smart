package com.stratio.cct.servicestatus.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MesosContainerInfo {

	@JsonProperty("docker")
	MesosDockerInfo docker;
}
