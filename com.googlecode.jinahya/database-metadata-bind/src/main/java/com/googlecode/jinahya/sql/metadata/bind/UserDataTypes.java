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
 * User Data Type.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class UserDataTypes extends EntrySets<UserDataType> {


    /**
     * Creates a new instance.
     *
     * @param databaseMetaData database metadata
     * @param catalog catalog
     * @param schemaNamePattern schemaNamePattern
     * @param typeNamePattern typeNamePattern
     * @param types types
     * @return a new instance
     * @throws SQLException if a database access error occurs
     */
    public static UserDataTypes newInstance(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaNamePattern, final String typeNamePattern,
        final int[] types)
        throws SQLException {

        final UserDataTypes instance = new UserDataTypes();
        getUDTs(databaseMetaData, catalog, schemaNamePattern, typeNamePattern,
                types, instance.getUDTs());

        return instance;
    }


    /**
     * Retrieves user data types.
     *
     * @param databaseMetaData database metadata
     * @param catalog catalog
     * @param schemaNamePattern schemaNamePattern
     * @param typeNamePattern typeNamePattern
     * @param types types
     * @param userDefinedTypes user data type collection
     * @throws SQLException if a database access error occurs
     * @see DatabaseMetaData#getUDTs(String, String, String, int[])
     */
    public static void getUDTs(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaNamePattern, final String typeNamePattern,
        final int[] types, final Collection<UserDataType> userDefinedTypes)
        throws SQLException {

        if (MethodNamesToOmit.instanceContainsName("getUDTs")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getUDTs(
            catalog, schemaNamePattern, typeNamePattern, types);
        try {
            while (resultSet.next()) {
                final UserDataType instance =
                    EntrySet.newInstance(UserDataType.class, resultSet);
                userDefinedTypes.add(instance);
            }
        } finally {
            resultSet.close();
        }
    }


    /**
     * Retrieves all UDTs mapped to given <code>catalog</code>.
     *
     * @param databaseMetaData meta
     * @param catalog catalog
     * @throws SQLException if a database access error occurs.
     * @see #getUDTs(DatabaseMetaData, String, String, String, int[],
     * Collection)
     */
    public static void getAllUDTs(final DatabaseMetaData databaseMetaData,
                                  final Catalog catalog)
        throws SQLException {

        getUDTs(databaseMetaData, catalog.getTABLE_CAT(), null, null, null,
                catalog.getUDTs());

        for (UserDataType udt : catalog.getUDTs()) {
            udt.setParent(catalog);
        }
    }


    /**
     * Creates a new instance.
     */
    public UserDataTypes() {
        super(UserDataType.class);
    }


    /**
     * Returns UDTs.
     *
     * @return UDTs.
     */
    @XmlElement(name = "UDT")
    public Collection<UserDataType> getUDTs() {
        return super.getEntrySets();
    }


}

