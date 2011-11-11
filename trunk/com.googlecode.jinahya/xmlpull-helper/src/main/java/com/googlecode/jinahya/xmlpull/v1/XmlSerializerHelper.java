/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.xmlpull.v1;


import java.io.IOException;
import org.xmlpull.v1.XmlSerializer;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class XmlSerializerHelper {


    /**
     * 
     * @param serializer
     * @throws IOException 
     */
    public static void nilAttribute(final XmlSerializer serializer)
        throws IOException {

        serializer.attribute(
            XmlPullConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, "nil", "true");
    }


    /**
     * 
     * @param serializer
     * @param namespaceURI
     * @param localName
     * @param text
     * @throws IOException 
     */
    public static void nillableTag(final XmlSerializer serializer,
                                   final String namespaceURI,
                                   final String localName, final String text)
        throws IOException {

        serializer.startTag(namespaceURI, localName);

        if (text == null) {
            nilAttribute(serializer);
        } else {
            serializer.text(text);
        }

        serializer.endTag(namespaceURI, localName);

    }


    /**
     * Creates a new instance.
     */
    private XmlSerializerHelper() {
        super();
    }


}

