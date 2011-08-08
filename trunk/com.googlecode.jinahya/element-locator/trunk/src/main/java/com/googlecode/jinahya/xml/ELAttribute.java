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
 * Attribute.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ELAttribute extends ELNode {


    /**
     * Creates a new instance.
     *
     * @param namespaceURI name space URI
     * @param localName local name
     * @param value value
     */
    public ELAttribute(final String namespaceURI, final String localName,
                       final String value) {

        super(namespaceURI, localName);

        if (value == null) {
            throw new NullPointerException("null value");
        }

        this.value = value;
    }


    //@Override
    public String toJSON() {

        return "{"
               + toJSONString("namespaceURI") + ":"
               + toJSONString(getNamespaceURI())
               + "," + toJSONString("localName") + ":"
               + toJSONString(getLocalName())
               + "," + toJSONString("value") + ":" + toJSONString(value)
               + "}";
    }


    public String getValue() {
        return value;
    }


    /** value. */
    private final String value;
}

