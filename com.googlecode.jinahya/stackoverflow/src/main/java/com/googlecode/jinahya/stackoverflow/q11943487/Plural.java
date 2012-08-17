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


package com.googlecode.jinahya.stackoverflow.q11943487;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Plural<S> {


    public static <P extends Plural<S>, S> P newInstance(
        final Class<P> pluralType) {

        return newInstance(pluralType, Collections.<S>emptyList());
    }


    public static <P extends Plural<S>, S> P newInstance(
        final Class<P> pluralType, final Collection<? extends S> singulars) {

        try {
            final P plural = pluralType.newInstance();
            plural.getSingulars().addAll(singulars);
            return plural;
        } catch (InstantiationException ie) {
            throw new RuntimeException(ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        }
    }


    protected Collection<S> getSingulars() {
        if (singulars == null) {
            singulars = new ArrayList<S>();
        }
        return singulars;
    }


    private Collection<S> singulars;


}

