package com.springjpa.controller;

import java.util.Arrays;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.drools.persistence.info.SessionInfo;
import org.kie.api.KieServices;
import org.kie.api.runtime.Environment;
import org.kie.api.runtime.EnvironmentName;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springjpa.drools.DroolsService;
import com.springjpa.model.Customer;
import com.springjpa.repo.Customer2;
import com.springjpa.repo.CustomerRepository;
import com.springjpa.repo.SessionInfoRepository;

@RestController
public class WebController {
	
	
	@Autowired
	CustomerRepository repository;
	
	@Autowired
	SessionInfoRepository repository2;
	
	@Autowired
	DroolsService droolsService;
	@Autowired
	JtaTransactionManager transactionManager;
	
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Autowired
	KieContainer kieContainer;
	
	
	
	@RequestMapping("/save")
	@Transactional
	public String process(){
		// save a single Customer
		repository.save(new Customer("Jack", "Smith"));
		
		
		repository2.save(new SessionInfo());
		
		
		
		// save a list of Customers
		repository.save(Arrays.asList(new Customer("Adam", "Johnson"), new Customer("Kim", "Smith"),
										new Customer("David", "Williams"), new Customer("Peter", "Davis")));
		
		KieServices kieServices = KieServices.Factory.get();
		Environment env = kieServices.newEnvironment();
		env.set( EnvironmentName.ENTITY_MANAGER_FACTORY,
				entityManagerFactory );
		env.set( EnvironmentName.TRANSACTION_MANAGER,
				transactionManager );
		//droolsService.getProductDiscount(kieServices, kieContainer, env);
		
		return "Done";
	}
	
	
	@RequestMapping("/findall")
	public String findAll(){
		String result = "";
		
		for(Customer cust : repository.findAll()){
			result += cust.toString() + "<br>";
		}
		
		return result;
	}
	
	@RequestMapping("/findbyid")
	public String findById(@RequestParam("id") long id){
		String result = "";
		result = repository.findOne(id).toString();
		return result;
	}
	
	@RequestMapping("/findbylastname")
	public String fetchDataByLastName(@RequestParam("lastname") String lastName){
		String result = "";
		
		for(Customer cust: repository.findByLastName(lastName)){
			result += cust.toString() + "<br>"; 
		}
		
		return result;
	}
}

