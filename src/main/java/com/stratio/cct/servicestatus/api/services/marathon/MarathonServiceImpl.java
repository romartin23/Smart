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
package com.stratio.cct.servicestatus.api.services.marathon;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.stratio.cct.servicestatus.api.constants.ServiceStatusConstants;
import com.stratio.cct.servicestatus.api.exceptions.MarathonNotAvariableException;
import com.stratio.cct.servicestatus.api.exceptions.ServiceStatusNotAuthException;
import com.stratio.cct.servicestatus.api.services.sso.GosecAuthenticator;

@Component
public class MarathonServiceImpl implements MarathonService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// Rest client used to make requests to Marathon
	private RestTemplate rest;

	GosecAuthenticator gosecAuthenticator;

	
	public static final String APP_ID_REGEX = "^(([a-z0-9]|[a-z0-9][a-z0-9\\-]*[a-z0-9])\\.)*([a-z0-9]|[a-z0-9][a-z0-9\\-]*[a-z0-9])|(\\.|\\.\\.)$";

	/**
	 * Constructor
	 */
	@Autowired
	public MarathonServiceImpl(GosecAuthenticator gosecAuthenticator)
			throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

		this.gosecAuthenticator = gosecAuthenticator;

		// => Rest client configuration

		// · Configuration to ignore https ..
		// TODO - verify=false ?? Clarify the use of certificates in DCOS
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
				.build();
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);

		// · Rest client creation
		this.rest = new RestTemplate(requestFactory);
	}


	/**
	 * Deployments status request to Marathon
	 */
	@Override
	public String getApps(String dcosSecurityToken, String dcosEntrypoint) throws MarathonNotAvariableException {

		logger.debug("=> Service - Marathon - getting app status");

		String marathonDeploymentsEndpoint = String.format("https://%s/marathon/v2/apps", dcosEntrypoint);

		String cookie = ServiceStatusConstants.DCOS_ACS_AUTH_COOKIE + "=" + dcosSecurityToken; 

		// Getting session from request
		HttpHeaders headers = new HttpHeaders();

		// · Deploy request to Marathon
		logger.debug("Sending request to Marathon to get microservice status.");

		headers.add("Cookie", cookie);
		headers.add("Content-Type", "application/json");

		HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
		try {
			ResponseEntity<String> responseEntity = rest.exchange(marathonDeploymentsEndpoint, HttpMethod.GET,
					requestEntity, String.class);

			int statusCode = responseEntity.getStatusCode().value();
			String responseBody = responseEntity.getBody();

			logger.debug("Status code: " + statusCode);
			logger.debug("Response body: " + responseBody);

			// · If app status has been correctly retrieved status code must be 200
			if (statusCode == 200) {
				return responseBody;
			} else {
				logger.error(String.format("Code: %s  Body: %s", statusCode, responseBody));
				throw new MarathonNotAvariableException();
			}
		} catch (HttpClientErrorException e) {
			String msn = e.getResponseBodyAsString();
			int statusCode = e.getStatusCode().value();
			logger.error(String.format(String.format("Code: %s  Body: %s", statusCode, msn)));
			throw statusCode == 401 ? new ServiceStatusNotAuthException() : new MarathonNotAvariableException();

		} catch (RestClientException e) {
			logger.error(String.format(e.getMessage()));
			throw new MarathonNotAvariableException();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MarathonNotAvariableException();
		}
	}

}
