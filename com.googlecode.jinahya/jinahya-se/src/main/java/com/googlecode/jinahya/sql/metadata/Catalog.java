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


import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlType(propOrder = {"tableCat", "schemas"})
public class Catalog implements Retrievable {


    public static final Catalog UNNAMED = new Catalog() {

        @Override
        public String getTableCat() {

            return null;
        }


        @Override
        public void setTableCat(final String tableCat) {
            // do nothing
        }

    };


    @Override
    public void retrieve(final DatabaseMetaData databaseMetaData)
        throws SQLException {

        final ResultSet resultSet = databaseMetaData.getSchemas(tableCat, null);
        try {
            while (resultSet.next()) {
            }
        } finally {
            resultSet.close();
        }
    }


    // --------------------------------------------------------------- TABLE_CAT
    public String getTableCat() {

        return tableCat;
    }


    public void setTableCat(final String tableCat) {

        this.tableCat = tableCat;
    }


    // ----------------------------------------------------------------- schemas
    public Map<String, Schema> getSchemas() {

        if (schemas == null) {
            schemas = new TreeMap<String, Schema>();
        }

        return schemas;
    }


    @ColumnLabel("TABLE_CAT")
    @XmlElement(nillable = true, required = true)
    private String tableCat;


    @XmlElement(required = true)
    @XmlJavaTypeAdapter(SchemaValuesMapAdapter.class)
    private Map<String, Schema> schemas;


}
