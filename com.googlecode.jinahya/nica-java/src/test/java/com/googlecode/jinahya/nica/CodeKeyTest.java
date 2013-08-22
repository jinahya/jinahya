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
public class CodeKeyTest {


    @Test
    public void testKeys() {

        final Set<String> keySet = new HashSet<String>();
        for (CodeKey value : CodeKey.values()) {
            final String key = value.getFieldValue();
            Assert.assertNotNull(key);
            Assert.assertEquals(key, key.trim());
            Assert.assertFalse(key.isEmpty());
            Assert.assertFalse(key.trim().isEmpty());
            Assert.assertTrue(keySet.add(key));
        }
    }


    @Test
    public static void testFromKey() {

        try {
            final CodeKey code = CodeKey.fromFieldValue(null);
            Assert.fail("passed: fromKey(null)");
        } catch (IllegalArgumentException iae) {
            // expected;
        }

        try {
            final CodeKey code = CodeKey.fromFieldValue("NOT_EXIST");
            Assert.fail("passed: fromKey(\"NOT_EXIST\"");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        for (CodeKey value : CodeKey.values()) {
            Assert.assertEquals(CodeKey.fromFieldValue(value.getFieldValue()), value);
        }
    }


}

