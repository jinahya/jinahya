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


package com.googlecode.jinahya.codec;


import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class IdEncoderTest {


    @Test(invocationCount = 128)
    public static void testEncodeLong() {

        final String encoded =
            IdEncoder.encodeLong(ThreadLocalRandom.current().nextLong());
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public static void testEncodeUUIDWithNullDecoded() {

        IdEncoder.encodeUUID(null);
    }


    @Test(invocationCount = 16)
    public static void testEncodeUUID() {

        System.out.printf("%40s %40s\n",
                          "----------------------------------------",
                          "----------------------------------------");

        final UUID decoded = UUID.randomUUID();

        for (int i = 0; i < 10; i++) {
            final String encoded = IdEncoder.encodeUUID(decoded);
            System.out.printf("%40s %40s\n", decoded.toString(), encoded);
        }
    }


    @Test(invocationCount = 128)
    public void testEncode() {

        final long decoded = ThreadLocalRandom.current().nextLong();
        final String encoded = new IdEncoder().encode(decoded);
        System.out.printf("%20d %20s\n", decoded, encoded);
    }


}

