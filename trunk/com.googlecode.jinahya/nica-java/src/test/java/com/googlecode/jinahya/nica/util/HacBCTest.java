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
public class HacBCTest extends HacTest<HacBC> {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(HacBCTest.class.getName());


    @Override
    protected HacBC create(final byte[] key) {
        return new HacBC(key);
    }


    @Test
    public void testAuthenticateWithBytesAgainstJCE() {

        final byte[] key = AesTest.newKey();
        final byte[] message = newMessage();

        final Hac bc = create(key);
        final Hac jce = new HacJCE(key);

        for (int i = 0; i < 32; i++) {
            final byte[] expected = jce.authenticate(message);
            final byte[] actual = bc.authenticate(message);
            Assert.assertEquals(actual, expected);
        }
    }


    @Test
    public void testAuthenticateWithStringAgainstJCE() {

        final byte[] key = AesTest.newKey();
        final String message = newMessageAsString();

        final Hac bc = create(key);
        final Hac jce = new HacJCE(key);

        for (int i = 0; i < 32; i++) {
            final byte[] expected = jce.authenticate(message);
            final byte[] actual = bc.authenticate(message);
            Assert.assertEquals(actual, expected);
        }
    }


}
