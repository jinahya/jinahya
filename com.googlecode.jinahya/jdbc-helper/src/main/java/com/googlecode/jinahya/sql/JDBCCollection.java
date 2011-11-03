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


import java.util.Collection;


/**
 * Interface for collection type of accessible.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <E> element type parameter
 */
public interface JDBCCollection<E extends JDBCElement<?>> {


    /**
     * Return element type.
     *
     * @return element type
     */
    Class<E> getElementType();


    /**
     * Returns accessible collection.
     *
     * @return accessible collection
     */
    Collection<E> getElementCollection();


}

