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
 * Binding for TableType.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class TableType extends PairSet {


    /**
     * Retrieves table types.
     *
     * @param databaseMetaData database meta data
     * @param metadata metadata
     * @throws SQLException if a database access error occurs.
     * @see DatabaseMetaData#getTables(String, String, String, String[])
     */
    static void getTableTypes(final DatabaseMetaData databaseMetaData,
                              final Metadata metadata)
        throws SQLException {

        if (metadata.excludes.contains("getTableTypes")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getTableTypes();
        try {
            while (resultSet.next()) {
                final TableType tableType = PairSet.newInstance(
                    TableType.class, resultSet);
                tableType.setMetadata(metadata);
                metadata.getTableTypes().add(tableType);
            }
        } finally {
            resultSet.close();
        }
    }


    /**
     * Returns the value of
     * <code>TABLE_TYPE</code> entry.
     *
     * @return TABLE_TYPE
     */
    public String getTABLE_TYPE() {
        return getValue("TABLE_TYPE");
    }


    /**
     * Sets the value of
     * <code>TABLE_TYPE</code> entry.
     *
     * @param TABLE_TYPE TABLE_TYPE
     */
    public void setTABLE_TYPE(final String TABLE_TYPE) {
        setValue("TABLE_TYPE", TABLE_TYPE);
    }


    @Override
    public String toString() {
        return super.toString() + "/" + getTABLE_TYPE();
    }


    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof TableType)) {
            return false;
        }

        final TableType tableType = (TableType) obj;

        if (!(getTABLE_TYPE() == tableType.getTABLE_TYPE())
            || (getTABLE_TYPE() != null
                && getTABLE_TYPE().equals(tableType.getTABLE_TYPE()))) {
            return false;
        }

        return true;
    }


    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode = 37 * hashCode
                   + (getTABLE_TYPE() == null ? 0 : getTABLE_TYPE().hashCode());

        return hashCode;
    }


}

