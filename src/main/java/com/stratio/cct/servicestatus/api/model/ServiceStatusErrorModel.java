package com.stratio.cct.servicestatus.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceStatusErrorModel {

	private String status; // "status": "422",
	private String title; // "title": "Invalid Attribute",
	private String detail; // "detail": "First name must contain at least three characters."

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
