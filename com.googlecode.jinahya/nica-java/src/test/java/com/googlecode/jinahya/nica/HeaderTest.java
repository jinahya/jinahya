/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.nica;


import java.util.HashSet;
import java.util.Set;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HeaderTest {


    @Test
    public void testFieldNames() {

        final Set<String> fieldNameSet = new HashSet<String>();
        for (Header value : Header.values()) {
            final String fieldName = value.fieldName();
            Assert.assertNotNull(fieldName);
            Assert.assertEquals(fieldName, fieldName.trim());
            Assert.assertFalse(fieldName.isEmpty());
            Assert.assertFalse(fieldName.trim().isEmpty());
            Assert.assertTrue(fieldNameSet.add(fieldName));
        }
    }


    @Test
    public static void testFromFieldName() {

        try {
            final Header header = Header.fromFieldName(null);
            Assert.fail("passed: fromFieldName(null)");
        } catch (IllegalArgumentException iae) {
            // expected;
        }

        try {
            final Header header = Header.fromFieldName("Not-Exist");
            Assert.fail("passed: fromId(\"/Not-Exist\"");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        for (Header value : Header.values()) {
            Assert.assertEquals(Header.fromFieldName(value.fieldName()), value);
        }
    }


}

