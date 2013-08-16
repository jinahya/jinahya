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


import com.googlecode.jinahya.lang.FieldEnum;
import com.googlecode.jinahya.lang.FieldEnumHelper;
import java.util.Calendar;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public enum CalendarMonth implements FieldEnum<CalendarMonth, Integer> {


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
     * Returns the constant mapped to given {@code fieldValue}. An
     * {@code IllegalArgumentException} will be thrown if no constant found.
     *
     * @param fieldValue field value
     *
     * @return the mapped instance.
     */
    public static CalendarMonth fromFieldValue(final int fieldValue) {

        return FieldEnumHelper.fromFieldValue(CalendarMonth.class, fieldValue);
    }


    /**
     * Returns all field values.
     *
     * @return all field values.
     */
    public Integer[] fieldValues() {

        return FieldEnumHelper.fieldValues(CalendarMonth.class, int.class);
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


    /**
     * Sets given {@code calendar}'s {@link Calendar#MONTH} field with
     * {@code fieldValue}.
     *
     * @param calendar the calendar to set
     */
    public void set(final Calendar calendar) {

        if (calendar == null) {
            throw new NullPointerException("calendar");
        }

        calendar.set(Calendar.MONTH, fieldValue);
    }


    /**
     * field value.
     */
    private final int fieldValue;


}
