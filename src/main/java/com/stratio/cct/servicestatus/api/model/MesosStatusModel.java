package com.stratio.cct.servicestatus.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MesosStatusModel {

	String state;
	Long timestamp;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = Math.round(Double.valueOf(timestamp));
	}
	
}
