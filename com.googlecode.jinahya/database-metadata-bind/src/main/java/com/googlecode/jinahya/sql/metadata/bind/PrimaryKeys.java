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
import javax.xml.bind.annotation.XmlRootElement;


/**
 * ExportedKey wrapper.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class PrimaryKeys extends EntrySetWrapper<PrimaryKey> {


    public static PrimaryKeys newInstance(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schema, final String table)
        throws SQLException {

        final PrimaryKeys primaryKeys = new PrimaryKeys();

        getPrimaryKeys(databaseMetaData, catalog, schema, table,
                       primaryKeys.getPrimaryKeys());

        return primaryKeys;
    }


    public static void getPrimaryKeys(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schema, final String table,
        final Collection<PrimaryKey> primaryKeys)
        throws SQLException {

        if (MethodNamesToOmit.instanceContainsName("getPrimaryKeys")) {
            return;
        }

        final ResultSet resultSet =
            databaseMetaData.getPrimaryKeys(catalog, schema, table);
        try {
            while (resultSet.next()) {
                final PrimaryKey primaryKey = PrimaryKey.newInstance(resultSet);
                primaryKeys.add(primaryKey);
            }
        } finally {
            resultSet.close();
        }
    }


    public static void getPrimaryKeys(final DatabaseMetaData databaseMetaData,
                                      final Table table)
        throws SQLException {

        getPrimaryKeys(databaseMetaData, table.getTABLE_CAT(),
                       table.getTABLE_SCHEM(), table.getTABLE_NAME(),
                       table.getPrimaryKeys());

        for (PrimaryKey primaryKey : table.getPrimaryKeys()) {
            primaryKey.setTable(table);
        }
    }


    /**
     * Creates a new instance.
     */
    public PrimaryKeys() {
        super(PrimaryKey.class);
    }


    /**
     * Returns attributes.
     *
     * @return attributes
     */
    @XmlElement(name = "primaryKey")
    public Collection<PrimaryKey> getPrimaryKeys() {
        return super.getEntrySets();
    }


}

