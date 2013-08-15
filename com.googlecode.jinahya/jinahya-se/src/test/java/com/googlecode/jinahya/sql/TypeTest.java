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


package com.googlecode.jinahya.sql;


import com.googlecode.jinahya.lang.FieldEnumHelper;
import java.lang.reflect.Field;
import java.sql.Types;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class TypeTest {


    @Test
    public static void verify() {
        FieldEnumHelper.verify(Type.class);
    }


    @Test
    public void testToTypes()
        throws NoSuchFieldException, IllegalAccessException {

        for (Type value : Type.values()) {

            // locate by name, compare field
            final String name = value.name();
            {
                final Field field = Types.class.getField(name);
                Assert.assertEquals(field.getInt(null),
                                    value.getFieldValue().intValue());
            }

            // locate by field, compare name
            final int fieldValue = value.getFieldValue();
            for (Field field : Types.class.getFields()) {
                if (field.getInt(null) == fieldValue) {
                    Assert.assertEquals(field.getName(), value.name());
                    break;
                }
            }
        }
    }


    @Test
    public void testFromTypes()
        throws NoSuchFieldException, IllegalAccessException {

        for (Field field : Types.class.getFields()) {

            // locate by name, compare raw
            final String name = field.getName();
            {
                final Type type = Type.valueOf(name);
                Assert.assertEquals(type.getFieldValue(), field.get(null));
            }

            // locate by raw, compare name
            final int fieldValue = field.getInt(null);
            {
                final Type type = Type.fromFieldValue(fieldValue);
                Assert.assertEquals(type.name(), name);
            }
        }
    }


}

