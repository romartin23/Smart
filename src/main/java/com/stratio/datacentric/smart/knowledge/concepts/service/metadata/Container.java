package com.stratio.datacentric.smart.knowledge.concepts.service.metadata;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

import com.springjpa.drools.model.Concept;

import mesosphere.marathon.client.utils.ModelUtils;

public class Container implements Serializable, Concept{
	
	
	private String type;
	private Docker docker;
	private Collection<Volume> volumes;
	
    public Integer getSemanticHash() {
		
    	int hash = Concept.toHash(type);
    	
    	Iterator<Volume> volumesI =
    			volumes.iterator();
    	while (volumesI.hasNext()) {
    		hash+=volumesI.next().getSemanticHash();
    	}
    	
    	hash += docker.getSemanticHash();
    	
    	return hash;
	}
    
    public static Container fromMarathonContainer(mesosphere.marathon.client.model.v2.Container marathonContainer) {
		
		Container container = new Container();
		if (marathonContainer.getDocker() != null) {
			container.setDocker(Docker.fromMarathonDocker(marathonContainer.getDocker()));
		}
		container.setType(marathonContainer.getType());
		Collection<Volume> volumesOld =
			marathonContainer.getVolumes().stream().map((a)->{
				return Volume.fromMarathonVolume(a);
			}).collect(Collectors.toList());
		
		container.setVolumes(volumesOld);
		return container;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Docker getDocker() {
		return docker;
	}

	public void setDocker(Docker docker) {
		this.docker = docker;
	}

	public Collection<Volume> getVolumes() {
		return volumes;
	}

	public void setVolumes(Collection<Volume> volumes) {
		this.volumes = volumes;
	}
	
	

	@Override
	public String toString() {
		return ModelUtils.toString(this);
	}
}
