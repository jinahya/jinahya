/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.codec;


import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class IdEncoderDecoderTest {


    private static void encodeDecode(final long expected) {

        System.out.printf("%40s %40s\n",
                          "----------------------------------------",
                          "----------------------------------------");

        for (int i = 0; i < 5; i++) {
            final String encoded = IdEncoder.encodeLong(expected);
            final long actual = IdDecoder.decodeLong(encoded);
            System.out.printf("%40d %40s\n", expected, encoded);
            Assert.assertEquals(actual, expected);
        }
    }


    private static void encodeDecode(final UUID expected) {

        System.out.printf("%40s %40s\n",
                          "----------------------------------------",
                          "----------------------------------------");

        for (int i = 0; i < 5; i++) {
            final String encoded = IdEncoder.encodeUUID(expected);
            final UUID actual = IdDecoder.decodeUUID(encoded);
            System.out.printf("%40s %40s\n", expected, encoded);
            Assert.assertEquals(actual, expected);
            Assert.assertEquals(actual.getLeastSignificantBits(),
                                expected.getLeastSignificantBits());
            Assert.assertEquals(actual.getMostSignificantBits(),
                                expected.getMostSignificantBits());
        }
    }


    @Test(invocationCount = 1)
    public static void testEncodeDecodeLongMagic() {

        encodeDecode(Long.MIN_VALUE);
        encodeDecode(Long.MAX_VALUE);
        encodeDecode(-2L);
        encodeDecode(-1L);
        encodeDecode(0L);
        encodeDecode(1L);
        encodeDecode(2L);
    }


    @Test(invocationCount = 128)
    public static void testEncodeDecodeLong() {

        final long expected = ThreadLocalRandom.current().nextLong();

        encodeDecode(expected);
    }


    @Test(invocationCount = 128)
    public static void testEncodeDecodeUUID() {

        final UUID expected = UUID.randomUUID();

        encodeDecode(expected);
    }


}

