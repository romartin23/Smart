package com.springjpa.services;

import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.stratio.datacentric.smart.knowledge.concepts.service.ServiceConcept;

@org.springframework.stereotype.Service
public class MarathonKnowledgeServiceImpl implements MarathonKnowledgeService{
	
	@Autowired
	KieSession kieSession;
	
	@Transactional
	public void mergeServices(List<ServiceConcept> services) {
		services.forEach((a)->kieSession.insert(a));
		kieSession.fireAllRules();
	}

}
