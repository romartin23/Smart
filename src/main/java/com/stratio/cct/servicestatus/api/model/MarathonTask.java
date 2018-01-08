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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stratio.datacentric.smart.knowledge.concepts.service.metadata.HealthCheck;
import com.stratio.datacentric.smart.knowledge.concepts.service.metadata.PortDefinition;
import com.stratio.datacentric.smart.knowledge.concepts.service.metadata.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import mesosphere.marathon.client.model.v2.Container;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Data
public class MarathonTask {

	
	private Container container;
	
	private String id;
	private String cmd;
	private Integer instances;
	private Double cpus;
	private Double mem;
	private Collection<String> uris;
	private List<List<String>> constraints;
	private Map<String, Object> env;
	private HashMap<String,String> labels;

	private String executor;
	private List<PortDefinition> portDefinitions;
	private List<HealthCheck> healthChecks;
	
	
	
	
	public List<PortDefinition> getPortDefinitions() {
		return portDefinitions;
	}
	public void setPortDefinitions(List<PortDefinition> portDefinitions) {
		this.portDefinitions = portDefinitions;
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
	public HashMap<String, String> getLabels() {
		return labels;
	}
	public void setLabels(HashMap<String, String> labels) {
		this.labels = labels;
	}
	public Container getContainer() {
		return container;
	}
	public void setContainer(Container container) {
		this.container = container;
	}
	
	
	
	
	
	
}
