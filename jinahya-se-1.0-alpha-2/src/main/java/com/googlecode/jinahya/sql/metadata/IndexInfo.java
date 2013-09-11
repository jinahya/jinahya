/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


package com.googlecode.jinahya.sql.metadata;


import javax.xml.bind.annotation.XmlElement;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class IndexInfo {


    @ColumnLabel("TABLE_CAT")
    @XmlElement(nillable = true, required = true)
    private String tableCat;


    @ColumnLabel("TABLE_SCHEM")
    @XmlElement(nillable = true, required = true)
    private String tableSchem;


    @ColumnLabel("TABLE_NAME")
    @XmlElement(nillable = false, required = true)
    private String tableName;


    private Table table; // ---------------------------------------------- table


    @ColumnLabel("NON_UNIQUE")
    @XmlElement(nillable = false, required = true)
    private boolean nonUnique;


    @ColumnLabel("INDEX_QUALIFIER")
    @XmlElement(nillable = true, required = true)
    private String indexQualifier;


    @ColumnLabel("INDEX_NAME")
    @XmlElement(nillable = true, required = true)
    private String indexName;


    @ColumnLabel("TYPE")
    @XmlElement(required = true)
    private short type;


    @ColumnLabel("ORDINAL_POSITION")
    @XmlElement(required = true)
    private short orginalPosition;


    @ColumnLabel("COLUMN_NAME")
    @XmlElement(nillable = true, required = true)
    private String columnName;


    @ColumnLabel("ASC_OR_DESC")
    @XmlElement(nillable = true, required = true)
    private String ascOrDesc;


    @ColumnLabel("CARDINALITY")
    @XmlElement(required = true)
    private int cardinality;


    @ColumnLabel("PAGES")
    @XmlElement(required = true)
    private int pages;


    @ColumnLabel("FILTER_CONDITION")
    @XmlElement(nillable = true, required = true)
    private String filterCondition;


}
