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

import java.util.Collection;


/**
 * A helper for DatabaseCollection.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class DatabaseCollectableHelper {


    public static <C extends DatabaseCollectable<A>, A extends AbstractDatabaseAccessible<?>> C select(
        final Connection connection, final String tableName,
        final String idColumnName, final Class<C> collectableType)
        throws SQLException {

        try {
            final C collectable = collectableType.newInstance();
            select(connection, tableName, idColumnName, collectable);
            return collectable;
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(
                "failed to create a new instance of " + collectableType, iae);
        } catch (InstantiationException ie) {
            throw new RuntimeException(
                "failed to create a new instance of " + collectableType, ie);
        }
    }


    public static <C extends DatabaseCollectable<A>, A extends DatabaseAccessible<?>> void select(
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
                final Class<A> accessibleType = collectable.getAccessibleType();
                final Collection<A> accessibles = collectable.getAccessibles();
                accessibles.clear();
                while (resultSet.next()) {
                    try {
                        final A accessible = accessibleType.newInstance();
                        accessible.read(resultSet);
                        accessibles.add(accessible);
                    } catch (IllegalAccessException iae) {
                        throw new RuntimeException(
                            "failed to create a new instance of "
                            + accessibleType, iae);
                    } catch (InstantiationException ie) {
                        throw new RuntimeException(
                            "failed to create a new instance of "
                            + accessibleType, ie);
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
    private DatabaseCollectableHelper() {
        super();
    }


}

