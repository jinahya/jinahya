/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


import com.googlecode.jinahya.lang.FieldEnum;
import com.googlecode.jinahya.lang.FieldEnumHelper;
import java.sql.Connection;
import java.sql.SQLException;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public enum TransactionIsolationLevel
    implements FieldEnum<TransactionIsolationLevel, Integer> {


    /**
     * A constant for {@link Connection#TRANSACTION_NONE}.
     */
    TRANSACTION_NONE(Connection.TRANSACTION_NONE), //                          0
    /**
     * A constant for {@link Connection#TRANSACTION_READ_UNCOMMITTED}.
     */
    TRANSACTION_READ_UNCOMMITTED(Connection.TRANSACTION_READ_UNCOMMITTED), //  1
    /**
     * A constant for {@link Connection#TRANSACTION_READ_COMMITTED}.
     */
    TRANSACTION_READ_COMMITTED(Connection.TRANSACTION_READ_COMMITTED), //      2
    /**
     * A constant for {@link Connection#TRANSACTION_REPEATABLE_READ}.
     */
    TRANSACTION_REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ), //    4
    /**
     * A constant for {@link Connection#TRANSACTION_SERIALIZABLE}.
     */
    TRANSACTION_SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE); //          8


    /**
     * Returns the enum constant of this type with the specified transaction
     * isolation level.
     *
     * @param fieldValue the transaction isolation level, which is one of {@link Connection#TRANSACTION_NONE},
     * {@link Connection#TRANSACTION_READ_UNCOMMITTED},
     * {@link Connection#TRANSACTION_READ_COMMITTED},
     * {@link Connection#TRANSACTION_REPEATABLE_READ}, or
     * {@link Connection#TRANSACTION_SERIALIZABLE}.
     *
     * @return the enum constant with the specified transaction isolation level
     */
    public static TransactionIsolationLevel fromFieldValue(final int fieldValue) {

        return FieldEnumHelper.fromFieldValue(
            TransactionIsolationLevel.class, fieldValue);
    }


    /**
     * Returns the enum constant of this type with the specified connection's
     * current transaction isolation level.
     *
     * @param connection the connection
     *
     * @return the enum constant with the specified connection's current
     * transaction isolation level
     *
     * @throws SQLException if a database access error occurs or this method is
     * called on a closed connection
     *
     * @see Connection#getTransactionIsolation()
     */
    public static TransactionIsolationLevel fromConnection(
        final Connection connection)
        throws SQLException {

        if (connection == null) {
            throw new NullPointerException("connection");
        }

        return fromFieldValue(connection.getTransactionIsolation());
    }


    /**
     * Returns an array containing the field values of this enum type.
     *
     * @return an array containing the field values of this enum type.
     */
    public static Integer[] fieldValues() {

        return FieldEnumHelper.fieldValues(
            TransactionIsolationLevel.class, Integer.class);
    }


    /**
     * Creates a new instance.
     *
     * @param fieldValue
     */
    private TransactionIsolationLevel(final int fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public Integer getFieldValue() {

        return fieldValue;
    }


    /**
     * Attempts to change the transaction isolation level for given connection
     * to this constant's field value.
     *
     * @param connection the connection
     *
     * @throws SQLException if a database access error occurs, this method is
     * called on a closed connection
     *
     * @see Connection#setTransactionIsolation(int)
     */
    public void set(final Connection connection) throws SQLException {

        if (connection == null) {
            throw new NullPointerException("connection");
        }

        connection.setTransactionIsolation(fieldValue);
    }


    /**
     * field value.
     */
    private final int fieldValue;


}
