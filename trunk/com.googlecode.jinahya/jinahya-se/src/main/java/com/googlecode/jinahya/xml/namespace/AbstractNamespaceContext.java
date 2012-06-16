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


import java.util.Collections;
import java.util.Iterator;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class AbstractNamespaceContext implements NamespaceContext {


    /**
     *
     * @param prefix
     *
     * @return
     */
    protected String getPredefinedNamespaceURI(final String prefix) {

        if (prefix == null) {
            throw new IllegalArgumentException("null prefix");
        }

        if (XMLConstants.DEFAULT_NS_PREFIX.equals(prefix)) {
            return XMLConstants.NULL_NS_URI;
        }

        if (XMLConstants.XML_NS_PREFIX.equals(prefix)) {
            return XMLConstants.XMLNS_ATTRIBUTE_NS_URI;
        }

        if (XMLConstants.XMLNS_ATTRIBUTE.equals(prefix)) {
            return XMLConstants.XMLNS_ATTRIBUTE_NS_URI;
        }

        return XMLConstants.NULL_NS_URI;
    }


    /**
     *
     * @param namespaceURI
     *
     * @return
     */
    protected String getPredefinedPrefix(final String namespaceURI) {

        if (XMLConstants.NULL_NS_URI.equals(namespaceURI)) {
            return XMLConstants.DEFAULT_NS_PREFIX;
        }

        if (XMLConstants.XML_NS_URI.equals(namespaceURI)) {
            return XMLConstants.XML_NS_PREFIX;
        }

        if (XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(namespaceURI)) {
            return XMLConstants.XMLNS_ATTRIBUTE;
        }

        return null;
    }


    @Override
    @SuppressWarnings("rawtypes")
    public Iterator getPrefixes(final String namespaceURI) {

        if (namespaceURI == null) {
            throw new IllegalArgumentException("null namespaceURI");
        }

        final String prefix = getPrefix(namespaceURI);

        if (prefix == null) {
            return Collections.emptyIterator();
        }

        return Collections.singleton(prefix).iterator();
    }


}

