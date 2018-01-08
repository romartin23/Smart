package com.springjpa.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springjpa.drools.DroolsSession;
import com.springjpa.drools.model.Element;
import com.springjpa.services.MarathonKnowledgeService;
import com.springjpa.tree.model.Leaf;
import com.springjpa.tree.model.Node;
import com.springjpa.tree.model.Parent;
import com.stratio.cct.servicestatus.api.model.MarathonAppsModel;
import com.stratio.cct.servicestatus.api.model.MarathonTask;
import com.stratio.cct.servicestatus.api.services.marathon.MarathonService;
import com.stratio.cct.servicestatus.api.services.sso.GosecAuthenticator;
import com.stratio.datacentric.smart.knowledge.concepts.service.ServiceConcept;
import com.stratio.datacentric.smart.knowledge.concepts.service.configuration.ServiceConfiguration;
import com.stratio.datacentric.smart.knowledge.concepts.service.configuration.ServiceConfigurationParameter;
import com.stratio.datacentric.smart.knowledge.concepts.service.metadata.Container;
import com.stratio.datacentric.smart.knowledge.concepts.service.metadata.PortDefinition;
import com.stratio.datacentric.smart.knowledge.concepts.service.metadata.ServicePort;
import com.stratio.datacentric.smart.knowledge.concepts.service.metadata.ServicesHierarchy;

