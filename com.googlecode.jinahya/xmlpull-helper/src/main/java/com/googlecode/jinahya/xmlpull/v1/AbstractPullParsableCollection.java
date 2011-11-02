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


package com.googlecode.jinahya.xmlpull.v1;


import java.util.ArrayList;
import java.util.Collection;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class AbstractPullParsableCollection<T extends PullParsable>
    implements PullParsableCollection<T> {


    /**
     * Creates a new instance.
     *
     * @param parsableType parsableType
     */
    public AbstractPullParsableCollection(final Class<T> parsableType) {
        super();

        if (parsableType == null) {
            throw new NullPointerException("null parsableType");
        }

        if (PullParsable.class.isAssignableFrom(parsableType)) {
            throw new IllegalArgumentException(
                parsableType + " is not assignable to " + PullParsable.class);
        }

        this.parsableType = parsableType;
    }


    @Override
    public final Class<T> getParsableType() {
        return parsableType;
    }


    @Override
    public final Collection<T> getParsableCollection() {

        if (parsableCollection == null) {
            parsableCollection = new ArrayList<T>();
        }

        return parsableCollection;
    }


    /** parsable type. */
    private final Class<T> parsableType;


    /** parsable collection. */
    private Collection<T> parsableCollection;


}

