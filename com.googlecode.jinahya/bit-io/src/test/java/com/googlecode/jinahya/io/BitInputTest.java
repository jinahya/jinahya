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


package com.googlecode.jinahya.io;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitInputTest extends BitIOTest {


    @Test(invocationCount = INVOCATION_COUNT)
    public void testReadUnsignedInt() throws IOException {

        for (int length = 1; length < 32; length++) {

            final int expected = RANDOM.nextInt() >>> (32 - length);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final DataOutputStream dos = new DataOutputStream(baos);
            dos.writeInt(expected << (32 - length));
            dos.flush();
            baos.flush();

            final ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            final BitInput bi = new BitInput(bais);
            final int actual = bi.readUnsignedInt(length);
            bi.align(4);

            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testReadInt() throws IOException {

        for (int length = 2; length <= 32; length++) {

            final int expected = RANDOM.nextInt() >> (32 - length);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final DataOutputStream dos = new DataOutputStream(baos);
            dos.writeInt(expected << (32 - length));
            dos.flush();
            baos.flush();

            final ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            final BitInput bi = new BitInput(bais);
            final int actual = bi.readInt(length);
            bi.align(4);

            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testReadFloat() throws IOException {

        final float expected = RANDOM.nextFloat();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final DataOutputStream dos = new DataOutputStream(baos);
        dos.writeFloat(expected);
        dos.flush();
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        final float actual = bi.readFloat();
        Assert.assertEquals(bi.align(1), 0);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testReadUnsignedLong() throws IOException {

        for (int length = 1; length < 64; length++) {

            final long expected = RANDOM.nextLong() >>> (64 - length);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final DataOutputStream dos = new DataOutputStream(baos);
            dos.writeLong(expected << (64 - length));
            dos.flush();
            baos.flush();

            final ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            final BitInput bi = new BitInput(bais);
            final long actual = bi.readUnsignedLong(length);
            bi.align(8);

            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testReadLong() throws IOException {

        for (int length = 2; length <= 64; length++) {

            final long expected = RANDOM.nextLong() >> (64 - length);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final DataOutputStream dos = new DataOutputStream(baos);
            dos.writeLong(expected << (64 - length));
            dos.flush();
            baos.flush();

            final ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            final BitInput bi = new BitInput(bais);
            final long actual = bi.readLong(length);
            bi.align(8);

            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testReadDouble() throws IOException {

        final double expected = RANDOM.nextDouble();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final DataOutputStream dos = new DataOutputStream(baos);
        dos.writeDouble(expected);
        dos.flush();
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        final double actual = bi.readDouble();
        Assert.assertEquals(bi.align(1), 0);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testReadUTF() throws IOException {

        final String expected = newString(false);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final DataOutputStream dos = new DataOutputStream(baos);
        dos.writeUTF(expected);
        dos.flush();
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        final String actual = bi.readUTF();
        bi.align(1);

        Assert.assertEquals(actual, expected);
    }


}

