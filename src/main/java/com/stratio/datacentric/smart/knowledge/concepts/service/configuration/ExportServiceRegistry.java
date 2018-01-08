package com.stratio.datacentric.smart.knowledge.concepts.service.configuration;

import java.io.Serializable;

public class ExportServiceRegistry implements Serializable {
	
	private int portIndex;
	private boolean isHttp;
	private boolean isHttps;
	
	public int getPortIndex() {
		return portIndex;
	}
	public void setPortIndex(int portIndex) {
		this.portIndex = portIndex;
	}
	public boolean isHttp() {
		return isHttp;
	}
	public void setHttp(boolean isHttp) {
		this.isHttp = isHttp;
	}
	public boolean isHttps() {
		return isHttps;
	}
	public void setHttps(boolean isHttps) {
		this.isHttps = isHttps;
	}

}
