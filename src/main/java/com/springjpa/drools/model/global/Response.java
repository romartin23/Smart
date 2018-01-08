package com.springjpa.drools.model.global;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Response implements Serializable{
	
	private int idAction;
	private boolean correct;
	private List<Error> errors = new ArrayList<Error>();
	
	public boolean isHasErrors() {
		if (errors.size()>0) {
			return true;
		}
		
		return false;
	}
	
	
	public void addError(Error error) {
		errors.add(error);
	}
	
	public int getIdAction() {
		return idAction;
	}
	public void setIdAction(int idAction) {
		this.idAction = idAction;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	public List<Error> getErrors() {
		return errors;
	}
	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	
	
	

}
