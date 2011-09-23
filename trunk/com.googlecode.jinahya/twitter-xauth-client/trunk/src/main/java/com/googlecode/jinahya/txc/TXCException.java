/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
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
 * Exception of Twitter xAuth Client.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class XTweetException extends Exception {


    /** GENERATED. */
    private static final long serialVersionUID = 4570015701480288647L;


    /**
     * Creates a new instance.
     *
     * @param cause cause
     */
    public XTweetException(final Throwable cause) {
        super(cause);
    }


    /**
     * Creates a new instance.
     *
     * @param message message
     * @param cause cause
     */
    public XTweetException(final String message, final Throwable cause) {
        super(message, cause);
    }


    /**
     * Creates a new instance.
     *
     * @param message message
     */
    public XTweetException(final String message) {
        super(message);
    }
}
