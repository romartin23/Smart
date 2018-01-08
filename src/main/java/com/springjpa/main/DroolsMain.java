package com.springjpa.main;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stratio.datacentric.smart.knowledge.concepts.service.ServiceDeployment;

public class DroolsMain implements CommandLineRunner{
	
	@Autowired
	KieSession kieSession;
	
	public static void main(String[] args) {
		
		SpringApplication.run(DroolsMain.class, args);
		System.out.println("aa");
	}

	
	@Override
	public void run(String... arg0) throws Exception {
		
		ServiceDeployment deployment = new ServiceDeployment();
		deployment.setService("discovery3");
		
		
		kieSession.insert(deployment);
		kieSession.fireAllRules();
		
	}
}
