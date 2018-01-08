
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
package com.stratio.cct.servicestatus.api.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import mesosphere.marathon.client.model.v2.App;
import mesosphere.marathon.client.utils.ModelUtils;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarathonAppsModel {

	private List<MarathonTask> apps;

	public List<MarathonTask> getApps() {
		return apps;
	}

	public void setApps(List<MarathonTask> apps) {
		this.apps = apps;
	}

	@Override
	public String toString() {
		return ModelUtils.toString(this);
	}
	
}
