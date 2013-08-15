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


    public static ResultSetFetchDirection fromFieldValue(final int fieldValue) {

        return FieldEnumHelper.fromFieldValue(
            ResultSetFetchDirection.class, fieldValue);
    }


    public static Integer[] fieldValues() {

        return FieldEnumHelper.fieldValues(ResultSetFetchDirection.class,
                                           Integer.class);
    }


    private ResultSetFetchDirection(final int fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public Integer getFieldValue() {

        return fieldValue;
    }


    private final int fieldValue;


}

