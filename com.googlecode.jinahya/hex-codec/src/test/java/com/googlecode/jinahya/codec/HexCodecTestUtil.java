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


import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.RandomStringUtils;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
final class HexCodecTestUtil {


    protected static byte[] newDecodedBytes() {

        final Random random = ThreadLocalRandom.current();

        final byte[] bytes = new byte[random.nextInt(1024)];

        random.nextBytes(bytes);

        return bytes;
    }


    protected static String newDecodedString() {

        final Random random = ThreadLocalRandom.current();

        return RandomStringUtils.random(random.nextInt(1024));
    }


    protected static byte[] newEncodedBytes() {

        final Random random = ThreadLocalRandom.current();

        final byte[] bytes = new byte[random.nextInt(512) << 1]; // even

        for (int i = 0; i < bytes.length; i++) {
            switch (random.nextInt(3)) {
                case 0: // alpha
                    bytes[i] = (byte) (random.nextInt(10) + 0x30);
                    break;
                case 1: // upper\
                    bytes[i] = (byte) (random.nextInt(6) + 0x41);
                    break;
                default: // lower
                    bytes[i] = (byte) (random.nextInt(6) + 0x61);
                    break;
            }
            switch (bytes[i]) {
                case 0x30:
                case 0x39:
                case 0x41:
                case 0x46:
                case 0x61:
                case 0x66:
//                        System.out.println((char) bytes[i]);
                    break;
                default:
                    break;
            }
        }

        return bytes;
    }


    protected static String newEncodedString() {
        try {
            return new String(newEncodedBytes(), "US-ASCII");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException(uee);
        }
    }


    protected HexCodecTestUtil() {
        super();
    }


}

