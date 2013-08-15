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
 * Constants for {@link ResultSet}'s types.
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public enum ResultSetType implements FieldEnum<ResultSetType, Integer> {


    /**
     * Constant for {@link ResultSet#TYPE_FORWARD_ONLY}.
     *
     * @see ResultSet#TYPE_FORWARD_ONLY
     */
    TYPE_FORWARD_ONLY(ResultSet.TYPE_FORWARD_ONLY), // 1003
    /**
     * Constant for {@link ResultSet#TYPE_SCROLL_INSENSITIVE}.
     *
     * @see ResultSet#TYPE_SCROLL_INSENSITIVE
     */
    TYPE_SCROLL_INSENSITIVE(ResultSet.TYPE_SCROLL_INSENSITIVE), // 1004
    /**
     * Constant for {@link ResultSet#TYPE_SCROLL_SENSITIVE}.
     *
     * @see ResultSet#TYPE_SCROLL_SENSITIVE
     */
    TYPE_SCROLL_SENSITIVE(ResultSet.TYPE_SCROLL_SENSITIVE); // 1005


    public static ResultSetType fromFieldValue(final int fieldValue) {

        return FieldEnumHelper.fromFieldValue(
            ResultSetType.class, int.class, fieldValue);
    }


    public static Integer[] fieldValues() {

        return FieldEnumHelper.fieldValues(ResultSetType.class, int.class);
    }


    private ResultSetType(final int fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public Integer getFieldValue() {

        return fieldValue;
    }


    private final int fieldValue;


}

