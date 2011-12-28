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
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schemaPattern
     * @param tableNamePattern
     * @return
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
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schemaPattern
     * @param tableNamePattern
     * @param privileges
     * @throws SQLException if a database access error occurs.
     *
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
                    TablePrivilege.newInstance(resultSet);
                privileges.add(tablePrivilege);
            }
        } finally {
            resultSet.close();
        }
    }


    /**
     * 
     * @param databaseMetaData
     * @param catalog
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

