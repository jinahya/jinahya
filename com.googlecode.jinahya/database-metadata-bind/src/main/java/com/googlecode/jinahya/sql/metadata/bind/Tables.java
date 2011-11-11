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


import java.lang.reflect.InvocationTargetException;

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
public class Tables extends MetadataCollectable<Table> {


    /**
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schemaPattern
     * @param tableNamePattern
     * @param types
     * @return
     * @throws SQLException 
     */
    public static Tables newInstance(final DatabaseMetaData databaseMetaData,
                                     final String catalog,
                                     final String schemaPattern,
                                     final String tableNamePattern,
                                     final String[] types)
        throws SQLException {

        final ResultSet tableResultSet = databaseMetaData.getTables(
            catalog, schemaPattern, tableNamePattern, types);
        try {
            final Tables tables = new Tables();
            while (tableResultSet.next()) {
                final Table table = MetadataAccessible.newInstance(
                    Table.class, tableResultSet);
                tables.getMetadata().add(table);
                if (table.getTableName().equals("PRODUCT")) {
                    table.print(System.out);
                }

                final Columns columns = Columns.newInstance(
                    databaseMetaData, table.getTableCatalog(),
                    table.getTableSchema(), table.getTableName(), null);
                table.getColumns().addAll(columns.getMetadata());

                final Indices indices = Indices.newInstance(
                    databaseMetaData, table.getTableCatalog(),
                    table.getTableSchema(), table.getTableName(), false, false);
                table.getIndices().addAll(indices.getMetadata());                
            }

            return tables;

        } finally {
            tableResultSet.close();
        }
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
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public static <O> void marshalInstance(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaPattern, final String tableNamePattern,
        final String[] types, final Map<String, Object> properties,
        final Class<O> outputType, final O output)
        throws SQLException, JAXBException, NoSuchMethodException,
               IllegalAccessException, InvocationTargetException {

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
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public static <I> Tables unmarshalInstance(
        final Map<String, Object> properties, final Class<I> inputTyep,
        final I input)
        throws SQLException, JAXBException, NoSuchMethodException,
               IllegalAccessException, InvocationTargetException {

        return unmarshal(Tables.class, properties, inputTyep, input);
    }


    @XmlElement(name = "table")
    public Collection<Table> getTables() {
        return super.getMetadata();
    }


}
