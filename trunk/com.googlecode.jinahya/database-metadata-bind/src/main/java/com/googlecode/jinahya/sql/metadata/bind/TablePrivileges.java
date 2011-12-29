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


/**
 * TablePrivilege wrapper.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class TablePrivileges extends Privileges<TablePrivilege, Catalog> {


    /**
     * Binds a new instance from given <code>databaseMetaData</code>.
     *
     * @param databaseMetaData database metadata
     * @param catalog catalog
     * @param schemaPattern schemaPattern
     * @param tableNamePattern tableNamePattern
     * @return a new instance
     * @throws SQLException if a database access error occurs.
     */
    public static TablePrivileges newInstance(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaPattern, final String tableNamePattern)
        throws SQLException {

        final TablePrivileges instance = new TablePrivileges();
        getTablePrivileges(databaseMetaData, catalog, schemaPattern,
                           tableNamePattern, instance.getPrivileges());

        return instance;
    }


    /**
     * Retrieves table privileges.
     *
     * @param databaseMetaData database metadata
     * @param catalog catalog
     * @param schemaPattern schemaPattern
     * @param tableNamePattern tableNamePattern
     * @param privileges privilege collection
     * @throws SQLException if a database access error occurs.
     * @see DatabaseMetaData#getTablePrivileges(String, String, String)
     */
    public static void getTablePrivileges(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaPattern, final String tableNamePattern,
        final Collection<TablePrivilege> privileges)
        throws SQLException {

        final ResultSet resultSet =
            databaseMetaData.getTablePrivileges(
            catalog, schemaPattern, tableNamePattern);
        try {
            while (resultSet.next()) {
                final TablePrivilege tablePrivilege =
                    EntrySet.newInstance(TablePrivilege.class, resultSet);
                privileges.add(tablePrivilege);
            }
        } finally {
            resultSet.close();
        }
    }


    /**
     * Retrieves all table privileges for specified <code>catalog</code>.
     *
     * @param databaseMetaData database metadata
     * @param catalog catalog
     * @throws SQLException if a database access error occurs.
     */
    public static void getAllTablePrivileges(
        final DatabaseMetaData databaseMetaData, final Catalog catalog)
        throws SQLException {

        getTablePrivileges(databaseMetaData, catalog.getTABLE_CAT(),
                           null, null, catalog.getTablePrivileges());
    }


    /**
     * Creates a new instance.
     */
    public TablePrivileges() {
        super(TablePrivilege.class);
    }


}

