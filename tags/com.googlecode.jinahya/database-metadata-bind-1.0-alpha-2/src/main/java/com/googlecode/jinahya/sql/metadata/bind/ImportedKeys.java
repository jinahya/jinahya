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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * ExportedKey wrapper.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class ImportedKeys extends EntrySetWrapper<ImportedKey> {


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
    public static ImportedKeys newInstance(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schema, final String table)
        throws SQLException {

        final ImportedKeys importedKeys = new ImportedKeys();
        getImportedKeys(databaseMetaData, catalog, schema, table,
                        importedKeys.getImportedKeys());

        return importedKeys;
    }


    public static void getImportedKeys(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schema, final String table,
        final Collection<ImportedKey> importedKeys)
        throws SQLException {

        final ResultSet importedKeyResultSet =
            databaseMetaData.getImportedKeys(catalog, schema, table);
        try {
            while (importedKeyResultSet.next()) {
                final ImportedKey importedKey = EntrySet.newInstance(
                    ImportedKey.class, importedKeyResultSet);
                importedKeys.add(importedKey);
            }
        } finally {
            importedKeyResultSet.close();
        }
    }


    public static void getImportedKeys(final DatabaseMetaData databaseMetaData,
                                       final Table table)
        throws SQLException {

        getImportedKeys(databaseMetaData, table.getTABLE_CAT(),
                        table.getTABLE_SCHEM(), table.getTABLE_NAME(),
                        table.getImportedKeys());
    }


    /**
     * Creates a new instance.
     */
    public ImportedKeys() {
        super(ImportedKey.class);
    }


    /**
     * Returns attributes.
     *
     * @return attributes
     */
    @XmlElement(name = "importedKey")
    public Collection<ImportedKey> getImportedKeys() {
        return super.getEntrySets();
    }


}
