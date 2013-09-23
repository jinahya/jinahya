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


package com.googlecode.jinahya.ws.core.response;


import javax.ws.rs.core.Response.Status;


/**
 * A status type for {@code 400 Bad Request} which is a member of
 * {@code Client Error 4xx}.
 *
 * @see <a href="http://tools.ietf.org/html/rfc2616#section-10.4.1">10.4.1 400
 * Bad Request (RFC 2616 Hypertext Transfer Protocol -- HTTP/1.1)</a>
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class BadRequest400 extends AbstractStatusType {


    /**
     * Creates a new instance with given reason phrase.
     *
     * @param reasonPhrase the reason phrase
     */
    public BadRequest400(final String reasonPhrase) {

        super(Status.BAD_REQUEST, reasonPhrase);
    }


}
