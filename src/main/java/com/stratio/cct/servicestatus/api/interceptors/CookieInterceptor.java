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
package com.stratio.cct.servicestatus.api.interceptors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.Interceptor;
import okhttp3.Response;


public class CookieInterceptor implements Interceptor {
    Logger LOG = LoggerFactory.getLogger(CookieInterceptor.class);

    private List<String> cookieHistory = new ArrayList<>();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        if (!response.headers("Set-Cookie").isEmpty()) {
            for (String header : response.headers("Set-Cookie")) {
                cookieHistory.add(header.toString());
            }
        }

        return response;
    }

    public List<String> getCookies(){
        return cookieHistory;
    }

    public void clearCookies(){
        cookieHistory.clear();
    }
}
