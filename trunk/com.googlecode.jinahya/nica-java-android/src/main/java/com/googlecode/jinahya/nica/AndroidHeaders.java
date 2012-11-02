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


import com.googlecode.jinahya.nica.util.AesJCE;
import com.googlecode.jinahya.nica.util.HacJCE;
import java.util.Iterator;
import java.util.Map.Entry;
import org.apache.http.HttpRequest;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class AndroidHeaders extends Headers {


    /**
     * Creates a new instance.
     *
     * @param name a Par-encoded nica-name.
     * @param codes codes
     * @param key the encryption key.
     */
    public AndroidHeaders(final String name, final AndroidCodes codes,
                          final byte[] key) {

        super(name, codes, new AesJCE(key), new HacJCE(key));
    }


    /**
     * Sets request headers on given
     * <code>request</code>.
     *
     * @param request request
     */
    public void setHeaders(final HttpRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("null connection");
        }

        for (Iterator<?> i = getHeaders().entrySet().iterator(); i.hasNext();) {
            final Entry<?, ?> entry = (Entry<?, ?>) i.next();
            request.setHeader((String) entry.getKey(),
                              (String) entry.getValue());
        }
    }


}

