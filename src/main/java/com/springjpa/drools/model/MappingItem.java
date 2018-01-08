package com.springjpa.drools.model;

public abstract class MappingItem extends Element<MappingItem> implements Concept{
	
	private String key;
	private String defaultValue;
	
	private boolean optional;
	private boolean customizable;
	
	private String semanticName;
	
	public Integer getSemanticHash() {
		return key.hashCode()+":".hashCode()+defaultValue.hashCode();
	}
	
	
	
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getSemanticName() {
		return semanticName;
	}

	public void setSemanticName(String semanticName) {
		this.semanticName = semanticName;
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public boolean isOptional() {
		return optional;
	}
	public void setOptional(boolean optional) {
		this.optional = optional;
	}
	public boolean isCustomizable() {
		return customizable;
	}
	public void setCustomizable(boolean customizable) {
		this.customizable = customizable;
	}
	
	@Override
	public String getSmallName() {
		return key;
	}
	
	
	

}
