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
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class TableType {


    @XmlEnum
    public enum Name {


        TABLE(),
        VIEW(),
        SYSTEM_TABLE("SYSTEM TABLE"),
        GLOBAL_TEMPORARY("GLOBAL TEMPORARY"),
        LOCAL_TEMPORARY("LOCAL TEMPORARY"),
        ALIAS(),
        SYNONYM();


        private Name() {
            this.value = name();
        }


        private Name(final String value) {
            this.value = value;
        }


        private final String value;


    }


    public String getCatalog() {
        return catalog;
    }


    public void setCatalog(final String catalog) {
        this.catalog = catalog;
    }


    public String getSchema() {
        return schema;
    }


    public void setSchema(final String schema) {
        this.schema = schema;
    }


    public Name getName() {
        return name;
    }


    public void setName(final Name name) {
        this.name = name;
    }


    @XmlAttribute()
    private String catalog;


    @XmlAttribute()
    private String schema;


    @XmlValue
    private Name name;


}

