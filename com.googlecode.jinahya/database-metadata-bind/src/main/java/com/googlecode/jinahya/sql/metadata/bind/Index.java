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
@XmlType(propOrder = {"tableCatalog", "tableSchema", "tableName", "nonUnique",
                      "indexQualifier", "indexName", "type", "ordinalPosition",
                      "columnName", "ascOrDesc", "cardinality", "pages",
                      "filterCondition"})
public class Index extends Metadata {


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


    @XmlElement(required = true, nillable = false)
    public BigDecimal getNonUnique() {
        return getValue(BigDecimal.class, "NON_UNIQUE");
    }


    public void setNonUnique(final BigDecimal nonUnique) {
        setValue(BigDecimal.class, "NON_UNIQUE", nonUnique);
    }


    @XmlElement(required = true)
    public String getIndexQualifier() {
        return getValue(String.class, "INDEX_QUALIFIER");
    }


    public void setIndexQualifier(final String indexQualifier) {
        setValue(String.class, "INDEX_QUALIFIER", indexQualifier);
    }


    @XmlElement(required = true)
    public String getIndexName() {
        return getValue(String.class, "INDEX_NAME");
    }


    public void setIndexName(final String indexName) {
        setValue(String.class, "INDEX_NAME", indexName);
    }


    @XmlElement(required = true, nillable = false)
    public Integer getType() {
        return getValue(Integer.class, "TYPE");
    }


    public void setType(final Integer type) {
        setValue(Integer.class, "TYPE", type);
    }


    @XmlElement(required = true, nillable = false)
    public Integer getOrdinalPosition() {
        return getValue(Integer.class, "ORDINAL_POSITION");
    }


    public void setOrdinalPosition(final Integer ordinalPosition) {
        setValue(Integer.class, "TYPE", ordinalPosition);
    }


    @XmlElement(required = true)
    public String getColumnName() {
        return getValue(String.class, "COLUMN_NAME");
    }


    public void setColumnName(final String columnName) {
        setValue(String.class, "COLUMN_NAME", columnName);
    }


    @XmlElement(required = true)
    public String getAscOrDesc() {
        return getValue(String.class, "ASC_OR_DESC");
    }


    public void setAscOrDesc(final String ascOrDesc) {
        setValue(String.class, "COLUMN_NAME", ascOrDesc);
    }


    @XmlElement(required = true, nillable = false)
    public Short getCardinality() {
        return getValue(Short.class, "CARDINALITY");
    }


    public void setCardinality(final Short cardinality) {
        setValue(Short.class, "CARDINALITY", cardinality);
    }


    @XmlElement(required = true, nillable = false)
    public Short getPages() {
        return getValue(Short.class, "PAGES");
    }


    public void setPages(final Short pages) {
        setValue(Short.class, "PAGES", pages);
    }


    @XmlElement(required = true)
    public String getFilterCondition() {
        return getValue(String.class, "FILTER_CONDITION");
    }


    public void setPages(final String filterCondition) {
        setValue(String.class, "FILTER_CONDITION", filterCondition);
    }


}

