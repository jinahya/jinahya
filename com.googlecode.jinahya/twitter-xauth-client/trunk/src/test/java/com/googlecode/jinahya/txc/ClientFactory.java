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


package com.googlecode.jinahya.txc;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
final class ClientFactory {


    public static Client newClient(final Authenticator authenticator,
                                   final Requester requester) {

        if (TestProperties.isEmpty()) {
            throw new IllegalStateException("properties is empty");
        }

        return new Client(TestProperties.getProperty("consumerKey"),
                          TestProperties.getProperty("consumerSecret"),
                          authenticator, requester);
    }


    /** PRIVATE. */
    private ClientFactory() {
        super();
    }
}
