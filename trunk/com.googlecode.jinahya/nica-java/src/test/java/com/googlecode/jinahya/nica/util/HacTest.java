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


package com.googlecode.jinahya.nica.util;


import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <H> Hac type parameter
 */
public abstract class HacTest<H extends Hac> {


    protected static final Random RANDOM = new Random();


    protected static byte[] newMessage() {
        final byte[] message = new byte[RANDOM.nextInt(1024)];
        RANDOM.nextBytes(message);
        return message;
    }


    protected static String newMessageAsString() {
        return RandomStringUtils.random(RANDOM.nextInt(1024));
    }


    protected abstract H newInstance(byte[] key);


    @Test//(invocationCount = 128)
    public void testAuthenticate() {

        final H mac = newInstance(AesTest.generateKey());

        try {
            mac.authenticate((byte[]) null);
            Assert.fail("passed: authenticate((byte[]) null)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        final byte[] output = mac.authenticate(newMessage());
        Assert.assertTrue(output.length == Sha.OUTPUT_SIZE_IN_BYTES);
    }


    @Test//(invocationCount = 128)
    public void testAuthenticateWithString() {

        final H mac = newInstance(AesTest.generateKey());

        try {
            mac.authenticate((String) null);
            Assert.fail("passed: authenticate((String) null)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        final byte[] output = mac.authenticate(newMessageAsString());
        Assert.assertTrue(output.length == Sha.OUTPUT_SIZE_IN_BYTES);
    }


    @Test//(invocationCount = 128)
    public void testAuthenticateToString() {

        final H mac = newInstance(AesTest.generateKey());

        try {
            mac.authenticateToString((byte[]) null);
            Assert.fail("passed: authenticate((byte[]) null)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        final String output = mac.authenticateToString(newMessage());
        Assert.assertTrue(output.length() == Sha.OUTPUT_SIZE_IN_BYTES * 2);
    }


    @Test//(invocationCount = 128)
    public void testAuthenticateToStringWithString() {

        final H mac = newInstance(AesTest.generateKey());

        try {
            mac.authenticateToString((String) null);
            Assert.fail("passed: authenticate((String) null)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        final String output = mac.authenticateToString(newMessageAsString());
        Assert.assertTrue(output.length() == Sha.OUTPUT_SIZE_IN_BYTES * 2);
    }


}

