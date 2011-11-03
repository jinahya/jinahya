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
public final class JDBCElementHelper {


    /**
     * Selects a new JDBCAccessible instance.
     *
     * @param <E> element type parameter
     * @param connection connection
     * @param elementType accessible type
     * @param id id
     * @return a new instance or null if not found
     * @throws SQLException if an SQL error occurs.
     */
    public static <E extends JDBCElement<I>, I> E selectInstance(
        final Connection connection, final Class<E> elementType, final I id)
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
     * 
     * @param <E> element type parameter
     * @param connection connection
     * @param element element
     * @return true if successfully selected; false if not found
     * @throws SQLException if an SQL error occurs.
     */
    public static <E extends JDBCElement<?>> boolean select(
        final Connection connection, final E element)
        throws SQLException {

        if (connection == null) {
            throw new NullPointerException("null connection");
        }

        if (element == null) {
            throw new NullPointerException("null accessible");
        }
        if (element.getId() == null) {
            throw new IllegalArgumentException("null element.id");
        }

        final PreparedStatement preparedStatement = connection.prepareStatement(
            "SELECT * FROM " + element.getTableName()
            + " WHERE " + element.getIdColumnName() + " = ?");
        try {
            int parameterIndex = 0;
            preparedStatement.setObject(++parameterIndex, element.getId(),
                                        element.getIdColumnType());

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


    public static <A extends JDBCElement<?>> boolean delete(
        final Connection connection, final A accessible)
        throws SQLException {

        if (connection == null) {
            throw new NullPointerException("null connection");
        }

        if (accessible == null) {
            throw new NullPointerException("null accessible");
        }

        final PreparedStatement preparedStatement = connection.prepareStatement(
            "DELETEFROM " + accessible.getTableName()
            + " WHERE " + accessible.getIdColumnName() + " = ?");
        try {
            int parameterIndex = 0;
            preparedStatement.setObject(++parameterIndex, accessible.getId(),
                                        accessible.getIdColumnType());

            return preparedStatement.executeUpdate() == 1;

        } finally {
            preparedStatement.close();
        }
    }


    /**
     * Creates a new instance.
     */
    private JDBCElementHelper() {
        super();
    }


}

