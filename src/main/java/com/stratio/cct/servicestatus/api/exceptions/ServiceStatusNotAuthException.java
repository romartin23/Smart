package com.stratio.cct.servicestatus.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN)
public class ServiceStatusNotAuthException extends RuntimeException {

}
