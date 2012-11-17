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
class HexCodecTestUtil {


    protected static byte[] newDecodedBytes() {

        final Random random = ThreadLocalRandom.current();

        final byte[] decodedBytes = new byte[random.nextInt(1024)];

        random.nextBytes(decodedBytes);

        return decodedBytes;
    }


    protected static byte[][] newMultipleDecodedBytes() {

        final byte[][] multipleDecodedBytes =
            new byte[ThreadLocalRandom.current().nextInt(1024)][];

        for (int i = 0; i < multipleDecodedBytes.length; i++) {
            multipleDecodedBytes[i] = HexCodecTestUtil.newDecodedBytes();
        }

        return multipleDecodedBytes;
    }


    protected static String newDecodedString() {

        final Random random = ThreadLocalRandom.current();

        return RandomStringUtils.random(random.nextInt(128));
    }


    protected static byte[] newEncodedBytes() {

        final Random random = ThreadLocalRandom.current();

        final byte[] encodedBytes = new byte[random.nextInt(512) << 1]; // even

        for (int i = 0; i < encodedBytes.length; i++) {
            switch (random.nextInt() % 3) {
                case 0: // alpha
                    encodedBytes[i] = (byte) (random.nextInt(10) + 0x30);
                    break;
                case 1: // upper\
                    encodedBytes[i] = (byte) (random.nextInt(6) + 0x41);
                    break;
                default: // lower
                    encodedBytes[i] = (byte) (random.nextInt(6) + 0x61);
                    break;
            }
        }

        return encodedBytes;
    }


    protected static byte[][] newMultipleEncodedBytes() {

        final byte[][] multipleEncodedBytes =
            new byte[ThreadLocalRandom.current().nextInt(1024)][];

        for (int i = 0; i < multipleEncodedBytes.length; i++) {
            multipleEncodedBytes[i] = HexCodecTestUtil.newEncodedBytes();
        }

        return multipleEncodedBytes;
    }


    protected static String newEncodedString() {
        return new String(newEncodedBytes(), StandardCharsets.US_ASCII);
    }


    protected HexCodecTestUtil() {
        super();
    }


}

