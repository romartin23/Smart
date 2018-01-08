package com.stratio.datacentric.smart.knowledge.concepts.service.metadata;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mesosphere.marathon.client.utils.ModelUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Port implements Serializable{
	private Integer containerPort;
	private Integer hostPort;
	private Integer servicePort;
	private String protocol;
	
	public Port() {}

	public Port(Integer containerPort) {
		this.containerPort = containerPort;
	}
	
	public Integer getContainerPort() {
		return containerPort;
	}

	public void setContainerPort(Integer containerPort) {
		this.containerPort = containerPort;
	}

	public Integer getHostPort() {
		return hostPort;
	}

	public void setHostPort(Integer hostPort) {
		this.hostPort = hostPort;
	}

	public Integer getServicePort() {
		return servicePort;
	}

	public void setServicePort(Integer servicePort) {
		this.servicePort = servicePort;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	@Override
	public String toString() {
		return ModelUtils.toString(this);
	}
}