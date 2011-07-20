/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
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


package com.googlecode.jinahya.rfc3986;


/**
 * Percent Encoding/Decoding Exception.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class PercentCodecException extends Exception {


    /** GENERATED. */
    private static final long serialVersionUID = -4852755086050007590L;


    /**
     * Creates a new instance.
     *
     * @param message message
     */
    public PercentCodecException(final String message) {
        super(message);
    }


    /**
     * Creates a new instance.
     *
     * @param cause cause
     */
    public PercentCodecException(final Throwable cause) {
        super(cause);
    }


    /**
     * Creates a new instance.
     *
     * @param message message
     * @param cause cause
     */
    public PercentCodecException(final String message, final Throwable cause) {
        super(message, cause);
    }
}

