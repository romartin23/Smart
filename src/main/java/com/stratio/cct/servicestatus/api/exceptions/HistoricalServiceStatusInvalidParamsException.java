package com.stratio.cct.servicestatus.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.stratio.cct.servicestatus.api.constants.ServiceStatusConstants;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class HistoricalServiceStatusInvalidParamsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return formatMessage();
	}

	private String formatMessage() {
		return String.format(ServiceStatusConstants.BAD_REQUEST_MESSAGE,"historical service status search");
	}
	
	@Override
	public String toString() {

		return "{\"message\": \"" + formatMessage() + "\","
				+ "\"code\":" + HttpStatus.BAD_REQUEST + "}";

	}
	
}
