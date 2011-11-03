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
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <T> accessible type
 */
@XmlTransient
public class AbstractJDBCCollection<T extends JDBCElement<?>>
    implements JDBCCollection<T> {


    /**
     * 
     * @param elementType 
     */
    public AbstractJDBCCollection(final Class<T> elementType) {
        super();

        this.elementType = elementType;
    }


    @Override
    public final Collection<T> getElementCollection() {

        if (accessibleCollection == null) {
            accessibleCollection = new ArrayList<T>();
        }

        return accessibleCollection;
    }


    @Override
    public final Class<T> getElementType() {
        return elementType;
    }


    private final Class<T> elementType;


    @XmlTransient
    private Collection<T> accessibleCollection;


}

