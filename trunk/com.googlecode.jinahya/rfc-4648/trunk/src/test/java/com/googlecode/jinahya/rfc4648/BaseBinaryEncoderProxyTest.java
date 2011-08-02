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


package com.googlecode.jinahya.rfc4648;


import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Hex;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BaseBinaryEncoderProxyTest {


    private static void test(final Map<String, String> vector, final Base base,
                             final BinaryEncoder commons)
        throws EncoderException {

        test(vector, base, commons, Modifier.TO_SAME);
    }


    private static void test(final Map<String, String> vector, final Base base,
                             final BinaryEncoder commons,
                             final Modifier modifier)
        throws EncoderException {

        final BinaryEncoder jinahya =
            ((BinaryEncoder) BaseBinaryEncoderProxy.newInstance(base));

        test(vector, jinahya, commons, modifier);
    }


    private static void test(final Map<String, String> vector,
                             final BinaryEncoder jinahya,
                             final BinaryEncoder commons,
                             final Modifier modifier)
        throws EncoderException {

        for (Entry<String, String> entry : vector.entrySet()) {

            final byte[] encoded = entry.getValue().getBytes();

            final byte[] source = entry.getKey().getBytes();

            final byte[] encoded1 = jinahya.encode(source);
            Assert.assertEquals(encoded1, encoded);
            //System.out.println("jinahya: " + hex(encoded1));

            final byte[] encoded2 = modifier.modify(commons.encode(source));
            Assert.assertEquals(encoded2, encoded);
            //System.out.println("commons: " + hex(encoded2));

            Assert.assertEquals(encoded1, encoded2);
        }
    }


    private static String hex(byte[] bytes) {
        final StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            final int i = b & 0xFF;
            if (i <= 0x0F) {
                builder.append('0');
            }
            builder.append(Integer.toHexString(i).toUpperCase());
        }
        return builder.toString();
    }


    @Test
    public void testBase16() throws EncoderException {

        final Base base = new Base16();
        final BinaryEncoder commons = new Hex();

        test(TestVectors.BASE16, base, commons, Modifier.TO_UPPER);
    }


    @Test
    public void testBase32() throws EncoderException {

        final Base base = new Base32();
        final BinaryEncoder commons =
            new org.apache.commons.codec.binary.Base32(-1, null, false);

        test(TestVectors.BASE32, base, commons);
    }


    @Test
    public void testBase32Hex() throws EncoderException {

        final Base base = new Base32Hex();
        final BinaryEncoder commons =
            new org.apache.commons.codec.binary.Base32(-1, null, true);

        test(TestVectors.BASE32HEX, base, commons);
    }


    @Test
    public void testBase64() throws EncoderException {

        final Base base = new Base64();
        final BinaryEncoder commons =
            new org.apache.commons.codec.binary.Base64(-1, null, false);

        test(TestVectors.BASE64, base, commons);
    }


    @Test
    public void testBase64URL() throws EncoderException {

        final Base base = new Base64URL();
        final BinaryEncoder commons =
            new org.apache.commons.codec.binary.Base64(-1, null, true);

        test(TestVectors.BASE64URL, base, commons);
    }
}

