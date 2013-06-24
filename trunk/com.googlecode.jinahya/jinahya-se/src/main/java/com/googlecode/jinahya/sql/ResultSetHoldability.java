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
public enum ResultSetHoldability {


    /**
     * @see java.sql.ResultSet#HOLD_CURSORS_OVER_COMMIT
     */
    HOLD_CURSORS_OVER_COMMIT(ResultSet.HOLD_CURSORS_OVER_COMMIT),
    /**
     *
     * @see java.sql.ResultSet#CLOSE_CURSORS_AT_COMMIT
     */
    CLOSE_CURSORS_AT_COMMIT(ResultSet.CLOSE_CURSORS_AT_COMMIT);


    public static ResultSetHoldability fromValue(final int value) {

        for (ResultSetHoldability constant : values()) {
            if (constant.value == value) {
                return constant;
            }
        }

        throw new IllegalArgumentException("unknown value: " + value);
    }


    private ResultSetHoldability(final int value) {
        this.value = value;
    }


    public int getValue() {
        return value;
    }


    private final int value;


}
