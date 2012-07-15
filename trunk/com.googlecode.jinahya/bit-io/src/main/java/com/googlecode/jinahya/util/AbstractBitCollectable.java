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


package com.googlecode.jinahya.util;


import com.googlecode.jinahya.io.BitInput;
import com.googlecode.jinahya.io.BitOutput;
import java.io.IOException;
import java.util.Collection;


/**
 * Abstract BitCollectable.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <A>
 * <code>BitAccessible</code> type parameter
 */
public abstract class AbstractBitCollectable<A extends BitAccessible>
    extends AbstractCollectable<A>
    implements BitCollectable<A> {


    /**
     * GENERATED.
     */
    private static final long serialVersionUID = 6344599398970732643L;


    /**
     * count length.
     */
    private static final int ACCESSIBLE_SIZE_LENGTH = 31;


    /**
     * Creates a new instance.
     *
     * @param accessibleType accessible type
     */
    public AbstractBitCollectable(final Class<A> accessibleType) {
        super(accessibleType);
    }


    /**
     * Reads accessibles from given
     * <code>input</code>. First, a 31-bit unsigned integer is read for
     * accessible count. And reads all accessibles.
     *
     * @param input input
     *
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void read(final BitInput input) throws IOException {

        final Collection<A> accessibles = getAccessibles();

        accessibles.clear();

        final int size = input.readUnsignedInt(ACCESSIBLE_SIZE_LENGTH);

        for (int i = 0; i < size; i++) {
            try {
                final A accessible = accessibleType.newInstance();
                accessible.read(input);
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


    /**
     * Writes accessibles to given
     * <code>output</code>. First, a 31-bit unsigned integer is written for
     * accessible count. And writes all accessibles.
     *
     * @param output output
     *
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void write(final BitOutput output) throws IOException {

        final Collection<A> accessibles = getAccessibles();

        output.writeUnsignedInt(ACCESSIBLE_SIZE_LENGTH, accessibles.size());

        for (A accessible : accessibles) {
            accessible.write(output);
        }
    }


}

