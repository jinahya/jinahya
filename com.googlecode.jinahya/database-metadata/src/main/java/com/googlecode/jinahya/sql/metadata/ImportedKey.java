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
 * ImportedKey.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ImportedKey extends ChildPairSet<Table> {


    /**
     * Retrieves imported keys for given <code>table</code>.
     *
     * @param databaseMetaData database metadata
     * @param table table
     * @throws SQLException if a database access error occurs
     * @see DatabaseMetaData#getImportedKeys(String, String, String)
     */
    static void getImportedKeys(final DatabaseMetaData databaseMetaData,
                                final Table table)
        throws SQLException {

        if (table.getMetadata().excludes.contains("getImportedKeys")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getImportedKeys(
            table.getValue("TABLE_CAT"), table.getValue("TABLE_SCHEM"),
            table.getValue("TABLE_NAME"));
        try {
            while (resultSet.next()) {
                final ImportedKey importedKey =
                    PairSet.newInstance(ImportedKey.class, resultSet);
                importedKey.setParent(table);
                table.getImportedKeys().add(importedKey);
            }
        } finally {
            resultSet.close();
        }
    }


    /**
     * Returns the value of 'PKTABLE_CAT' entry.
     *
     * @return 'PKTABLE_CAT' entry value.
     */
    public String getPKTABLE_CAT() {
        return getValue("PKTABLE_CAT");
    }


    /**
     * Sets the value of 'PKTABLE_CAT' entry.
     *
     * @param PKTABLE_CAT 'PKTABLE_CAT' entry value.
     */
    public void setPKTABLE_CAT(final String PKTABLE_CAT) {
        setValue("PKTABLE_CAT", PKTABLE_CAT);
    }


    public String getPKTABLE_SCHEM() {
        return getValue("PKTABLE_SCHEM");
    }


    public void setPKTABLE_SCHEM(final String PKTABLE_SCHEM) {
        setValue("PKTABLE_SCHEM", PKTABLE_SCHEM);
    }


    public String getPKTABLE_NAME() {
        return getValue("PKTABLE_NAME");
    }


    public void setPKTABLE_NAME(final String PKTABLE_NAME) {
        setValue("PKTABLE_NAME", PKTABLE_NAME);
    }


    public String getPKCOLUMN_NAME() {
        return getValue("PKCOLUMN_NAME");
    }


    public void setPKCOLUMN_NAME(final String PKCOLUMN_NAME) {
        setValue("PKCOLUMN_NAME", PKCOLUMN_NAME);
    }


    public String getFKTABLE_CAT() {
        return getValue("FKTABLE_CAT");
    }


    public void setFKTABLE_CAT(final String FKTABLE_CAT) {
        setValue("FKTABLE_CAT", FKTABLE_CAT);
    }


    public String getFKTABLE_SCHEM() {
        return getValue("FKTABLE_SCHEM");
    }


    public void setFKTABLE_SCHEM(final String FKTABLE_SCHEM) {
        setValue("FKTABLE_SCHEM", FKTABLE_SCHEM);
    }


    public String getFKTABLE_NAME() {
        return getValue("FKTABLE_NAME");
    }


    public void setFKTABLE_NAME(final String FKTABLE_NAME) {
        setValue("FKTABLE_NAME", FKTABLE_NAME);
    }


    public String getFKCOLUMN_NAME() {
        return getValue("FKCOLUMN_NAME");
    }


    public void setFKCOLUMN_NAME(final String FKCOLUMN_NAME) {
        setValue("FKCOLUMN_NAME", FKCOLUMN_NAME);
    }


    public String getKEY_SEQ() {
        return getValue("KEY_SEQ");
    }


    public void setKEY_SEQ(final String KEY_SEQ) {
        setValue("KEY_SEQ", KEY_SEQ);
    }


    public String getUPDATE_RULE() {
        return getValue("UPDATE_RULE");
    }


    public void setUPDATE_RULE(final String UPDATE_RULE) {
        setValue("UPDATE_RULE", UPDATE_RULE);
    }


    public String getDELETE_RULE() {
        return getValue("DELETE_RULE");
    }


    public void setDELETE_RULE(final String DELETE_RULE) {
        setValue("DELETE_RULE", DELETE_RULE);
    }


    public String getFK_NAME() {
        return getValue("FK_NAME");
    }


    public void setFK_NAME(final String FK_NAME) {
        setValue("FK_NAME", FK_NAME);
    }


    public String getPK_NAME() {
        return getValue("PK_NAME ");
    }


    public void setPK_NAME(final String PK_NAME) {
        setValue("PK_NAME", PK_NAME);
    }


    public String getDEFERRABILITY() {
        return getValue("DEFERRABILITY");
    }


    public void setDEFERRABILITY(final String DEFERRABILITY) {
        setValue("DEFERRABILITY", DEFERRABILITY);
    }


}

