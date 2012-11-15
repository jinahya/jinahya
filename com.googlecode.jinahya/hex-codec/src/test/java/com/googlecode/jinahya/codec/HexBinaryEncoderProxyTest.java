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
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HexBinaryEncoderProxyTest {


    @Test
    public void testAsCommonsCodecBinaryEncoder() throws EncoderException {

        final BinaryEncoder encoder =
            (BinaryEncoder) HexBinaryEncoderProxy.newInstance();

        final byte[] decoded = HexCodecTestUtil.newDecodedBytes();

        final byte[] encoded = encoder.encode(decoded);
        System.out.println("encoded: "
                           + new String(encoded, StandardCharsets.US_ASCII));
    }


    @Test
    public void testAsCommonsCodecEncoder() throws EncoderException {

        final Encoder encoder = (Encoder) HexBinaryEncoderProxy.newInstance();

        try {
            encoder.encode(new Object());
            Assert.fail("passed: encode(new Object())");
        } catch (EncoderException ee) {
            // expected
        }

        final String decoded = HexCodecTestUtil.newDecodedString();

        final byte[] encoded = (byte[]) encoder.encode(decoded);
        System.out.println("encoded: "
                           + new String(encoded, StandardCharsets.US_ASCII));
    }


}

