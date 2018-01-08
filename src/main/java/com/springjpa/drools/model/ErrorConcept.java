package com.springjpa.drools.model;

public class ErrorConcept extends Element<Element> {
	
	public static String COD_ERROR_PARAMETER_MANDATORY = "1";
	public static String COD_ERROR_WITHOUT_DEPLOYMENT_CONFIGURATION = "2";
	
	
	private String codeError;
	private String messageError;
	
	public String getId() {
		return getParentConceptId()+"_"+codeError;
	}

	public String getCodeError() {
		return codeError;
	}

	public void setCodeError(String codeError) {
		this.codeError = codeError;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

	@Override
	public String getSmallName() {
		return codeError;
	}

}
