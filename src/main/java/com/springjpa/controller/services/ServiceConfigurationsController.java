package com.springjpa.controller.services;

import java.net.URLEncoder;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springjpa.drools.DroolsSession;
import com.springjpa.drools.model.Element;
import com.springjpa.services.MarathonKnowledgeService;
import com.stratio.cct.servicestatus.api.services.marathon.MarathonService;
import com.stratio.cct.servicestatus.api.services.sso.GosecAuthenticator;
import com.stratio.datacentric.smart.knowledge.concepts.service.configuration.ServiceConfiguration;
import com.stratio.datacentric.smart.knowledge.concepts.service.configuration.ServiceConfigurationParameter;

@RestController
public class ServiceConfigurationsController {

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
	

    @RequestMapping("/learnedServiceConfiguration")
    public void learnedServiceConfiguration(@RequestParam(value="serviceConfiguration", defaultValue="") String serviceConfiguration) {
    	List<Object> elements = droolSession.query(kieSession, "service_configurations_by_id", serviceConfiguration);
    	Element element = (Element) elements.get(0);
    	element.setState(ServiceConfiguration.LEARNED);
    	droolSession.updateElement(kieSession, element);
    }
    
    
    
    
    @RequestMapping(value = "/putServiceConfigurationParameter", method = RequestMethod.PUT)
    public @ResponseBody String putServiceConfigurationParameter(@RequestBody ServiceConfigurationParameter serviceConfigurationParameter) {
    	/*List<Object> elements = droolSession.query(
    			kieSession, "service_configurations_by_id", 
    			serviceConfigurationParameter.getServiceConfiguration().getService(), serviceConfigurationParameter.getKey());
    	ServiceConfigurationParameter element = (ServiceConfigurationParameter) elements.get(0);*/
    	droolSession.updateElement(kieSession, serviceConfigurationParameter);
        return "ok";

    }
    
    @RequestMapping(value = "/getServiceConfigurationParameter", method = RequestMethod.GET, produces = "application/json")
    public ServiceConfigurationParameter getServiceConfigurationParameter(
    		@RequestParam(required = true) String serviceConfiguration, @RequestParam(required = true) String key) {
    	   	
    	return getServiceConfigurationParameterInternal(serviceConfiguration, key);
    }
    
    @RequestMapping(value = "/putMemoryConcept", method = RequestMethod.PUT)
    public @ResponseBody String putServiceConfigurationParameter(@RequestBody Element element) {
    	/*List<Object> elements = droolSession.query(
    			kieSession, "service_configurations_by_id", 
    			serviceConfigurationParameter.getServiceConfiguration().getService(), serviceConfigurationParameter.getKey());
    	ServiceConfigurationParameter element = (ServiceConfigurationParameter) elements.get(0);*/
    	droolSession.updateElement(kieSession, element);
        return "okkk";

    }
    
    @RequestMapping(value = "/getMemoryConcept", method = RequestMethod.GET, produces = "application/json")
    public Element getServiceConfigurationParameter(
    		@RequestParam(required = true) String elementGlobalIdentifier) {
    	
    	String newElementGlobalIdentifier = elementGlobalIdentifier;
    	try {
    		newElementGlobalIdentifier=  URLEncoder.encode(elementGlobalIdentifier, "UTF-8");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
    	
		List<Object> elements = droolSession.query(
				kieSession, "elementByGlobalIdentifier", 
				newElementGlobalIdentifier);
		Element element = (Element) elements.get(0);    	
    	return element;
    }
    
    private ServiceConfigurationParameter getServiceConfigurationParameterInternal(
    		String serviceConfiguration, String key) {
    	List<Object> elements = droolSession.query(kieSession, "service_configurations_parameter", serviceConfiguration, key);
    	ServiceConfigurationParameter element = (ServiceConfigurationParameter) elements.get(0);    	
    	return element;
    }
    
   
    
}