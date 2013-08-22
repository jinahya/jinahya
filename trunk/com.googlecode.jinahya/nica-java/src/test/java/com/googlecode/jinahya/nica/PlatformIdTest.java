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
public class PlatformIdTest {


    @Test
    public void testIds() {

        final Set<String> idSet = new HashSet<String>();
        for (PlatformId value : PlatformId.values()) {
            final String id = value.getFieldValue();
            Assert.assertNotNull(id);
            Assert.assertEquals(id, id.trim());
            Assert.assertFalse(id.isEmpty());
            Assert.assertFalse(id.trim().isEmpty());
            Assert.assertTrue(idSet.add(id));
        }
    }


    @Test
    public static void testFromId() {

        try {
            final PlatformId platform = PlatformId.fromFieldValue(null);
            Assert.fail("passed: fromId(null)");
        } catch (IllegalArgumentException iae) {
            // expected;
        }

        try {
            final PlatformId platform = PlatformId.fromFieldValue("/not/exist");
            Assert.fail("passed: fromId(\"/not/exist\"");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        for (PlatformId value : PlatformId.values()) {
            Assert.assertEquals(PlatformId.fromFieldValue(value.getFieldValue()), value);
        }
    }


}

