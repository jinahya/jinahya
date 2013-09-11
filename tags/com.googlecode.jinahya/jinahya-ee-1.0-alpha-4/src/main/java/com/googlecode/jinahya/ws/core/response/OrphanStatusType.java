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


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class OrphanStatusType implements StatusType {


    /**
     * Creates a new instance of this status type for given arguments.
     *
     * @param statusCode the HTTP status code
     * @param reasonPhrase the HTTP reason phrase
     *
     * @return a new instance of this status type.
     */
    public static OrphanStatusType newInstance(final int statusCode,
                                               final String reasonPhrase) {

        return new OrphanStatusType(null, statusCode, reasonPhrase);
    }


    /**
     * Returns a new response builder for given arguments.
     *
     * @param statusCode the HTTP status code
     * @param reasonPhrase the HTTP reason phrase
     *
     * @return a new response builder to build
     */
    public static ResponseBuilder buildable(final int statusCode,
                                            final String reasonPhrase) {

        return Response.status(newInstance(statusCode, reasonPhrase));
    }


    /**
     * Returns a new response for given arguments.
     *
     * @param statusCode the HTTP status code
     * @param reasonPhrase the HTTP reason phrase
     *
     * @return a new response to respond
     */
    public static Response built(final int statusCode,
                                 final String reasonPhrase) {

        return buildable(statusCode, reasonPhrase).build();
    }


    /**
     * Returns a new web application exception for given arguments.
     *
     * @param statusCode the HTTP status code
     * @param reasonPhrase the HTTP reason phrase
     *
     * @return a new web application exception to throw
     */
    public static WebApplicationException throwable(final int statusCode,
                                                    final String reasonPhrase) {

        return new WebApplicationException(built(statusCode, reasonPhrase));
    }


    /**
     * Creates a new instance with given arguments.
     *
     * @param family the family
     * @param statusCode the status code
     * @param reasonPhrase the reason phrase
     */
    protected OrphanStatusType(final Family family, final int statusCode,
                               final String reasonPhrase) {

        super();

//        if (reasonPhrase == null) {
//            throw new NullPointerException("reasonPhrase");
//        }

        this.family = family;
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
    }


    @Override
    public Family getFamily() {

        if (family != null) {
            return family;
        }

        switch (statusCode / 100) {
            case 1:
                return Family.INFORMATIONAL;
            case 2:
                return Family.SUCCESSFUL;
            case 3:
                return Family.REDIRECTION;
            case 4:
                return Family.CLIENT_ERROR;
            case 5:
                return Family.SERVER_ERROR;
            default:
                return Family.OTHER;
        }
    }


    @Override
    public String getReasonPhrase() {

        return reasonPhrase;
    }


    @Override
    public int getStatusCode() {

        return statusCode;
    }


    /**
     * Returns a new response builder for this status type.
     *
     * @return a new response builder to build
     *
     * @see Response#status(javax.ws.rs.core.Response.StatusType)
     */
    public ResponseBuilder buildable() {

        return Response.status(this);
    }


    /**
     * Returns a new response for this status type.
     *
     * @return a new response to respond.
     *
     * @see #builder()
     */
    public Response built() {

        return buildable().build();
    }


    /**
     * Returns a new web application exception for this status type.
     *
     * @return a new web application exception to throw
     *
     * @see #build()
     */
    public WebApplicationException throwable() {

        return new WebApplicationException(built());
    }


    /**
     * family.
     */
    private final Family family;


    /**
     * statusCode.
     */
    private final int statusCode;


    /**
     * reasonPhrase.
     */
    private final String reasonPhrase;


}
