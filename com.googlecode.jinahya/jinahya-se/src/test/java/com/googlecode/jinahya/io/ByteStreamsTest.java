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


import com.googlecode.jinahya.security.MessageDigests;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ByteStreamsTest {


    protected static File newTempFile() throws IOException {

        final File file = File.createTempFile("prefix", null);
        file.deleteOnExit();

        // fill input
        final RandomAccessFile raf = new RandomAccessFile(file, "rwd");
        raf.setLength(ThreadLocalRandom.current().nextInt(65536));
        raf.close();

        return file;
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public static void testCopyWithNullInput() throws IOException {

        ByteStreams.copy((InputStream) null, new BlackOutputStream(), new byte[1],
                         -1L);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public static void testCopyWithNullOutput() throws IOException {

        ByteStreams.copy(new WhiteInputStream(), (OutputStream) null, new byte[1],
                         -1L);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public static void testCopyWithNullBuffer() throws IOException {

        ByteStreams.copy(new WhiteInputStream(), new BlackOutputStream(), null,
                         -1L);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public static void testCopyWithZeroLengthBuffer() throws IOException {

        ByteStreams.copy(new WhiteInputStream(), new BlackOutputStream(),
                         new byte[0], -1L);
    }


    @Test(invocationCount = 32)
    public static void testCopyWithPositiveLength() throws IOException {

        final Random random = ThreadLocalRandom.current();

        final long length = random.nextInt(1048576);

        final long count = ByteStreams.copy(
            new WhiteInputStream(), new BlackOutputStream(), new byte[8192],
            length);

        Assert.assertEquals(count, length);
    }


    @Test(invocationCount = 32)
    public static void testCopyWithNegativeLength() throws IOException {

        final Random random = ThreadLocalRandom.current();

        final long limit = random.nextInt(1048576);

        final long count = ByteStreams.copy(
            new WhiteInputStream(limit), new BlackOutputStream(),
            new byte[8192], -1L);

        Assert.assertEquals(count, limit);
    }


    @Test(invocationCount = 32)
    public static void testCopyFileToFileWithoutLength()
        throws IOException, NoSuchAlgorithmException {

        final File input = newTempFile();
        final File output = newTempFile();

        ByteStreams.copy(input, output, new byte[1], -1L);

        final MessageDigest digest = MessageDigest.getInstance("SHA-1");

        Assert.assertEquals(
            MessageDigests.digest(digest, input, new byte[1024], -1L),
            MessageDigests.digest(digest, output, ByteBuffer.allocate(9), -1L));
    }


}
