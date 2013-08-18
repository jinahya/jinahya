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


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.xml.bind.annotation.XmlTransient;


/**
 * General class for wrapper classes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <T> singular type parameter
 */
@XmlTransient
public abstract class Plural<T> implements Serializable {


    private static final long serialVersionUID = 7188972683803348989L;


    /**
     * Creates a new instance of {@code pluralType}.
     *
     * @param <P> plural type parameter.
     * @param <S> singular type parameter.
     * @param pluralType plural type
     *
     * @return a new instance of given {@code pluralType}
     */
    public static <P extends Plural<S>, S> P newInstance(
        final Class<P> pluralType) {

        return newInstance(pluralType, Collections.<S>emptyList());
    }


    /**
     * Creates a new instance of given {@code pluralType} contains specified
     * {@code singulars}.
     *
     * @param <P> plural type parameter
     * @param <S> singular type parameter
     * @param pluralType plural type
     * @param singulars a collection of singular elements to add; may be
     * {@code null}.
     *
     * @return a new instance of given <code>pluralType<code>
     */
    public static <P extends Plural<S>, S> P newInstance(
        final Class<P> pluralType, final Collection<? extends S> singulars) {

        if (pluralType == null) {
            throw new NullPointerException("pluralType");
        }

        try {
            final P instance = pluralType.newInstance();
            if (singulars != null && !singulars.isEmpty()) {
                instance.getSingulars().addAll(singulars);
            }
            return instance;
        } catch (InstantiationException ie) {
            throw new RuntimeException(ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        }
    }


    /**
     * Returns singular collection.
     *
     * @return singular collection
     */
    @XmlTransient
    protected Collection<T> getSingulars() {

        if (singulars == null) {
            singulars = new ArrayList<T>();
        }

        return singulars;
    }


    /**
     * singular collection.
     */
    private Collection<T> singulars;


}

