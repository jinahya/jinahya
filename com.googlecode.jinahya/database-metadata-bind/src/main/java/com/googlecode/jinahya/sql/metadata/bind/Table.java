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


import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class Table extends Metadata {


    public Columns getColumns() {
        return columns;
    }


    public void setColumns(final Columns columns) {
        this.columns = columns;
    }


    public Indices getIndices() {
        return indices;
    }


    public void setIndices(final Indices indices) {
        this.indices = indices;
    }


    public String getTableCatalog() {
        return getValue(String.class, "TABLE_CAT");
    }


    public void setTableCatalog(final String tableCatalog) {
        setValue(String.class, "TABLE_CAT", tableCatalog);
    }


    public String getTableSchema() {
        return getValue(String.class, "TABLE_SCHEM");
    }


    public void setTableSchema(final String tableSchema) {
        setValue(String.class, "TABLE_SCHEM", tableSchema);
    }


    public String getTableName() {
        return getValue(String.class, "TABLE_NAME");
    }


    public void setTableName(final String tableName) {
        setValue(String.class, "TABLE_NAME", tableName);
    }


    @XmlElement()
    private Columns columns;


    @XmlElement()
    private Indices indices;


}

