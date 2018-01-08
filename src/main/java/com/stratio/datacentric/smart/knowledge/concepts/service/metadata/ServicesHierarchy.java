package com.stratio.datacentric.smart.knowledge.concepts.service.metadata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stratio.datacentric.smart.knowledge.concepts.service.ServiceConcept;

public class ServicesHierarchy implements Serializable{
	
	private Map<String, ServicesHierarchy> servicesHierarchy;
	private Map<String, ServiceConcept> services;
	
	
	public ServicesHierarchy() {
		servicesHierarchy = new HashMap<String, ServicesHierarchy>();
		
		services = new HashMap<String, ServiceConcept>();
	}

	public Map<String, ServicesHierarchy> getServicesHierarchy() {
		return servicesHierarchy;
	}

	public void setServicesHierarchy(Map<String, ServicesHierarchy> services) {
		this.servicesHierarchy = services;
	}
	
	public Map<String, ServiceConcept> getServices() {
		return services;
	}
	
	
	
	public ServicesHierarchy getNext(String step) {
		return servicesHierarchy.get(step);
	}
	
	public void addHierachy(String step, ServicesHierarchy hierarchy) {
		servicesHierarchy.put(step, hierarchy);
	}
	
	public void addLeaf(String name, ServiceConcept service) {
		services.put(name, service);
	}
	
	public void add(ServiceConcept service) {
		List<String> path = Arrays.asList(service.getPath());
		ServicesHierarchy current = this;
		for (int cont=0;cont<path.size();cont++) {
			String step = path.get(cont);
			ServicesHierarchy next = current.getNext(step);
			if (next == null) {
				next = new ServicesHierarchy();
				current.addHierachy(step,next);
			}
			current=next;
		}
		current.addLeaf(service.getFinalName(), service);
	}
	
	public static ServicesHierarchy createHierarchy(List<ServiceConcept> services) {
		services.sort((a,b)->(a.getPath().length-b.getPath().length));
		
		ServicesHierarchy servicesHierarchy = new ServicesHierarchy();
		for (int cont=0;cont<services.size();cont++) {
			servicesHierarchy.add(services.get(cont));
    	}
		return servicesHierarchy;
		
	}
	

}
