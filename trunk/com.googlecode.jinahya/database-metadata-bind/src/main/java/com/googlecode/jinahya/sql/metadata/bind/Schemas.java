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
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class Schemas extends EntrySets<Schema> {


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

        final Schemas instance = new Schemas();
        getSchemas(databaseMetaData, catalog, schemaPattern,
                   instance.getSchemas());

        return instance;
    }


    /**
     * Retrieves <code>Schema</code>s.
     *
     * @param databaseMetaData meta
     * @param catalog catalog
     * @param schemaPattern schema pattern
     * @param schemas the collection to be filled
     * @throws SQLException if a database access error occurs.
     * @see DatabaseMetaData#getSchemas(String, String)
     */
    public static void getSchemas(final DatabaseMetaData databaseMetaData,
                                  final String catalog,
                                  final String schemaPattern,
                                  final Collection<Schema> schemas)
        throws SQLException {

        if (MethodNamesToOmit.instanceContainsName("getSchemas")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getSchemas(
            catalog, schemaPattern);
        try {
            while (resultSet.next()) {
                final Schema schema =
                    EntrySet.newInstance(Schema.class, resultSet);
                schemas.add(schema);
            }
            if (schemas.isEmpty()) {
                schemas.add(Schema.newNullInstance(catalog));
            }
            for (Schema schema : schemas) {
                Tables.getAllTables(databaseMetaData, schema);
            }
        } finally {
            resultSet.close();
        }
    }


    /**
     * Retrieves all <code>Schema</code> mapped to given <code>catalog</code>.
     *
     * @param databaseMetaData meta
     * @param catalog catalog
     * @throws SQLException if a database access error occurs.
     */
    public static void getAllSchemas(final DatabaseMetaData databaseMetaData,
                                     final Catalog catalog)
        throws SQLException {

        getSchemas(databaseMetaData, catalog.getTABLE_CAT(), null,
                   catalog.getSchemas());

        for (Schema schema : catalog.getSchemas()) {
            schema.setParent(catalog);
        }
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

