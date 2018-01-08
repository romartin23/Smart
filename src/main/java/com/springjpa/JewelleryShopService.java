package com.springjpa;

import javax.persistence.EntityManagerFactory;
import javax.transaction.TransactionManager;

import org.jbpm.persistence.api.ProcessPersistenceContext;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.Environment;
import org.kie.api.runtime.EnvironmentName;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.persistence.jpa.JPAKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javainuse.model.Product;
import com.springjpa.drools.DroolsService;

@Service
public class JewelleryShopService {

	private final TransactionManager transactionManager;
	private final EntityManagerFactory entityManagerFactory;
	
	private KieSession ksession;
	
	


	@Autowired
	public JewelleryShopService(
			KieContainer kieContainer,
			DroolsService droolsService,
			TransactionManager transactionManagerm,
			EntityManagerFactory entityManagerFactorym) throws Exception {
		
		this.transactionManager = transactionManagerm;
		this.entityManagerFactory = entityManagerFactorym;
		KieServices kieServices = KieServices.Factory.get();
		Environment env = kieServices.newEnvironment();
		env.set( EnvironmentName.ENTITY_MANAGER_FACTORY,
				entityManagerFactorym );
		env.set( EnvironmentName.TRANSACTION_MANAGER,
				transactionManagerm );
		//droolsService.getProductDiscount(kieServices, kieContainer, env);
		// KieSessionConfiguration may be null, and a default will be used
		
		
	}
	
	public Product getProductDiscount(Product product) {
		return null;
	}
	
	
	public void main(String[] args) {
		
	}
	
	 
	

	
}