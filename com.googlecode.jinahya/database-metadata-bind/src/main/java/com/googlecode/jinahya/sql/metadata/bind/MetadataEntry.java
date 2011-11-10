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


package com.googlecode.jinahya.sql.metadata.bind;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlValue;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public final class MetadataEntry {


    /**
     * Creates a new instance.
     *
     * @param label label
     * @param value value
     * @return a new instance.
     */
    public static MetadataEntry newIntance(final String label,
                                           final Object value) {

        final MetadataEntry instance = new MetadataEntry();
        instance.setLabel(label);
        instance.setValue(value);

        return instance;
    }


    public String getLabel() {
        return label;
    }


    public void setLabel(final String label) {

        if (label == null) {
            throw new NullPointerException("null label");
        }

        if (label.trim().length() == 0) {
            throw new IllegalArgumentException("emtpy label");
        }

        this.label = label;
    }


    public Object getValue() {
        return value;
    }


    public void setValue(final Object value) {
        this.value = value;
    }


    @XmlAttribute(required = true)
    private String label;


    @XmlValue
    @XmlSchemaType(name = "anySimpleType")
    private Object value;


}

