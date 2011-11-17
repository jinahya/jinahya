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

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Catalog collection.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class Catalogs extends EntrySetWrapper<Catalog> {


    /**
     * Retrieves <code>Catalog</code>s from given <code>databaseMetaData</code>
     * and creates a new instance of <code>Catalogs</code>.
     *
     * @param databaseMetaData database meta data
     * @return a new instance of <code>Catalogs</code>
     * @throws SQLException if an SQL error occurs.
     *
     * @see DatabaseMetaData#getCatalogs()
     * @see DatabaseMetaData#getSchemas(String, String)
     */
    public static Catalogs newInstance(final DatabaseMetaData databaseMetaData)
        throws SQLException {

        final Catalogs catalogs = new Catalogs();
        getCatalogs(databaseMetaData, catalogs.getCatalogs());

        return catalogs;
    }


    /**
     * Retrieves <code>Catalog</code>s from given <code>databaseMetaData</code>
     * and adds them to specified <code>catalogs</code>.
     *
     * @param databaseMetaData database meta data
     * @param catalogs the collection to be filled
     * @throws SQLException if an SQL error occurs.
     *
     * @see DatabaseMetaData#getCatalogs()
     */
    public static void getCatalogs(final DatabaseMetaData databaseMetaData,
                                   final Collection<Catalog> catalogs)
        throws SQLException {

        final ResultSet catalogResultSet = databaseMetaData.getCatalogs();
        try {
            while (catalogResultSet.next()) {

                // ----------------------------------------------------- entries
                final Catalog catalog = EntrySet.newInstance(
                    Catalog.class, catalogResultSet);
                catalogs.add(catalog);

                // --------------------------------------------- functionColumns
                FunctionColumns.getAllFunctionColumns(
                    databaseMetaData, catalog);

                // --------------------------------------------------- functions
                Functions.getAllFunctions(databaseMetaData, catalog);

                // ----------------------------------------------------- schemas
                Schemas.getAllSchemas(databaseMetaData, catalog);

                // -------------------------------------------- procedureColumns
                ProcedureColumns.getAllProcedureColumns(
                    databaseMetaData, catalog);

                // -------------------------------------------------------- UDTs
                UDTs.getAllUDTs(databaseMetaData, catalog);
            }
        } finally {
            catalogResultSet.close();
        }
    }


    /**
     * Creates a new instance.
     */
    public Catalogs() {
        super(Catalog.class);
    }


    /**
     * Returns catalogs.
     *
     * @return catalogs
     */
    @XmlElement(name = "catalog")
    public Collection<Catalog> getCatalogs() {
        return super.getEntrySets();
    }


}

