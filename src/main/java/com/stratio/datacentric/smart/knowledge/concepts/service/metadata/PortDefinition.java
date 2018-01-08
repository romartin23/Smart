package com.stratio.datacentric.smart.knowledge.concepts.service.metadata;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mesosphere.marathon.client.utils.ModelUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PortDefinition implements Serializable{
	private Integer port;
	private String protocol;
	
	public PortDefinition() {}


	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	

	public Integer getPort() {
		return port;
	}


	public void setPort(Integer port) {
		this.port = port;
	}


	@Override
	public String toString() {
		return ModelUtils.toString(this);
	}
}