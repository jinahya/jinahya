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

        final Codes codes = new Codes();

        try {
            codes.putConstantCode(null, "");
            Assert.fail("passed: putConstantCode(null, )");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            codes.putConstantCode("", null);
            Assert.fail("passed: putConstantCode(, null)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        Assert.assertNull(codes.putConstantCode("", ""));

        final String key = RandomStringUtils.random(16);
        final String value = RandomStringUtils.random(16);
        Assert.assertNull(codes.putConstantCode(key, value));
        Assert.assertEquals(codes.putConstantCode(key, value), value);
    }


    @Test
    public void testPutVariableCode() {

        final Codes codes = new Codes();

        try {
            codes.putVariableCode(null, "");
            Assert.fail("passed: putVariableCode(null, )");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            codes.putVariableCode("", null);
            Assert.fail("passed: putVariableCode(, null)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        Assert.assertNull(codes.putVariableCode("", ""));

        final String key = RandomStringUtils.random(16);
        final String value = RandomStringUtils.random(16);
        Assert.assertNull(codes.putVariableCode(key, value));
        Assert.assertEquals(codes.putVariableCode(key, value), value);
    }


}

