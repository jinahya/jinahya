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


package com.googlecode.jinahya.nica;


import com.googlecode.jinahya.nica.util.Aes;
import com.googlecode.jinahya.nica.util.Hac;
import java.net.MalformedURLException;
import java.util.Map.Entry;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpUriRequest;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class AndroidHeaders extends DefaultHeaders {


    /**
     * Creates a new instance.
     *
     * @param names names
     * @param key the encryption key.
     */
    public AndroidHeaders(final String name, final AndroidCodes codes,
                          final Aes aes, final Hac hac) {

        super(name, codes, aes, hac);
    }


    /**
     * Sets HTTP request headers on given
     * <code>request</code>.
     *
     * @param request request
     *
     * @deprecated Use {@link #setHeaders(java.net.HttpURLConnection) }
     */
    public void setHeaders(final HttpRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("null request");
        }

        if (request instanceof HttpUriRequest) {
            final HttpUriRequest uriRequest = (HttpUriRequest) request;
            try {
                putVolatileEntry(Code.REQUEST_URL.key(),
                                 uriRequest.getURI().toURL().toExternalForm());
            } catch (MalformedURLException murle) {
                throw new RuntimeException(murle);
            }
            putVolatileEntry(Code.REQUEST_METHOD.key(), uriRequest.getMethod());
        }

        for (Entry<String, String> entry : getEntries().entrySet()) {
            request.setHeader(entry.getKey(), entry.getValue());
        }
    }


}

