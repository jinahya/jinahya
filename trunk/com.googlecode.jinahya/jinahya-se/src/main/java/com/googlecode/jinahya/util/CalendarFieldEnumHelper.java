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


package com.googlecode.jinahya.util;


import com.googlecode.jinahya.lang.FieldEnumHelper;
import java.util.Calendar;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public final class CalendarFieldEnumHelper {


    /**
     *
     * @param <E> enum type parameter
     * @param enumType enum type
     * @param calendar calendar
     * @param field field
     *
     * @return
     */
    public static <E extends Enum<E> & CalendarFieldEnum<E, Integer>> E get(
        final Class<E> enumType, final Calendar calendar, final int field) {

        if (calendar == null) {
            throw new NullPointerException("calendar");
        }

        if (enumType == null) {
            throw new NullPointerException("enumType");
        }

        return FieldEnumHelper.fromFieldValue(enumType, calendar.get(field));
    }


    /**
     *
     * @param <E> enum type parameter
     * @param calendar calendar
     * @param field field
     * @param value value
     */
    public static <E extends Enum<E> & CalendarFieldEnum<E, Integer>> void set(
        final Calendar calendar, final int field, final E value) {

        if (calendar == null) {
            throw new NullPointerException("calendar");
        }

        if (value == null) {
            throw new NullPointerException("value");
        }

        calendar.set(field, value.getFieldValue());
    }


    /**
     * protected constructor.
     */
    private CalendarFieldEnumHelper() {

        super();
    }


}
