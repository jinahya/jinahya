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


import com.googlecode.jinahya.lang.FieldEnumTest;
import java.util.Calendar;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 * @param <E>
 * @param <F>
 */
public abstract class CalendarFieldEnumTest<E extends Enum<E> & CalendarFieldEnum<E, F>, F>
    extends FieldEnumTest<E, F> {


    public CalendarFieldEnumTest(final Class<E> enumType,
                                 final int calendarField) {

        super(enumType);

        this.calendarField = calendarField;
    }


    @Test
    public void testSet() {

        final Calendar calendar = Calendar.getInstance();

        final E[] enumConstants = enumType.getEnumConstants();

        for (final E enumConstant : enumConstants) {
            enumConstant.set(calendar);
            Assert.assertEquals(Integer.valueOf(calendar.get(calendarField)),
                                enumConstant.getFieldValue());
            System.out.println(calendar.getTime());
        }
    }


    protected final int calendarField;


}
