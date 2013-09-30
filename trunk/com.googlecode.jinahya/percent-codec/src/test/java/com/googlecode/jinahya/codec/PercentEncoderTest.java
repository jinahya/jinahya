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


import com.googlecode.jinahya.codec.PercentEncoder;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class PercentEncoderTest {


    private static Random random() {

        return ThreadLocalRandom.current();
    }


    static byte[] encoded(final byte[] decoded) {

        return PercentEncoder.encodeMultiple(decoded);
    }


    static byte[] encodedBytes() {

        return encoded(PercentDecoderTest.decodedBytes());
    }


    static String encodedString() {

        return new String(encodedBytes(), StandardCharsets.US_ASCII);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void testEncodeWithNullBytes() {

        final PercentEncoder encoder = new PercentEncoder();

        final byte[] decoded = null;

        final byte[] encoded = encoder.encode(decoded);
    }


    @Test(invocationCount = 128)
    public void testEncodeWithBytes() {

        final PercentEncoder encoder = new PercentEncoder();

        final byte[] decoded = PercentDecoderTest.decodedBytes();

        final byte[] encoded = encoder.encode(decoded);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void testEncodeToStringWithNullBytes() {

        final PercentEncoder encoder = new PercentEncoder();

        final byte[] decoded = null;

        final String encoded =
            encoder.encodeToString(decoded, StandardCharsets.US_ASCII);
    }


    @Test(invocationCount = 128)
    public void testEncodeToStringWithBytes() {

        final PercentEncoder encoder = new PercentEncoder();

        final byte[] decoded = PercentDecoderTest.decodedBytes();

        final String encoded =
            encoder.encodeToString(decoded, StandardCharsets.US_ASCII);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void testEncodeWithNullString() {

        final PercentEncoder encoder = new PercentEncoder();

        final String decoded = null;

        final byte[] encoded = encoder.encode(decoded, StandardCharsets.UTF_8);
    }


    @Test(invocationCount = 128)
    public void testEncodeWithString() {

        final PercentEncoder encoder = new PercentEncoder();

        final String decoded = PercentDecoderTest.decodedString();

        final byte[] encoded = new PercentEncoder().encode(
            decoded, StandardCharsets.UTF_8);
    }


    @Test(invocationCount = 128)
    public void testEncodeToStringWithString()
        throws UnsupportedEncodingException {

        final PercentEncoder encoder = new PercentEncoder();

        final String decoded = PercentDecoderTest.decodedString();

        final String encoded = new PercentEncoder().encodeToString(
            decoded, StandardCharsets.UTF_8, StandardCharsets.US_ASCII);
    }


}
