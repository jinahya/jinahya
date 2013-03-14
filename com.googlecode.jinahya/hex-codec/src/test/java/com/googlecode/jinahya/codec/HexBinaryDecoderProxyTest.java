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
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.Decoder;
import org.apache.commons.codec.DecoderException;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HexBinaryDecoderProxyTest {


    @Test(expectedExceptions = {UnsupportedOperationException.class})
    public void testUnsupportedOperations() {

        final Object proxy = HexBinaryDecoderProxy.newInstance();

        proxy.toString();
        Assert.fail("passed: toString()");
    }


    @Test
    public void testAsCommonsCodecDecoderForObject() throws DecoderException {

        final byte[] expected = HexCodecTestUtils.newDecodedBytes();

        final Object encoded = new HexEncoder().encode(expected);

        final Decoder decoder = (Decoder) HexBinaryDecoderProxy.newInstance();
        final byte[] actual = (byte[]) decoder.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testAsCommonsCodecDecoderForString()
        throws UnsupportedEncodingException, DecoderException {

        final byte[] expected = HexCodecTestUtils.newDecodedBytes();

        final String encoded =
            new String(new HexEncoder().encode(expected), "US-ASCII");

        final Decoder decoder = (Decoder) HexBinaryDecoderProxy.newInstance();
        final byte[] actual = (byte[]) decoder.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testAsCommonsCodecBinaryDecoder() throws DecoderException {

        final byte[] expected = HexCodecTestUtils.newDecodedBytes();

        final byte[] encoded = new HexEncoder().encode(expected);

        final BinaryDecoder decoder =
            (BinaryDecoder) HexBinaryDecoderProxy.newInstance();
        final byte[] actual = decoder.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


}

