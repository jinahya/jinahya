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
 * ColumnPrivilege wrapper.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ColumnPrivileges extends Privileges<ColumnPrivilege, Table> {


    public static ColumnPrivileges newInstance(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schema, final String table, final String columnNamePattern)
        throws SQLException {

        final ColumnPrivileges instance = new ColumnPrivileges();
        getColumnPrivileges(databaseMetaData, catalog, schema,
                            table, columnNamePattern, instance.getPrivileges());

        return instance;
    }


    public static void getColumnPrivileges(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schema, final String table, final String columnNamePattern,
        final Collection<ColumnPrivilege> privileges)
        throws SQLException {

        final ResultSet columnPrvilegesResultSet =
            databaseMetaData.getColumnPrivileges(
            catalog, schema, table, columnNamePattern);
        try {
            while (columnPrvilegesResultSet.next()) {
                final ColumnPrivilege privilege = EntrySet.newInstance(
                    ColumnPrivilege.class, columnPrvilegesResultSet);
                privileges.add(privilege);
            }
        } finally {
            columnPrvilegesResultSet.close();
        }
    }


    public static void getAllColumnPrivileges(
        final DatabaseMetaData databaseMetaData, final Table table)
        throws SQLException {

        getColumnPrivileges(databaseMetaData, table.getTABLE_CAT(),
                            table.getTABLE_SCHEM(), table.getTABLE_NAME(), null,
                            table.getColumnPrivileges());

        for (ColumnPrivilege columnPrivilege : table.getColumnPrivileges()) {
            columnPrivilege.setTable(table);
        }
    }


    /**
     * Creates a new instance.
     */
    public ColumnPrivileges() {
        super(ColumnPrivilege.class);
    }


}

