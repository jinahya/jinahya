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
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class Columns extends EntrySetWrapper<Column> {


    /**
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schemaPattern
     * @param tableNamePattern
     * @param columnNamePattern
     * @return
     * @throws SQLException
     *
     * @see DatabaseMetaData#getColumns(String, String, String, String) 
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
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schemaPattern
     * @param tableNamePattern
     * @param columnNamePattern
     * @param columns
     * @throws SQLException 
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

        final ResultSet columnResultSet = databaseMetaData.getColumns(
            catalog, schemaPattern, tableNamePattern, columnNamePattern);
        try {
            while (columnResultSet.next()) {
                final Column column = EntrySet.newInstance(
                    Column.class, columnResultSet);
                columns.add(column);
            }
        } finally {
            columnResultSet.close();
        }
    }


    public static void getColumns(final DatabaseMetaData databaseMetaData,
                                  final Table table,
                                  final String columnNamePattern)
        throws SQLException {

        getColumns(databaseMetaData, table.getTABLE_CAT(),
                   table.getTABLE_SCHEM(), table.getTABLE_NAME(),
                   columnNamePattern, table.getColumns());
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

