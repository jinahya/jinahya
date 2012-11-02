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


import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class MacBCTest extends HacTest<HacBC> {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(MacBCTest.class.getName());


    @Override
    protected HacBC newInstance(final byte[] key) {
        return new HacBC(key);
    }


    @Test(invocationCount = 128)
    public void testAuthenticateAgainstJCE() {

        final byte[] key = AesJCETest.generateKey();

        final byte[] message = newMessage();

        final byte[] expected = new HacJCE(key).authenticate(message);

        final byte[] actual = newInstance(key).authenticate(message);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public void testAuthenticateWithStringAgainstJCE() {

        final byte[] key = AesJCETest.generateKey();

        final String message = newMessageAsString();

        final byte[] expected = new HacJCE(key).authenticate(message);

        final byte[] actual = newInstance(key).authenticate(message);

        Assert.assertEquals(actual, expected);
    }


}

