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


import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HexDecoderTest {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(HexDecoderTest.class);


    @Test
    public static void testDecodeSingle() {

        final byte[] input = new byte[2];
        for (int i = 0; i < 256; i++) {
            final String hex = Integer.toHexString(i);
            if (hex.length() == 1) {
                input[0] = (byte) '0';
                input[1] = (byte) hex.charAt(0);
            } else {
                input[0] = (byte) hex.charAt(0);
                input[1] = (byte) hex.charAt(1);
            }
            final int decoded = HexDecoder.decodeSingle(input, 0);
            Assert.assertEquals(decoded, i);
        }
    }


    @Test(enabled = true, expectedExceptions = {NullPointerException.class})
    public void testDecodeWithNullBytes() {

        new HexDecoder().decode(null);
    }


    @Test(enabled = false, expectedExceptions = IllegalArgumentException.class)
    public void testDecodeWithOddLengthArray() {
        new HexDecoder().decode(new byte[]{0x30}); // '0'
    }


    @Test(enabled = false, expectedExceptions = IllegalArgumentException.class)
    public void testDecodeWithIllegalSingle() {
        new HexDecoder().decode(new byte[]{0x29, 0x30}); // '/', '0'
    }


    @Test(enabled = true)
    public void testDecode() {
        new HexDecoder().decode(new byte[0]);
        new HexDecoder().decode(Tests.encodedBytes());
    }


    @Test(enabled = true, invocationCount = 128)
    public void testDecodeAgainstCommonsCodecHex() throws DecoderException {

        final byte[] encoded = Tests.encodedBytes();

        final byte[] expected = new Hex().decode(encoded);

        final byte[] actual = new HexDecoder().decode(encoded);

        Assert.assertEquals(actual, expected);
    }


}

