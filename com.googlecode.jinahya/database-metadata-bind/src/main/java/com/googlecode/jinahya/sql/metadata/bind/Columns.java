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
public class Columns extends EntrySetWrapper<Column> {


    /**
     * Creates a new instance.
     *
     * @param databaseMetaData database meta data
     * @param catalog catalog
     * @param schemaPattern schema pattern
     * @param tableNamePattern table name pattern
     * @param columnNamePattern column name pattern
     * @return a new instance
     * @throws SQLException if an SQL error occurs.
     */
    public static Columns newInstance(final DatabaseMetaData databaseMetaData,
                                      final String catalog,
                                      final String schemaPattern,
                                      final String tableNamePattern,
                                      final String columnNamePattern)
        throws SQLException {

        final Columns columns = new Columns();
        getColumns(databaseMetaData, catalog, schemaPattern, tableNamePattern,
                   columnNamePattern, columns.getColumns());

        return columns;
    }


    /**
     * Retrieves <code>Column</code>s from given <code>databaseMetaData</code>
     * and adds them to specified <code>columns</code>.
     *
     * @param databaseMetaData database meta data
     * @param catalog catalog
     * @param schemaPattern schema pattern
     * @param tableNamePattern table name pattern
     * @param columnNamePattern column name pattern
     * @param columns the collection to be filled
     * @throws SQLException if an SQL error occurs.
     *
     * @see DatabaseMetaData#getColumns(String, String, String, String)
     */
    public static void getColumns(final DatabaseMetaData databaseMetaData,
                                  final String catalog,
                                  final String schemaPattern,
                                  final String tableNamePattern,
                                  final String columnNamePattern,
                                  final Collection<Column> columns)
        throws SQLException {

        if (MethodNamesToOmit.instanceContainsName("getColumns")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getColumns(
            catalog, schemaPattern, tableNamePattern, columnNamePattern);
        try {
            while (resultSet.next()) {
                final Column column = Column.newInstance(resultSet);
                columns.add(column);
            }
        } finally {
            resultSet.close();
        }
    }


    /**
     * Retrieves all <code>Column</code>s for given <code>table</code>.
     *
     * @param databaseMetaData metadata.
     * @param table table
     * @throws SQLException if a database access error occurs
     */
    public static void getAllColumns(final DatabaseMetaData databaseMetaData,
                                     final Table table)
        throws SQLException {

        getColumns(databaseMetaData, table.getTABLE_CAT(),
                   table.getTABLE_SCHEM(), table.getTABLE_NAME(), null,
                   table.getColumns());
    }


    /**
     * Creates a new instance.
     */
    public Columns() {
        super(Column.class);
    }


    @XmlElement(name = "column")
    public Collection<Column> getColumns() {
        return super.getEntrySets();
    }


}

