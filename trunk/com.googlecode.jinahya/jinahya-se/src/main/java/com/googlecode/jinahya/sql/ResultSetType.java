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


import java.sql.ResultSet;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public enum ResultSetType {


    /**
     * @see ResultSet#TYPE_FORWARD_ONLY
     */
    TYPE_FORWARD_ONLY(ResultSet.TYPE_FORWARD_ONLY),
    /**
     *
     * @see ResultSet#TYPE_SCROLL_INSENSITIVE
     */
    TYPE_SCROLL_INSENSITIVE(ResultSet.TYPE_SCROLL_INSENSITIVE),
    /**
     * @see ResultSet#TYPE_SCROLL_SENSITIVE
     */
    TYPE_SCROLL_SENSITIVE(ResultSet.TYPE_SCROLL_SENSITIVE);


    public static ResultSetType fromValue(final int value) {

        for (ResultSetType constant : values()) {
            if (constant.value == value) {
                return constant;
            }
        }

        throw new IllegalArgumentException("unknown value: " + value);
    }


    private ResultSetType(final int value) {
        this.value = value;
    }


    public int getValue() {
        return value;
    }


    private final int value;


}
