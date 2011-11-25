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


import com.googlecode.jinahya.util.AbstractCollectable;

import java.io.IOException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <A> <code>BitAccessible</code> type parameter
 */
public abstract class AbstractBitCollectable<A extends BitAccessible>
    extends AbstractCollectable<A>
    implements BitCollectable<A> {


    /** count length. */
    private static final int COUNT_LENGTH = 31;


    /**
     * Creates a new instance.
     *
     * @param accessibleType accessible type
     */
    public AbstractBitCollectable(final Class<A> accessibleType) {
        super(accessibleType);
    }


    @Override
    public void read(final BitInput input) throws IOException {

        new BitCollectableSupport<A>(this, accessibleType).read(input);
    }


    @Override
    public void write(final BitOutput output) throws IOException {
        new BitCollectableSupport<A>(this, accessibleType).write(output);
    }


}

