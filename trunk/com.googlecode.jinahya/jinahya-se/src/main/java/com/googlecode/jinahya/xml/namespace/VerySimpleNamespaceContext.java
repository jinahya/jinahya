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


import javax.xml.XMLConstants;


/**
 * Very simple implementation for NamespaceContext.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class VerySimpleNamespaceContext extends AbstractNamespaceContext {


    /**
     * Creates a new instance.
     *
     * @param prefix XML namespace prefix
     * @param namespaceURI XML namespace URI
     */
    public VerySimpleNamespaceContext(final String prefix,
                                      final String namespaceURI) {
        super();

        if (prefix == null) {
            throw new NullPointerException("null prefix");
        }

        if (isPredefinedPrefix(prefix)) {
            throw new IllegalArgumentException(
                "prefix(" + prefix + ") is one of predefined");
        }

        if (namespaceURI == null) {
            throw new NullPointerException("null namespaceURI");
        }

        if (isPredefinedNamespaceURI(namespaceURI)) {
            throw new IllegalArgumentException(
                "namespaceURI(" + namespaceURI + ") is one of predefined");
        }

        this.prefix = prefix;
        this.namespaceURI = namespaceURI;
    }


    @Override
    public String getNamespaceURI(final String prefix) {

        if (prefix == null) {
            throw new IllegalArgumentException("null prefix");
        }

        if (isPredefinedPrefix(prefix)) {
            return getPredefinedNamespaceURI(prefix);
        }

        if (this.prefix.equals(prefix)) {
            return namespaceURI;
        }

        return XMLConstants.NULL_NS_URI;
    }


    @Override
    public String getPrefix(final String namespaceURI) {

        if (namespaceURI == null) {
            throw new IllegalArgumentException("null namespaceURI");
        }

        if (isPredefinedNamespaceURI(namespaceURI)) {
            return getPredefinedPrefix(namespaceURI);
        }

        if (this.namespaceURI.equals(namespaceURI)) {
            return prefix;
        }

        return null;
    }


    /**
     * prefix.
     */
    private final String prefix;


    /**
     * namespaceURI.
     */
    private final String namespaceURI;


}

