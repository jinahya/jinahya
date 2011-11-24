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


import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlTransient
public abstract class AbstractXmlTag implements XmlTag {


    /** GENERATED. */
    private static final long serialVersionUID = -7803837515706819615L;


    /**
     * Creates a new instance.
     *
     * @param namespaceURI XML namespace URI
     * @param localName XML local name
     */
    public AbstractXmlTag(final String namespaceURI, final String localName) {

        super();

        if (localName == null) {
            throw new NullPointerException("null localName");
        }
        if (localName.trim().length() == 0) {
            throw new IllegalArgumentException("emtpy localName");
        }

        this.namespaceURI = namespaceURI;
        this.localName = localName;
    }


    @Override
    public final String getNamespaceURI() {
        return namespaceURI;
    }


    @Override
    public final String getLocalName() {
        return localName;
    }


    /**
     * XML namespace URI.
     */
    protected final String namespaceURI;


    /** XML local name. */
    protected final String localName;


}

