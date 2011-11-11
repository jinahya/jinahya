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


import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Catalog binding.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlType(propOrder = {"tableCatalog", "schemas"})
public class Catalog extends MetadataAccessible {


    /**
     * Returns the value of 'TABLE_CAT'.
     *
     * @return tableCatalog
     */
    @XmlElement(required = true)
    public String getTableCatalog() {
        return getValue(String.class, "TABLE_CAT");
    }


    /**
     * Sets the value of 'TABLE_CAT'.
     *
     * @param tableCatalog tableCatalog
     */
    public void setTableCatalog(final String tableCatalog) {
        setValue(String.class, "TABLE_CAT", tableCatalog);
    }


    /**
     * Returns schemas.
     *
     * @return schemas
     */
    public Collection<Schema> getSchemas() {

        if (schemas == null) {
            schemas = new ArrayList<Schema>();
        }

        return schemas;
    }


    /**
     * schemas.
     */
    @XmlElement(name = "schema")
    @XmlElementWrapper(name = "schemas", required = true, nillable = true)
    private Collection<Schema> schemas;


}
