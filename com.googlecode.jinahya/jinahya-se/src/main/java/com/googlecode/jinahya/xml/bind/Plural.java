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
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <S> singular type parameter
 */
@XmlTransient
public class Plural<S> {


    /**
     *
     * @param <P> plural type parameter
     * @param <S> singular type parameter
     * @param pluralType plural type
     * @param singulars singular collection
     *
     * @return a new plural
     *
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <P extends Plural<S>, S> P newInstance(
        final Class<P> pluralType, final Collection<? extends S> singulars)
        throws InstantiationException, IllegalAccessException {

        if (pluralType == null) {
            throw new NullPointerException("null pluralType");
        }

        if (singulars == null) {
            throw new NullPointerException("null singluars");
        }

        return newInstance(pluralType.newInstance(), singulars);
    }


    /**
     *
     * @param <P> plural type parameter
     * @param <S> singular type parameter
     * @param plural plural
     * @param singulars singular collection
     *
     * @return given
     * <code>plural</code>
     */
    protected static <P extends Plural<S>, S> P newInstance(
        final P plural, final Collection<? extends S> singulars) {

        if (plural == null) {
            throw new NullPointerException("null");
        }

        if (singulars == null) {
            throw new NullPointerException("null singluars");
        }

        plural.setSingulars(new ArrayList<S>(singulars.size()));
        plural.getSingulars().addAll(singulars);

        return plural;
    }


    /**
     *
     * @return singular collection
     */
    @XmlTransient
    protected final Collection<S> getSingulars() {
        if (singulars == null) {
            singulars = new ArrayList<S>();
        }
        return singulars;
    }


    /**
     *
     * @param singulars singular collection
     */
    protected final void setSingulars(final Collection<S> singulars) {
        this.singulars = singulars;
    }


    /**
     * singular collection.
     */
    @XmlTransient
    private Collection<S> singulars;


}

