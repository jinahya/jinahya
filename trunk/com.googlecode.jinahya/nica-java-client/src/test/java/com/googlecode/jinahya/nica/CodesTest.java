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


import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class CodesTest {


    @Test
    public void testPutConstantCode() {

        final DefaultCodes codes = new DefaultCodes();

        try {
            codes.putConstantEntry(null, "");
            Assert.fail("passed: putConstantCode((String) null, \"\")");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            codes.putConstantEntry("", null);
            Assert.fail("passed: putConstantCode(\"\", null)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        codes.putConstantEntry("", "");

        final String key = RandomStringUtils.random(16);
        final String value = RandomStringUtils.random(16);
        codes.putConstantEntry(key, value);
        try {
            codes.putConstantEntry(key, value);
            Assert.fail("passed: putConstantCode(, ) duplicate key");
        } catch (IllegalArgumentException iae) {
            // expected
        }
    }


    @Test
    public void testPutVariableCode() {

        final DefaultCodes codes = new DefaultCodes();

        try {
            codes.putVariableEntry(null, "");
            Assert.fail("passed: putVariableCode((String) null, )");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            codes.putVariableEntry("", null);
            Assert.fail("passed: putVariableCode(\"\", null)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        Assert.assertNull(codes.putVariableEntry("", ""));

        final String key = RandomStringUtils.random(16);
        final String value = RandomStringUtils.random(16);
        Assert.assertNull(codes.putVariableEntry(key, value));
        Assert.assertEquals(codes.putVariableEntry(key, value), value);
    }


    @Test
    public void testPutVolatileCode() {

        final DefaultCodes codes = new DefaultCodes();

        try {
            codes.putVolatileEntry(null, "");
            Assert.fail("passed: putVolatileCode((String) null, )");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            codes.putVolatileEntry("", null);
            Assert.fail("passed: putVolatileCode(\"\", null)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        Assert.assertNull(codes.putVolatileEntry("", ""));

        final String key = RandomStringUtils.random(16);
        final String value = RandomStringUtils.random(16);
        Assert.assertNull(codes.putVolatileEntry(key, value));
        Assert.assertEquals(codes.putVolatileEntry(key, value), value);
    }


}

