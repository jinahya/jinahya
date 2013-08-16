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
 * Constants for {@link Calendar#DAY_OF_WEEK}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public enum CalendarDayOfWeek implements FieldEnum<CalendarDayOfWeek, Integer> {


    SUNDAY(Calendar.SUNDAY), //       1
    MONDAY(Calendar.MONDAY), //       2
    TUESDAY(Calendar.TUESDAY),//      3
    WEDNESDAY(Calendar.WEDNESDAY), // 4
    THURSDAY(Calendar.THURSDAY), //   5
    FRIDAY(Calendar.FRIDAY), //       6
    SATURDAY(Calendar.SATURDAY); //   7


    public static CalendarDayOfWeek fromFieldValue(final int fieldValue) {

        return FieldEnumHelper.fromFieldValue(
            CalendarDayOfWeek.class, fieldValue);
    }


    public Integer[] fieldValues() {

        return FieldEnumHelper.fieldValues(CalendarDayOfWeek.class, int.class);
    }


    /**
     * Creates a new instance.
     *
     * @param fieldValue field value.
     */
    private CalendarDayOfWeek(final int fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public Integer getFieldValue() {

        return fieldValue;
    }


    /**
     * Sets given {@code calendar}'s {@link Calendar#DAY_OF_WEEK} field with
     * {@code fieldValue}.
     *
     * @param calendar the calendar to set
     */
    public void set(final Calendar calendar) {

        if (calendar == null) {
            throw new NullPointerException("calendar");
        }

        calendar.set(Calendar.DAY_OF_WEEK, fieldValue);
    }


    /**
     * field value.
     */
    private final int fieldValue;


}

