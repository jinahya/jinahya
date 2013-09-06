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


package com.googlecode.jinahya.xml.bind;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;


/**
 * General class for wrapper classes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <E> element type parameter
 */
@XmlTransient
public abstract class ListWrapper<E> {


    /**
     * Creates a new instance of given {@code listType} contains specified
     * {@code elements}.
     *
     * @param <T> plural type parameter
     * @param <E> singular type parameter
     * @param listType plural type
     * @param elements a collection of elements to add; may be {@code null}.
     *
     * @return a new instance of given {@code listType}.
     */
    public static <T extends ListWrapper<E>, E> T newInstance(
        final Class<T> listType, final Collection<? extends E> elements) {

        if (listType == null) {
            throw new NullPointerException("listType");
        }

        try {
            final T instance = listType.newInstance();
            if (elements != null) {
                instance.getList().addAll(elements);
            }
            return instance;
        } catch (InstantiationException ie) {
            throw new RuntimeException(ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        }
    }


    /**
     * Creates a new instance of {@code pluralType}.
     *
     * @param <T> list type parameter.
     * @param <E> element type parameter.
     * @param listType list type
     *
     * @return a new instance of given {@code listType}
     */
    public static <T extends ListWrapper<E>, E> T newInstance(
        final Class<T> listType) {

        return newInstance(listType, null);
    }


    /**
     * Returns singular collection.
     *
     * @return singular collection
     */
    @XmlTransient
    protected List<E> getList() {

        if (list == null) {
            list = new ArrayList<E>();
        }

        return list;
    }


    /**
     * singular collection.
     */
    @XmlTransient
    private List<E> list;


}
