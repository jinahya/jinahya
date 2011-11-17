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

import javax.xml.bind.annotation.XmlRootElement;


/**
 * TablePrivilege wrapper.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class TablePrivileges extends Privileges<TablePrivilege> {


    public static TablePrivileges newInstance(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaPattern, final String tableNamePattern)
        throws SQLException {

        final TablePrivileges instance = new TablePrivileges();
        getTablePrivileges(databaseMetaData, catalog, schemaPattern,
                           tableNamePattern, instance.getPrivileges());

        return instance;
    }


    public static void getTablePrivileges(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaPattern, final String tableNamePattern,
        final Collection<TablePrivilege> privileges)
        throws SQLException {

        final ResultSet tablePrvilegeResultSet =
            databaseMetaData.getTablePrivileges(
            catalog, schemaPattern, tableNamePattern);
        try {
            while (tablePrvilegeResultSet.next()) {
                final TablePrivilege privilege = EntrySet.newInstance(
                    TablePrivilege.class, tablePrvilegeResultSet);
                privileges.add(privilege);
            }
        } finally {
            tablePrvilegeResultSet.close();
        }
    }


    public static void getTablePrivileges(
        final DatabaseMetaData databaseMetaData, final Table table)
        throws SQLException {

        getTablePrivileges(databaseMetaData, table.getTABLE_CAT(),
                           table.getTABLE_SCHEM(), table.getTABLE_NAME(),
                           table.getPrivileges());
    }


    /**
     * Creates a new instance.
     */
    public TablePrivileges() {
        super(TablePrivilege.class);
    }


}

