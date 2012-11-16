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


    @Test
    public void testUnsupportedOperations() {

        final Object proxy = HexBinaryDecoderProxy.newInstance();

        try {
            proxy.toString();
            Assert.fail("passed: .toString()");
        } catch (UnsupportedOperationException uoe) {
            // expected
        }
    }


    @Test
    public void testAsCommonsCodecBinaryDecoder() throws DecoderException {

        final BinaryDecoder decoder =
            (BinaryDecoder) HexBinaryDecoderProxy.newInstance();

        final byte[] encoded = HexCodecTestUtil.newEncodedBytes();
//        System.out.println("encoded: "
//                           + new String(encoded, StandardCharsets.US_ASCII));

        final byte[] decoded = decoder.decode(encoded);
    }


    @Test
    public void testAsCommonsCodecDecoder() throws DecoderException {

        final Decoder decoder = (Decoder) HexBinaryDecoderProxy.newInstance();

        try {
            decoder.decode(new Object());
            Assert.fail("passed: decode(new Object())");
        } catch (DecoderException ee) {
            // expected
        }

        final String encoded = HexCodecTestUtil.newEncodedString();
//        System.out.println("encoded: " + encoded);

        final byte[] decoded = (byte[]) decoder.decode(encoded);
    }


}

