package com.springjpa.drools;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.kie.internal.logger.KnowledgeRuntimeLoggerFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springjpa.LogAV;
import com.springjpa.drools.model.Element;
import com.springjpa.drools.model.ElementCts;
import com.springjpa.drools.model.global.Action;
import com.springjpa.drools.model.global.Cts;
import com.springjpa.drools.model.global.Recommendation;
import com.springjpa.drools.model.global.RecommendationResponse;
import com.springjpa.drools.model.global.Response;
import com.stratio.datacentric.smart.knowledge.concepts.service.configuration.GlobalParameter;

@Service
public class DroolsSessionImpl implements DroolsSession{
	
	private int cont=0;

	
	public void init(
			StatefulKnowledgeSession ksession) {
		
	}
	
	@Transactional
	public synchronized Response executeAction (
			Action action, 
			StatefulKnowledgeSession ksession) {
		
		try {
			
			KieRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession,Cts.BASE_PATH+"/rules/logs/action"+cont);
			cont++;
			ksession.insert(action);
			ksession.fireAllRules();
			logger.close();
			QueryResults results =
				ksession.getQueryResults("response");
			Response response = null;
			if (results.size()==1) {
				QueryResultsRow queryResultsRow = results.iterator().next();
				response = (Response ) queryResultsRow.get("response");
				ksession.retract(queryResultsRow.getFactHandle("response"));
			} else {
				while (results.iterator().hasNext()) {
					QueryResultsRow queryResultsRow = results.iterator().next();
					ksession.retract(queryResultsRow.getFactHandle("response"));
				}
			}
			
			return response;
		} catch (Exception e){
			LogAV.error("ThreadRoo: error executing action", e);
			return null;
		}
	}
	
	@Transactional
	public synchronized List<RecommendationResponse> executeRecommendation (
			StatefulKnowledgeSession ksession,
			Recommendation recommendation) {
		
		try {
			KieRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession,Cts.BASE_PATH+"/rules/logs/action"+cont);
			cont++;
			ksession.insert(recommendation);
			ksession.fireAllRules();
			logger.close();
			QueryResults results =
				ksession.getQueryResults("recommendation_response", recommendation.getId());

			List<RecommendationResponse> elements = new ArrayList<RecommendationResponse>();
			Iterator<QueryResultsRow> iterator = results.iterator();
			while (iterator.hasNext()) {
				QueryResultsRow queryResultsRow = iterator.next();
				elements.add((RecommendationResponse) queryResultsRow.get("recommendation_response"));
				ksession.retract(queryResultsRow.getFactHandle("recommendation_response"));
			}
			
			return elements;
		} catch (Exception e){
			LogAV.error("ThreadRoo: error executing action", e);
			return null;
		}
		
	}
	
	
	@Transactional
	public synchronized List<Object> query (
			KieSession ksession,
			String query,
			Object ... parameters) {
		
		
		for (int cont=0;cont<parameters.length;cont++) {
			if (parameters[cont] != null) {
				parameters[cont] = parameters[cont].toString().trim();
			}
		}
		List<Object> resultado = new ArrayList<Object>();
		QueryResults results =
				ksession.getQueryResults(query, parameters);
		for (QueryResultsRow queryResultsRow : results) {
			resultado.add(queryResultsRow.get(query));
		}
		return resultado;
	}
	
	public synchronized boolean updateElement(
			KieSession ksession,
			Element element) {
		
		KieRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession,Cts.BASE_PATH+"/rules/logs/action"+cont);
		cont++;
		QueryResults results =
				ksession.getQueryResults("elementByGlobalIdentifier",element.getGlobalIdentifier());
		for (QueryResultsRow queryResultsRow : results) {
			FactHandle factHandle = queryResultsRow.getFactHandle("elementByGlobalIdentifier");
			ksession.update(factHandle, element);
		}
		logger.close();
		return true;
	}
	
	@Transactional
	public synchronized boolean clean (
			KieSession ksession) {
		
		
		QueryResults results =
				ksession.getQueryResults("allElements");
		FactHandle factHandle = null;
		for (QueryResultsRow queryResultsRow : results) {
			factHandle = queryResultsRow.getFactHandle("allElements");
			Element element = (Element) queryResultsRow.get("allElements");
			BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(element);
			int numeroAtributo = 0;
			PropertyDescriptor[]  propertyDescriptors = wrapper.getPropertyDescriptors();
			boolean borrado = false;
			while (!borrado && numeroAtributo<propertyDescriptors.length) {
				PropertyDescriptor propertyDescriptor = propertyDescriptors[numeroAtributo];
				Object object = wrapper.getPropertyValue(propertyDescriptor.getName());
				if (Element.class.isAssignableFrom(Element.class)) {
					Element relation = (Element) object;
					boolean borrar = true;
					if (borrar) {
						QueryResults results2 =
								ksession.getQueryResults("elementByGlobalIdentifier", relation.getGlobalIdentifier());
						if (results2.size() == 0) {
							ksession.retract(factHandle);
							borrado = true;
						}
					}
					
				}
				numeroAtributo++;
			}
		}
		
		return true;
	}
	
	@Transactional
	public synchronized boolean cleanMerge (
			StatefulKnowledgeSession ksession) {
		
		
		QueryResults results =
				ksession.getQueryResults("response");
		FactHandle factHandle = null;
		for (QueryResultsRow queryResultsRow : results) {
			factHandle = queryResultsRow.getFactHandle("response");
			ksession.retract(factHandle);
		}
		return true;
	}
	
	
	@Transactional
	public synchronized boolean delete (
			KieSession ksession,
			String globalId) {
		
		QueryResults results =
				ksession.getQueryResults("elementByGlobalIdentifier", globalId);
		FactHandle factHandle = null;
		for (QueryResultsRow queryResultsRow : results) {
			factHandle = queryResultsRow.getFactHandle("elementByGlobalIdentifier");
		}
		if (factHandle != null) {
			ksession.retract(factHandle);
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional
	public synchronized boolean delete (
			KieSession ksession,
			List<String> globalIds) {
		
		globalIds.stream().forEach( (globalId) ->  
			{
				delete(ksession, globalId);
			} 
		);
		return true;
	}
	
	@Transactional
	public synchronized boolean merge(
			StatefulKnowledgeSession ksession,
			List<Element> elements) {
		
		KieRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession,Cts.BASE_PATH+"/rules/logs/action"+cont);
		cont++;
		
		for (Element element : elements) {
			element.setState(ElementCts.MERGE_STATE);
			ksession.insert(element);
		}
		ksession.fireAllRules();
		
		logger.close();
		
		return true;
	}
	
	public boolean removeResponses (
			StatefulKnowledgeSession ksession) {
		QueryResults results =
				ksession.getQueryResults("response");
		
		FactHandle factHandle = null;
		for (QueryResultsRow queryResultsRow : results) {
			factHandle = queryResultsRow.getFactHandle("response");
			ksession.retract(factHandle);
		}
		
		return true;
	}
	
}
