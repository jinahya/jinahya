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


import com.googlecode.jinahya.sql.metadata.MethodNamesToOmit;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;


/**
 * Column wrapper.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class VersionColumns extends EntrySets<VersionColumn> {


    /**
     * Creates a new instance.
     *
     * @param databaseMetaData database meta data
     * @param catalog catalog
     * @param schema schema
     * @param table table
     * @return a new instance
     * @throws SQLException if a database access error occurs.
     * @see #getVersionColumns(DatabaseMetaData, String, String, String,
     * Collection)
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
     * Retrieves version columns.
     *
     * @param databaseMetaData database metadata
     * @param catalog catalog
     * @param schema schema
     * @param table table
     * @param versionColumns version column collection
     * @throws SQLException if a database access error occurs.
     * @see DatabaseMetaData#getVersionColumns(String, String, String)
     */
    public static void getVersionColumns(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schema, final String table,
        final Collection<VersionColumn> versionColumns)
        throws SQLException {

        if (MethodNamesToOmit.instanceContainsName("getVersionColumns")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getVersionColumns(
            catalog, schema, table);
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
     * Retrieves all version columns for specified <code>table</code>.
     *
     * @param databaseMetaData database metadata
     * @param table table
     * @throws SQLException if a database access error occurs.
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

