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


package com.googlecode.jinahya.el;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;


/**
 * Element.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ELElement extends ELNode {


    /**
     * Creates a new instance.
     *
     * @param namesapceURI name space URI
     * @param localName local name
     */
    public ELElement(final String namesapceURI, final String localName) {
        super(namesapceURI, localName);
    }


    /**
     * Returns all name space URIs.
     *
     * @return a set of name space URIs.
     */
    public Set<String> getNamespaceURIs() {

        final Set<String> set = new HashSet<String>();

        getNamespaceURIs(set);

        return set;
    }


    /**
     * 
     * @param set 
     */
    private void getNamespaceURIs(final Set<String> set) {

        if (set == null) {
            throw new NullPointerException("null set");
        }

        set.add(namespaceURI);

        for (Entry<String, ELAttribute> entry : attributes.entrySet()) {
            set.add(entry.getValue().namespaceURI);
        }

        for (ELElement element : elements) {
            element.getNamespaceURIs(set);
        }
    }


    @Override
    public String toJSON() {

        final StringBuilder builder = new StringBuilder();

        builder.append("{");


        builder.append(toJSONString("namespaceURI")).append(":").
            append(toJSONString(namespaceURI));

        builder.append(",").append(toJSONString("localName")).append(":").
            append(toJSONString(localName));


        builder.append(",").append(toJSONString("attributes")).append(":[");
        final Iterator<Entry<String, ELAttribute>> i =
            attributes.entrySet().iterator();
        if (i.hasNext()) {
            builder.append(i.next().getValue().toJSON());
        }
        while (i.hasNext()) {
            builder.append(",").append(i.next().getValue().toJSON());
        }
        builder.append("]");


        builder.append(",").append(toJSONString("text")).append(":").
            append(toJSONString(text));


        builder.append(",").append(toJSONString("elements")).append(":[");
        final Iterator<ELElement> j = elements.iterator();
        if (j.hasNext()) {
            builder.append(j.next().toJSON());
        }
        while (j.hasNext()) {
            builder.append(",").append(j.next().toJSON());
        }
        builder.append("]");


        builder.append("}");

        return builder.toString();
    }


    /** attributes. */
    final Map<String, ELAttribute> attributes =
        new TreeMap<String, ELAttribute>();


    /** text. */
    String text = null;


    /** elements. */
    final List<ELElement> elements = new ArrayList<ELElement>();
}

