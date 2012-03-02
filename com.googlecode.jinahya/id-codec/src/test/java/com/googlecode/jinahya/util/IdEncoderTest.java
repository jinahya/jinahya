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
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class IdEncoderTest {


    private static final Random RANDOM = new Random();


    private static void test(final long decoded) {
        System.out.println(
            "decode: " + decoded + " / " + IdEncoder.encodeId(decoded));
    }


    @Test(invocationCount = 1024)
    public void testEncodeId() {
        IdEncoder.encodeId(RANDOM.nextLong());
    }


    @Test(invocationCount = 1024)
    public void testEncodeUUID() {
        IdEncoder.encodeUUID(UUID.randomUUID());
    }


    @Test(invocationCount = 1024)
    public void testEncode() {
        new IdEncoder().encode(RANDOM.nextLong());
    }


    @Test
    public void test() {

        for (long decoded = -50L; decoded < 50L; decoded++) {
            test(decoded);
            test(decoded);
        }

        test(-100000000L);

        test(100000000L);

        test(Long.MAX_VALUE);
    }


}

