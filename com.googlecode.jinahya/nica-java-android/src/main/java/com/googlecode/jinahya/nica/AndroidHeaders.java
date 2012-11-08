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


import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicHeader;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class AndroidHeaders extends Headers {


    /**
     * Creates a new instance.
     *
     * @param names names
     * @param key the encryption key.
     */
    public AndroidHeaders(final Map<String, String> names, final byte[] key) {

        super(names, key);
    }


    /**
     * Sets request headers on given
     * <code>request</code>.
     *
     * @param request request
     *
     * @deprecated Use {@link java.net.HttpURLConnection}.
     */
    @Deprecated
    public void setHeaders(final HttpUriRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("null request");
        }

        try {
            putVolatileCode(Code.REQUEST_URL.key(),
                            request.getURI().toURL().toExternalForm());
        } catch (MalformedURLException murle) {
            throw new RuntimeException(murle);
        }
        putVolatileCode(Code.REQUEST_METHOD.key(), request.getMethod());

        for (Entry<String, String> entry : getHeaders().entrySet()) {
            request.setHeader(entry.getKey(), entry.getValue());
        }
    }


    /**
     * Adds HTTP headers to given
     * <code>headers</code>.
     *
     * @param headers the list to be filled
     *
     * @deprecated Use {@link java.net.HttpURLConnection}.
     */
    @Deprecated
    public void getHeaders(final List<Header> headers) {

        final String[] entries = getEntries();
        for (int i = 0; i < entries.length; i += 2) {
            headers.add(new BasicHeader(entries[i], entries[i + 1]));
        }
    }


}

