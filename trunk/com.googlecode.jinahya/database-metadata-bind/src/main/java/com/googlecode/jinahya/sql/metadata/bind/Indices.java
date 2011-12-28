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
public class Indices extends EntrySets<Index> {


    /**
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schema
     * @param table
     * @param unique
     * @param approximate
     * @return
     * @throws SQLException 
     *
     * @see DatabaseMetaData#getIndexInfo(String, String, String, boolean,
     *      boolean) 
     */
    public static Indices newInstance(final DatabaseMetaData databaseMetaData,
                                      final String catalog, final String schema,
                                      final String table, final boolean unique,
                                      final boolean approximate)
        throws SQLException {

        final Indices indices = new Indices();
        getIndexInfo(databaseMetaData, catalog, schema, table, unique,
                     approximate, indices.getIndices());

        return indices;
    }


    /**
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schema
     * @param table
     * @param unique
     * @param approximate
     * @param indices
     * @throws SQLException if a database access error occurs
     * @see DatabaseMetaData#getIndexInfo(String, String, String, boolean,
     * boolean) 
     */
    public static void getIndexInfo(final DatabaseMetaData databaseMetaData,
                                    final String catalog, final String schema,
                                    final String table, final boolean unique,
                                    final boolean approximate,
                                    final Collection<Index> indices)
        throws SQLException {

        if (MethodNamesToOmit.instanceContainsName("getIndexInfo")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getIndexInfo(
            catalog, schema, table, unique, approximate);
        try {
            while (resultSet.next()) {
                final Index index = Index.newInstance(resultSet);
                indices.add(index);
            }
        } finally {
            resultSet.close();
        }
    }


    /**
     * 
     * @param databaseMetaData
     * @param table
     * @throws SQLException if a database access error occurs.
     */
    public static void getAllIndexInfo(final DatabaseMetaData databaseMetaData,
                                       final Table table)
        throws SQLException {

        getIndexInfo(databaseMetaData, table.getTABLE_CAT(),
                     table.getTABLE_SCHEM(), table.getTABLE_NAME(), false,
                     false, table.getIndices());

        for (Index index : table.getIndices()) {
            index.setParent(table);
        }
    }


    /**
     * Creates a new instance.
     */
    public Indices() {
        super(Index.class);
    }


    @XmlElement(name = "index")
    public Collection<Index> getIndices() {
        return super.getEntrySets();
    }


}

