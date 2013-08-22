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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class AbstractStatusType implements StatusType {


    private static class AnonymousStatusType extends AbstractStatusType {


        public AnonymousStatusType(final Family family, final int statusCode,
                                   final String reasonPhrase) {

            super(family, statusCode, reasonPhrase);
        }


    }


    public static StatusType newAnonymouseInstance(final int statusCode,
                                                   final String reasonPhrase) {

        if (statusCode >= 100) {
            return new AnonymousStatusType(Family.INFORMATIONAL, statusCode,
                                           reasonPhrase);
        }

        if (statusCode >= 200) {
            return new AnonymousStatusType(Family.SUCCESSFUL, statusCode,
                                           reasonPhrase);
        }

        if (statusCode >= 300) {
            return new AnonymousStatusType(Family.REDIRECTION, statusCode,
                                           reasonPhrase);
        }

        if (statusCode >= 400) {
            return new AnonymousStatusType(Family.CLIENT_ERROR, statusCode,
                                           reasonPhrase);
        }

        if (statusCode >= 500 && statusCode < 600) {
            return new AnonymousStatusType(Family.SERVER_ERROR, statusCode,
                                           reasonPhrase);
        }

        return new AnonymousStatusType(Family.OTHER, statusCode, reasonPhrase);
    }


    /**
     * Creates a new instance.
     *
     * @param family
     * @param statusCode
     * @param reasonPhrase
     */
    public AbstractStatusType(final Family family, final int statusCode,
                              final String reasonPhrase) {
        super();

        if (family == null) {
            throw new IllegalArgumentException("null family");
        }

        if (reasonPhrase == null) {
            throw new IllegalArgumentException("null reasonPhrase");
        }

        this.family = family;
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
    }


    /**
     * Creates a new instance.
     *
     * @param status the status to wrap
     * @param reasonPhrase reason phrase
     */
    protected AbstractStatusType(final Status status,
                                 final String reasonPhrase) {

        this(status.getFamily(), status.getStatusCode(), reasonPhrase);
    }


    @Override
    public Family getFamily() {

        return family;
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
     * Returns a {@link ResponseBuilder} for this type.
     *
     * @return a {@link ResponseBuilder} for this type.
     *
     * @see Response#status(javax.ws.rs.core.Response.StatusType)
     */
    public ResponseBuilder responseBuilder() {

        return Response.status(this);
    }


    /**
     * Returns a new {@code Response} for this type.
     *
     * @return a new {@code Response} to respond.
     *
     * @see #responseBuilder()
     */
    public Response build() {

        return responseBuilder().build();
    }


    /**
     * Returns a new {@link WebApplicationException} for this type.
     *
     * @return a new {@link WebApplicationException} to throw
     *
     * @see #build()
     */
    public WebApplicationException except() {

        return new WebApplicationException(build());
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
