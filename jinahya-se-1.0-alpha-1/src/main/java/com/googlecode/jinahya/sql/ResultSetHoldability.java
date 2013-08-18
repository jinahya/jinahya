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
 * Constants for {@link ResultSet}'s holdabilities.
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public enum ResultSetHoldability
    implements FieldEnum<ResultSetHoldability, Integer> {


    /**
     * Constant for {@link ResultSet#HOLD_CURSORS_OVER_COMMIT}.
     *
     * @see java.sql.ResultSet#HOLD_CURSORS_OVER_COMMIT
     */
    HOLD_CURSORS_OVER_COMMIT(ResultSet.HOLD_CURSORS_OVER_COMMIT), // 1
    /**
     * Constant for {@link ResultSet#CLOSE_CURSORS_AT_COMMIT}.
     *
     * @see java.sql.ResultSet#CLOSE_CURSORS_AT_COMMIT
     */
    CLOSE_CURSORS_AT_COMMIT(ResultSet.CLOSE_CURSORS_AT_COMMIT); // 2


    /**
     *
     * @param fieldValue
     *
     * @return
     */
    public static ResultSetHoldability fromFieldValue(final int fieldValue) {

        return FieldEnumHelper.fromFieldValue(
            ResultSetHoldability.class, fieldValue);
    }


    /**
     *
     * @param resultSet resultSet
     *
     * @return
     *
     * @throws SQLException if a database access error occurs or this method is
     * called on a closed result set
     */
    public static ResultSetHoldability fromResultSet(final ResultSet resultSet)
        throws SQLException {

        if (resultSet == null) {
            throw new NullPointerException("resultSet");
        }

        return fromFieldValue(resultSet.getHoldability());
    }


    public static Integer[] fieldValues() {

        return FieldEnumHelper.fieldValues(
            ResultSetHoldability.class, int.class);
    }


    private ResultSetHoldability(final int fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public Integer getFieldValue() {

        return fieldValue;
    }


    private final int fieldValue;


}

