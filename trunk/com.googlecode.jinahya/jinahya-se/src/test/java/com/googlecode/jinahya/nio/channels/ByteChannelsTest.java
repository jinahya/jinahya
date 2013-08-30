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


package com.googlecode.jinahya.nio.channels;


import com.googlecode.jinahya.io.BlackOutputStream;
import com.googlecode.jinahya.io.WhiteInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class ByteChannelsTest {


    protected static File newTempFile(final boolean empty) throws IOException {

        final File f = File.createTempFile("prefix", null);
        f.deleteOnExit();

        if (!empty) {
            // fill input
            final RandomAccessFile raf = new RandomAccessFile(f, "rwd");
            raf.setLength(ThreadLocalRandom.current().nextInt(65536));
            raf.close();
        }

        return f;
    }


    @Test(invocationCount = 32)
    public void testCopyFromFileToFile() throws IOException {

        final File source = newTempFile(false);
        final File target = newTempFile(true);

        final long count = ByteChannels.copy(
            source, target, ByteBuffer.allocate(1024), -1L);

        Assert.assertEquals(count, source.length());
    }


    @Test(invocationCount = 32)
    public void testCopyFromFileToBlackOutput() throws IOException {

        final File input = newTempFile(false);

        final WritableByteChannel output = new BlackOutputStream().newChannel();

        final long count = ByteChannels.copy(
            input, output, ByteBuffer.allocate(1024), -1L);

        Assert.assertEquals(count, input.length());
    }


    @Test(invocationCount = 32)
    public void testCopyFromWhiteInputToFile() throws IOException {

        final long limit = ThreadLocalRandom.current().nextInt(65535);
        final ReadableByteChannel input =
            new WhiteInputStream(limit).newChannel();

        final File output = newTempFile(true);

        final long count = ByteChannels.copy(
            input, output, ByteBuffer.allocate(1024), -1L);

        Assert.assertEquals(count, limit);
        Assert.assertEquals(output.length(), limit);
    }


}
