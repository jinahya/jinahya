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


package com.googlecode.jinahya.http;


import com.googlecode.jinahya.nica.util.AES;
import java.io.IOException;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class NicaRequestInterceptor implements HttpRequestInterceptor {


    public NicaRequestInterceptor(final byte[] key, final String... names) {

        super();

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (key.length != AES.KEY_SIZE_IN_BYTES) {
            throw new IllegalArgumentException(
                "key.length(" + key.length + ") != KEY_SIZE_IN_BYTES("
                + AES.KEY_SIZE_IN_BYTES + ")");
        }

        if (names == null) {
            throw new IllegalArgumentException("null names");
        }

        if (names.length == 0) {
            throw new IllegalArgumentException("empty names");
        }

        this.key = new SecretKeySpec(key, AES.ALGORITHM);
        this.names = names.clone();
    }


    @Override
    public void process(final HttpRequest request, final HttpContext context)
        throws HttpException, IOException {

        throw new UnsupportedOperationException("Not supported yet.");
    }


    private final Key key;


    private final String[] names;


}

