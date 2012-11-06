/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.nica.http;


import com.googlecode.jinahya.nica.Code;
import com.googlecode.jinahya.nica.Headers;
import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;


/**
 * A RequestInterceptor which sets NICA headers.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class NicaRequestInterceptor implements HttpRequestInterceptor {


    /**
     * Creates a new instance.
     *
     * @param headres the headers to be wrapped.
     */
    public NicaRequestInterceptor(final Headers headres) {
        super();

        if (headres == null) {
            throw new IllegalArgumentException("null headers");
        }

        this.headers = headres;
    }


    @Override
    public void process(final HttpRequest request, final HttpContext context)
        throws HttpException, IOException {

        if (request instanceof HttpUriRequest) {
            final HttpUriRequest uriRequest = (HttpUriRequest) request;
            try {
                headers.getCodes().putVariableCode(
                    Code.REQUEST_URL.key(),
                    uriRequest.getURI().toURL().toExternalForm());
            } catch (MalformedURLException murle) {
                throw new RuntimeException(murle);
            }
            headers.getCodes().putVariableCode(
                Code.REQUEST_METHOD.key(), uriRequest.getMethod());
        }
    }


    /**
     * headers.
     */
    private final Headers headers;


}

