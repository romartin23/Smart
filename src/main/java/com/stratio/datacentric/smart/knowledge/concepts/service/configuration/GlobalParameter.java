package com.stratio.datacentric.smart.knowledge.concepts.service.configuration;

import com.springjpa.drools.model.MappingItem;

public class GlobalParameter extends MappingItem {

	public String getId() {
		return getKey();
	}
	

}
