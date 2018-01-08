package com.stratio.datacentric.smart.knowledge.concepts.service.metadata;

import java.io.Serializable;
import java.util.List;

import com.springjpa.drools.model.Concept;

import mesosphere.marathon.client.utils.ModelUtils;

public class Docker implements Serializable, Concept{
	
	
	private String image;
	private String network;
	private boolean forcePullImage;
	private List<Port> portMappings;
	private List<Parameter> parameters;
	private boolean privileged;
	
	public Integer getSemanticHash() {
		 return 
				 Concept.toHash(image)+
				 Concept.toHash(network);
	 }
	
	
    public static Docker fromMarathonDocker(mesosphere.marathon.client.model.v2.Docker marathonDocker) {
		Docker docker = new Docker();
		docker.setImage(marathonDocker.getImage());
		docker.setNetwork(marathonDocker.getNetwork());
    	return docker;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public List<Port> getPortMappings() {
		return portMappings;
	}

	public void setPortMappings(List<Port> portMappings) {
		this.portMappings = portMappings;
	}

	public boolean isPrivileged() {
		return privileged;
	}

	public void setPrivileged(boolean privileged) {
		this.privileged = privileged;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public boolean isForcePullImage() {
		return forcePullImage;
	}

	public void setForcePullImage(boolean forcePullImage) {
		this.forcePullImage = forcePullImage;
	}
	@Override
	public String toString() {
		return ModelUtils.toString(this);
	}

}
