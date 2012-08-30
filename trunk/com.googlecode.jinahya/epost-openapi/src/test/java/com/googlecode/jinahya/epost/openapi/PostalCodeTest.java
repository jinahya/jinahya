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


package com.googlecode.jinahya.epost.openapi;


import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class PostalCodeTest {


    private static final String[] VALID_ADDRESSES = new String[]{
        "aaaa bbb ccc",
        "aaaa bbb 111~222",
        "aaa bbb 1~1"
    };


    private static final String[] INVALID_ADDRESSES = new String[]{};


    private static final String[] VALID_CODES = new String[]{
        "000-000",
        "111-111"
    };


    private static final String[] INVALID_CODES = new String[]{
        "111111",
        "11111",
        "11-111",
        "111-11",
        "a",
        "a11-111"
    };


    @Test
    public static void testCODE_PATTERN() {

        for (String validCode : VALID_CODES) {
            Assert.assertTrue(
                PostalCode.CODE_PATTERN.matcher(validCode).matches());
        }

        for (String invalidCode : INVALID_CODES) {
            Assert.assertFalse(
                PostalCode.CODE_PATTERN.matcher(invalidCode).matches());
        }
    }


    @Test
    public void setAddress() {

        final PostalCode postalCode = new PostalCode();

        try {
            postalCode.setAddress(null);
            Assert.fail("passed: setAddress(null)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        for (String validAddress : VALID_ADDRESSES) {
            postalCode.setAddress(validAddress);
        }

        for (String invalidAddress : INVALID_ADDRESSES) {
            try {
                postalCode.setAddress(invalidAddress);
                Assert.fail("passed: setAddress(" + invalidAddress + ")");
            } catch (IllegalArgumentException iae) {
                // expected
            }
        }
    }


    @Test
    public void getAddress() {

        final PostalCode postalCode = new PostalCode();

        Assert.assertNull(postalCode.getAddress());

        for (String validAddress : VALID_ADDRESSES) {
            postalCode.setAddress(validAddress);
            Assert.assertEquals(postalCode.getAddress(), validAddress);
        }
    }


    @Test
    public void testSetCode() {

        final PostalCode postalCode = new PostalCode();

        try {
            postalCode.setCode(null);
            Assert.fail("passed: setCode(null)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        for (String validCode : VALID_CODES) {
            postalCode.setCode(validCode);
        }

        for (String invalidCode : INVALID_CODES) {
            try {
                postalCode.setCode(invalidCode);
                Assert.fail("passed: setCode(" + invalidCode + ")");
            } catch (IllegalArgumentException iae) {
                // expected
            }
        }
    }


    @Test
    public void testGetCode1() {

        final PostalCode postalCode = new PostalCode();

        for (String validCode : VALID_CODES) {
            postalCode.setCode(validCode);
            Assert.assertEquals(
                postalCode.getCode1(),
                validCode.substring(validCode.indexOf('-') + 1));
        }
    }


}

