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


package com.googlecode.jinahya.io;


import java.util.Collection;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <A> <code>BitAccessible</code> type parameter
 */
public class BitCollectableSupport<A extends BitAccessible>
    extends AbstractBitCollectable<A> {


    /** GENERATED. */
    private static final long serialVersionUID = 2715873935405106762L;


    /**
     * Creates a new instance.
     *
     * @param supported the object to be supported.
     */
    public BitCollectableSupport(final BitCollectable<A> supported) {
        super(supported.getAccessibleType());

        this.supported = supported;
    }


    @Override
    public Collection<A> getAccessibles() {
        return supported.getAccessibles();
    }


    /**
     * Returns supported.
     *
     * @return supported.
     */
    public BitCollectable<A> getSupported() {
        return supported;
    }


    /**
     * supported.
     */
    private final BitCollectable<A> supported;


}

