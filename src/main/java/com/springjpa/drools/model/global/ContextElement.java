package com.springjpa.drools.model.global;

import java.util.List;

import com.springjpa.drools.model.Element;


public class ContextElement extends Element{
	
	private String mid;
	
	private String name;
	private String description;
	private String value;
	private String queryParameter;
	private List<String> types;
	
	
	
	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

	public String getQueryParameter() {
		return queryParameter;
	}

	public void setQueryParameter(String queryParameter) {
		this.queryParameter = queryParameter;
	}
	
	

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}
	

	
	

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	@Override
	public String getSmallName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
