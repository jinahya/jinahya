/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


package com.googlecode.jinahya.nica.client;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class NicaClientException extends Exception {


    /**
     * generated.
     */
    private static final long serialVersionUID = -5097801054690265673L;


    /**
     * Creates a new instance with the specified detail message.
     *
     * @param message the detail message.
     */
    public NicaClientException(final String message) {

        super(message);
    }


    /**
     * Creates a new exception with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause the cause.
     */
    public NicaClientException(final String message, final Throwable cause) {

        super(message, cause);
    }


    /**
     * Creates a new instance with specified cause.
     *
     * @param cause the cause
     */
    public NicaClientException(final Throwable cause) {

        super(cause);
    }


}
