/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
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


package com.googlecode.jinahya.xml;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Element.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ELElement extends ELNode {


    /**
     * Creates a new instance.
     *
     * @param namesapceURI namespace URI
     * @param localName local name
     */
    public ELElement(final String namesapceURI, final String localName) {
        super(namesapceURI, localName);
    }


    /**
     * Returns all namespace URIs.
     *
     * @return a set which contains all namespace URIs.
     */
    public Set getNamespaceURIs() {

        return getNamespaceURIs(new LinkedHashSet());
    }


    /**
     * Gets all namespace URIs.
     *
     * @param namespaceURIs the set to which all namesapce URIs are added
     * @return given <code>namespaceURIs</code>
     */
    public Set getNamespaceURIs(final Set namespaceURIs) {

        if (namespaceURIs == null) {
            throw new NullPointerException("null set");
        }

        namespaceURIs.add(namespaceURI);

        for (Iterator i = attributes.values().iterator(); i.hasNext();) {
            namespaceURIs.add(((ELAttribute) i.next()).namespaceURI);
        }

        for (Iterator i = elements.iterator(); i.hasNext();) {
            ((ELElement) i.next()).getNamespaceURIs(namespaceURIs);
        }

        return namespaceURIs;
    }


    //@Override
    public String toJSON() {

        final StringBuffer buffer = new StringBuffer();

        buffer.append("{");


        buffer.append(toJSONString("namespaceURI")).append(":").
            append(toJSONString(namespaceURI));

        buffer.append(",").append(toJSONString("localName")).append(":").
            append(toJSONString(localName));


        buffer.append(",").append(toJSONString("attributes")).append(":[");
        final Iterator i = attributes.values().iterator();
        if (i.hasNext()) {
            buffer.append(((ELAttribute) i.next()).toJSON());
        }
        while (i.hasNext()) {
            buffer.append(",").append(((ELAttribute) i.next()).toJSON());
        }
        buffer.append("]");


        buffer.append(",").append(toJSONString("text")).append(":").
            append(toJSONString(text));


        buffer.append(",").append(toJSONString("elements")).append(":[");
        final Iterator j = elements.iterator();
        if (j.hasNext()) {
            buffer.append(((ELElement) j.next()).toJSON());
        }
        while (j.hasNext()) {
            buffer.append(",").append(((ELElement) j.next()).toJSON());
        }
        buffer.append("]");


        buffer.append("}");

        return buffer.toString();
    }


    /** attributes. */
    protected final Map attributes = new HashMap(); // <String, ELAttribute>


    /** text. */
    protected String text = null;


    /** elements. */
    protected final List elements = new ArrayList(); // <Element>
}

