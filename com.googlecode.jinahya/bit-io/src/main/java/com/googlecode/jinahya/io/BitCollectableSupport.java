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

import java.util.Iterator;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <A> <code>BitAccessible</code> type parameter
 */
public class BitCollectableSupport<A extends BitAccessible>
    extends AbstractCollectable<A>
    implements BitCollectable<A> {


    /** count length. */
    private static final int ACCESSIBLE_SIZE_LENGTH = 31;


    /**
     * Creates a new instance.
     *
     * @param supported the object to be supported.
     * @param accessibleType accessibleType
     */
    public BitCollectableSupport(final BitCollectable<A> supported,
                                 final Class<A> accessibleType) {
        super(accessibleType);

        if (supported == null) {
            throw new NullPointerException("null supported");
        }

        if (!BitAccessible.class.isAssignableFrom(accessibleType)) {
            throw new IllegalArgumentException(
                accessibleType + " is not assignable to "
                + BitAccessible.class);
        }

        this.supported = supported;
    }


    @Override
    public void read(final BitInput input) throws IOException {

        getAccessibles().clear();

        final int size = input.readUnsignedInt(ACCESSIBLE_SIZE_LENGTH);

        for (int i = 0; i < size; i++) {
            try {
                final A accessible = accessibleType.newInstance();
                accessible.read(input);
                getAccessibles().add(accessible);
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

        output.writeUnsignedInt(
            ACCESSIBLE_SIZE_LENGTH, getAccessibles().size());

        for (final Iterator<A> i = getAccessibles().iterator(); i.hasNext();) {
            i.next().write(output);
        }
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

