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
 * Constants for {@link ResultSet}'s concurrencies.
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public enum ResultSetConcurrency
    implements FieldEnum<ResultSetConcurrency, Integer> {


    /**
     * Constant for {@link ResultSet#CONCUR_READ_ONLY}.
     *
     * @see ResultSet#CONCUR_READ_ONLY
     */
    CONCUR_READ_ONLY(ResultSet.CONCUR_READ_ONLY), // 1007
    /**
     * Constant for {@link ResultSet#CONCUR_UPDATABLE}.
     *
     * @see ResultSet#CONCUR_UPDATABLE
     */
    CONCUR_UPDATABLE(ResultSet.CONCUR_UPDATABLE); // 1008


    /**
     * Returns the constant whose value is mapped to given {@code value}.
     *
     * @param fieldValue either {@link ResultSet#CONCUR_READ_ONLY} or
     * {@link ResultSet#CONCUR_UPDATABLE}.
     *
     * @return mapped constant.
     */
    public static ResultSetConcurrency fromFieldValue(final int fieldValue) {

        return FieldEnumHelper.fromFieldValue(
            ResultSetConcurrency.class, Integer.class, fieldValue);
    }


    public static Integer[] fieldValues() {

        return FieldEnumHelper.fieldValues(ResultSetConcurrency.class,
                                           Integer.class);
    }


    private ResultSetConcurrency(final int fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public Integer getFieldValue() {

        return fieldValue;
    }


    private final int fieldValue;


}

