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
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.RandomStringUtils;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
final class Tests {


    static Random random() {

        return ThreadLocalRandom.current();
    }


    static byte[] decodedBytes(final int maxlen) {

        final byte[] decodedBytes = new byte[random().nextInt(maxlen)];

        random().nextBytes(decodedBytes);

        return decodedBytes;
    }


    static byte[] decodedBytes() {

        return decodedBytes(128);
    }


    static String decodedString(final int maxlen) {

        return RandomStringUtils.random(random().nextInt(maxlen));
    }


    static String decodedString() {

        return decodedString(128);
    }


    static byte[] encodedBytes() {

        final byte[] encodedBytes = new byte[random().nextInt(128) << 1];

        for (int i = 0; i < encodedBytes.length; i++) {
            switch (random().nextInt() % 3) {
                case 0: // alpha
                    encodedBytes[i] = (byte) (random().nextInt(0x0A) + 0x30);
                    break;
                case 1: // upper
                    encodedBytes[i] = (byte) (random().nextInt(0x06) + 0x41);
                    break;
                default: // lower
                    encodedBytes[i] = (byte) (random().nextInt(0x06) + 0x61);
                    break;
            }
        }

        return encodedBytes;
    }


    static String encodedString() {

        return new String(encodedBytes(), StandardCharsets.US_ASCII);
    }


    static byte[] uppercase(final byte[] lowercased) {

        final byte[] uppercased = new byte[lowercased.length];

        for (int i = 0; i < uppercased.length; i++) {
            if (lowercased[i] >= 0x61 && lowercased[i] <= 0x7A) {
                uppercased[i] = (byte) (lowercased[i] - 0x20);
            } else {
                uppercased[i] = lowercased[i];
            }
        }

        return uppercased;
    }


    private Tests() {

        super();
    }


}

