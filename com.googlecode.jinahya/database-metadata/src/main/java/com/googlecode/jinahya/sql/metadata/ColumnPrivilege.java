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


package com.googlecode.jinahya.sql.metadata;


import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Binding for column privileges.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ColumnPrivilege extends Privilege<Column> {


    /*
     * Retrieves all column privileges of given <code>table</code>.
     *
     * @param databaseMetaData database meta data @param table table @throws
     * SQLException if an database access error occurs. @see
     * DatabaseMetaData#getColumnPrivileges(String, String, String, String)
     * static void getColumnPrivileges(final DatabaseMetaData databaseMetaData,
     * final Table table) throws SQLException {
     *
     * if (table.getMetadata().excludes.contains("getColumnPrivileges")) {
     * return; }
     *
     * final ResultSet resultSet = databaseMetaData.getColumnPrivileges(
     * table.getValue("TABLE_CAT"), table.getValue("TABLE_SCHEM"),
     * table.getValue("TABLE_NAME"), null); try { while (resultSet.next()) {
     * final ColumnPrivilege columnPrivilege =
     * EntrySet.newInstance(ColumnPrivilege.class, resultSet);
     * columnPrivilege.setParent(table);
     * table.getColumnPrivileges().add(columnPrivilege); } } finally {
     * resultSet.close(); } }
     */
    /**
     *
     * @param databaseMetaData database meta data
     * @param column column
     * @throws SQLException if a database access error occurs
     */
    static void getColumnPrivileges(final DatabaseMetaData databaseMetaData,
                                    final Column column)
        throws SQLException {

        if (column.getMetadata().excludes.contains("getColumnPrivileges")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getColumnPrivileges(
            column.getValue("TABLE_CAT"), column.getValue("TABLE_SCHEM"),
            column.getValue("TABLE_NAME"), column.getValue("COLUMN_NAME"));
        try {
            while (resultSet.next()) {
                final ColumnPrivilege columnPrivilege =
                    EntrySet.newInstance(ColumnPrivilege.class, resultSet);
                columnPrivilege.setParent(column);
                column.getPrivileges().add(columnPrivilege);
            }
        } finally {
            resultSet.close();
        }
    }


    /**
     * Returns the value of 'COLUMN_NAME' entry.
     *
     * @return 'COLUMN_NAME' entry value
     */
    public String getCOLUMN_NAME() {
        return getValue("COLUMN_NAME");
    }


    /**
     * Sets the value of 'COLUMN_NAME' entry.
     *
     * @param COLUMN_NAME 'COLUMN_NAME' entry value
     */
    public void setCOLUMN_NAME(final String COLUMN_NAME) {
        setValue("COLUMN_NAME", COLUMN_NAME);
    }


}

