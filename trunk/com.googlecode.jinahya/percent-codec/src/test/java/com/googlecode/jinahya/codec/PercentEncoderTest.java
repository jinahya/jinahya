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


import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class PercentEncoderTest {


    @Test(expectedExceptions = {NullPointerException.class})
    public void testEncodeWithNullBytes() {

        final PercentEncoder encoder = new PercentEncoder();

        final byte[] input = null;

        final byte[] output = encoder.encode(input);
    }


    @Test(invocationCount = 128)
    public void testEncodeWithBytes() {

        final PercentEncoder encoder = new PercentEncoder();

        final byte[] input = PercentCodecTestHelper.decodedBytes(1024);

        final byte[] output = encoder.encode(input);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void testEncodeToStringWithNullBytes() {

        final PercentEncoder encoder = new PercentEncoder();

        final byte[] input = null;

        final String output =
            encoder.encodeToString(input, Charset.defaultCharset());
    }


    @Test(invocationCount = 128)
    public void testEncodeToStringWithBytes() {

        final PercentEncoder encoder = new PercentEncoder();

        final byte[] input = PercentCodecTestHelper.decodedBytes(1024);

        final String output =
            encoder.encodeToString(input, Charset.defaultCharset());
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void testEncodeWithNullString() {

        final PercentEncoder encoder = new PercentEncoder();

        final String input = null;

        final byte[] output = encoder.encode(input, Charset.defaultCharset());
    }


    @Test(invocationCount = 128)
    public void testEncodeWithString() {

        final PercentEncoder encoder = new PercentEncoder();

        final String input = PercentCodecTestHelper.decodedString(1024);

        final byte[] output = encoder.encode(input, StandardCharsets.UTF_8);
    }


    @Test(invocationCount = 128)
    public void testEncodeToStringWithString()
        throws UnsupportedEncodingException {

        final PercentEncoder encoder = new PercentEncoder();

        final String input = PercentCodecTestHelper.decodedString(1024);

        final String output = encoder.encodeToString(
            input, StandardCharsets.UTF_8, StandardCharsets.US_ASCII);
    }


}
