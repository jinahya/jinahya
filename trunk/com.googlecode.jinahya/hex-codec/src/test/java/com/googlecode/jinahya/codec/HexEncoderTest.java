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


import java.util.Random;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HexEncoderTest {


    private static final Random RANDOM = new Random();


    private static byte[] newRandomBytes() {

        synchronized (RANDOM) {
            final byte[] bytes = new byte[RANDOM.nextInt(128)];
            RANDOM.nextBytes(bytes);
            return bytes;
        }
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEncodeWithNull() {
        new HexEncoder().encode(null);
    }


    @Test(invocationCount = 128)
    public void testEncode() {
        new HexEncoder().encode(newRandomBytes());
    }


    @Test(invocationCount = 128)
    public void testEncodingAgainstCommonsHex() throws DecoderException {

        final byte[] expected = newRandomBytes();

        final byte[] encoded = new HexEncoder().encode(expected);

        final byte[] actual = new Hex().decode(encoded);

        Assert.assertEquals(actual, expected);
    }


}

