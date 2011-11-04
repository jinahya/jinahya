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
public final class DBCollectionHelper {


    /**
     * Select a new instance.
     *
     * @param <C> collection type parameter
     * @param <E> element type parameter;
     * @param connection connection
     * @param tableName element table name
     * @param idColumnName element id column name
     * @param collectionType collection type
     * @return a new collection instance
     * @throws SQLException if an SQL error occurs
     */
    public static <C extends DBCollection<E>, E extends DBElement> C select(
        final Connection connection, final String tableName,
        final String idColumnName, final Class<C> collectionType)
        throws SQLException {

        try {
            final C collection = collectionType.newInstance();
            select(connection, tableName, idColumnName, collection);
            return collection;
        } catch (IllegalAccessException iae) {
            throw new SQLException(
                "failed to create instance of " + collectionType, iae);
        } catch (InstantiationException ie) {
            throw new SQLException(
                "failed to create instance of " + collectionType, ie);
        }
    }


    /**
     * Selects given <code>collectio</code>.
     *
     * @param <C> collection type parameter
     * @param <E> element type parameter
     * @param connection connection
     * @param tableName element table name
     * @param idColumnName element id column name
     * @param collection collection
     * @throws SQLException if an SQL error occurs
     */
    public static <C extends DBCollection<E>, E extends DBElement> void select(
        final Connection connection, final String tableName,
        final String idColumnName, final C collection)
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

        if (collection == null) {
            throw new NullPointerException("null element");
        }

        final PreparedStatement preparedStatement = connection.prepareStatement(
            "SELECT * FROM " + tableName
            + " ORDER BY " + idColumnName + " ASC");
        try {
            final ResultSet resultSet = preparedStatement.executeQuery();
            try {
                final Class<E> elementType = collection.getElementType();
                while (resultSet.next()) {
                    try {
                        final E element = elementType.newInstance();
                        element.read(resultSet, "");
                        collection.getElementCollection().add(element);
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
    private DBCollectionHelper() {
        super();
    }


}

