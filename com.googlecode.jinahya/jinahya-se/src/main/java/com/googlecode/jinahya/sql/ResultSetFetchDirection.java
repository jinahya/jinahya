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
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Constants for {@link ResultSet}'s fetch directions.
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public enum ResultSetFetchDirection
    implements FieldEnum<ResultSetFetchDirection, Integer> {


    /**
     * Constant for {@link ResultSet#FETCH_FORWARD}.
     *
     * @see ResultSet#FETCH_REVERSE
     */
    FETCH_FORWARD(ResultSet.FETCH_FORWARD), // 1000
    /**
     * Constant for {@link ResultSet#FETCH_REVERSE}.
     *
     * @see ResultSet#FETCH_REVERSE
     */
    FETCH_REVERSE(ResultSet.FETCH_REVERSE), // 1001
    /**
     * Constant for {@link ResultSet#FETCH_UNKNOWN}.
     *
     * @see ResultSet#FETCH_UNKNOWN
     */
    FETCH_UNKNOWN(ResultSet.FETCH_UNKNOWN); // 1002


    /**
     * Returns the enum constant of this type with the specified
     * {@code fieldValue}.
     *
     * @param fieldValue the fetch direction value; one of
     * {@link ResultSet#FETCH_FORWARD}, {@link ResultSet#FETCH_REVERSE}, or
     * {@link ResultSet#FETCH_UNKNOWN}.
     *
     * @throws IllegalArgumentException if this enum type has no constant with
     * the specified fieldValue.
     *
     * @return the enum constant with the specified fieldValue.
     */
    public static ResultSetFetchDirection fromFieldValue(final int fieldValue) {

        return FieldEnumHelper.fromFieldValue(
            ResultSetFetchDirection.class, fieldValue);
    }


    /**
     * Returns the enum constant of this type with the specified
     * {@code resultSet}'s current fetch direction value.
     *
     * @param resultSet the result set
     *
     * @throws SQLException see {@link ResultSet#getFetchDirection()}
     *
     * @return the enum constant with the specified resultSet's current fetch
     * direction.
     */
    public static ResultSetFetchDirection fromResultSet(
        final ResultSet resultSet)
        throws SQLException {

        if (resultSet == null) {
            throw new NullPointerException("resultSet");
        }

        return fromFieldValue(resultSet.getFetchDirection());
    }


    /**
     * Returns an array containing the field values of this enum type, in the
     * order they are declared.
     *
     * @return an array containing field values.
     */
    public static Integer[] fieldValues() {

        return FieldEnumHelper.fieldValues(ResultSetFetchDirection.class,
                                           Integer.class);
    }


    /**
     * Creates a new instance.
     *
     * @param fieldValue field value
     */
    private ResultSetFetchDirection(final int fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public Integer getFieldValue() {

        return fieldValue;
    }


    /**
     * Invokes {@link ResultSet#setFetchDirection(int) } with
     * {@code fieldValue}.
     *
     * @param resultSet the result set.
     *
     * @throws SQLException if a database access error occurs.
     * @see ResultSet#setFetchDirection(int)
     */
    public void set(final ResultSet resultSet) throws SQLException {

        if (resultSet == null) {
            throw new NullPointerException("resultSet");
        }

        resultSet.setFetchDirection(fieldValue);
    }


    /**
     * field value.
     */
    private final int fieldValue;


}

