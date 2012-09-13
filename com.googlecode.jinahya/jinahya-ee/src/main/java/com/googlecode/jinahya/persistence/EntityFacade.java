/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.persistence;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <E> entity type parameter
 * @param <I> primary key type parameter
 */
public interface EntityFacade<E, I> {


    /**
     *
     * @param primaryKey primary key
     *
     * @return found entity instance or
     * <code>null</code> if not found
     */
    E find(I primaryKey);


    /**
     *
     * @param entity entity instance to persist
     */
    void persist(E entity);


    /**
     *
     * @param entity entity instance to merge
     *
     * @return the merged instance
     */
    E merge(E entity);


    /**
     *
     */
    /**
     *
     * @param primaryKey primary key
     *
     * @return removed entity instance or
     * <code>null</code> if not found
     */
    E remove(I primaryKey);


}

