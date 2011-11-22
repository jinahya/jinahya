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


package com.googlecode.jinahya.sql;


import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlTransient;


/**
 * Abstract implementation of DatabaseCollection.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <A> accessible type parameter
 */
@XmlTransient
public abstract class AbstractDatabaseCollectable<A extends DatabaseAccessible<?>>
    implements DatabaseCollectable<A> {


    /**
     * Creates a new instance.
     *
     * @param accessibleType accessible type
     */
    public AbstractDatabaseCollectable(final Class<A> accessibleType) {
        super();

        if (accessibleType == null) {
            throw new NullPointerException("null accessibleType");
        }

        if (!DatabaseAccessible.class.isAssignableFrom(accessibleType)) {
            throw new IllegalArgumentException(
                accessibleType + " is not assignable to "
                + DatabaseAccessible.class);
        }

        this.accessibleType = accessibleType;
    }


    @Override
    public final Collection<A> getAccessibles() {

        if (accessibles == null) {
            accessibles = new ArrayList<A>();
        }

        return accessibles;
    }


    @Override
    public final Class<A> getAccessibleType() {
        return accessibleType;
    }


    private final Class<A> accessibleType;


    private Collection<A> accessibles;


}

