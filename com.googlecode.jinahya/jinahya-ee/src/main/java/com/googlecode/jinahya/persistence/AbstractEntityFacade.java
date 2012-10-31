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
 * Abstract implementation.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <E> entity instance type parameter
 * @param <I> primary key type parameter
 */
public abstract class AbstractEntityFacade<E, I> implements EntityFacade<E, I> {


    /**
     * Creates a new instance.
     *
     * @param entityClass entity class
     */
    public AbstractEntityFacade(final Class<E> entityClass) {
        super();

        if (entityClass == null) {
            throw new IllegalArgumentException("null entityClass");
        }

        this.entityClass = entityClass;
    }


    @Override
    public E find(final I primaryKey) {
        return EntityFacadeSupport.find(
            getEntityManager(), entityClass, primaryKey);
    }


    @Override
    public E persist(final E entityInstance) {
        return EntityFacadeSupport.find(
            getEntityManager(), entityClass, entityInstance);
    }


    @Override
    public E merge(final E entityInstance) {
        return EntityFacadeSupport.merge(
            getEntityManager(), entityClass, entityInstance);
    }


    @Override
    public E remove(final I primaryKey) {
        return EntityFacadeSupport.remove(
            getEntityManager(), entityClass, primaryKey);
    }


    /**
     * Returns entity manager.
     *
     * @return entity manager
     */
    protected abstract EntityManager getEntityManager();


    /**
     * entity class.
     */
    protected final Class<E> entityClass;


}

