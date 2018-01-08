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
package com.stratio.cct.servicestatus.api.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.stratio.cct.servicestatus.api.interceptors.SpringSessionClientHttpRequestInterceptor;

@Configuration
public class ServiceStatusConfiguration {

	@Bean
	RestTemplate restTemplate() {

		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectionRequestTimeout(500);
		httpRequestFactory.setConnectTimeout(500);
		httpRequestFactory.setReadTimeout(1000);

		RestTemplate rest = new RestTemplate(httpRequestFactory);

		ClientHttpRequestInterceptor interceptor = new SpringSessionClientHttpRequestInterceptor();
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(interceptor);
		rest.setInterceptors(interceptors);
		return rest;

	}

}
