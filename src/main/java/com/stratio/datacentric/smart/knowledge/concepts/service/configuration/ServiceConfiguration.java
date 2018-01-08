package com.stratio.datacentric.smart.knowledge.concepts.service.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.springjpa.drools.model.Element;
import com.stratio.cct.servicestatus.api.model.DeploymentMarathon;
import com.stratio.datacentric.smart.knowledge.concepts.service.metadata.HealthCheck;
import com.stratio.datacentric.smart.knowledge.concepts.service.metadata.PortDefinition;
import com.stratio.datacentric.smart.knowledge.concepts.service.metadata.ServicePort;


public class ServiceConfiguration extends Element<ServiceConfiguration>{
	
	public static Integer LEARNING = 1;
	public static Integer LEARNED = 2;


	private String service;
	private String description;
	private String cmd;
	
	private boolean isFramework;
	
	private String containerImage;
	private String containerDeploymentMode;
	
	private List<ServicePort> servicePorts;
	private List<HealthCheck> healthChecks;
	
	private List<ExportServiceRegistry> exportServiceRegistries;
	
	
	public DeploymentMarathon toDeployment() {
		DeploymentMarathon deployment =
			new DeploymentMarathon();
		
		deployment.setCmd(cmd);
		deployment.setHealthChecks(healthChecks);
		 
		
		Map<String, Object> env = new HashMap<String, Object>();
		Map<String, String> labels = new HashMap<String, String>();
		
		deployment.setEnv(env);
		deployment.setLabels(labels);
		
		
		return deployment;
	}
	
	public boolean isFramework() {
		return isFramework;
	}

	public void setFramework(boolean isFramework) {
		this.isFramework = isFramework;
	}
	
	

	public List<ServicePort> getServicePorts() {
		return servicePorts;
	}

	public void setServicePorts(List<ServicePort> servicePorts) {
		this.servicePorts = servicePorts;
	}

	public String getContainerImage() {
		return containerImage;
	}

	public void setContainerImage(String containerImage) {
		this.containerImage = containerImage;
	}

	public String getContainerDeploymentMode() {
		return containerDeploymentMode;
	}

	public void setContainerDeploymentMode(String containerDeploymentMode) {
		this.containerDeploymentMode = containerDeploymentMode;
	}


	public List<HealthCheck> getHealthChecks() {
		return healthChecks;
	}

	public void setHealthChecks(List<HealthCheck> healthChecks) {
		this.healthChecks = healthChecks;
	}

	public List<ExportServiceRegistry> getExportServiceRegistries() {
		return exportServiceRegistries;
	}

	public void setExportServiceRegistries(List<ExportServiceRegistry> exportServiceRegistries) {
		this.exportServiceRegistries = exportServiceRegistries;
	}

	public String getService() {
		return service;
	}
	
	public void setService(String service) {
		this.service = service;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	@Override
	public String getSmallName() {
		return service;
	}
	
	

}
