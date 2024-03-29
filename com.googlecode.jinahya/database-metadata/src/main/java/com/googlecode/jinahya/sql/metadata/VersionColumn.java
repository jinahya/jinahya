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
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class VersionColumn extends ChildPairSet<Table> {


    /**
     * Retrieves all version columns for specified
     * <code>table</code>.
     *
     * @param databaseMetaData database metadata
     * @param table table
     * @throws SQLException if a database access error occurs.
     */
    static void getVersionColumns(final DatabaseMetaData databaseMetaData,
                                  final Table table)
        throws SQLException {

        if (table.getMetadata().excludes.contains("getVersionColumns")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getVersionColumns(
            table.getValue("TABLE_CAT"), table.getValue("TABLE_SCHEM"),
            table.getValue("TABLE_NAME"));
        try {
            while (resultSet.next()) {
                final VersionColumn versionColumn = PairSet.newInstance(
                    VersionColumn.class, resultSet);
                versionColumn.setParent(table);
                table.getVersionColumns().add(versionColumn);
            }
        } finally {
            resultSet.close();
        }
    }


    public String getSCOPE() {
        return getValue("SCOPE");
    }


    public void setSCOPE(final String SCOPE) {
        setValue("SCOPE", SCOPE);
    }


    public String getCOLUMN_NAME() {
        return getValue("COLUMN_NAME");
    }


    public void setCOLUMN_NAME(final String COLUMN_NAME) {
        setValue("COLUMN_NAME", COLUMN_NAME);
    }


    public String getDATA_TYPE() {
        return getValue("DATA_TYPE");
    }


    public void setDATA_TYPE(final String DATA_TYPE) {
        setValue("DATA_TYPE", DATA_TYPE);
    }


    public String getTYPE_NAME() {
        return getValue("TYPE_NAME");
    }


    public void setTYPE_NAME(final String TYPE_NAME) {
        setValue("TYPE_NAME", TYPE_NAME);
    }


    public String getCOLUMN_SIZE() {
        return getValue("COLUMN_SIZE");
    }


    public void setCOLUMN_SIZE(final String COLUMN_SIZE) {
        setValue("COLUMN_SIZE", COLUMN_SIZE);
    }


    public String getBUFFER_LENGTH() {
        return getValue("BUFFER_LENGTH");
    }


    public void setBUFFER_LENGTH(final String BUFFER_LENGTH) {
        setValue("BUFFER_LENGTH", BUFFER_LENGTH);
    }


    public String getDECIMAL_DIGITS() {
        return getValue("DECIMAL_DIGITS");
    }


    public void setDECIMAL_DIGITS(final String DECIMAL_DIGITS) {
        setValue("DECIMAL_DIGITS", DECIMAL_DIGITS);
    }


    public String getPSEUDO_COLUMN() {
        return getValue("PSEUDO_COLUMN");
    }


    public void setPSEUDO_COLUMN(final String PSEUDO_COLUMN) {
        setValue("PSEUDO_COLUMN", PSEUDO_COLUMN);
    }


}

