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


import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Column wrapper.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
//@XmlRootElement
public class VersionColumns extends EntrySetWrapper<VersionColumn> {


    /**
     * Creates a new instance.
     *
     * @param databaseMetaData database meta data
     * @param catalog catalog
     * @param schema schema
     * @param table table
     * @return a new instance
     * @throws SQLException if a database access error occurs.
     */
    public static VersionColumns newInstance(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schema, final String table)
        throws SQLException {

        final VersionColumns instance = new VersionColumns();

        getVersionColumns(databaseMetaData, catalog, schema, table,
                          instance.getVersionColumns());

        return instance;
    }


    /**
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schema
     * @param table
     * @param versionColumns
     * @throws SQLException 
     *
     * @see DatabaseMetaData#getVersionColumns(String, String, String)
     */
    public static void getVersionColumns(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schema, final String table,
        final Collection<VersionColumn> versionColumns)
        throws SQLException {

        final ResultSet resultSet =
            databaseMetaData.getVersionColumns(catalog, schema, table);
        try {
            while (resultSet.next()) {
                final VersionColumn instance = EntrySet.newInstance(
                    VersionColumn.class, resultSet);
                versionColumns.add(instance);
            }
        } finally {
            resultSet.close();
        }
    }


    /**
     * 
     * @param databaseMetaData
     * @param table
     * @throws SQLException 
     */
    public static void getVersionColumns(
        final DatabaseMetaData databaseMetaData, final Table table)
        throws SQLException {

        getVersionColumns(databaseMetaData, table.getTABLE_CAT(),
                          table.getTABLE_SCHEM(), table.getTABLE_NAME(),
                          table.getVersionColumns());

        for (VersionColumn versionColumn : table.getVersionColumns()) {
            versionColumn.setParent(table);
        }
    }


    /**
     * Creates a new instance.
     */
    public VersionColumns() {
        super(VersionColumn.class);
    }


    /**
     * Returns versionColumns.
     *
     * @return versionColumns.
     */
    @XmlElement(name = "versionColumn")
    public Collection<VersionColumn> getVersionColumns() {
        return super.getEntrySets();
    }


}

