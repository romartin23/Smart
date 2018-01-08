package com.springjpa.drools.model.global;

import com.springjpa.drools.model.Element;

public class MergeUpdate {
	
	public static final String WITHOUT_CHANGES = "1";
	public static final String WITH_CHANGES = "2";

	private Element element;
	private String state;
	
		
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public MergeUpdate(Element element) {
		this.element = element;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}
}
