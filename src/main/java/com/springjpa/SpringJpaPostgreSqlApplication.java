package com.springjpa;

import java.util.Arrays;

import javax.persistence.EntityManagerFactory;

import org.drools.persistence.info.SessionInfo;
import org.kie.api.KieServices;
import org.kie.api.runtime.Environment;
import org.kie.api.runtime.EnvironmentName;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.springjpa.drools.DroolsService;
import com.springjpa.drools.DroolsSessionImpl;
import com.springjpa.model.Customer;
import com.springjpa.repo.CustomerRepository;
import com.springjpa.repo.SessionInfoRepository;

@SpringBootApplication (scanBasePackages = {"com.springjpa", "com.springjpa.drools", "org.drools.persistence","com.stratio"})
@EntityScan( basePackages = {"com.springjpa", "com.springjpa.drools", "org.drools.persistence","com.stratio"} )
public class SpringJpaPostgreSqlApplication implements CommandLineRunner{

	@Autowired
	CustomerRepository repository;
	
	@Autowired
	SessionInfoRepository repository2;
	
	@Autowired
	JtaTransactionManager transactionManager;
	
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Autowired
	DroolsService droolsService;
	
	@Autowired
	KieContainer kieContainer;

	
	public static void main(String[] args){
		SpringApplication.run(SpringJpaPostgreSqlApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// clear all record if existed before do the tutorial with new data 
		repository.deleteAll();
	}
	
	
	@Bean
	public KieContainer kieContainer() {
		return KieServices.Factory.get().getKieClasspathContainer();
	}
	
	@Bean
	public KieSession droolsSession() {
		
		repository2.save(new SessionInfo());
		KieServices kieServices = KieServices.Factory.get();
		Environment env = kieServices.newEnvironment();
		env.set( EnvironmentName.ENTITY_MANAGER_FACTORY,
				entityManagerFactory );
		env.set( EnvironmentName.TRANSACTION_MANAGER,
				transactionManager );
		KieSession kie = droolsService.getDroolsSession(kieServices, kieContainer, env);
		return kie;
	}
	
	
}
