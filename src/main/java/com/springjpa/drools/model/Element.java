package com.springjpa.drools.model;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.io.Serializable;
import java.net.URLEncoder;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.stratio.datacentric.smart.knowledge.concepts.service.configuration.ServiceConfiguration;
import com.stratio.datacentric.smart.knowledge.concepts.service.configuration.ServiceConfigurationParameter;

@JsonTypeInfo(use = Id.CLASS,
	include = JsonTypeInfo.As.PROPERTY,
	property = "type")
@JsonSubTypes({
	@Type(value = ServiceConfiguration.class),
	@Type(value = ServiceConfigurationParameter.class),
})
public abstract class Element <T extends Element> implements Serializable{

	public final static int INITIAL_STATE = 1;
	public final static int FINAL_STATE = 1000;
	
	private String id;
	private int state=INITIAL_STATE;
	private String parentConceptId;
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getGlobalIdentifier()  {
		String global = getType()+"----"+getId();
		try {
			return URLEncoder.encode(global, "UTF-8");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "|@|"+getId();
	}
	
	
	public String getType() {
		return this.getClass().getTypeName();
	}
	
	public abstract String getSmallName();
	
	public String getParentConceptId() {
		return parentConceptId;
	}
	public void setParentConceptId(String parentConceptId) {
		this.parentConceptId = parentConceptId;
	}
	
	public String calculeConceptName() {
		return getType();
	}
	
	
}
