package com.springjpa.drools.model.global;

import com.springjpa.drools.model.Element;

public class RecommendationResponse extends Element<RecommendationResponse>{
	
	
	public final static String CODE_RECOMMENDATION_RESPONSE = "C_RECOMMENDATION_RESPONSE";
	
	private Recommendation recommendation;
	private ContextElement contextElement;

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return CODE_RECOMMENDATION_RESPONSE;
	}

	@Override
	public String getSmallName() {
		// TODO Auto-generated method stub
		return contextElement.getSmallName();
	}

	public Recommendation getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(Recommendation recommendation) {
		this.recommendation = recommendation;
	}

	public ContextElement getContextElement() {
		return contextElement;
	}

	public void setContextElement(ContextElement contextElement) {
		this.contextElement = contextElement;
	}
	
	

	

}
