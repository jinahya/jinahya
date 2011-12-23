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


import com.googlecode.jinahya.sql.metadata.MethodNamesToOmit;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;


/**
 * ExportedKey wrapper.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ExportedKeys extends EntrySetWrapper<ExportedKey> {


    /**
     * Creates a new instance from <code>databaseMetaData</code> with given
     * arguments.
     *
     * @param databaseMetaData database meta data
     * @param catalog catalog
     * @param schema schema
     * @param table table
     * @return a new instance
     * @throws SQLException if an SQL error occurs.
     */
    public static ExportedKeys newInstance(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schema, final String table)
        throws SQLException {

        final ExportedKeys exportedKeys = new ExportedKeys();
        getExportedKeys(databaseMetaData, catalog, schema, table,
                        exportedKeys.getExportedKeys());

        return exportedKeys;
    }


    /**
     * Binds <code>ExportKey</code>s and adds them to specified
     * <code>exportedKeys</code>.
     *
     * @param databaseMetaData database meta data
     * @param catalog catalog
     * @param schema schema
     * @param table table
     * @param exportedKeys the collection to be filled
     * @throws SQLException if an SQL error occurs.
     * @see DatabaseMetaData#getExportedKeys(String, String, String)
     */
    public static void getExportedKeys(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schema, final String table,
        final Collection<ExportedKey> exportedKeys)
        throws SQLException {

        if (MethodNamesToOmit.instanceContainsName("getExportedKeys")) {
            return;
        }

        final ResultSet resultSet =
            databaseMetaData.getExportedKeys(catalog, schema, table);
        try {
            while (resultSet.next()) {
                final ExportedKey exportedKey =
                    ExportedKey.newInstance(resultSet);
                exportedKeys.add(exportedKey);
            }
        } finally {
            resultSet.close();
        }
    }


    /**
     * Binds <code>EexportedKey</code>s and adds them to specified
     * <code>table</code>'s <code>exportedKeys</code> collection field.
     *
     * @param databaseMetaData database meta data
     * @param table table
     * @throws SQLException if an SQL error occurs.
     */
    public static void getExportedKeys(final DatabaseMetaData databaseMetaData,
                                       final Table table)
        throws SQLException {

        getExportedKeys(databaseMetaData, table.getTABLE_CAT(),
                        table.getTABLE_SCHEM(), table.getTABLE_NAME(),
                        table.getExportedKeys());

        for (ExportedKey exportedKey : table.getExportedKeys()) {
            exportedKey.setParent(table);
        }
    }


    /**
     * Creates a new instance.
     */
    public ExportedKeys() {
        super(ExportedKey.class);
    }


    /**
     * Returns attributes.
     *
     * @return attributes
     */
    @XmlElement(name = "exportedKey")
    public Collection<ExportedKey> getExportedKeys() {
        return super.getEntrySets();
    }


}

