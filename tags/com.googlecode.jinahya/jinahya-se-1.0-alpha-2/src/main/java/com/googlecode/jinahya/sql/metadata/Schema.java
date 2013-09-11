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


package com.googlecode.jinahya.sql.metadata;


import java.util.Map;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlType(propOrder = {"tableSchem", "tables"})
public class Schema {


    public static final Schema UNNAMED = new Schema() {


        @Override
        public Catalog getCatalog() {

            return Catalog.UNNAMED;
        }


        @Override
        public final String getTableSchem() {

            return null;
        }


        @Override
        public final void setTableSchem(final String tableSchem) {
            // do nothing
        }

    };


    // ----------------------------------------------------------------- catalog
    public Catalog getCatalog() {

        return catalog;
    }


    // ------------------------------------------------------------- TABLE_SCHEM
    public String getTableSchem() {

        return tableSchem;
    }


    public void setTableSchem(final String tableSchem) {

        this.tableSchem = tableSchem;
    }


    // ------------------------------------------------------------------ tables
    public Map<String, Table> getTables() {

        if (tables == null) {
            tables = new TreeMap<String, Table>();
        }

        return tables;
    }


    @ColumnLabel("TABLE_CATALOG")
    @XmlTransient
    private String tableCatalog;


    @XmlTransient
    private Catalog catalog; // ---------------------------------------- catalog


    @ColumnLabel("TABLE_SCHEM")
    @XmlElement(required = true)
    private String tableSchem;


    @XmlElement(required = true)
    @XmlJavaTypeAdapter(TableValuesAdapter.class)
    private Map<String, Table> tables;


}
