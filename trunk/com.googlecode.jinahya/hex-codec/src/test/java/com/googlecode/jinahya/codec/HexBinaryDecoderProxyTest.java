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
    public void testAsDecoder() throws DecoderException {

        final Decoder decoder = (Decoder) HexBinaryDecoderProxy.newInstance();

        try {
            decoder.decode(null);
            Assert.fail("decode(null)");
        } catch (final NullPointerException npe) {
            // expected
        }

        final Object encoded = Tests.encodedBytes();
        final Object decoded = decoder.decode(encoded);
    }


    @Test
    public void testAsBinaryDecoder() throws DecoderException {

        final BinaryDecoder decoder =
            (BinaryDecoder) HexBinaryDecoderProxy.newInstance();

        try {
            decoder.decode((byte[]) null);
            Assert.fail("decode((byte[]) null)");
        } catch (final NullPointerException npe) {
            // expected
        }

        final byte[] encoded = Tests.encodedBytes();
        final byte[] decoded = decoder.decode(encoded);
    }


}

