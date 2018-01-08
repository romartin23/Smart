package com.stratio.datacentric.smart.knowledge.concepts.service;

import com.springjpa.drools.model.Concept;
import com.springjpa.drools.model.Element;
import com.stratio.cct.servicestatus.api.model.DeploymentMarathon;
import com.stratio.datacentric.smart.knowledge.concepts.service.configuration.ServiceConfiguration;


public class ServiceDeployment extends Element<ServiceDeployment> implements Concept{

	
	public static int STATE_PRE_CHECKING_DEPLOYMENT = 2;
	public static int STATE_CREATING_DEPLOYMENT = 3;
	public static int STATE_CHECKING_DEPLOYMENT = 4;
	public static int STATE_DEPLOYING = 5;
	public static int STATE_DEPLOYED = 6;
	public static int STATE_WITHERRORS = 7;


	private String deploymentID;
	private String service;
	private DeploymentMarathon deployment;
	private ServiceConfiguration serviceConfiguration;

	
	public String getDeploymentID() {
		return deploymentID;
	}

	public void setDeploymentID(String deploymentID) {
		this.deploymentID = deploymentID;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public ServiceConfiguration getServiceConfiguration() {
		return serviceConfiguration;
	}

	public void setServiceConfiguration(ServiceConfiguration serviceConfiguration) {
		this.serviceConfiguration = serviceConfiguration;
	}
	
	public DeploymentMarathon getDeployment() {
		return deployment;
	}

	public void setDeployment(DeploymentMarathon deployment) {
		this.deployment = deployment;
	}

	@Override
	public Integer getSemanticHash() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSmallName() {
		return "Deployment: "+ deployment.getId();
	}



}
