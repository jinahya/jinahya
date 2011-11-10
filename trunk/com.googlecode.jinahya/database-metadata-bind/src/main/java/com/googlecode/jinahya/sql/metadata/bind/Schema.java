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
 * Schema binding.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlType(propOrder = {"tableCatalog", "tableSchema", "tables"})
public class Schema extends MetadataAccessible {


    /**
     * Returns tableCatalog.
     *
     * @return tableCatalog
     */
    @XmlElement(required = true)
    public String getTableCatalog() {
        return getValue(String.class, "TABLE_CAT");
    }


    /**
     * Sets tableCatalog.
     *
     * @param tableCatalog tableCatalog
     */
    public void setTableCatalog(final String tableCatalog) {
        setValue(String.class, "TABLE_CAT", tableCatalog);
    }


    /**
     * Returns tableSchema.
     *
     * @return tableSchema
     */
    @XmlElement(required = true)
    public String getTableSchema() {
        return getValue(String.class, "TABLE_SCHEM");
    }


    /**
     * Sets the value of 'TABLE_SCHEM'.
     *
     * @param tableSchema tableSchema
     */
    public void setTableSchema(final String tableSchema) {
        setValue(String.class, "TABLE_SCHEM", tableSchema);
    }


    /**
     * Returns tables.
     *
     * @return tables
     */
    public Collection<Table> getTables() {

        if (tables == null) {
            tables = new ArrayList<Table>();
        }

        return tables;
    }


    /** tables. */
    @XmlElement(name = "table")
    @XmlElementWrapper(name = "tables", required = true, nillable = true)
    private Collection<Table> tables;


}

