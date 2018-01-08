package com.stratio.datacentric.smart.knowledge.concepts.service;

import java.io.Serializable;

import com.springjpa.drools.model.Element;

public class ServiceDeploymentParameter extends Element<ServiceDeploymentParameter> implements Serializable{
	
	private String key;
	private String value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String getSmallName() {
		return key;
	}
	
	

}
