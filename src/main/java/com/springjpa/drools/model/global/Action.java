package com.springjpa.drools.model.global;

import com.springjpa.drools.model.Element;

public class Action {
	
	
	public static final String VALIDATION_ACTION = "VALIDATION";
	public static final String CREATE_ACTION = "CREATE";
	public static final String UPDATE_ACTION = "UPDATE";
	
	private Integer id;
	private String codeAction;
	
	private String concept;
	
	
	private Element element;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getCodeAction() {
		return codeAction;
	}
	public void setCodeAction(String codeAction) {
		this.codeAction = codeAction;
	}
	public String getConcept() {
		return concept;
	}
	public void setConcept(String concept) {
		this.concept = concept;
	}
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	
}
