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


/**
 * Abstract node.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class ELNode {


    static final String NULL_NS_URI = "";


    static final String XML_NS_PREFIX = "xml";


    static final String XML_NS_URI = "http://www.w3.org/XML/1998/namespace";


    static final String XMLNS_ATTRIBUTE_NS_URI =
        "http://www.w3.org/2000/xmlns/";


    /**
     * Converts given <code>string</code> to a JSON string.
     *
     * @param string string
     * @return a JSON string
     */
    static String toJSONString(final String string) {

        if (string == null) {
            return "null";
        }

        final StringBuffer buffer = new StringBuffer();

        buffer.append("\"");

        final int length = string.length();
        for (int i = 0; i < length;) {
            final int codePoint = string.codePointAt(i);
            final char[] chars = Character.toChars(codePoint);
            for (int j = 0; j < chars.length; j++) {
                buffer.append("\\u");
                final int high = chars[j] >> 8;
                if (high <= 0x0F) {
                    buffer.append('0');
                }
                buffer.append(Integer.toHexString(high).toUpperCase());
                final int low = chars[j] & 0xFF;
                if (low <= 0x0F) {
                    buffer.append('0');
                }
                buffer.append(Integer.toHexString(low).toUpperCase());
            }
            i += chars.length;
        }

        buffer.append("\"");

        return buffer.toString();
    }


    /**
     * James Clark notation.
     *
     * @param node node
     * @return string
     */
    static String jamesClark(final ELNode node) {

        if (node == null) {
            throw new NullPointerException("null node");
        }

        return jamesClark(node.getNamespaceURI(), node.getLocalName());
    }


    /**
     * 
     * @param namespaceURI
     * @param localName
     * @return 
     */
    static String jamesClark(final String namespaceURI,
                             final String localName) {

        if (namespaceURI == null) {
            throw new NullPointerException("null namespaceURI");
        }

        if (localName == null) {
            throw new NullPointerException("null localName");
        }

        if (localName.trim().isEmpty()) {
            throw new IllegalArgumentException("empty localName");
        }

        return "{" + namespaceURI + "}" + localName;
    }


    /**
     * Creates a new instance.
     *
     * @param namespaceURI namespace URI
     * @param localName local name
     */
    public ELNode(final String namespaceURI, final String localName) {
        super();

        if (namespaceURI == null) {
            throw new NullPointerException("null namespaceURI");
        }

        if (XMLNS_ATTRIBUTE_NS_URI.equals(namespaceURI)) {
            throw new IllegalArgumentException(
                "wrong namespaceURI: " + namespaceURI);
        }

        if (localName == null) {
            throw new NullPointerException("null localName");
        }

        if (localName.trim().isEmpty()) {
            throw new IllegalArgumentException("empty localName");
        }


        this.namespaceURI = namespaceURI;

        this.localName = localName;
    }


    public String getNamespaceURI() {
        return namespaceURI;
    }


    public String getLocalName() {
        return localName;
    }


    /**
     * Returns a JSON representation.
     *
     * @return a JSON string
     */
    public abstract String toJSON();


    /** name space URI. */
    private final String namespaceURI;


    /** local name. */
    private final String localName;
}

