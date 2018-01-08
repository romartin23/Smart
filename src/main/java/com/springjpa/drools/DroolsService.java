package com.springjpa.drools;

import javax.transaction.Transactional;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.Environment;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.persistence.jpa.JPAKnowledgeService;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class DroolsService {
	
	
	
	public KieSession getDroolsSession(
			KieServices kieServices,
			KieContainer kieContainer,
			Environment env) {
		
		KieBase kBase = kieContainer.getKieBase("rules");
		try {
			StatefulKnowledgeSession ksession =
					 JPAKnowledgeService.loadStatefulKnowledgeSession(2, kBase, null, env);
			return ksession;
		}catch (Exception e) {
			int idSession = createDroolsSession(kieServices, kieContainer, env);
			/*StatefulKnowledgeSession ksession =
					 JPAKnowledgeService.loadStatefulKnowledgeSession(1, kBase, null, env);*/
			return null;
		}
		
	}
	
	public int createDroolsSession(
			KieServices kieServices,
			KieContainer kieContainer,
			Environment env) {
		
		KieBase kBase = kieContainer.getKieBase("rules");
		KieSession ksession =
		        kieServices.getStoreServices().newKieSession( kBase, null, env );
		
		int sessionId = ksession.getId();
		System.out.println(sessionId);
		return sessionId;
		
		
	}

}
