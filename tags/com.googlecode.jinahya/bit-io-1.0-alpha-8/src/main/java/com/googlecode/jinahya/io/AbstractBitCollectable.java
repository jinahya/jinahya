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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class AbstractBitCollectable<T extends BitAccessible>
    implements BitCollectable<T> {


    /** count length. */
    private static final int COUNT_LENGTH = 31;


    public AbstractBitCollectable(final Class<T> accessibleType) {
        super();

        if (accessibleType == null) {
            throw new NullPointerException("null accessibleType");
        }

        if (!BitAccessible.class.isAssignableFrom(accessibleType)) {
            throw new IllegalArgumentException(
                accessibleType + " is not assignable to "
                + BitAccessible.class);
        }

        this.accessibleType = accessibleType;
    }


    @Override
    public void read(BitInput input) throws IOException {

        getAccessibles().clear();
        
        final int count = input.readUnsignedInt(COUNT_LENGTH);

        for (int i = 0; i < count; i++) {
            final T accessible = createAccessible();
            accessible.read(input);
            getAccessibles().add(accessible);
        }
    }


    @Override
    public void write(BitOutput output) throws IOException {

        output.writeUnsignedInt(COUNT_LENGTH, getAccessibles().size());

        for (final Iterator<T> i = getAccessibles().iterator(); i.hasNext();) {
            i.next().write(output);
        }
    }


    protected T createAccessible() {
        try {
            return accessibleType.newInstance();
        } catch (InstantiationException ie) {
            throw new RuntimeException(
                "faild to create a new instance of " + accessibleType, ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(
                "faild to create a new instance of " + accessibleType, iae);
        }
    }


    @Override
    public Collection<T> getAccessibles() {

        if (accessibles == null) {
            accessibles = new ArrayList<T>();
        }

        return accessibles;
    }


    /**
     * Returns accessibleType.
     *
     * @return accessibleType.
     */
    public final Class<T> getAccessibleType() {
        return accessibleType;
    }


    /**
     * Accessible type.
     */
    protected final Class<T> accessibleType;


    /**
     * Accessibles.
     */
    private Collection<T> accessibles;


}

