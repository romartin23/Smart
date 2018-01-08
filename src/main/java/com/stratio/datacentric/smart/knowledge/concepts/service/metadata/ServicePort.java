package com.stratio.datacentric.smart.knowledge.concepts.service.metadata;

import java.io.Serializable;

public class ServicePort implements Serializable{
	
	private Integer index;
	private String protocol;
	private String containerPort;
	
	
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getContainerPort() {
		return containerPort;
	}
	public void setContainerPort(String containerPort) {
		this.containerPort = containerPort;
	}
	
	
	

}
