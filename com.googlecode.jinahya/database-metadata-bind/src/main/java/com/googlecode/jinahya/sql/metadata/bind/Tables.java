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
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Table wrapper.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class Tables extends EntrySets<Table> {


    /**
     * Creates a new instance.
     *
     * @param databaseMetaData database meta data
     * @param catalog catalog
     * @param schemaPattern schema pattern
     * @param tableNamePattern table name pattern
     * @param types types
     * @return a new instance
     * @throws SQLException if an SQL error occurs.
     * @see #getTables(DatabaseMetaData, String, String, String, String[],
     *                 Collection)
     */
    public static Tables newInstance(final DatabaseMetaData databaseMetaData,
                                     final String catalog,
                                     final String schemaPattern,
                                     final String tableNamePattern,
                                     final String[] types)
        throws SQLException {

        final Tables tables = new Tables();
        getTables(databaseMetaData, catalog, schemaPattern, tableNamePattern,
                  types, tables.getTables());

        return tables;
    }


    /**
     * Retrieves tables.
     *
     * @param databaseMetaData database metadata
     * @param catalog catalog
     * @param schemaPattern schemaPattern
     * @param tableNamePattern tableNamePattern
     * @param types types
     * @param tables table collection
     * @throws SQLException if a database access error occurs
     * @see DatabaseMetaData#getTables(String, String, String, String[]) 
     */
    public static void getTables(final DatabaseMetaData databaseMetaData,
                                 final String catalog,
                                 final String schemaPattern,
                                 final String tableNamePattern,
                                 final String[] types,
                                 final Collection<Table> tables)
        throws SQLException {

        if (MethodNamesToOmit.instanceContainsName("getTables")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getTables(
            catalog, schemaPattern, tableNamePattern, types);
        try {
            while (resultSet.next()) {

                // ----------------------------------------------------- entries
                final Table table =
                    EntrySet.newInstance(Table.class, resultSet);
                tables.add(table);

                // -------------------------------------------- columnPrivileges
                ColumnPrivileges.getColumnPrivileges(
                    databaseMetaData, table);

                // ----------------------------------------------------- columns
                Columns.getAllColumns(databaseMetaData, table);

                // ------------------------------------------------ exportedKeys
                ExportedKeys.getExportedKeys(databaseMetaData, table);

                // ------------------------------------------------- identifiers
                Identifiers.getAllBestRowIdentifier(databaseMetaData, table);

                // ------------------------------------------------ importedKeys
                ImportedKeys.getImportedKeys(databaseMetaData, table);

                // ----------------------------------------------------- indices
                Indices.getIndexInfo(databaseMetaData, table);

                // ---------------------------------------------- versionColumns
                VersionColumns.getVersionColumns(databaseMetaData, table);

            }
        } finally {
            resultSet.close();
        }
    }


    /**
     * Retrieves all tables mapped to given <code>schema</code>.
     *
     * @param databaseMetaData meta
     * @param schema schema
     * @throws SQLException if database access error occurs.
     */
    public static void getAllTables(final DatabaseMetaData databaseMetaData,
                                    final Schema schema)
        throws SQLException {

        getTables(databaseMetaData, schema.getTABLE_CATALOG(),
                  schema.getTABLE_SCHEM(), null, null, schema.getTables());

        for (Table table : schema.getTables()) {
            table.setParent(schema);
        }
    }


    /**
     * Creates a new instance.
     */
    public Tables() {
        super(Table.class);
    }


    @XmlElement(name = "table")
    public Collection<Table> getTables() {
        return super.getEntrySets();
    }


}
