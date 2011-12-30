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


package com.googlecode.jinahya.sql.metadata;


import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <S> EntrySet type parameter.
 */
@XmlTransient
abstract class EntrySets<S extends EntrySet> {


    /**
     * Creates a new instance.
     *
     * @param entrySetType EntrySet type
     */
    public EntrySets(final Class<S> entrySetType) {
        super();

        if (entrySetType == null) {
            throw new NullPointerException("null entrySetType");
        }

        if (!EntrySet.class.isAssignableFrom(entrySetType)) {
            throw new IllegalArgumentException(
                entrySetType + " is not assignable to " + EntrySet.class);
        }

        this.entrySetType = entrySetType;
    }


    /**
     * Returns the type of EntrySet.
     *
     * @return entrySetType
     */
    public final Class<S> getEntrySetType() {
        return entrySetType;
    }


    /**
     * Returns entries.
     *
     * @return entries
     */
    protected final Collection<S> getEntrySets() {

        if (entrySets == null) {
            entrySets = new ArrayList<S>();
        }

        return entrySets;
    }


    /**
     * EntrySet type.
     */
    private final Class<S> entrySetType;


    /**
     * EntrySet collection.
     */
    private Collection<S> entrySets;


}

