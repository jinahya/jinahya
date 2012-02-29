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
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class IdCodecTest {


    private static final Random RANDOM = new Random();


    private static void test(final long decoded) {
        final String encoded = IdEncoder.encodeId(decoded);
        Assert.assertEquals(IdDecoder.decodeId(encoded), decoded);
        System.out.println("codec: " + decoded + " / " + encoded);
    }


    @Test(invocationCount = 1024)
    public void testRandom() {
        test(RANDOM.nextLong());
    }


    @Test
    public void test() {

        for (long decoded = 0L; decoded < 100L; decoded++) {
            test(decoded);
        }

        test(1000000L);
        test(1000001L);

        test(Long.MIN_VALUE);
        test(Long.MAX_VALUE);
    }


}

