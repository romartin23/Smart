package com.stratio.datacentric.smart.knowledge.concepts.service.configuration;

import com.springjpa.drools.model.MappingItem;

public abstract class ServiceMappingItem extends MappingItem {

	
	private String globalParameterId;
	
	
	public String getId() {
		return getParentConceptId()+"_"+getKey();
	}

	public String getGlobalParameterId() {
		return globalParameterId;
	}

	public void setGlobalParameterId(String globalParameterId) {
		this.globalParameterId = globalParameterId;
	}

	@Override
	public Integer getSemanticHash() {
		return super.getSemanticHash()+getId().hashCode();
	}
	
}
