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


/**
 * Column wrapper.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class UDTs extends EntrySetWrapper<UDT> {


    /**
     * Creates a new instance.
     *
     * @param databaseMetaData
     * @param catalog
     * @param schemaNamePattern
     * @param typeNamePattern
     * @param types
     * @return
     * @throws SQLException 
     */
    public static UDTs newInstance(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaNamePattern, final String typeNamePattern,
        final int[] types)
        throws SQLException {

        final UDTs instance = new UDTs();

        getUDTs(databaseMetaData, catalog, schemaNamePattern, typeNamePattern,
                types, instance.getUDTs());

        return instance;
    }


    /**
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schemaNamePattern
     * @param typeNamePattern
     * @param types
     * @param userDefinedTypes
     * @throws SQLException if a database access error occurs
     */
    public static void getUDTs(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaNamePattern, final String typeNamePattern,
        final int[] types, final Collection<UDT> userDefinedTypes)
        throws SQLException {

        try {
            final ResultSet resultSet = databaseMetaData.getUDTs(
                catalog, schemaNamePattern, typeNamePattern, types);
            try {
                while (resultSet.next()) {
                    final UDT instance = EntrySet.newInstance(
                        UDT.class, resultSet);
                    userDefinedTypes.add(instance);
                }
            } finally {
                resultSet.close();
            }
        } catch (Exception e) {
        }
    }


    /**
     * 
     * @param databaseMetaData
     * @param catalog
     * @throws SQLException 
     */
    public static void getAllUDTs(final DatabaseMetaData databaseMetaData,
                                  final Catalog catalog)
        throws SQLException {

        getUDTs(databaseMetaData, catalog.getTABLE_CAT(), null, null, null,
                catalog.getUDTs());

        for (UDT udt : catalog.getUDTs()) {
            udt.setParent(catalog);
        }
    }


    /**
     * Creates a new instance.
     */
    public UDTs() {
        super(UDT.class);
    }


    /**
     * Returns UDTs.
     *
     * @return UDTs.
     */
    @XmlElement(name = "UDT")
    public Collection<UDT> getUDTs() {
        return super.getEntrySets();
    }


}

