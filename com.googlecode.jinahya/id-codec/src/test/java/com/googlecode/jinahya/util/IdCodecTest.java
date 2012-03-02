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


package com.googlecode.jinahya.util;


import java.util.Random;
import java.util.UUID;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class IdCodecTest {


    private static final Random RANDOM = new Random();


    private static void test(final long decoded) {

        final String encoded1 = IdCodec.encodeId(decoded);
        Assert.assertEquals(IdCodec.decodeId(encoded1), decoded);

        final String encoded2 = IdCodec.encodeId(decoded);
        Assert.assertEquals(IdCodec.decodeId(encoded2), decoded);

        System.out.printf(
            "%1$20d %2$20s %3$20s\n", decoded, encoded1, encoded2);
    }


    @Test(invocationCount = 1024)
    public void testRandom() {
        test(RANDOM.nextLong());
    }


    @Test
    public void test2() {
        System.out.printf("%1$20s %2$20s %3$20s\n", "id", "encoded1",
                          "encoded2");
        System.out.printf("%1$20s %1$20s %1$20s\n", "--------------------");
        for (long decoded = -5L; decoded < 5L; decoded++) {
            test(decoded);
            test(decoded);
        }
        test(-100000000L);
        test(100000000L);
        test(Long.MIN_VALUE);
        test(Long.MIN_VALUE);
        test(Long.MAX_VALUE);
        test(Long.MAX_VALUE);
    }


    @Test(invocationCount = 1024)
    public void testUUID() {
        final UUID original = UUID.randomUUID();
        final String encoded = IdCodec.encodeUUID(original);
        final UUID decoded = IdCodec.decodeUUID(encoded);
        Assert.assertEquals(decoded, original);
        Assert.assertEquals(decoded.getMostSignificantBits(),
                            original.getMostSignificantBits());
        Assert.assertEquals(decoded.getLeastSignificantBits(),
                            original.getLeastSignificantBits());
        System.out.printf("%1$36s %2$40s\n", original.toString(), encoded);
    }


}

