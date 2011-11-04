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
public final class AccessibleHelper {


    /**
     * Selects a new DBElement instance.
     *
     * @param <A> accessible type parameter
     * @param connection connection
     * @param accessibleType element type
     * @param id id
     * @return a new instance or null if not found
     * @throws SQLException if an SQL error occurs.
     */
    public static <A extends Accessible> A select(
        final Connection connection, final Class<A> accessibleType,
        final Long id)
        throws SQLException {

        try {
            final A element = accessibleType.newInstance();
            element.setId(id);

            if (select(connection, element)) {
                return element;
            }
            return null;

        } catch (IllegalAccessException iae) {
            throw new SQLException(
                "failed to create instance of " + accessibleType, iae);
        } catch (InstantiationException ie) {
            throw new SQLException(
                "failed to create instance of " + accessibleType, ie);
        }
    }


    /**
     * Selects given <code>element</code>.
     *
     * @param <A> accessible type parameter
     * @param connection connection
     * @param accessible accessible
     * @return true if successfully selected; false if not found
     * @throws SQLException if an SQL error occurs.
     */
    public static <A extends Accessible> boolean select(
        final Connection connection, final A accessible)
        throws SQLException {

        if (connection == null) {
            throw new NullPointerException("null connection");
        }

        if (accessible == null) {
            throw new NullPointerException("null element");
        }
        if (accessible.getId() == null) {
            throw new IllegalArgumentException("null element.id");
        }

        final PreparedStatement preparedStatement = connection.prepareStatement(
            "SELECT * FROM " + accessible.getTableName()
            + " WHERE " + accessible.getIdColumnName() + " = ?");
        try {
            int parameterIndex = 0;
            preparedStatement.setLong(++parameterIndex, accessible.getId());

            final ResultSet resultSet = preparedStatement.executeQuery();
            try {
                if (!resultSet.next()) {
                    return false;
                }

                accessible.read(resultSet, "");
                return true;

            } finally {
                resultSet.close();
            }
        } finally {
            preparedStatement.close();
        }
    }


    /**
     * Deletes an accessible identified by given <code>id</code>.
     *
     * @param <A> accessible type parameter
     * @param connection connection
     * @param tableName table name
     * @param idColumnName id column name
     * @param id id
     * @return true if successfully delete; false otherwise
     * @throws SQLException if an SQL error occurs.
     */
    public static <A extends Accessible> boolean delete(
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
     * @param <A> accessible type parameter
     * @param connection connection
     * @param accessible element
     * @return true if successfully deleted; false otherwise
     * @throws SQLException if an SQL error occurs.
     */
    public static <A extends Accessible> boolean delete(
        final Connection connection, final A accessible)
        throws SQLException {

        return delete(connection, accessible.getTableName(),
                      accessible.getIdColumnName(), accessible.getId());
    }


    /**
     * Creates a new instance.
     */
    private AccessibleHelper() {
        super();
    }


}

