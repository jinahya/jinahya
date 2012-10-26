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


import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class PlatformTest {


    @Test
    public void testIdsAreValid() {

        for (NicaPlatform value : NicaPlatform.values()) {
            final String id = value.id();
            Assert.assertNotNull(id);
            Assert.assertEquals(id, id.trim());
            Assert.assertFalse(id.isEmpty());
            Assert.assertFalse(id.trim().isEmpty());
        }
    }


    @Test
    public static void testFromId() {

        try {
            final NicaPlatform platform = NicaPlatform.fromId(null);
            Assert.fail("passed: fromId(null)");
        } catch (IllegalArgumentException iae) {
            // expected;
        }

        try {
            final NicaPlatform platform = NicaPlatform.fromId("/not/exist");
            Assert.fail("passed: fromId(\"/not/exist\"");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        for (NicaPlatform value : NicaPlatform.values()) {
            Assert.assertEquals(NicaPlatform.fromId(value.id()), value);
        }
    }


    @Test
    public void testIdUniqueness() {
        for (NicaPlatform value : NicaPlatform.values()) {
            for (NicaPlatform other : NicaPlatform.values()) {
                if (!value.equals(other) && value.id().equals(other.id())) {
                    Assert.fail("duplicated id");
                }
            }
        }
    }


}

