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


package com.googlecode.jinahya.util;


/**
 * General exception for dependency resolver.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DependencyResolverException extends Exception {


    /** GENERATED. */
    private static final long serialVersionUID = -4398231764430765498L;


    /**
     * Creates a new instance with given <code>cause</code>.
     *
     * @param cause cause
     */
    public DependencyResolverException(final Throwable cause) {
        super(cause);
    }


    /**
     * Creates a new instance with given <code>message</code> and
     * <code>cause</code>.
     *
     * @param message message
     * @param cause cause
     */
    public DependencyResolverException(final String message,
                                       final Throwable cause) {
        super(message, cause);
    }


    /**
     * Creates a new instance with given <code>message</code>.
     *
     * @param message message
     */
    public DependencyResolverException(final String message) {
        super(message);
    }
}

