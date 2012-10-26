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
public class HeaderTest {


    @Test
    public void testIdsAreValid() {

        for (NicaHeader value : NicaHeader.values()) {
            final String fieldName = value.fieldName();
            Assert.assertNotNull(fieldName);
            Assert.assertEquals(fieldName, fieldName.trim());
            Assert.assertFalse(fieldName.isEmpty());
            Assert.assertFalse(fieldName.trim().isEmpty());
        }
    }


    @Test
    public void testFieldNameUniqueness() {
        for (NicaHeader value : NicaHeader.values()) {
            for (NicaHeader other : NicaHeader.values()) {
                if (!value.equals(other)
                    && value.fieldName().equals(other.fieldName())) {
                    Assert.fail("duplicated fieldName");
                }
            }
        }
    }


}

