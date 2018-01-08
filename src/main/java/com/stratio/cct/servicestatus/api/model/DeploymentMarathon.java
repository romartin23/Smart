/*
 * © 2017 Stratio Big Data Inc., Sucursal en España. All rights reserved.
 *
 * This software – including all its source code – contains proprietary
 * information of Stratio Big Data Inc., Sucursal en España and
 * may not be revealed, sold, transferred, modified, distributed or
 * otherwise made available, licensed or sublicensed to third parties;
 * nor reverse engineered, disassembled or decompiled, without express
 * written authorization from Stratio Big Data Inc., Sucursal en España.
 */
package com.stratio.cct.servicestatus.api.model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stratio.datacentric.smart.knowledge.concepts.service.metadata.HealthCheck;
import com.stratio.datacentric.smart.knowledge.concepts.service.metadata.PortDefinition;
import com.stratio.datacentric.smart.knowledge.concepts.service.metadata.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import mesosphere.marathon.client.model.v2.Container;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Data
public class DeploymentMarathon implements Serializable {

	
	
	private String id;
	private String cmd;
	
	private Integer instances;
	private Double cpus;
	private Double mem;
	private Double disk;
	private Double gpu;
	
	private Collection<String> uris;
	private List<List<String>> constraints;
	private Container container;

	private Map<String, Object> env;
	private Map<String,String> labels;

	private String executor;
	
	
	private List<HealthCheck> healthChecks;
	private List<PortDefinition> portDefinitions;
	
	private boolean requirePorts;
	
	
	
	public String toJson() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("deployment.json"), this);
		return "";
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
	public Map<String, Object> getEnv() {
		return env;
	}
	public void setEnv(Map<String, Object> env) {
		this.env = env;
	}
	public String getExecutor() {
		return executor;
	}
	public void setExecutor(String executor) {
		this.executor = executor;
	}
	public List<HealthCheck> getHealthChecks() {
		return healthChecks;
	}
	public void setHealthChecks(List<HealthCheck> healthChecks) {
		this.healthChecks = healthChecks;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Map<String, String> getLabels() {
		return labels;
	}
	public void setLabels(Map<String, String> labels) {
		this.labels = labels;
	}
	public Container getContainer() {
		return container;
	}
	public void setContainer(Container container) {
		this.container = container;
	}
	public Double getDisk() {
		return disk;
	}
	public void setDisk(Double disk) {
		this.disk = disk;
	}
	public Double getGpu() {
		return gpu;
	}
	public void setGpu(Double gpu) {
		this.gpu = gpu;
	}
	public List<PortDefinition> getPortDefinitions() {
		return portDefinitions;
	}
	public void setPortDefinitions(List<PortDefinition> portDefinitions) {
		this.portDefinitions = portDefinitions;
	}
	public boolean isRequirePorts() {
		return requirePorts;
	}
	public void setRequirePorts(boolean requirePorts) {
		this.requirePorts = requirePorts;
	}
	
	
}
