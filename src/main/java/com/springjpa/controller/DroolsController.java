package com.springjpa.controller;


import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springjpa.drools.DroolsSession;
import com.springjpa.drools.model.ErrorConcept;
import com.springjpa.services.MarathonKnowledgeService;
import com.stratio.cct.servicestatus.api.services.marathon.MarathonService;
import com.stratio.cct.servicestatus.api.services.sso.GosecAuthenticator;
import com.stratio.datacentric.smart.knowledge.concepts.service.ServiceDeployment;
import com.stratio.datacentric.smart.knowledge.concepts.service.ServiceDeploymentEndpoint;
import com.stratio.datacentric.smart.knowledge.concepts.service.ServiceDeploymentParameter;

@RestController
public class DroolsController {

    @Autowired
	GosecAuthenticator gosecAuthenticator;
	
	@Autowired
	MarathonService marathonService;
	
	@Autowired
	MarathonKnowledgeService marathonKnowledgeService;
	
	@Autowired
	KieSession kieSession;
	
	@Autowired
	DroolsSession droolSession;
	

    @RequestMapping(value = "/drools1", method = RequestMethod.GET, produces = "application/json")
    public List<ErrorConcept> drools1() {
    	ServiceDeployment deployment = new ServiceDeployment();
    	
    	String id = "discovery_"+System.currentTimeMillis();
    	deployment.setId(id);
    	deployment.setDeploymentID("discovery3");
		deployment.setService("/discovery3");
		deployment.setState(ServiceDeployment.INITIAL_STATE);
		
		ServiceDeploymentParameter serviceDeploymentParameter =
			new ServiceDeploymentParameter();
		serviceDeploymentParameter.setKey("TENANT_NAME");
		serviceDeploymentParameter.setValue("crossdata-1");
		serviceDeploymentParameter.setParentConceptId(deployment.getGlobalIdentifier());
		
		ServiceDeploymentEndpoint serviceDeploymentEndpoint =
			new ServiceDeploymentEndpoint();
		serviceDeploymentEndpoint.setHostName("carrefour.stratio.com");
		serviceDeploymentEndpoint.setPath("/metabase2");
		serviceDeploymentEndpoint.setPortIndex(1);
		serviceDeploymentEndpoint.setParentConceptId(deployment.getGlobalIdentifier());
		
		kieSession.insert(deployment);
		kieSession.insert(serviceDeploymentParameter);
		kieSession.insert(serviceDeploymentEndpoint);

		kieSession.fireAllRules();
		List<Object> elements =
				droolSession.query(kieSession, "errosByGlobalIdentifier", id);
		List<ErrorConcept> errors = (List<ErrorConcept>) (Object) elements;
		return errors;
    }
    
   
    
}