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


package com.googlecode.jinahya.sql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * A helper for DBCollection.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class DBCollectableHelper {


    /**
     * Select a new instance.
     *
     * @param <C> collection type parameter
     * @param <A> accessible type parameter;
     * @param connection connection
     * @param tableName element table name
     * @param idColumnName element id column name
     * @param collectableType collectable type
     * @return a new collection instance
     * @throws SQLException if an SQL error occurs
     */
    public static <C extends DBCollectable<A>, A extends DBAccessible> C select(
        final Connection connection, final String tableName,
        final String idColumnName, final Class<C> collectableType)
        throws SQLException {

        try {
            final C collection = collectableType.newInstance();
            select(connection, tableName, idColumnName, collection);
            return collection;
        } catch (IllegalAccessException iae) {
            throw new SQLException(
                "failed to create instance of " + collectableType, iae);
        } catch (InstantiationException ie) {
            throw new SQLException(
                "failed to create instance of " + collectableType, ie);
        }
    }


    /**
     * Selects given <code>collectio</code>.
     *
     * @param <C> collection type parameter
     * @param <A> accessible type parameter
     * @param connection connection
     * @param tableName element table name
     * @param idColumnName element id column name
     * @param collectable collectable
     * @throws SQLException if an SQL error occurs
     */
    public static <C extends DBCollectable<A>, A extends DBAccessible> void select(
        final Connection connection, final String tableName,
        final String idColumnName, final C collectable)
        throws SQLException {

        if (connection == null) {
            throw new NullPointerException("null connection");
        }

        if (tableName == null) {
            throw new NullPointerException("null elementTableName");
        }

        if (idColumnName == null) {
            throw new NullPointerException("null elementIdColumnName");
        }

        if (collectable == null) {
            throw new NullPointerException("null element");
        }

        final PreparedStatement preparedStatement = connection.prepareStatement(
            "SELECT * FROM " + tableName
            + " ORDER BY " + idColumnName + " ASC");
        try {
            final ResultSet resultSet = preparedStatement.executeQuery();
            try {
                final Class<A> elementType = collectable.getAccessibleType();
                while (resultSet.next()) {
                    try {
                        final A element = elementType.newInstance();
                        element.read(resultSet, "");
                        collectable.getAccessibles().add(element);
                    } catch (IllegalAccessException iae) {
                        throw new SQLException(
                            "failed to create instance of " + elementType, iae);
                    } catch (InstantiationException ie) {
                        throw new SQLException(
                            "failed to create instance of " + elementType, ie);
                    }
                }
            } finally {
                resultSet.close();
            }
        } finally {
            preparedStatement.close();
        }
    }


    /**
     * Creates a new instance.
     */
    private DBCollectableHelper() {
        super();
    }


}

