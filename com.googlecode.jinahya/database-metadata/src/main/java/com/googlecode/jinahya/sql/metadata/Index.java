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
import java.util.Collection;


/**
 * Index binding.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Index extends ChildEntrySet<Table> {


    /**
     * Retrieves indices of specified <code>table</code>.
     *
     * @param databaseMetaData database metadata
     * @param table table
     * @throws SQLException if a database access error occurs.
     */
    static void getIndexInfo(final DatabaseMetaData databaseMetaData,
                             final Table table)
        throws SQLException {

        if (table.getMetadata().excludes.contains("getIndexInfo")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getIndexInfo(
            table.getValue("TABLE_CAT"), table.getValue("TABLE_SCHEM"),
            table.getValue("TABLE_NAME"), false, false);
        try {
            while (resultSet.next()) {
                final Index index =
                    EntrySet.newInstance(Index.class, resultSet);
                index.setParent(table);
                table.getIndices().add(index);
            }
        } finally {
            resultSet.close();
        }
    }


    public String getTABLE_CAT() {
        return getValue("TABLE_CAT");
    }


    public void setTABLE_CAT(final String TABLE_CAT) {
        setValue("TABLE_CAT", TABLE_CAT);
    }


    public String getTABLE_SCHEM() {
        return getValue("TABLE_SCHEM");
    }


    public void setTABLE_SCHEM(final String TABLE_SCHEM) {
        setValue("TABLE_SCHEM", TABLE_SCHEM);
    }


    public String getTABLE_NAME() {
        return getValue("TABLE_NAME");
    }


    public void setTABLE_NAME(final String TABLE_NAME) {
        setValue("TABLE_NAME", TABLE_NAME);
    }


    public String getNON_UNIQUE() {
        return getValue("NON_UNIQUE");
    }


    public void setNON_UNIQUE(final String NON_UNIQUE) {
        setValue("NON_UNIQUE", NON_UNIQUE);
    }


    public String getINDEX_QUALIFIER() {
        return getValue("INDEX_QUALIFIER");
    }


    public void setINDEX_QUALIFIER(final String INDEX_QUALIFIER) {
        setValue("INDEX_QUALIFIER", INDEX_QUALIFIER);
    }


    public String getINDEX_NAME() {
        return getValue("INDEX_NAME");
    }


    public void setINDEX_NAME(final String INDEX_NAME) {
        setValue("INDEX_NAME", INDEX_NAME);
    }


    public String getTYPE() {
        return getValue("TYPE");
    }


    public void setTYPE(final String TYPE) {
        setValue("TYPE", TYPE);
    }


    public String getORDINAL_POSITION() {
        return getValue("ORDINAL_POSITION");
    }


    public void setORDINAL_POSITION(final String ORDINAL_POSITION) {
        setValue("ORDINAL_POSITION", ORDINAL_POSITION);
    }


    public String getCOLUMN_NAME() {
        return getValue("COLUMN_NAME");
    }


    public void setCOLUMN_NAME(final String COLUMN_NAME) {
        setValue("COLUMN_NAME", COLUMN_NAME);
    }


    public String getASC_OR_DESC() {
        return getValue("ASC_OR_DESC");
    }


    public void setASC_OR_DESC(final String ASC_OR_DESC) {
        setValue("ASC_OR_DESC", ASC_OR_DESC);
    }


    public String getCARDINALITY() {
        return getValue("CARDINALITY");
    }


    public void setCARDINALITY(final String CARDINALITY) {
        setValue("CARDINALITY", CARDINALITY);
    }


    public String getPAGES() {
        return getValue("PAGES");
    }


    public void setPAGES(final String PAGES) {
        setValue("PAGES", PAGES);
    }


    /**
     * Returns the value of 'FILTER_CONDITION' entry value.
     *
     * @return 'FILTER_CONDITION' entry value
     */
    public String getFILTER_CONDITION() {
        return getValue("FILTER_CONDITION");
    }


    /**
     * Sets the value of 'FILTER_CONDITION' entry value.
     *
     * @param FILTER_CONDITION 'FILTER_CONDITION' entry value
     */
    public void setFILTER_CONDITION(final String FILTER_CONDITION) {
        setValue("FILTER_CONDITION", FILTER_CONDITION);
    }


}

