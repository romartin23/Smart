package com.springjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stratio.cct.servicestatus.api.services.sso.GosecAuthenticator;

@SpringBootApplication
public class TestMain implements CommandLineRunner{
	
	@Autowired
	GosecAuthenticator gosecAuthenticator;
	
	public static void main(String[] args) {
		
		SpringApplication.run(TestMain.class, args);
		System.out.println("aa");
	}

	
	@Override
	public void run(String... arg0) throws Exception {
		
		gosecAuthenticator.authenticate("bootstrap.crc.labs.stratio.com", "admin", "LOCAL", "1234");
		System.out.println(gosecAuthenticator.getDcosToken());
	}
}
