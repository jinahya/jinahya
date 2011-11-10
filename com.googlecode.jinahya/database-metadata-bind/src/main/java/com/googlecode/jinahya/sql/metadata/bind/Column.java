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


import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlType(propOrder = {"tableCatalog", "tableSchema", "tableName", "columnName",
                      "dataType", "typeName", "columnSize"})
public class Column extends Metadata {


    @XmlElement(required = true)
    public String getTableCatalog() {
        return getValue(String.class, "TABLE_CAT");
    }


    public void setTableCatalog(final String tableCatalog) {
        setValue(String.class, "TABLE_CAT", tableCatalog);
    }


    @XmlElement(required = true)
    public String getTableSchema() {
        return getValue(String.class, "TABLE_SCHEM");
    }


    public void setTableSchema(final String tableSchema) {
        setValue(String.class, "TABLE_SCHEM", tableSchema);
    }


    @XmlElement(required = true, nillable = false)
    public String getTableName() {
        return getValue(String.class, "TABLE_NAME");
    }


    public void setTableName(final String tableName) {
        setValue(String.class, "TABLE_NAME", tableName);
    }


    @XmlElement(required = true, nillable = false)
    public String getColumnName() {
        return getValue(String.class, "COLUMN_NAME");
    }


    public void setColumnName(final String columnName) {
        setValue(String.class, "COLUMN_NAME", columnName);
    }


    @XmlElement(required = true, nillable = false)
    public Integer getDataType() {
        return getValue(Integer.class, "DATA_TYPE");
    }


    public void setDataType(Integer dataType) {
        setValue(Integer.class, "DATA_TYPE", dataType);
    }


    @XmlElement(required = true, nillable = false)
    public String getTypeName() {
        return getValue(String.class, "TYPE_NAME");
    }


    public void setTypeName(final String typeName) {
        setValue(String.class, "TYPE_NAME", typeName);
    }


    @XmlElement(required = true, nillable = false)
    public BigDecimal getColumnSize() {
    return getValue(BigDecimal.class, "COLUMN_SIZE");
    }
    
    
    public void setColumnSize(final BigDecimal columnSize) {
    setValue(BigDecimal.class, "COLUMN_SIZE", columnSize);
    }

    /*
    @XmlElement(required = true, nillable = false)
    public Number getColumnSize() {
        return getValue(BigDecimal.class, "COLUMN_SIZE");
    }


    public void setColumnSize(final Number columnSize) {
        setValue(Number.class, "COLUMN_SIZE", columnSize);
    }
     */


}

