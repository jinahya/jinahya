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
 * Identifier wrapper.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class Identifiers extends EntrySetWrapper<Identifier> {


    /**
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schema
     * @param table
     * @param scope
     * @param nullable
     * @return
     * @throws SQLException 
     * 
     * @see DatabaseMetaData#getBestRowIdentifier(String, String, String, int,
     *      boolean) 
     */
    public static Identifiers newInstance(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schema, final String table, final int scope,
        final boolean nullable)
        throws SQLException {

        final Identifiers identifiers = new Identifiers();
        getBestRowIdentifier(databaseMetaData, catalog, schema, table, scope,
                             nullable, identifiers.getIdentifiers());

        return identifiers;
    }


    /**
     * Gets identifiers from <code>databaseMetaData</code> and and adds bound
     * <code>Identifiers</code> to specified <code>identifiers</code>.
     *
     * @param databaseMetaData database meta data
     * @param catalog catalog
     * @param schema schema
     * @param table table
     * @param scope scope
     * @param nullable nullable
     * @param identifiers identifiers to be filled
     * @throws SQLException if an SQL error occurs.
     *
     * @see DatabaseMetaData#getBestRowIdentifier(String, String, String, int,
     *      boolean)
     */
    public static void getBestRowIdentifier(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schema, final String table, final int scope,
        final boolean nullable, final Collection<Identifier> identifiers)
        throws SQLException {

        final ResultSet identifierResultSet =
            databaseMetaData.getBestRowIdentifier(
            catalog, schema, table, scope, nullable);
        try {
            while (identifierResultSet.next()) {
                final Identifier identifier = EntrySet.newInstance(
                    Identifier.class, identifierResultSet);
                identifiers.add(identifier);
            }
        } finally {
            identifierResultSet.close();
        }
    }


    /**
     * 
     * @param databaseMetaData
     * @param table
     * @param scope
     * @param nullable
     * @param identifiers
     * @throws SQLException 
     *
     * @see DatabaseMetaData#getBestRowIdentifier(String, String, String, int,
     *      boolean) 
     */
    public static void getBestRowIdentifier(
        final DatabaseMetaData databaseMetaData, final Table table,
        final int scope, final boolean nullable,
        final Collection<Identifier> identifiers)
        throws SQLException {

        getBestRowIdentifier(
            databaseMetaData, table.getTABLE_CAT(), table.getTABLE_SCHEM(),
            table.getTABLE_NAME(), scope, nullable, identifiers);
    }


    /**
     * 
     * @param databaseMetaData
     * @param table
     * @param scope
     * @param nullable
     * @throws SQLException 
     */
    public static void getBestRowIdentifier(
        final DatabaseMetaData databaseMetaData, final Table table,
        final int scope, final boolean nullable)
        throws SQLException {

        getBestRowIdentifier(
            databaseMetaData, table.getTABLE_CAT(), table.getTABLE_SCHEM(),
            table.getTABLE_NAME(), scope, nullable, table.getIdentifiers());
    }


    /**
     * 
     * @param databaseMetaData
     * @param table
     * @throws SQLException 
     */
    public static void getAllBestRowIdentifier(
        final DatabaseMetaData databaseMetaData, final Table table)
        throws SQLException {

        getBestRowIdentifier(databaseMetaData, table,
                             DatabaseMetaData.bestRowTemporary, true);

        getBestRowIdentifier(databaseMetaData, table,
                             DatabaseMetaData.bestRowTransaction, true);

        getBestRowIdentifier(databaseMetaData, table,
                             DatabaseMetaData.bestRowSession, true);
    }


    /**
     * Creates a new instance.
     */
    public Identifiers() {
        super(Identifier.class);
    }


    /**
     * Returns attributes.
     *
     * @return attributes
     */
    @XmlElement(name = "identifier")
    public Collection<Identifier> getIdentifiers() {
        return super.getEntrySets();
    }


}

