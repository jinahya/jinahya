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
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class PERTest {


    private static final Random RANDOM = new Random();


    @Test(invocationCount = 128)
    public static void testEncodingDecoding() {

        final byte[] expected = new byte[RANDOM.nextInt(512) + 512];
        RANDOM.nextBytes(expected);

        final byte[] encoded = PER.encode(expected);

        final byte[] actual = PER.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public static void testEncodingDecodingString() {

        final byte[] expected = new byte[RANDOM.nextInt(512) + 512];
        RANDOM.nextBytes(expected);

        final String encoded = PER.encodeToString(expected);

        final byte[] actual = PER.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


}

