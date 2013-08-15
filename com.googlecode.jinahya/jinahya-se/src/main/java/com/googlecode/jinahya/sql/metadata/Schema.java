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


import com.googlecode.jinahya.sql.metadata.Table.TablesMapAdapter;
import com.googlecode.jinahya.xml.bind.ValuesMapAdapter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAttribute;
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


    public static final Schema UNNAMED = new Schema();


    static {
        UNNAMED.tableSchem = null;
    }


    public static class Schemas extends ValuesMapAdapter.AbstractValues<Schema> {


        @XmlElement
        public List<Schema> getSchema() {

            return getValueList();
        }


    }


    public static class SchemasMapAdapter
        extends ValuesMapAdapter<Schema.Schemas, String, Schema> {


        public SchemasMapAdapter() {

            super(Schema.Schemas.class);
        }


        @Override
        protected String getKey(final Schema value) {

            return value.getTableSchem();
        }


    }


    public Catalog getCatalog() {

        return catalog;
    }


    public void setCatalog(final Catalog catalog) {

        this.catalog = catalog;
    }


    @XmlAttribute
    public String getCatalogName() {

        return catalog == null ? null : catalog.getTableCat();
    }


    public String getTableSchem() {

        return tableSchem;
    }


    public Map<String, Table> getTables() {

        if (tables == null) {
            tables = new HashMap<String, Table>();
        }

        return tables;
    }


    @XmlTransient
    private Catalog catalog;


    @Label("TABLE_SCHEM")
    @XmlElement(nillable = true, required = true)
    private String tableSchem;


    @XmlElement(required = true)
    @XmlJavaTypeAdapter(TablesMapAdapter.class)
    private Map<String, Table> tables;


}
