/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
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
import org.apache.commons.codec.binary.Base64;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class PercentBinaryEncoderProxyTest {


    @Test
    public void testAsEncoder() throws Exception {

        final Encoder encoder =
            (Encoder) PercentBinaryEncoderProxy.newInstance();

        try {
            encoder.encode((Object) null);
            Assert.fail("passed: encode((Object) null)");
        } catch (final NullPointerException npe) {
            // ok
        }

        final Object input = PercentCodecTestHelper.decodedBytes(1024);
        final Object output = encoder.encode(input);
    }


    @Test
    public void testAsBinaryDecoder() throws Exception {

        final BinaryEncoder encoder =
            (BinaryEncoder) PercentBinaryEncoderProxy.newInstance();

        try {
            encoder.encode((byte[]) null);
            Assert.fail("passed: encode((byte[]) null)");
        } catch (final NullPointerException npe) {
            // ok
        }

        final byte[] input = PercentCodecTestHelper.decodedBytes(1024);
        final byte[] output = encoder.encode(input);
    }


    @Test(invocationCount = 128)
    public void testEncode() throws Exception {

        final BinaryEncoder encoder =
            (BinaryEncoder) PercentBinaryEncoderProxy.newInstance();

        try {
            encoder.encode((Object) null);
            Assert.fail("passed: encode((Object) null)");
        } catch (final NullPointerException npe) {
            // expected
        }

        try {
            encoder.encode((byte[]) null);
            Assert.fail("passed: encode((byte[]) null)");
        } catch (final NullPointerException npe) {
            // expected
        }

        final byte[] origin = PercentCodecTestHelper.decodedBytes(1024);
        System.out.println("original ----------------------------------------");
        System.out.println(Base64.encodeBase64String(origin));

        final byte[] encoded = encoder.encode(origin);
        System.out.println("encoded -----------------------------------------");
        System.out.println(new String(encoded, "US-ASCII"));

        final byte[] decoded = PercentDecoder.decodeMultiple(encoded);
        System.out.println("decoded -----------------------------------------");
        System.out.println(Base64.encodeBase64String(decoded));

        Assert.assertEquals(decoded, origin);
    }


}
