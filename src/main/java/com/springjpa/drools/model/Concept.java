package com.springjpa.drools.model;

public interface Concept {
	
	public Integer getSemanticHash();
	
	static public Integer toHash (String data) {
		if (data != null) {
			return data.hashCode();
		} else {
			return 0;
		}
	}
	
	static public Integer toHash (String[] data) {
		
		if (data != null) {
			int hash =0;
			for (int cont=0;cont<data.length;cont++) {
				hash+=toHash(data[cont]);
			}
			return hash;
		} else {
			return 0;
		}
		
	}

}
