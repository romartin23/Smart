package com.stratio.datacentric.smart.knowledge.concepts.service.configuration;

public class ServiceConfigurationParameter extends ServiceMappingItem{
	
	private boolean inputMandatory;
	
	public boolean isInputMandatory() {
		return inputMandatory;
	}

	public void setInputMandatory(boolean inputMandatory) {
		this.inputMandatory = inputMandatory;
	}

}
