package com.springjpa.drools.model.global;

import com.springjpa.drools.model.Element;

public class Alert extends Element{
	
	public final static String CODE_ALERT = "C_ALERT";
	
	public static String ALERT_INFO;
	public static String ALERT_WARNING;
	public static String ALERT_ERROR;
	
	public static String CODE_NOT_EXIST_ATTRIBUTES="1";
	public static String DESCRIPTION_NOT_EXIST_ATTRIBUTES="No se ha definido atributos para esta entidad.";
	
	
	
	
	private String description;
	private String code;
	private String type;
	private Element element;
	
	private boolean sent = false;
	private boolean closed = false;
	
	
	

	
	
	public boolean isSent() {
		return sent;
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return CODE_ALERT;
	}

	@Override
	public String getSmallName() {
		// TODO Auto-generated method stub
		return description;
	}

}
