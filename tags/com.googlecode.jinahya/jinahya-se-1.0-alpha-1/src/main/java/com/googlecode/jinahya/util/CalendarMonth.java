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
 * Constants for {@link Calendar#MONTH}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public enum CalendarMonth implements CalendarFieldEnum<CalendarMonth, Integer> {


    JANUARY(Calendar.JANUARY), //        0
    FEBRUARY(Calendar.FEBRUARY), //      1
    MARCH(Calendar.MARCH), //            2
    APRIL(Calendar.APRIL), //            3
    MAY(Calendar.MAY), //                4
    JUNE(Calendar.JUNE), //              5
    JULY(Calendar.JULY), //              6
    AUGUST(Calendar.AUGUST), //          7
    SEPTEMBER(Calendar.SEPTEMBER), //    8
    OCTOBER(Calendar.OCTOBER), //        9
    NOVEMBER(Calendar.NOVEMBER), //     10
    DECEMBER(Calendar.DECEMBER); //     11
    //UNDECIMBER(Calendar.UNDECIMBER); // 12


    /**
     * The target field of {@link Calendar} which this enum type is for.
     *
     * @see Calendar#MONTH
     */
    public static final int CALENDAR_FIELD = Calendar.MONTH;


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
    public static CalendarMonth fromFieldValue(final int fieldValue) {

        return FieldEnumHelper.fromFieldValue(CalendarMonth.class, fieldValue);
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
    public static CalendarMonth fromCalendar(final Calendar calendar) {

        return CalendarFieldEnumHelper.get(CalendarMonth.class, calendar,
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

        return FieldEnumHelper.fieldValues(CalendarMonth.class, Integer.class);
    }


    /**
     * Creates a new instance.
     *
     * @param fieldValue field value.
     */
    private CalendarMonth(final int fieldValue) {

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
