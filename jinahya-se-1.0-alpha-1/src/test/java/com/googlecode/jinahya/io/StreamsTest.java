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


package com.googlecode.jinahya.io;


import java.io.EOFException;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class StreamsTest {


    @Test(expectedExceptions = {NullPointerException.class})
    public static void testCopyWithNullInput() throws IOException {

        Streams.copy(null, new BlackOutputStream(), new byte[1]);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public static void testCopyWithNullOutput() throws IOException {

        Streams.copy(new WhiteInputStream(), null, new byte[1]);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public static void testCopyWithNullBuffer() throws IOException {

        Streams.copy(new WhiteInputStream(), new BlackOutputStream(), null);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public static void testCopyWithZeroLengthBuffer() throws IOException {

        Streams.copy(new WhiteInputStream(), new BlackOutputStream(),
                     new byte[0]);
    }


    @Test(invocationCount = 32)
    public static void testCopyWithLength() throws IOException {

        final Random random = ThreadLocalRandom.current();

        final long length = random.nextInt(1048576);

        final long count = Streams.copy(new WhiteInputStream(),
                                        new BlackOutputStream(), new byte[8192],
                                        length);

        Assert.assertEquals(count, length);
    }


    @Test(invocationCount = 32)
    public static void testCopyWithoutLength() throws IOException {

        final Random random = ThreadLocalRandom.current();

        final long limit = random.nextInt(1048576);

        final long count = Streams.copy(
            new WhiteInputStream(limit), new BlackOutputStream(),
            new byte[8192], -1L);

        Assert.assertEquals(count, limit);
    }


    @Test(expectedExceptions = {EOFException.class})
    public static void testCopyWithLengthOverLimit() throws IOException {

        final Random random = ThreadLocalRandom.current();

        final long limit = random.nextInt(1048576);

        final long count = Streams.copy(
            new WhiteInputStream(limit), new BlackOutputStream(),
            new byte[8192], limit + 1);
    }


}

