package com.stratio.datacentric.smart.knowledge.concepts.service;

import java.io.Serializable;

import com.springjpa.drools.model.Element;

public class ServiceDeploymentEndpoint extends Element<ServiceDeploymentEndpoint> implements Serializable{
	
	private int portIndex;
	private String hostName;
	private String path;
	private String port;
	
	public int getPortIndex() {
		return portIndex;
	}
	public void setPortIndex(int portIndex) {
		this.portIndex = portIndex;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	@Override
	public String getSmallName() {
		return String.valueOf(portIndex);
	}
	
	
	

	

}
