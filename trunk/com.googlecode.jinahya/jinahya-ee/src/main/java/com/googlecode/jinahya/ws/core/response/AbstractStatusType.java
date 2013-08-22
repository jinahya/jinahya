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
import javax.ws.rs.core.Response.Status.Family;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class AbstractStatusType extends OrphanStatusType {


    /**
     * Creates a new instance.
     *
     * @param family the status family
     * @param statusCode the HTTP status code
     * @param reasonPhrase the HTTP reason phrase
     */
    protected AbstractStatusType(final Family family, final int statusCode,
                                 final String reasonPhrase) {

        super(family, statusCode, reasonPhrase);
    }


    /**
     * Creates a new instance with given {@code status} and
     * {@code reasonPhrase}.
     *
     * @param status the status to wrap
     * @param reasonPhrase the HTTP reason phrase
     */
    protected AbstractStatusType(final Status status,
                                 final String reasonPhrase) {

        this(status.getFamily(), status.getStatusCode(), reasonPhrase);
    }


}
