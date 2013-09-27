/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


import org.apache.commons.codec.Decoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.StringDecoder;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class RareStringDecoderProxyTest {


    @Test
    public void testAsDecoder() throws DecoderException {

        final Decoder decoder = (Decoder) RareStringDecoderProxy.newInstance();

        try {
            decoder.decode((Object) null);
            Assert.fail("passed: decode((Object) null)");
        } catch (final NullPointerException npe) {
            // expected
        }

        try {
            decoder.decode(new Object());
            Assert.fail("passed: decode(new Object())");
        } catch (final DecoderException de) {
            // expected;
        }

        final Object expected = "";
        final Object actual = decoder.decode(expected);
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testAsStringDecoder() throws DecoderException {

        final StringDecoder decoder =
            (StringDecoder) RareStringDecoderProxy.newInstance();

        try {
            decoder.decode((String) null);
            Assert.fail("passed: decode((String) null)");
        } catch (final NullPointerException npe) {
            // expected
        }

        final String expected = "";
        final String actual = decoder.decode(expected);
        Assert.assertEquals(actual, expected);
    }


}
