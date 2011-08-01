/*
 * Copyright 2011 Jin Kwon.
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


package com.googlecode.jinahya.twitter.xauth;


import java.io.InputStream;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public final class ClientFactory {


    public static Client newClient(final Authenticator authenticator,
                                   final Requester requester) {

        if (TestProperties.isEmpty()) {
            throw new IllegalStateException("properties is empty");
        }

        return new Client(TestProperties.getProperty("consumerKey"),
                          TestProperties.getProperty("consumerSecret")) {


            @Override
            public byte[] authenticate(final byte[] key, final byte[] input)
                throws Exception {

                return authenticator.authenticate(key, input);
            }


            @Override
            public InputStream request(final String method, final String url,
                                       final String parameters,
                                       final String authorization)
                throws Exception {

                return requester.request(method, url, parameters,
                                         authorization);
            }
        };
    }


    private ClientFactory() {
        super();
    }
}

