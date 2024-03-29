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


import java.io.IOException;
import java.io.InputStream;


/**
 * Interface for requesting resource.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public interface Requester {


    /**
     * Requests with given arguments.
     *
     * @param method request method
     * @param url request URL
     * @param parameters parameters (already URL-Encoded)
     * @param authorization authorization header value or null.
     * @return resource stream
     * @throws IOException if an I/O error occurs.
     * @throws XTweetException if any error occurs
     */
    InputStream request(final String method, final String url,
                        final String parameters, final String authorization)
        throws IOException, XTweetException;
}
