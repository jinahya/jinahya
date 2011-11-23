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


import java.io.IOException;
import java.util.Collection;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <A> <code>BitAccessible</code> type parameter
 */
public abstract class BitCollectableSupport<A extends BitAccessible>
    implements BitAccessible {


    /** count length. */
    private static final int SIZE_BIT_LENGTH = 31;


    /**
     * Creates a new instance.
     *
     * @param collectable collectable.
     */
    public BitCollectableSupport(final BitCollectable<A> collectable) {
        super();

        if (collectable == null) {
            throw new NullPointerException("null collectable");
        }

        this.collectable = collectable;
    }


    @Override
    public void read(final BitInput input) throws IOException {

        final Class<A> accessibleType = collectable.getAccessibleType();
        final Collection<A> accessibles = collectable.getAccessibles();

        accessibles.clear();

        final int size = input.readUnsignedInt(SIZE_BIT_LENGTH); // ------- SIZE

        for (int i = 0; i < size; i++) {
            try {
                final A accessible = accessibleType.newInstance();
                accessible.read(input); // -------------------------- ACCESSIBLE
                accessibles.add(accessible);
            } catch (InstantiationException ie) {
                throw new RuntimeException(
                    "faild to create a new instance of " + accessibleType, ie);
            } catch (IllegalAccessException iae) {
                throw new RuntimeException(
                    "faild to create a new instance of " + accessibleType, iae);
            }
        }
    }


    @Override
    public void write(final BitOutput output) throws IOException {

        final Collection<A> accessibles = collectable.getAccessibles();

        output.writeUnsignedInt(SIZE_BIT_LENGTH, accessibles.size()); // -- SIZE

        for (final A accessible : accessibles) {
            accessible.write(output); // ---------------------------- ACCESSIBLE
        }
    }


    /**
     * collectable.
     */
    private final BitCollectable<A> collectable;


}

