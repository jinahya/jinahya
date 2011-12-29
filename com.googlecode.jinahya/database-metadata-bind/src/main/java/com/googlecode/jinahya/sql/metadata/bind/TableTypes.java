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

import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * TableType wrapper.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class TableTypes extends EntrySets<TableType> {


    /**
     * Creates a new instance.
     *
     * @param databaseMetaData database metadata
     * @return a new instance
     * @throws SQLException if a database access error occurs
     */
    public static TableTypes newInstance(
        final DatabaseMetaData databaseMetaData)
        throws SQLException {

        final TableTypes instance = new TableTypes();

        getTableTypes(databaseMetaData, instance.getTableTypes());

        return instance;
    }


    /**
     * Retrieved table types.
     *
     * @param databaseMetaData database metadata
     * @param tableTypes the collection to be filled
     * @throws SQLException if a database access error occurs.
     *
     * @see DatabaseMetaData#getTables(String, String, String, String[])
     */
    public static void getTableTypes(final DatabaseMetaData databaseMetaData,
                                     final Collection<TableType> tableTypes)
        throws SQLException {

        final ResultSet resultSet = databaseMetaData.getTableTypes();
        try {
            while (resultSet.next()) {

                // ----------------------------------------------------- entries
                final TableType tableType = EntrySet.newInstance(
                    TableType.class, resultSet);
                tableTypes.add(tableType);

            }
        } finally {
            resultSet.close();
        }
    }


    /**
     * Creates a new instance.
     */
    public TableTypes() {
        super(TableType.class);
    }


    /**
     * 
     * @param TABLE_TYPE
     * @return 
     */
    public TableType getByTABLE_TYPE(final String TABLE_TYPE) {

        for (TableType tableType : getTableTypes()) {
            if (tableType.getTABLE_TYPE().equals(TABLE_TYPE)) {
                return tableType;
            }
        }

        return null;
    }


    @XmlElement(name = "tableType")
    public Collection<TableType> getTableTypes() {
        return super.getEntrySets();
    }


    private Map<String, TableType> tableTypes;


}

