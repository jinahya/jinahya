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
@XmlType(propOrder = {"tableCatalog", "tableSchema", "tableName", "tableType",
                      "remarks", "typeCatalog", "typeSchema", "typeName",
                      "idColumnName", "idColumnGeneration", "columns",
                      "indices"})
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


    @XmlElement(required = true, nillable = true)
    public String getTableCatalog() {
        return getValue(String.class, "TABLE_CAT");
    }


    public void setTableCatalog(final String tableCatalog) {
        setValue(String.class, "TABLE_CAT", tableCatalog);
    }


    @XmlElement(required = true, nillable = true)
    public String getTableSchema() {
        return getValue(String.class, "TABLE_SCHEM");
    }


    public void setTableSchema(final String tableSchema) {
        setValue(String.class, "TABLE_SCHEM", tableSchema);
    }


    @XmlElement(required = true)
    public String getTableName() {
        return getValue(String.class, "TABLE_NAME");
    }


    public void setTableName(final String tableName) {
        setValue(String.class, "TABLE_NAME", tableName);
    }


    @XmlElement(required = true)
    public String getTableType() {
        return getValue(String.class, "TABLE_TYPE");
    }


    public void setTableType(final String tableType) {
        setValue(String.class, "TABLE_TYPE", tableType);
    }


    @XmlElement(required = true)
    public String getRemarks() {
        return getValue(String.class, "REMARKS");
    }


    public void setRemarks(final String remarks) {
        setValue(String.class, "REMARKS", remarks);
    }


    @XmlElement(required = true, nillable = true)
    public String getTypeCatalog() {
        return getValue(String.class, "TYPE_CAT");
    }


    public void setTypeCatalog(final String remarks) {
        setValue(String.class, "TYPE_CAT", remarks);
    }


    @XmlElement(required = true, nillable = true)
    public String getTypeSchema() {
        return getValue(String.class, "TYPE_SCHEM");
    }


    public void setTypeSchema(final String typeSchema) {
        setValue(String.class, "TYPE_SCHEM", typeSchema);
    }


    @XmlElement(required = true, nillable = true)
    public String getTypeName() {
        return getValue(String.class, "TYPE_NAME");
    }


    public void setTypeName(final String typeSchema) {
        setValue(String.class, "TYPE_NAME", typeSchema);
    }


    @XmlElement(required = true, nillable = true)
    public String getIdColumnName() {
        return getValue(String.class, "SELF_REFERENCING_COL_NAME");
    }


    public void setIdColumnName(final String idColumnName) {
        setValue(String.class, "SELF_REFERENCING_COL_NAME", idColumnName);
    }


    @XmlElement(required = true, nillable = true)
    public String getIdColumnGeneration() {
        return getValue(String.class, "REF_GENERATION");
    }


    public void setIdColumnGeneration(final String idColumnGeneration) {

        setValue(String.class, "REF_GENERATION", idColumnGeneration);
    }


    @XmlElement(name = "column")
    @XmlElementWrapper(name = "columns", required = true, nillable = true)
    private Collection<Column> columns;


    @XmlElement(name = "index")
    @XmlElementWrapper(name = "indices", required = true, nillable = true)
    private Collection<Index> indices;


}

