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
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class CyclicDependencyResolverException
    extends DependencyResolverException {


    /** GENERATED. */
    private static final long serialVersionUID = 8162863238681407043L;


    /**
     * Creates a new dependency for cyclic dependency between
     * <code>source</code> and <code>target</code>.
     *
     * @param source source
     * @param target target
     */
    public CyclicDependencyResolverException(final Object source,
                                             final Object target) {

        super("cyclic dependency: " + source + " -> " + target);
    }
}
