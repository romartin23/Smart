package com.stratio.datacentric.smart.knowledge.concepts.service.metadata;

import java.io.Serializable;

import com.springjpa.drools.model.Concept;

import mesosphere.marathon.client.utils.ModelUtils;

public class Volume implements Serializable, Concept{
	
	private String containerPath;
	private String hostPath;
	private String mode;
	
	 public Integer getSemanticHash() {
		 return 
				 Concept.toHash(containerPath)+
				 Concept.toHash(hostPath)+
				 Concept.toHash(mode);
	 }
	
	public static Volume fromMarathonVolume(mesosphere.marathon.client.model.v2.Volume marathonVolume) {
		
		Volume volume = new Volume();
		volume.setContainerPath(marathonVolume.getContainerPath());
		volume.setHostPath(marathonVolume.getHostPath());
		volume.setMode(marathonVolume.getMode());
		
		return volume;
	}

	public String getContainerPath() {
		return containerPath;
	}

	public void setContainerPath(String containerPath) {
		this.containerPath = containerPath;
	}

	public String getHostPath() {
		return hostPath;
	}

	public void setHostPath(String hostPath) {
		this.hostPath = hostPath;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	


	@Override
	public String toString() {
		return ModelUtils.toString(this);
	}
}
