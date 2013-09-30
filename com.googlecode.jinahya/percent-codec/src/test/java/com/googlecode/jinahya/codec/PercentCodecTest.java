/*
 * Copyright 2011 Jin Kwon.
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


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class PercentCodecTest {


    @Test(invocationCount = 128)
    public void testBytesToBytes() throws IOException {

        final byte[] expected = PercentCodecTestHelper.decodedBytes(1024);

        final byte[] encoded = new PercentEncoder().encode(expected);

        final byte[] actual = new PercentDecoder().decode(encoded);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public void testBytesToString() throws IOException {

        final byte[] expected = PercentCodecTestHelper.decodedBytes(1024);

        final String encoded = new PercentEncoder().encodeToString(
            expected, StandardCharsets.US_ASCII);

        final byte[] actual = new PercentDecoder().decode(
            encoded, StandardCharsets.US_ASCII);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public void testStringToBytes() throws IOException {

        final String expected = PercentCodecTestHelper.decodedString(1024);

        final byte[] encoded = new PercentEncoder().encode(
            expected, StandardCharsets.UTF_8);

        final String actual = new PercentDecoder().decodeToString(
            encoded, StandardCharsets.UTF_8);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public void testStringToString() throws IOException {

        final String expected = PercentCodecTestHelper.decodedString(1024);

        final String encoded = new PercentEncoder().encodeToString(
            expected, StandardCharsets.UTF_8, StandardCharsets.US_ASCII);

        final String actual = new PercentDecoder().decodeToString(
            encoded, StandardCharsets.US_ASCII, StandardCharsets.UTF_8);

        Assert.assertEquals(actual, expected);
    }


}
