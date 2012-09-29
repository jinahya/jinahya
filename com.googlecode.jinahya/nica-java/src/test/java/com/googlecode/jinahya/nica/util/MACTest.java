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
 */
public abstract class MACTest<M extends MAC> {


    protected static final Random RANDOM = new Random();


    protected static byte[] newUnauthenticated() {
        final byte[] unauthenticated = new byte[RANDOM.nextInt(1024)];
        RANDOM.nextBytes(unauthenticated);
        return unauthenticated;
    }


    protected static String newUnauthenticatedAsString() {
        return RandomStringUtils.random(RANDOM.nextInt(1024));
    }


    protected abstract M newInstance(byte[] key);


    @Test(invocationCount = 128)
    public void testAuthenticate() {

        final M mac = newInstance(AESTest.generateKey());

        try {
            mac.authenticate((byte[]) null);
            Assert.fail("passed: authenticate((byte[]) null)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        mac.authenticate(newUnauthenticated());
    }


    @Test(invocationCount = 128)
    public void testAuthenticateWithString() {

        final M mac = newInstance(AESTest.generateKey());

        try {
            mac.authenticate((String) null);
            Assert.fail("passed: authenticate((String) null)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        mac.authenticate(newUnauthenticatedAsString());
    }


}

