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


import com.googlecode.jinahya.codec.PercentDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class PercentDecoderTest {


    private static Random random() {

        return ThreadLocalRandom.current();
    }


    static byte[] decodedBytes() {

        final byte[] decoded = new byte[random().nextInt(1024)];

        random().nextBytes(decoded);

        return decoded;
    }


    static String decodedString() {

        return RandomStringUtils.random(random().nextInt(1024));
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void testDecodeWithNullBytes() {

        final PercentDecoder decoder = new PercentDecoder();

        final byte[] encoded = null;

        decoder.decode(encoded);
    }


    @Test(invocationCount = 128)
    public void testDecodeWithBytes() {

        final PercentDecoder decoder = new PercentDecoder();

        final byte[] encoded = PercentEncoderTest.encodedBytes();

        final byte[] decoded = decoder.decode(encoded);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void testDecodeToStringWithNullBytes() {

        final PercentDecoder decoder = new PercentDecoder();

        final byte[] encoded = null;

        final String decoded = decoder.decodeToString(
            encoded, StandardCharsets.UTF_8);
    }


    @Test(invocationCount = 128)
    public void testDecodeToStringWithBytes() {

        final PercentDecoder decoder = new PercentDecoder();

        final byte[] encoded = PercentEncoderTest.encodedBytes();

        final String decoded = decoder.decodeToString(
            encoded, StandardCharsets.UTF_8);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void testDecodeWithNullString() {

        final PercentDecoder decoder = new PercentDecoder();

        final String encoded = null;

        final byte[] decoded = decoder.decode(
            encoded, StandardCharsets.US_ASCII);
    }


    @Test(invocationCount = 128)
    public void testDecodeWithString() {

        final PercentDecoder decoder = new PercentDecoder();

        final String encoded = PercentEncoderTest.encodedString();

        final byte[] decoded =
            decoder.decode(encoded, StandardCharsets.US_ASCII);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void testDecodeToStringWithNullString() {

        final PercentDecoder decoder = new PercentDecoder();

        final String encoded = null;

        final String decoded = decoder.decodeToString(
            encoded, StandardCharsets.US_ASCII, StandardCharsets.UTF_8);
    }


    @Test(invocationCount = 128)
    public void testDecodeToStringWithString() {

        final PercentDecoder decoder = new PercentDecoder();

        final String encoded = PercentEncoderTest.encodedString();

        final String decoded = decoder.decodeToString(
            encoded, StandardCharsets.US_ASCII, StandardCharsets.UTF_8);
    }


}
