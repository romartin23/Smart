package com.springjpa.drools.model.global;

import com.springjpa.drools.model.Element;

public class Recommendation extends Element<Recommendation>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2061696472101321929L;

	public final static String CODE_RECOMMENDATION = "C_RECOMMENDATION";
	
	private String query;
	
	
	private String recommendationType;

	@Override
	public String getType() {
		return CODE_RECOMMENDATION;
	}

	@Override
	public String getSmallName() {
		return query;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getRecommendationType() {
		return recommendationType;
	}

	public void setRecommendationType(String recommendationType) {
		this.recommendationType = recommendationType;
	}
	
	

}
