/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


package com.googlecode.jinahya.commons.codec;


import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.Decoder;
import org.apache.commons.codec.DecoderException;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class RareBinaryDecoderProxyTest {


    @Test
    public void testAsDecoder() throws DecoderException {

        final Decoder decoder = (Decoder) RareBinaryDecoderProxy.newInstance();

        try {
            decoder.decode(null);
            Assert.fail("passed.<Object>decode(null)");
        } catch (final NullPointerException npe) {
            // expected
        }

        final Object expected = new byte[0];
        final Object actual = decoder.decode(expected);
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testAsBinaryDecoder() throws DecoderException {

        final BinaryDecoder decoder =
            (BinaryDecoder) RareBinaryDecoderProxy.newInstance();

        try {
            decoder.decode((byte[]) null);
            Assert.fail("passed.decode((byte[]) null)");
        } catch (final NullPointerException npe) {
            // expected
        }

        final byte[] expected = new byte[0];
        final byte[] actual = decoder.decode(expected);
        Assert.assertEquals(actual, expected);
    }


}
