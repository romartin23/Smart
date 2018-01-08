package com.springjpa.services;

import java.util.List;

import com.stratio.datacentric.smart.knowledge.concepts.service.ServiceConcept;

public interface MarathonKnowledgeService {
	
	public void mergeServices(List<ServiceConcept> services);

}
