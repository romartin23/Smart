package com.stratio.datacentric.smart.knowledge.concepts.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.springjpa.drools.model.Concept;
import com.springjpa.drools.model.Element;
import com.stratio.datacentric.smart.knowledge.concepts.service.configuration.ExportServiceRegistry;
import com.stratio.datacentric.smart.knowledge.concepts.service.configuration.ServiceConfiguration;
import com.stratio.datacentric.smart.knowledge.concepts.service.metadata.Container;
import com.stratio.datacentric.smart.knowledge.concepts.service.metadata.HealthCheck;
import com.stratio.datacentric.smart.knowledge.concepts.service.metadata.ServicePort;
import com.stratio.datacentric.smart.knowledge.concepts.service.metadata.Task;



public class ServiceConcept extends Element<ServiceConcept> implements Concept{
	
	private String finalName;
	private String[] path;
	private Container container;
	private String id;
	private String cmd;
	private Integer instances;
	private Double cpus;
	private Double mem;
	private Collection<String> uris;
	private List<List<String>> constraints;
	private Map<String, String> env;
	private Map<String, String> labels;
	private String executor;
	private List<ServicePort> servicePorts;
	private Collection<Task> tasks;
	private Integer tasksStaged;
	private Integer tasksRunning;
	private List<HealthCheck> healthChecks;
	
	
	

	
	public Integer getSemanticHash() {
		int hash = Concept.toHash(finalName) + Concept.toHash(path);
		if (container != null) {
			hash += container.getSemanticHash();
		}
		return hash;
	}
	
	public String getServiceConfigurationId() {
		if (labels.containsKey("SERVICE_CONFIGURATION_ID")) {
			return labels.get("SERVICE_CONFIGURATION_ID");
		}
		return id;
	}
	
	public List<ExportServiceRegistry> calculeExportServiceRegistries () {
		List<ExportServiceRegistry> lExportServiceRegistries =
			new ArrayList<ExportServiceRegistry>();
		
		Iterator<String> iterator =
			labels.keySet().iterator();
		
		Map<String, ExportServiceRegistry> mExportServiceRegistry = 
				new HashMap<String, ExportServiceRegistry>();
		
		while (iterator.hasNext()) {
			String label = iterator.next();
			
			
			if (label.contains("HAPROXY_")) {
				String labelWithOutPrefix = label.substring("HAPROXY_".length(), label.length());
				if (labelWithOutPrefix.indexOf("_") != -1) {
					String sPort = labelWithOutPrefix.substring(0,labelWithOutPrefix.indexOf("_"));
					ExportServiceRegistry exportServiceRegistry = mExportServiceRegistry.get(sPort);
					if (exportServiceRegistry == null) {
						exportServiceRegistry = new ExportServiceRegistry();
						mExportServiceRegistry.put(sPort, exportServiceRegistry);
					}
					exportServiceRegistry.setPortIndex(Integer.valueOf(sPort));
					String sPortProperty = labelWithOutPrefix.substring(labelWithOutPrefix.indexOf("_")+1,labelWithOutPrefix.length()); 
					if (sPortProperty.equals("REDIRECT_TO_HTTPS")) {
						exportServiceRegistry.setHttps(true);
					} 
				}
			}
			
			
		}
		lExportServiceRegistries.addAll(mExportServiceRegistry.values());
		return lExportServiceRegistries;
	}
	
	public List<ServicePort> getServicePorts() {
		return servicePorts;
	}


	public void setServicePorts(List<ServicePort> servicePorts) {
		this.servicePorts = servicePorts;
	}

	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getCmd() {
		return cmd;
	}



	public void setCmd(String cmd) {
		this.cmd = cmd;
	}



	public Integer getInstances() {
		return instances;
	}



	public void setInstances(Integer instances) {
		this.instances = instances;
	}



	public Double getCpus() {
		return cpus;
	}



	public void setCpus(Double cpus) {
		this.cpus = cpus;
	}



	public Double getMem() {
		return mem;
	}



	public void setMem(Double mem) {
		this.mem = mem;
	}



	public Collection<String> getUris() {
		return uris;
	}



	public void setUris(Collection<String> uris) {
		this.uris = uris;
	}



	public List<List<String>> getConstraints() {
		return constraints;
	}



	public void setConstraints(List<List<String>> constraints) {
		this.constraints = constraints;
	}



	public Map<String, String> getEnv() {
		return env;
	}



	public void setEnv(Map<String, String> env) {
		this.env = env;
	}



	public String getExecutor() {
		return executor;
	}



	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public Collection<Task> getTasks() {
		return tasks;
	}



	public void setTasks(Collection<Task> tasks) {
		this.tasks = tasks;
	}



	public Integer getTasksStaged() {
		return tasksStaged;
	}



	public void setTasksStaged(Integer tasksStaged) {
		this.tasksStaged = tasksStaged;
	}



	public Integer getTasksRunning() {
		return tasksRunning;
	}



	public void setTasksRunning(Integer tasksRunning) {
		this.tasksRunning = tasksRunning;
	}



	public List<HealthCheck> getHealthChecks() {
		return healthChecks;
	}



	public void setHealthChecks(List<HealthCheck> healthChecks) {
		this.healthChecks = healthChecks;
	}



	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	public String getFinalName() {
		return finalName;
	}

	public void setFinalName(String finalName) {
		this.finalName = finalName;
	}

	public String[] getPath() {
		return path;
	}

	public void setPath(String[] path) {
		this.path = path;
	}
	
	public Map<String, String> getLabels() {
		return labels;
	}


	public void setLabels(Map<String, String> labels) {
		this.labels = labels;
	}

	@Override
	public String getSmallName() {
		return finalName;
	}

}
