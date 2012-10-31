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


import javax.persistence.EntityManager;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class EntityFacadeSupport {


    /**
     * Finds an entity instance.
     *
     * @param <E> entity instance type parameter
     * @param <I> primary key type parameter
     * @param entityManager entity manager
     * @param entityClass entity class
     * @param primaryKey primary key
     *
     * @return found entity instance or <code>null</code> if not found
     */
    public static <E, I> E find(final EntityManager entityManager,
                                final Class<E> entityClass,
                                final I primaryKey) {

        if (entityManager == null) {
            throw new IllegalArgumentException("null entityManager");
        }

        if (entityClass == null) {
            throw new IllegalArgumentException("null entityClass");
        }

        if (primaryKey == null) {
            throw new IllegalArgumentException("null primaryKey");
        }

        return entityManager.find(entityClass, primaryKey);
    }


    /**
     * Persists an entity instance.
     *
     * @param <E> entity type parameter
     * @param entityManager entity manager
     * @param entityClass entity class
     * @param entityInstance entity instance
     */
    public static <E> void persist(final EntityManager entityManager,
                                   final Class<E> entityClass,
                                   final E entityInstance) {

        if (entityManager == null) {
            throw new IllegalArgumentException("null entityManager");
        }

        if (entityClass == null) {
            throw new IllegalArgumentException("null entityClass");
        }

        if (entityInstance == null) {
            throw new IllegalArgumentException("null entity instance");
        }

        entityManager.persist(entityInstance);
    }


    /**
     * Merges an entity instance.
     *
     * @param <E> entity type parameter
     * @param entityManager entity manager
     * @param entityClass entity class
     * @param entityInstance entity instance
     *
     * @return merged entity instance or <code>null</code> if removed
     */
    public static <E> E merge(final EntityManager entityManager,
                              final Class<E> entityClass,
                              final E entityInstance) {

        if (entityManager == null) {
            throw new IllegalArgumentException("null entityManager");
        }

        if (entityClass == null) {
            throw new IllegalArgumentException("null entityClass");
        }

        if (entityInstance == null) {
            throw new IllegalArgumentException("null entity instance");
        }

        try {
            return entityManager.merge(entityInstance);
        } catch (IllegalArgumentException iae) {
        }

        return null;
    }


    /**
     * Removes an entity instance.
     *
     * @param <E> entity type parameter
     * @param <I> primary key type parameter
     * @param entityManager entity manager
     * @param entityClass entity class
     * @param primaryKey primary key
     *
     * @return removed entity instance or <code>null</code> if not found
     */
    public static <E, I> E remove(final EntityManager entityManager,
                                  final Class<E> entityClass,
                                  final I primaryKey) {

        if (entityManager == null) {
            throw new IllegalArgumentException("null entityManager");
        }

        if (entityClass == null) {
            throw new IllegalArgumentException("null entityClass");
        }

        if (primaryKey == null) {
            throw new IllegalArgumentException("null primaryKey");
        }

        final E entity = find(entityManager, entityClass, primaryKey);

        if (entity != null) {
            entityManager.remove(entity);
        }

        return entity;
    }


    /**
     * Creates a new instance.
     */
    protected EntityFacadeSupport() {
        super();
    }


}

