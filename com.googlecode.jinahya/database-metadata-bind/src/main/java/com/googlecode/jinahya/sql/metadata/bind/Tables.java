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
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class Tables extends EntrySetWrapper<Table> {


    /**
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schemaPattern
     * @param tableNamePattern
     * @param types
     * @return
     * @throws SQLException 
     *
     * @see DatabaseMetaData#getTables(String, String, String, String[]) 
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
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schemaPattern
     * @param tableNamePattern
     * @param types
     * @param tables
     * @throws SQLException 
     *
     * @see DatabaseMetaData#getTables(String, String, String, String[]) 
     */
    public static void getTables(final DatabaseMetaData databaseMetaData,
                                 final String catalog,
                                 final String schemaPattern,
                                 final String tableNamePattern,
                                 final String[] types,
                                 final Collection<Table> tables)
        throws SQLException {

        final ResultSet tableResultSet = databaseMetaData.getTables(
            catalog, schemaPattern, tableNamePattern, types);
        try {
            while (tableResultSet.next()) {
                final Table table = EntrySet.newInstance(
                    Table.class, tableResultSet);
                tables.add(table);

                Columns.getColumns(databaseMetaData, table, null);

                Indices.getIndexInfo(databaseMetaData, table, false, false);

                Identifiers.getBestRowIdentifier(
                    databaseMetaData, table, DatabaseMetaData.bestRowTemporary,
                    true, table.getTemporayIdentifiers());

                Identifiers.getBestRowIdentifier(
                    databaseMetaData, table,
                    DatabaseMetaData.bestRowTransaction, true,
                    table.getTransactionIdentifiers());

                Identifiers.getBestRowIdentifier(
                    databaseMetaData, table, DatabaseMetaData.bestRowSession,
                    true, table.getSessionIdentifiers());
            }
        } finally {
            tableResultSet.close();
        }
    }


    public static void getTables(final DatabaseMetaData databaseMetaData,
                                 final Schema schema,
                                 final String tableNamePattern,
                                 final String[] types)
        throws SQLException {

        getTables(databaseMetaData, schema.getTABLE_CATALOG(),
                  schema.getTABLE_SCHEM(), tableNamePattern, types,
                  schema.getTables());
    }


    /**
     * @param <O> output type parameter
     * @param databaseMetaData
     * @param catalog
     * @param schemaPattern
     * @param tableNamePattern
     * @param types
     * @param properties
     * @param outputType
     * @param output
     * @throws SQLException
     * @throws JAXBException
     */
    public static <O> void marshalInstance(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaPattern, final String tableNamePattern,
        final String[] types, final Map<String, Object> properties,
        final Class<O> outputType, final O output)
        throws SQLException, JAXBException {

        final Tables instance = newInstance(
            databaseMetaData, catalog, schemaPattern, tableNamePattern, types);

        marshal(instance, properties, outputType, output);
    }


    /**
     * 
     * @param <I>
     * @param properties
     * @param inputTyep
     * @param input
     * @return
     * @throws SQLException
     * @throws JAXBException
     */
    public static <I> Tables unmarshalInstance(
        final Map<String, Object> properties, final Class<I> inputTyep,
        final I input)
        throws SQLException, JAXBException {

        return unmarshal(Tables.class, properties, inputTyep, input);
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

