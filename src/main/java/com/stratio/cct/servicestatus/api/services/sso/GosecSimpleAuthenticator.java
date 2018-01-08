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
package com.stratio.cct.servicestatus.api.services.sso;

import java.io.IOException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stratio.cct.servicestatus.api.constants.ServiceStatusConstants;
import com.stratio.cct.servicestatus.api.interceptors.CookieInterceptor;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by shernandez on 8/03/17. Modified by dherranz Dic-2017
 */

@Service
public class GosecSimpleAuthenticator implements GosecAuthenticator{
	
	
	Logger LOG = LoggerFactory.getLogger(GosecSimpleAuthenticator.class);


    private OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
    private OkHttpClient clientHttp;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private String gosecLt;
    private String gosecExecution;
    private String gosecEvent;

    @Override
    public String getDcosToken() {
        return DcosToken;
    }

    private String DcosToken;

    private RedirectionInterceptor redirectionInterceptor = new RedirectionInterceptor();
    private CookieInterceptor cookieInterceptor = new CookieInterceptor();

    @Autowired
    public GosecSimpleAuthenticator() {

        clientBuilder = getUnsafeOkHttpClientBuilder();

        clientBuilder.addNetworkInterceptor(this.redirectionInterceptor);
        clientBuilder.addNetworkInterceptor(this.cookieInterceptor);

        clientHttp = clientBuilder.build();
    }

    @Override
    public boolean authenticate(
    		String dcosEntryPoint,
    		String userName,
    		String dcosAppId,
    		String pass) {
    	
    	String baseUrl = String.format("https://%s/login?firstUser=false", dcosEntryPoint);
    	

		String marathonUser = userName;
		String marathonSecret = pass;

        LOG.debug("1. GO TO " + baseUrl);

        Response response1_1 = getRequest(baseUrl);

        String callBackLocation = redirectionInterceptor.getLocationHistory().get(1);
        redirectionInterceptor.clearLocationHistory();


        LOG.debug("2. REDIRECT TO : " + callBackLocation);
        Response response1_2 = getRequest(callBackLocation);

        String JSESSIONIDCookie = null;
        for(String cookie: cookieInterceptor.getCookies()){
            if(cookie.contains("JSESSIONID")){
                JSESSIONIDCookie = cookie;
                break;
            }
        }

        cookieInterceptor.clearCookies();

        LOG.debug("2. COOKIE : " + JSESSIONIDCookie);

        String callBackLocation2 = redirectionInterceptor.getLocationHistory().get(1);
        redirectionInterceptor.clearLocationHistory();

        LOG.debug("3. REDIRECT TO : " + callBackLocation2 + " with JSESSIONID");
        Response response1_3 = getRequest(callBackLocation2, JSESSIONIDCookie);

        try {
            parseGosecBody(response1_3.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String lastRedirection = callBackLocation2;

        RequestBody formBody = new FormBody.Builder()
                .add("lt", gosecLt)
                .add("_eventId", gosecEvent)
                .add("execution", gosecExecution)
                .add("submit", "LOGIN")
                .add("username", marathonUser)
                .add("password", marathonSecret)
                .build();

        Request request = new Request.Builder()
                .url(lastRedirection)
                .addHeader("Cookie", JSESSIONIDCookie)
                .post(formBody)
                .build();

        try {
            Response response2_1 = clientHttp.newCall(request).execute();

        } catch (IOException e) {
            e.printStackTrace();
        }


        //String CASPRIVACY =  cookieInterceptor.getCookies().get(0);
        //String TGC = cookieInterceptor.getCookies().get(1);
        //DcosToken = cookieInterceptor.getCookies().get(2);

        for(String cookie: cookieInterceptor.getCookies()){
            if( cookie.contains(ServiceStatusConstants.DCOS_ACS_AUTH_COOKIE)){
                this.DcosToken = cookie;
                LOG.debug("LOGIN SUCCESSFUL: " + cookie);
                return true;
            }
        }
        /*
        LOG.info("JSESSIONID: " + JSESSIONIDCookie);
        LOG.info("CASPRIVACY: " + CASPRIVACY);
        LOG.info("TGC: " + TGC);
        LOG.info("Oauth Token obtained: " + DcosToken);
        */

        return false;
    }

    private void parseGosecBody(String htmlDocument){
        org.jsoup.nodes.Document doc = Jsoup.parse(htmlDocument);
        Elements elements = doc.select("input[type=hidden]");
        for (Element element : elements){
            if (element.attr("name").equals("lt"))
                gosecLt = element.attr("value");
            else if (element.attr("name").equals("execution"))
                gosecExecution = element.attr("value");
            else if (element.attr("name").equals("_eventId"))
                gosecEvent = element.attr("value");

        }
    }


    private Response getRequest(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = clientHttp.newCall(request).execute();
            return response;


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Response getRequest(String url, String cookie) {
        Request request = new Request.Builder()
                .addHeader("Cookie", cookie)
                .url(url)
                .build();

        try {
            Response response = clientHttp.newCall(request).execute();
            return response;


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Response getRequest(String url, String cookie, String casprivacy, String tgc) {
        Request request = new Request.Builder()
                .addHeader("Cookie", cookie)
                .addHeader("Cookie", casprivacy)
                .addHeader("Cookie", tgc)
                .url(url)
                .build();

        try {
            Response response = clientHttp.newCall(request).execute();
            return response;


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static OkHttpClient.Builder getUnsafeOkHttpClientBuilder() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}



