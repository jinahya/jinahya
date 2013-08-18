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


package com.googlecode.jinahya.xml.namespace;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.XMLConstants;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class SimpleNamespaceContext extends AbstractNamespaceContext {


    /**
     * Creates a new instance.
     */
    public SimpleNamespaceContext() {
        super();

        list = new ArrayList<String>();
    }


    @Override
    public String getNamespaceURI(final String prefix) {

        if (prefix == null) {
            throw new IllegalArgumentException("null prefix");
        }

        if (isReservedPrefix(prefix)) {
            return getReservedNamespaceURI(prefix);
        }

        for (int i = list.size() - 1; i >= 1; i -= 2) {
            if (prefix.equals(list.get(i - 1))) {
                return list.get(i);
            }
        }

        return XMLConstants.NULL_NS_URI;
    }


    @Override
    public String getPrefix(final String namespaceURI) {

        if (namespaceURI == null) {
            throw new IllegalArgumentException("null namespaceURI");
        }

        if (isReservedNamespaceURI(namespaceURI)) {
            return getReservedPrefix(namespaceURI);
        }

        for (int i = list.size() - 1; i >= 1; i -= 2) {
            if (namespaceURI.equals(list.get(i))) {
                return list.get(i - 1);
            }
        }

        return null;
    }


    @Override
    @SuppressWarnings("rawtypes")
    public Iterator getPrefixes(final String namespaceURI) {

        if (namespaceURI == null) {
            throw new IllegalArgumentException("null namespaceURI");
        }

        final List<String> prefixes = new ArrayList<String>();

        for (int i = list.size() - 1; i >= 1; i -= 2) {
            if (namespaceURI.equals(list.get(i))) {
                prefixes.add(list.get(i - 1));
            }
        }

        return prefixes.iterator();
    }


    /**
     * Adds a new namespace. \
     *
     * @param prefix XML namespace prefix
     * @param namespaceURI XML namespace URI
     */
    public void addNamespace(final String prefix, final String namespaceURI) {

        if (prefix == null) {
            throw new NullPointerException("null prefix");
        }

        if (isReservedPrefix(prefix)) {
            throw new IllegalArgumentException("predefined prefix: " + prefix);
        }

        if (namespaceURI == null) {
            throw new NullPointerException("null namespaceURI");
        }

        if (isReservedNamespaceURI(namespaceURI)) {
            throw new IllegalArgumentException(
                "predefined namespaceURI: " + namespaceURI);
        }

        list.add(prefix);
        list.add(namespaceURI);
    }


    /**
     * list.
     */
    private final List<String> list;


}

