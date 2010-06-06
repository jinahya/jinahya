/*
 *  Copyright 2010 Jin Kwon.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package jinahya.xml.namespace;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class NamespaceContextImpl implements NamespaceContext {


    @Override
    public String getNamespaceURI(final String prefix) {

        if (prefix == null) {
            throw new IllegalArgumentException("prefix");
        }

        if (XMLConstants.XML_NS_PREFIX.equals(prefix)) {
            return XMLConstants.XML_NS_URI;
        }

        if (XMLConstants.XMLNS_ATTRIBUTE.equals(prefix)) {
            return XMLConstants.XMLNS_ATTRIBUTE_NS_URI;
        }

        synchronized (namespaces) {
            for (Entry<String, List<String>> entry : namespaces.entrySet()) {
                if (entry.getValue().contains(prefix)) {
                    return entry.getKey();
                }
            }
        }

        if (XMLConstants.DEFAULT_NS_PREFIX.equals(prefix)) {
            return XMLConstants.NULL_NS_URI;
        }

        return XMLConstants.NULL_NS_URI;
    }


    @Override
    public String getPrefix(final String namespaceURI) {

        if (namespaceURI == null) {
            throw new IllegalArgumentException("namespaceURI");
        }

        if (XMLConstants.XML_NS_URI.equals(namespaceURI)) {
            return XMLConstants.XML_NS_PREFIX;
        }

        if (XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(namespaceURI)) {
            return XMLConstants.XMLNS_ATTRIBUTE;
        }

        synchronized (namespaces) {
            final List<String> prefixes = namespaces.get(namespaceURI);
            if (prefixes == null) {
                return null;
            }
            return prefixes.get(prefixes.size() - 1);
        }
    }


    @Override
    public Iterator<String> getPrefixes(final String namespaceURI) {

        if (namespaceURI == null) {
            throw new IllegalArgumentException("namespaceURI");
        }

        final List<String> prefixes = new ArrayList<String>();

        if (XMLConstants.XML_NS_URI.equals(namespaceURI)) {
            prefixes.add(XMLConstants.XML_NS_PREFIX);
        } else if (XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(namespaceURI)) {
            prefixes.add(XMLConstants.XMLNS_ATTRIBUTE);
        } else {
            synchronized (namespaces) {
                List<String> bounds = namespaces.get(namespaceURI);
                if (bounds != null) {
                    prefixes.addAll(bounds);
                }
            }
        }

        return Collections.unmodifiableList(prefixes).iterator();
    }


    /**
     *
     * @param namespaceURI namespace uri
     * @param prefix namespece prefix
     */
    public void bind(final String namespaceURI, final String prefix) {

        if (namespaceURI == null) {
            throw new IllegalArgumentException("'namespaceURI' is null");
        }

        if (prefix == null) {
            throw new IllegalArgumentException("'prefix' is null");
        }

        synchronized (namespaces) {
            List<String> prefixes = namespaces.get(namespaceURI);
            if (prefixes == null) {
                prefixes = new ArrayList<String>();
                namespaces.put(namespaceURI, prefixes);
            }
            if (!prefixes.contains(prefix)) {
                prefixes.add(prefix);
            }
        }
    }


    /**
     * The default Namespace URI in the current scope or
     * XMLConstants.NULL_NS_URI("") when there is no default Namespace URI in
     * the current scope
     *
     * @return the default Namespace URI
     */
    public String getDefaultNamespaceURI() {
        synchronized (namespaces) {
            for (Entry<String, List<String>> entry : namespaces.entrySet()) {
                if (entry.getValue().contains(XMLConstants.DEFAULT_NS_PREFIX)) {
                    return entry.getKey();
                }
            }
            return XMLConstants.NULL_NS_URI;
        }
    }


    /**
     *
     * @param defaultNamespaceURI
     */
    public void setDefaultNamespaceURI(final String defaultNamespaceURI) {

        if (defaultNamespaceURI == null) {
            throw new IllegalArgumentException("'defaultNamespaceURI' is null");
        }

        synchronized (namespaces) {
            for (List<String> value : namespaces.values()) {
                if (value.remove(XMLConstants.DEFAULT_NS_PREFIX)) {
                    break;
                }
            }
            bind(defaultNamespaceURI, XMLConstants.DEFAULT_NS_PREFIX);
        }
    }


    /**
     *
     * @param namespaceURI
     * @param prefix
     * @return
     */
    public boolean unbind(final String namespaceURI, final String prefix) {

        if (namespaceURI == null) {
            throw new IllegalArgumentException("'namespaceURI' is null");
        }

        if (prefix == null) {
            throw new IllegalArgumentException("'prefix' is null");
        }

        synchronized (namespaces) {
            if (!namespaces.containsKey(namespaceURI)) {
                return false;
            }
            final List<String> prefixes = namespaces.get(namespaceURI);
            final boolean result = prefixes.remove(prefix);
            if (result && prefixes.isEmpty()) {
                namespaces.remove(namespaceURI);
            }
            return result;
        }
    }


    /**
     *
     * @param namespaceURI
     * @return
     */
    public boolean unbindAll(final String namespaceURI) {

        if (namespaceURI == null) {
            throw new IllegalArgumentException("namespaceURI");
        }

        synchronized (namespaces) {
            return (namespaces.remove(namespaceURI) != null);
        }
    }


    // <URI, List<prefix>>
    private final Map<String, List<String>> namespaces =
        Collections.synchronizedMap(new HashMap<String, List<String>>());
}
