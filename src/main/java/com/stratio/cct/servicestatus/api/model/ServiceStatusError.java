package com.stratio.cct.servicestatus.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceStatusError {

	private ServiceStatusErrorModel error;	
	
	@Override
	public String toString() {
		try {
			return new com.fasterxml.jackson.databind.ObjectMapper().writerWithDefaultPrettyPrinter()
					.writeValueAsString(this);
		} catch (com.fasterxml.jackson.core.JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
