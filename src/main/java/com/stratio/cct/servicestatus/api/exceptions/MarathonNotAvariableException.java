
/*
 * © 2017 Stratio Big Data Inc., Sucursal en España. All rights reserved.
 *
 * This software – including all its source code – contains proprietary
 * information of Stratio Big Data Inc., Sucursal en España and
 * may not be revealed, sold, transferred, modified, distributed or
 * otherwise made available, licensed or sublicensed to third parties;
 * nor reverse engineered, disassembled or decompiled, without express
 * written authorization from Stratio Big Data Inc., Sucursal en España.
 */
package com.stratio.cct.servicestatus.api.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason="Unable to connect to Marathon data")
public class MarathonNotAvariableException extends DataSourceNotAvariableException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
