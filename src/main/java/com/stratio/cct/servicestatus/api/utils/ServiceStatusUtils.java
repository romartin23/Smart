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
package com.stratio.cct.servicestatus.api.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.stratio.cct.servicestatus.api.model.MarathonTask;
import com.stratio.cct.servicestatus.api.model.ServiceStatusLabelEnum;
import com.stratio.cct.servicestatus.api.model.ServiceStatusView;
import com.stratio.cct.servicestatus.api.model.ServiceStatusViewEnum;

@Component
public class ServiceStatusUtils {
	

	

	public static String sanitizeServiceName(String key) {

		if (StringUtils.hasText(key)) {

			// delete first character if begins with dot.
			if (key.charAt(0) == '.' || key.charAt(0) == '/') {
				key = key.substring(1);
			}

			key = key.replace('/', ' ');

			return key;

		} else {
			return "";
		}

	}

	public static int mapStatusToPersistSimplifiedStatus(String status) {

		int out = -1;

		if (ServiceStatusViewEnum.healthy.name().equals(status)) {

			out = ServiceStatusViewEnum.healthy.ordinal();

		} else if (ServiceStatusViewEnum.unhealthy.name().equals(status)) {

			out = ServiceStatusViewEnum.unhealthy.ordinal();

		} else if (ServiceStatusViewEnum.unknown.name().equals(status)) {

			out = ServiceStatusViewEnum.unknown.ordinal();
		}

		return out;
	}

	

}
