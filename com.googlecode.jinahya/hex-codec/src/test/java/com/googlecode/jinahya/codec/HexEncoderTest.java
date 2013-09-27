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
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HexEncoderTest {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(HexEncoderTest.class);


    @Test(enabled = true)
    public static void testEncodeSingle() {

        final byte[] output = new byte[2];
        for (int i = 0; i < 256; i++) {
            HexEncoder.encodeSingle(i, output, 0);
            final String hex =
                (i < 0x10 ? "0" : "") + Integer.toHexString(i).toUpperCase();
            Assert.assertEquals(new String(output, StandardCharsets.US_ASCII),
                                hex);
        }
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void testEncodeWithNullBytes() {

        new HexEncoder().encode(null);
    }


    @Test(invocationCount = 1)
    public void testEncode() {
        new HexEncoder().encode(new byte[0]);
        new HexEncoder().encode(Tests.decodedBytes());
    }


    @Test(enabled = true, invocationCount = 1)
    public void testEncodeAgainstCommonsCodecHex() throws EncoderException {

        final byte[] decoded = Tests.decodedBytes();

        final byte[] expected = Tests.uppercase(new Hex().encode(decoded));
        LOGGER.debug("expected: {}", expected);

        final byte[] actual = new HexEncoder().encode(decoded);
        LOGGER.debug("actual: {}", actual);

        Assert.assertEquals(actual, expected);
    }


}

