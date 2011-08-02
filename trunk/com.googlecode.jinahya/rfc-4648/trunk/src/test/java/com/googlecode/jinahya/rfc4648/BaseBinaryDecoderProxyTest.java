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

import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BaseBinaryDecoderProxyTest {


    private static void test(final Map<String, String> vector, final Base base,
                             final BinaryDecoder commons)
        throws DecoderException {

        test(vector, base, commons, Modifier.TO_SAME);
    }


    private static void test(final Map<String, String> vector,
                             final Base base, final BinaryDecoder commons,
                             final Modifier modifier)
        throws DecoderException {

        final BinaryDecoder jinahya =
            ((BinaryDecoder) BaseBinaryDecoderProxy.newInstance(base));

        test(vector, jinahya, commons, modifier);
    }


    private static void test(final Map<String, String> vector,
                             final BinaryDecoder jinahya,
                             final BinaryDecoder commons,
                             final Modifier modifier)
        throws DecoderException {

        for (Entry<String, String> entry : vector.entrySet()) {

            final byte[] original = entry.getKey().getBytes();

            final byte[] source = entry.getValue().getBytes();

            final byte[] decoded1 = jinahya.decode(source);
            Assert.assertEquals(decoded1, original);
            //System.out.println("jinahya: " + hex(decoded1));

            final byte[] decoded2 = commons.decode(modifier.modify(source));
            Assert.assertEquals(decoded2, original);
            //System.out.println("commons: " + hex(decoded2));

            Assert.assertEquals(decoded1, decoded2);
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
    public void testBase16() throws DecoderException {

        final Base base = new Base16();
        final BinaryDecoder commons = new Hex();

        test(TestVectors.BASE16, base, commons, Modifier.TO_LOWER);
    }


    @Test
    public void testBase32() throws DecoderException {

        final Base base = new Base32();
        final BinaryDecoder commons =
            new org.apache.commons.codec.binary.Base32(-1, null, false);

        test(TestVectors.BASE32, base, commons);
    }


    @Test
    public void testBase32Hex() throws DecoderException {

        final Base base = new Base32Hex();
        final BinaryDecoder commons =
            new org.apache.commons.codec.binary.Base32(-1, null, true);

        test(TestVectors.BASE32HEX, base, commons);
    }


    @Test
    public void testBase64() throws DecoderException {

        final Base base = new Base64();
        final BinaryDecoder commons =
            new org.apache.commons.codec.binary.Base64(-1, null, false);

        test(TestVectors.BASE64, base, commons);
    }


    @Test
    public void testBase64URL() throws DecoderException {

        final Base base = new Base64URL();
        final BinaryDecoder commons =
            new org.apache.commons.codec.binary.Base64(-1, null, true);

        test(TestVectors.BASE64URL, base, commons);
    }
}

