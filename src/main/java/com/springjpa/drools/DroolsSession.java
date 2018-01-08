package com.springjpa.drools;

import java.util.List;

import org.kie.api.runtime.KieSession;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import com.springjpa.drools.model.Element;

public interface DroolsSession {

	
	public List<Object> query (
			KieSession ksession,
			String query,
			Object ... parameters);
	
	
	public boolean clean (
			KieSession ksession);
	
	public void init(
			StatefulKnowledgeSession ksession);
	
	public boolean updateElement(
			KieSession ksession,
			Element element);
	
	public boolean delete (
			KieSession ksession,
			List<String> globalIds);
}
