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


    @Test(expectedExceptions = {UnsupportedOperationException.class})
    public void testUnsupportedOperations() {

        final Object proxy = HexBinaryEncoderProxy.newInstance();

        proxy.toString();
        Assert.fail("passed: toString()");
    }


    @Test
    public void testAsEncoder() throws EncoderException {

        final Encoder encoder = (Encoder) HexBinaryEncoderProxy.newInstance();

        try {
            encoder.encode(null);
            Assert.fail("passed: <Object>encode(null)");
        } catch (final NullPointerException npe) {
            // expected
        }

        final Object decoded = Tests.decodedBytes();
        final Object encoded = encoder.encode(decoded);
    }


    @Test
    public void testAsBinaryEncoder() throws EncoderException {

        final BinaryEncoder encoder =
            (BinaryEncoder) HexBinaryEncoderProxy.newInstance();

        try {
            encoder.encode((byte[]) null);
            Assert.fail("passed: encode((byte[]) null)");
        } catch (final NullPointerException npe) {
            // expected
        }

        final byte[] decoded = Tests.decodedBytes();
        final byte[] encoded = encoder.encode(decoded);
    }


}

