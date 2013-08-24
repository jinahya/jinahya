/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.xml.bind.annotations.adapters;


import java.util.Collection;
import java.util.Iterator;
import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <C> collection type parameter
 * @param <E> element type parameter
 */
public abstract class StringCollectionAdapter<C extends Collection<E>, E>
    extends XmlAdapter<String, C> {


    public StringCollectionAdapter(final String regex) {

        super();

        if (regex == null) {
            throw new NullPointerException("regex");
        }

        if (regex.isEmpty()) {
            throw new IllegalArgumentException("empty regex");
        }

        this.delimiter = regex;
    }


    @Override
    public C unmarshal(final String value) throws Exception {

        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        final C bound = bound(value);

        for (final String token : value.split(delimiter)) {
            bound.add(element(token));
        }

        return bound;
    }


    /**
     * Returns a collection for given concatenated string
     *
     * @param value the concatenated string
     *
     * @return a collection
     */
    protected abstract C bound(String value);


    /**
     * Parses given token to desired element.
     *
     * @param token the token
     *
     * @return a desired element
     */
    protected abstract E element(String token);


    @Override
    public String marshal(final C bound) throws Exception {

        if (bound == null) {
            return null;
        }

        final Appendable appendable = appendable(bound);

        final Iterator<E> i = bound.iterator();
        if (i.hasNext()) {
            appendable.append(token(i.next()));
        }
        while (i.hasNext()) {
            appendable.append(delimiter).append(token(i.next()));
        }

        return string(appendable);
    }


    /**
     * Returns an appendable.
     *
     * @param bound bound
     *
     * @return an appendable.
     */
    protected Appendable appendable(final C bound) {

        return new StringBuilder();
    }


    /**
     * Prints given element.
     *
     * @param element the element
     *
     * @return printed value.
     */
    protected abstract String token(E element);


    /**
     * Concatenates given appendable.
     *
     * @param appendable the appendable
     *
     * @return concatenated string.
     */
    protected String string(final Appendable appendable) {

        return ((StringBuilder) appendable).toString();
    }


    /**
     * the delimiter.
     */
    protected final String delimiter;


}

