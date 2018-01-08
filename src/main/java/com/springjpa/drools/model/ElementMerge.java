package com.springjpa.drools.model;

import java.io.Serializable;
import java.util.List;

import com.springjpa.drools.model.global.Alert;

public abstract class  ElementMerge <T extends Element<?>> extends Element<T> implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Alert> alerts;
	
	public List<Alert> getAlerts() {
		return alerts;
	}
	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}
	
	public abstract Element<?> getPrincipalElement();
	
	public String getType() {
		return "MERGE|@|"+getPrincipalElement().getType(); 
	}
	public String getSmallName(){
		return getPrincipalElement().getSmallName();
	}
	
	public void merge(T element){
		
	}
	

}
