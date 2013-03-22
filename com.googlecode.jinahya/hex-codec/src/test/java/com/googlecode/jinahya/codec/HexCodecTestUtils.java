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


import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HexCodecTestUtils {


    protected static byte[] newDecodedBytes() {

        final Random random = ThreadLocalRandom.current();

        final byte[] decodedBytes = new byte[random.nextInt(1024)];

        random.nextBytes(decodedBytes);

        return decodedBytes;
    }


//    protected static byte[][] newMultipleDecodedBytes() {
//
//        final byte[][] multipleDecodedBytes =
//            new byte[ThreadLocalRandom.current().nextInt(1024)][];
//
//        for (int i = 0; i < multipleDecodedBytes.length; i++) {
//            multipleDecodedBytes[i] = HexCodecTestUtil.newDecodedBytes();
//        }
//
//        return multipleDecodedBytes;
//    }
    protected static String newDecodedString() {

        final Random random = ThreadLocalRandom.current();

        return RandomStringUtils.random(random.nextInt(128));
    }


    protected static byte[] newEncodedBytes() {

        final Random random = ThreadLocalRandom.current();

        final byte[] encodedBytes = new byte[random.nextInt(1048576) << 1];

        for (int i = 0; i < encodedBytes.length; i++) {
            switch (random.nextInt() % 3) {
                case 0: // alpha
                    encodedBytes[i] = (byte) (random.nextInt(0x0A) + 0x30);
                    break;
                case 1: // upper
                    encodedBytes[i] = (byte) (random.nextInt(0x06) + 0x41);
                    break;
                default: // lower
                    encodedBytes[i] = (byte) (random.nextInt(0x06) + 0x61);
                    break;
            }
        }

        return encodedBytes;
    }


//    protected static byte[][] newMultipleEncodedBytes() {
//
//        final byte[][] multipleEncodedBytes =
//            new byte[ThreadLocalRandom.current().nextInt(1024)][];
//
//        for (int i = 0; i < multipleEncodedBytes.length; i++) {
//            multipleEncodedBytes[i] = HexCodecTestUtil.newEncodedBytes();
//        }
//
//        return multipleEncodedBytes;
//    }
    protected static String newEncodedString() {
        return new String(newEncodedBytes(), StandardCharsets.US_ASCII);
    }


    protected static byte[] toUpperCase(final byte[] encoded) {

        final byte[] uppercased = new byte[encoded.length];

        for (int i = 0; i < uppercased.length; i++) {
            if (encoded[i] >= 0x61) {
                uppercased[i] = (byte) (encoded[i] - 0x20);
            } else {
                uppercased[i] = encoded[i];
            }
        }

        return uppercased;
    }


    @Test
    public static void testNewEncodedBytes() {

        final Set<Integer> limits = new HashSet<Integer>();

        limits.add(0x30); // '0'
        limits.add(0x39); // '9'
        limits.add(0x41); // 'A'
        limits.add(0x46); // 'F;
        limits.add(0x61); // 'a'
        limits.add(0x66); // 'f'

        while (!limits.isEmpty()) {
            for (byte b : HexCodecTestUtils.newEncodedBytes()) {
                limits.remove(b & 0xFF);
            }
        }
    }


    protected HexCodecTestUtils() {
        super();
    }


}