@RestController
public class ConceptsController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
	GosecAuthenticator gosecAuthenticator;
	
	@Autowired
	MarathonService marathonService;
	
	@Autowired
	MarathonKnowledgeService marathonKnowledgeService;
	
	@Autowired
	KieSession kieSession;
	
	@Autowired
	DroolsSession droolSession;
	

    @RequestMapping("/concepts")
    public List<Node> concepts(@RequestParam(value="name", defaultValue="World") String name) {
    	List<Node> nodes = new ArrayList<Node>();
    	try {
			
			List<Element> elements = (List<Element>) (Object) droolSession.query(kieSession, "allElements");
			
			Map<String, List<Element>> maps = new HashMap<String, List<Element>>();
			for (int cont=0;cont<elements.size();cont++) {
				Element element = elements.get(cont);
				String conceptName = element.calculeConceptName();
				List<Element> list = maps.get(conceptName);
				if (list == null) {
					list = new ArrayList<Element>();
					maps.put(conceptName, list);
				}
				list.add(element);
			}
			
			Iterator<String> iterator = maps.keySet().iterator();
			while (iterator.hasNext()) {
				String conceptName = iterator.next();
				System.out.println(conceptName+ "=>"+maps.get(conceptName).size());
			}
			
    	} catch (Exception e) {
    		e.printStackTrace();
		}
    	
    	return nodes;	

    }
    
   
    private Node toTree(
    		List<ServiceConfiguration> serviceConfigurations, 
    		List<ServiceConcept> services, 
    		List<ServiceConfigurationParameter> serviceParameterConfigurations) {
    	
    	Parent nodeConfigurations = new Parent();
    	nodeConfigurations.setTitle("Configurations");
    	nodeConfigurations.setFolder("true");
    	nodeConfigurations.setKey("Configurations");
    	
    	
    	Parent nodeConfigurationsLearning = new Parent();
    	nodeConfigurationsLearning.setTitle("Configurations - Learning");
    	nodeConfigurationsLearning.setFolder("true");
    	nodeConfigurationsLearning.setKey("Configurations - Learning");
    	List<Node> nodesLearning = new ArrayList<Node>();
    	nodeConfigurationsLearning.setChildren(nodesLearning);
    	
    	List<Node> nodesConfigurations = new ArrayList<Node>();
    	nodesConfigurations.add(nodeConfigurationsLearning);
    	
    	nodeConfigurations.setChildren(nodesConfigurations);
    	
    	
    	for (int cont=0;cont<serviceConfigurations.size();cont++) {
    		ServiceConfiguration serviceConfiguration = serviceConfigurations.get(cont);
    		Stream<ServiceConcept> serviceFiltered = services.stream().filter( (service) -> {
    			return (service.getServiceConfigurationId() == serviceConfiguration.getId());
    		});
    		Parent node = new Parent();
    		
    		
    		List<Node> children = new ArrayList<Node>();
    		children.addAll(
    			serviceFiltered.map( (service) -> toNode(service)).collect(Collectors.toList()));
    		
    		children.add(
				toTree(
						serviceConfiguration, 
						serviceParameterConfigurations.stream().filter(
							(serviceParameterConfiguration) -> {
				    			return (serviceParameterConfiguration.getParentConceptId().equals(serviceConfiguration.getGlobalIdentifier()));
				    		}).collect(Collectors.toList())
						)
				);
    		
    		node.setChildren(children);
        	node.setTitle(serviceConfiguration.getService());
        	node.setFolder("true");
        	node.setKey(serviceConfiguration.getId());
        	
        	if (serviceConfiguration.getState() == ServiceConfiguration.LEARNING) {
        		nodesLearning.add(node);
        	} else {
        		nodesConfigurations.add(node);
        	}
    	}
    	
    	return nodeConfigurations;
    }
    
    private Node toTree(String step, ServicesHierarchy servicesHierarchy) {
    	
    	Map<String, ServicesHierarchy> mServicesHierarchy = servicesHierarchy.getServicesHierarchy();
    	Map<String, ServiceConcept> mServices = servicesHierarchy.getServices();
    	
    	Parent node = new Parent();
    	
    	List<Node> children = new ArrayList<Node>();
    	
    	Set<String> set = mServicesHierarchy.keySet();
    	Iterator<String> iterator = set.iterator();
    	while (iterator.hasNext()) {
    		String childStep = iterator.next();
    		Node child = toTree(childStep, mServicesHierarchy.get(childStep));
    		children.add(child);
    	}
    	
    	Set<String> setLeafs = mServices.keySet();
    	Iterator<String> iteratorLeft = setLeafs.iterator();
    	while (iteratorLeft.hasNext()) {
    		String childName = iteratorLeft.next();
    		Node child = new Leaf();
    		ServiceConcept service = mServices.get(childName);
    		children.add(toNode(service));
    	}
    	
    	node.setChildren(children);
    	node.setTitle(step);
    	node.setFolder("true");
    	node.setKey(step);
    	
    	return node;
    	
    	
    }
    
    private Node toTree(ServiceConfiguration serviceConfiguration, List<ServiceConfigurationParameter> serviceParameterConfigurations) {
    	
    	Parent node = new Parent();
    	
    	Parent nodeGlobal = new Parent();
    	List<Node> childrenGlobal = new ArrayList<Node>();
    	nodeGlobal.setChildren(childrenGlobal);
    	nodeGlobal.setTitle("Global");
    	nodeGlobal.setFolder("true");
    	nodeGlobal.setKey("Global: "+serviceConfiguration.getService());
    	
    	Parent nodeValueMandatory = new Parent();
    	List<Node> childrenValueMandatory = new ArrayList<Node>();
    	nodeValueMandatory.setChildren(childrenValueMandatory);
    	nodeValueMandatory.setTitle("Value Mandatory");
    	nodeValueMandatory.setFolder("true");
    	nodeValueMandatory.setKey("Value Mandatory: "+serviceConfiguration.getService());
    	
    	Parent nodeInputMandatory = new Parent();
    	List<Node> childrenInputMandatory = new ArrayList<Node>();
    	nodeInputMandatory.setChildren(childrenInputMandatory);
    	nodeInputMandatory.setTitle("Input Mandatory");
    	nodeInputMandatory.setFolder("true");
    	nodeInputMandatory.setKey("Input Mandatory: "+serviceConfiguration.getService());
    	
    	Parent nodeLocal = new Parent();
    	List<Node> childrenLocal = new ArrayList<Node>();
    	nodeLocal.setChildren(childrenLocal);
    	nodeLocal.setTitle("Other");
    	nodeLocal.setFolder("true");
    	nodeLocal.setKey("Other: "+serviceConfiguration.getService());
    	
    	
    	
    	for (int cont=0;cont<serviceParameterConfigurations.size();cont++) {
    		ServiceConfigurationParameter serviceConfigurationParameter = 
    			serviceParameterConfigurations.get(cont);	
    		
    		Leaf leaf = new Leaf();
			leaf.setKey(serviceConfigurationParameter.getGlobalIdentifier());
			leaf.setTitle(serviceConfigurationParameter.getKey()+": "+serviceConfigurationParameter.getDefaultValue());
			
			if (serviceConfigurationParameter.getGlobalParameterId() != null) {
				childrenGlobal.add(leaf);
			} else {
				if (serviceConfigurationParameter.isInputMandatory()) {
					childrenInputMandatory.add(leaf);
				} else if (!serviceConfigurationParameter.isOptional()) {
					childrenValueMandatory.add(leaf);
				} else {
					childrenLocal.add(leaf);
				}
			}
    	}
    	
    	List<Node> children = new ArrayList<Node>();
    	children.add(nodeInputMandatory);
    	children.add(nodeGlobal);
    	children.add(nodeValueMandatory);
    	children.add(nodeLocal);

    	node.setChildren(children);
    	node.setTitle("Configuration: "+serviceConfiguration.getService());
    	node.setFolder("true");
    	node.setKey(serviceConfiguration.getGlobalIdentifier());
    	
    	return node;
    	
    	
    }
    
    private Node toNode(ServiceConcept service) {
    	Parent node = new Parent();
    	
    	List<Node> children = new ArrayList<Node>();
    	
    	if (service.getContainer() != null) {
	    	//Container name:
    		Node child = new Leaf();
	    	child.setTitle("container-name: "+service.getContainer().getDocker().getImage());
	    	child.setKey(service.getId()+"-"+service.getContainer().getDocker().getImage());
	    	children.add(child);
	    	
	    	//Container name:
    		Node containerType = new Leaf();
    		containerType.setTitle("container-type: "+service.getContainer().getType());
    		containerType.setKey(service.getId()+"-"+service.getContainer().getType());
	    	children.add(containerType);
    	}
    	 
    	node.setChildren(children);
    	node.setTitle(service.getFinalName());
    	node.setFolder("true");
    	node.setKey(service.getGlobalIdentifier());
    	
    	return node;
    }
    
    private List<ServiceConcept> toServices(MarathonAppsModel marathonAppsModel) {
    	List<ServiceConcept> services = 
    		marathonAppsModel.getApps().stream().map((MarathonTask task)->{
	    		
    			String [] path = task.getId().split("/");
    			List<String> lPath = Arrays.asList(path); 
    			int indexLast = lPath.size()-1;
    			String name = lPath.get(indexLast);
    			ServiceConcept service = new ServiceConcept();
    			service.setFinalName(name);
    			service.setPath(lPath.subList(0, indexLast).toArray(new String[indexLast]));
    			service.setId(task.getId());
    			
    			if (task.getContainer() != null) {
    				service.setContainer(Container.fromMarathonContainer(task.getContainer()));
    			}
    			
    			if (task.getEnv() != null) {
    				Map<String, Object> mTask = task.getEnv();
    				Map<String, String> env = new HashMap<String, String>();
    				Iterator<String> keys =
    						mTask.keySet().iterator();
    				while (keys.hasNext()) {
    					String key = keys.next();
    					Object value = mTask.get(key);
    					if (value instanceof String) {
    						env.put(key, value.toString());
    					}
    				}
    				service.setEnv(env);
    			}
    			
    			service.setLabels(task.getLabels());
    			service.setHealthChecks(task.getHealthChecks());
    			
    			List<PortDefinition> ports = task.getPortDefinitions();
    			List<ServicePort> servicePorts = new ArrayList<ServicePort>();
    			for (int cont=0;cont<ports.size();cont++) {
    				PortDefinition portDefinition = ports.get(cont);
    				ServicePort servicePort = new ServicePort();
    				servicePort.setIndex(cont);
    				servicePort.setProtocol(portDefinition.getProtocol());
    				servicePorts.add(servicePort);
    			}
    			service.setServicePorts(servicePorts);
    			
    			return service;
    			
	    	}).collect(Collectors.toList());
    	return services;
    }
}