/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.util;


import com.googlecode.jinahya.lang.FieldEnumHelper;
import java.util.Calendar;


/**
 * Constants for {@link Calendar#AM_PM}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public enum CalendarPeriodOfDay
    implements CalendarFieldEnum<CalendarPeriodOfDay, Integer> {


    /**
     * Constant for {@link Calendar#AM}.
     */
    AM(Calendar.AM), // 0
    /**
     * Constant for {@link Calendar#PM}.
     */
    PM(Calendar.PM); // 1


    /**
     * The target field of {@link Calendar} which this enum type is for.
     *
     * @see Calendar#AM_PM
     */
    public static final int CALENDAR_FIELD = Calendar.AM_PM;


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
    public static CalendarPeriodOfDay fromFieldValue(final int fieldValue) {

        return FieldEnumHelper.fromFieldValue(
            CalendarPeriodOfDay.class, fieldValue);
    }


    /**
     * Returns the enum constant of this type with the specified calendar's
     * field value.
     *
     * @param calendar calendar
     *
     * @throws IllegalArgumentException if this enum type has no constant with
     * the specified calendar's field value.
     *
     * @return the enum constant with the specified calendar's field value.
     */
    public static CalendarPeriodOfDay fromCalendar(final Calendar calendar) {

        return CalendarFieldEnumHelper.get(CalendarPeriodOfDay.class, calendar,
                                           CALENDAR_FIELD);
    }


    /**
     * Returns an array containing the field values of this enum type, in order
     * they are declared.
     *
     * @return an array containing the fields values of this enum type, in the
     * order they are declared
     */
    public Integer[] fieldValues() {

        return FieldEnumHelper.fieldValues(
            CalendarPeriodOfDay.class, Integer.class);
    }


    /**
     * Creates a new instance.
     *
     * @param fieldValue field value.
     */
    private CalendarPeriodOfDay(final int fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public Integer getFieldValue() {

        return fieldValue;
    }


    @Override
    public void set(final Calendar calendar) {

        CalendarFieldEnumHelper.set(calendar, CALENDAR_FIELD, this);
    }


    /**
     * field value.
     */
    private final int fieldValue;


}
