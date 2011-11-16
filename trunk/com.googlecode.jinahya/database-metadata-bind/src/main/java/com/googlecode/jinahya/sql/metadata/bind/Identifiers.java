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
 * Identifier collection.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class Identifiers extends MetadataSet<Identifier> {


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
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schema
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
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schema, final String table, final int scope,
        final boolean nullable, final Collection<Identifier> identifiers)
        throws SQLException {

        final ResultSet identifierResultSet =
            databaseMetaData.getBestRowIdentifier(
            catalog, schema, table, scope, nullable);
        try {
            while (identifierResultSet.next()) {
                final Identifier identifier = Metadata.newInstance(
                    Identifier.class, identifierResultSet);
                identifiers.add(identifier);
            }
        } finally {
            identifierResultSet.close();
        }
    }


    public static void getBestRowIdentifier(
        final DatabaseMetaData databaseMetaData, final Table table,
        final int scope,
        final boolean nullable, final Collection<Identifier> identifiers)
        throws SQLException {

        getBestRowIdentifier(
            databaseMetaData, table.getTABLE_CAT(), table.getTABLE_SCHEM(),
            table.getTABLE_NAME(), scope, nullable, identifiers);
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
        return super.getMetadata();
    }


}

