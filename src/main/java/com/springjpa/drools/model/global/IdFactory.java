package com.springjpa.drools.model.global;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class IdFactory implements Serializable{
	
	private Map<String, Integer> idsTypes = new HashMap<String, Integer>();
	
	
	public Integer next(String type) {
		
		Integer nextId = idsTypes.get(type);
		if (nextId == null) {
			nextId = 1;
			idsTypes.put(type, (nextId+1));
		} else {
			idsTypes.put(type, (nextId+1));
		}
		return nextId;
	}

}
