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
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.Encoder;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HexBinaryEncoderProxyTest {


    @Test(expectedExceptions = {UnsupportedOperationException.class})
    public void testUnsupportedOperations() {

        final Object proxy = HexBinaryEncoderProxy.newInstance();

        proxy.toString();
        Assert.fail("passed: toString()");
    }


    @Test
    public void testAsCommonsCodecEncoderForObject() throws EncoderException {

        final byte[] expected = HexCodecTestUtils.newDecodedBytes();

        final Encoder proxy = (Encoder) HexBinaryEncoderProxy.newInstance();
        final byte[] encoded = (byte[]) proxy.encode(expected);

        final byte[] actual = new HexDecoder().decode(encoded);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testAsCommonsCodecEncoderForString() throws EncoderException {

        final String expected = RandomStringUtils.random(1024);

        final Encoder proxy = (Encoder) HexBinaryEncoderProxy.newInstance();
        final byte[] encoded = (byte[]) proxy.encode(expected);

        final String actual = new String(HexDecoder.decodeMultiple(encoded),
                                         StandardCharsets.UTF_8);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testAsCommonsCodecBinaryEncoder() throws EncoderException {

        final byte[] expected = HexCodecTestUtils.newDecodedBytes();

        final BinaryEncoder encoder =
            (BinaryEncoder) HexBinaryEncoderProxy.newInstance();
        final byte[] encoded = encoder.encode(expected);

        final byte[] actual = HexDecoder.decodeMultiple(encoded);

        Assert.assertEquals(actual, expected);
    }


}

