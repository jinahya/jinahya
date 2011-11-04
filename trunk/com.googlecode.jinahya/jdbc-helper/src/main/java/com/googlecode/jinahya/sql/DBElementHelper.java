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
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class DBElementHelper {


    /**
     * Selects a new DBElement instance.
     *
     * @param <E> element type parameter
     * @param connection connection
     * @param elementType element type
     * @param id id
     * @return a new instance or null if not found
     * @throws SQLException if an SQL error occurs.
     */
    public static <E extends DBElement> E select(final Connection connection,
                                                 final Class<E> elementType,
                                                 final Long id)
        throws SQLException {

        try {
            final E element = elementType.newInstance();
            element.setId(id);

            if (select(connection, element)) {
                return element;
            }
            return null;

        } catch (IllegalAccessException iae) {
            throw new SQLException(
                "failed to create instance of " + elementType, iae);
        } catch (InstantiationException ie) {
            throw new SQLException(
                "failed to create instance of " + elementType, ie);
        }
    }


    /**
     * Selects given <code>element</code>.
     *
     * @param <E> element type parameter
     * @param connection connection
     * @param element element
     * @return true if successfully selected; false if not found
     * @throws SQLException if an SQL error occurs.
     */
    public static <E extends DBElement> boolean select(
        final Connection connection, final E element)
        throws SQLException {

        if (connection == null) {
            throw new NullPointerException("null connection");
        }

        if (element == null) {
            throw new NullPointerException("null element");
        }
        if (element.getId() == null) {
            throw new IllegalArgumentException("null element.id");
        }

        final PreparedStatement preparedStatement = connection.prepareStatement(
            "SELECT * FROM " + element.getTableName()
            + " WHERE " + element.getIdColumnName() + " = ?");
        try {
            int parameterIndex = 0;
            preparedStatement.setLong(++parameterIndex, element.getId());

            final ResultSet resultSet = preparedStatement.executeQuery();
            try {
                if (!resultSet.next()) {
                    return false;
                }

                element.read(resultSet, "");
                return true;

            } finally {
                resultSet.close();
            }
        } finally {
            preparedStatement.close();
        }
    }


    public static <E extends DBElement> boolean delete(
        final Connection connection, final String tableName,
        final String idColumnName, final Long id)
        throws SQLException {

        if (connection == null) {
            throw new NullPointerException("null connection");
        }

        final PreparedStatement preparedStatement = connection.prepareStatement(
            "DELETE FROM " + tableName
            + " WHERE " + idColumnName + " = ?");
        try {
            int parameterIndex = 0;
            preparedStatement.setLong(++parameterIndex, id);

            return preparedStatement.executeUpdate() == 1;

        } finally {
            preparedStatement.close();
        }
    }


    /**
     * Delete given <code>element</code>.
     *
     * @param <E> element type parameter
     * @param connection connection
     * @param element element
     * @return true if successfully deleted; false otherwise
     * @throws SQLException if an SQL error occurs.
     */
    public static <E extends DBElement> boolean delete(
        final Connection connection, final E element)
        throws SQLException {

        return delete(connection, element.getTableName(),
                      element.getIdColumnName(), element.getId());
    }


    /**
     * Creates a new instance.
     */
    private DBElementHelper() {
        super();
    }


}

