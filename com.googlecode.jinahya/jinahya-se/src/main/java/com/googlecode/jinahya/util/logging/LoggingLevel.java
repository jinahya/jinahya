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


package com.googlecode.jinahya.util.logging;


import com.googlecode.jinahya.lang.FieldEnum;
import com.googlecode.jinahya.lang.FieldEnumHelper;
import java.util.logging.Level;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public enum LoggingLevel implements FieldEnum<LoggingLevel, Integer> {


    OFF(Level.OFF),
    SEVERE(Level.SEVERE),
    WARNING(Level.WARNING),
    INFO(Level.INFO),
    CONFIG(Level.CONFIG),
    FINE(Level.FINE),
    FINER(Level.FINER),
    FINEST(Level.FINEST),
    ALL(Level.ALL);


    /**
     * Returns the enum constant of this type with the specified field value.
     *
     * @param fieldValue field value
     *
     * @throws IllegalArgumentException if this enum type has no constant with
     * the specified field value.
     *
     * @return the enum constant with the specified field value.
     */
    public static LoggingLevel fromFieldValue(final int fieldValue) {

        return FieldEnumHelper.fromFieldValue(LoggingLevel.class, fieldValue);
    }


    /**
     * Returns the enum constant of this type with the specified level's int
     * value.
     *
     * @param level level
     *
     * @throws IllegalArgumentException if this enum type has no constant with
     * the specified level's int value.
     *
     * @return the enum constant with the specified level's field value.
     */
    public static LoggingLevel fromLevel(final Level level) {

        if (level == null) {
            throw new NullPointerException("level");
        }

        return fromFieldValue(level.intValue());
    }


    /**
     * Returns an array containing the field values of this enum type, in order
     * they are declared.
     *
     * @return an array containing the fields values of this enum type, in the
     * order they are declared
     */
    public Integer[] fieldValues() {

        return FieldEnumHelper.fieldValues(LoggingLevel.class, int.class);
    }


    private LoggingLevel(final int fieldValue) {

        this.fieldValue = fieldValue;
    }


    private LoggingLevel(final Level level) {

        this(level.intValue());
    }


    @Override
    public Integer getFieldValue() {

        return fieldValue;
    }


    /**
     * field value.
     */
    private final int fieldValue;


}
