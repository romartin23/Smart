package com.stratio.datacentric.smart.knowledge.concepts.service;

import com.springjpa.drools.model.Concept;
import com.stratio.datacentric.smart.knowledge.concepts.service.configuration.ServiceMappingItem;

public class ServiceParameter extends ServiceMappingItem implements Concept{
	
	private ServiceConcept serviceConcept;
	
	@Override
	public String getType() {
		return this.getClass().toString();
	}

	public ServiceConcept getServiceConcept() {
		return serviceConcept;
	}

	public void setServiceConcept(ServiceConcept serviceConcept) {
		this.serviceConcept = serviceConcept;
	}
	
	

}
