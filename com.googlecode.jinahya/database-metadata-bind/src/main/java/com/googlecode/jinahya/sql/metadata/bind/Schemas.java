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
public class Schemas extends EntrySetWrapper<Schema> {


    /**
     * Selects a new instance of Schemas.
     *
     * @param databaseMetaData database metadata
     * @param catalog catalog
     * @param schemaPattern schema pattern
     * @return a new instance of Schemas
     * @throws SQLException if an SQL error occurs.
     *
     * @see DatabaseMetaData#getSchemas(String, String)
     */
    public static Schemas newInstance(final DatabaseMetaData databaseMetaData,
                                      final String catalog,
                                      final String schemaPattern)
        throws SQLException {

        final Schemas schemas = new Schemas();
        getSchemas(databaseMetaData, catalog, schemaPattern,
                   schemas.getSchemas());

        return schemas;
    }


    /**
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schemaPattern
     * @param schemas
     * @throws SQLException 
     *
     * @see DatabaseMetaData#getSchemas(String, String) 
     */
    public static void getSchemas(final DatabaseMetaData databaseMetaData,
                                  final String catalog,
                                  final String schemaPattern,
                                  final Collection<Schema> schemas)
        throws SQLException {

        try {
            final ResultSet schemaResultSet =
                databaseMetaData.getSchemas(catalog, schemaPattern);
            try {
                while (schemaResultSet.next()) {

                    // ------------------------------------------------- entries
                    final Schema schema = EntrySet.newInstance(
                        Schema.class, schemaResultSet);
                    schemas.add(schema);

                    // ---------------------------------------------- attributes
                    Attributes.getAllAttributes(databaseMetaData, schema);

                    // ----------------------------------------------- functions
                    Functions.getAllFunctions(databaseMetaData, schema);

                    // ---------------------------------------- procedureColumns
                    ProcedureColumns.getAllProcedureColumns(
                        databaseMetaData, schema);

                    // -------------------------------------------------- tables
                    Tables.getAllTables(databaseMetaData, schema);

                    // ---------------------------------------------------- UDTs
                    UDTs.getAllUDTs(databaseMetaData, schema);
                }
            } finally {
                schemaResultSet.close();
            }
        } catch (AbstractMethodError ame) {
            //ame.printStackTrace(System.err);
        }
    }


    /**
     * 
     * @param databaseMetaData database meta data
     * @param catalog catalog
     * @param schemaPattern schema pattern
     * @throws SQLException if an SQL error occurs
     *
     * @see DatabaseMetaData#getSchemas(String, String) 
     */
    public static void getSchemas(final DatabaseMetaData databaseMetaData,
                                  final Catalog catalog,
                                  final String schemaPattern)
        throws SQLException {


        getSchemas(databaseMetaData, catalog.getTABLE_CAT(), schemaPattern,
                   catalog.getSchemas());
    }


    /**
     * 
     * @param databaseMetaData database meta data
     * @param catalog catalog
     * @throws SQLException if an SQL error occurs.
     */
    public static void getAllSchemas(final DatabaseMetaData databaseMetaData,
                                     final Catalog catalog)
        throws SQLException {


        getSchemas(databaseMetaData, catalog, null);
    }


    /**
     * Creates a new instance.
     */
    public Schemas() {
        super(Schema.class);
    }


    @XmlElement(name = "schema")
    public Collection<Schema> getSchemas() {
        return super.getEntrySets();
    }


}

