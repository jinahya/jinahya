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
import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class PerTest {


    @Test(invocationCount = 32)
    public static void testEncodeDecode() {

        final Random random = ThreadLocalRandom.current();

        final byte[] expected = new byte[random.nextInt(64)];
        random.nextBytes(expected);

        final byte[] encoded = Per.encode(expected);

        final byte[] actual = Per.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 32)
    public static void testEncodeDecodeString() {

        final Random random = ThreadLocalRandom.current();

        final byte[] expected = new byte[random.nextInt(64)];
        random.nextBytes(expected);

        final String encoded = Per.encodeToString(expected);

        final byte[] actual = Per.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


}
