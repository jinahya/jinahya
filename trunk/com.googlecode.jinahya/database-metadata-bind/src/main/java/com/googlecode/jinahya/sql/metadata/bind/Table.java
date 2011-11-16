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
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlType(propOrder = {"entries", "columns", "indices"})
public class Table extends Metadata {


    public Collection<Column> getColumns() {
        if (columns == null) {
            columns = new ArrayList<Column>();
        }
        return columns;
    }


    public Collection<Index> getIndices() {

        if (indices == null) {
            indices = new ArrayList<Index>();
        }

        return indices;
    }


    public String getTableCatalog() {
        return getValue("TABLE_CAT");
    }


    public void setTableCatalog(final String tableCatalog) {
        setValue("TABLE_CAT", tableCatalog);
    }


    public String getTableSchema() {
        return getValue("TABLE_SCHEM");
    }


    public void setTableSchema(final String tableSchema) {
        setValue("TABLE_SCHEM", tableSchema);
    }


    public String getTableName() {
        return getValue("TABLE_NAME");
    }


    public void setTableName(final String tableName) {
        setValue("TABLE_NAME", tableName);
    }


    @XmlElement(name = "column")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<Column> columns;


    @XmlElement(name = "index")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<Index> indices;


}

