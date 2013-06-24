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
public enum ResultSetFetchDirection {


    /**
     * @see ResultSet#FETCH_REVERSE
     */
    FETCH_FORWARD(ResultSet.FETCH_FORWARD),
    /**
     *
     * @see ResultSet#FETCH_REVERSE
     */
    FETCH_REVERSE(ResultSet.FETCH_REVERSE);


    public static ResultSetFetchDirection fromValue(final int value) {

        for (ResultSetFetchDirection constant : values()) {
            if (constant.value == value) {
                return constant;
            }
        }

        throw new IllegalArgumentException("unknown value: " + value);
    }


    private ResultSetFetchDirection(final int value) {
        this.value = value;
    }


    public int getValue() {
        return value;
    }


    private final int value;


}
