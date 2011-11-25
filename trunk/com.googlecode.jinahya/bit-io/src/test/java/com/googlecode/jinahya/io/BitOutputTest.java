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
import java.io.DataInputStream;
import java.io.IOException;

import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitOutputTest {


    private static final Random RANDOM = new Random();


    @Test(invocationCount = 64)
    public void testWriteUnsignedByte() throws IOException {

        for (int length = 1; length <= 8; length++) {

            final int expected = RANDOM.nextInt() >>> (32 - length);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final BitOutput bo = new BitOutput(baos);
            bo.writeUnsignedByte(length, expected);
            bo.align(1);
            baos.flush();

            final ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            final DataInputStream dis = new DataInputStream(bais);

            final int actual = dis.readUnsignedByte() >> (8 - length);

            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = 64)
    public void testWriteUnsignedShort() throws IOException {

        for (int length = 2; length <= 16; length++) {

            final int expected = RANDOM.nextInt() >>> (32 - length);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final BitOutput bo = new BitOutput(baos);
            bo.writeUnsignedShort(length, expected);
            bo.align(2);
            baos.flush();

            final ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            final DataInputStream dis = new DataInputStream(bais);

            final int actual = dis.readUnsignedShort() >> (16 - length);

            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = 64)
    public void testWriteUnsignedInt() throws IOException {

        for (int length = 1; length < 32; length++) {

            final int expected = RANDOM.nextInt() >>> (32 - length);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final BitOutput bo = new BitOutput(baos);
            bo.writeUnsignedInt(length, expected);
            bo.align(4);
            baos.flush();

            final ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            final DataInputStream dis = new DataInputStream(bais);

            final int actual = dis.readInt() >>> (32 - length);

            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = 10)
    public void testWriteInt() throws IOException {

        for (int length = 2; length <= 32; length++) {

            final int expected = RANDOM.nextInt() >> (32 - length);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final BitOutput bo = new BitOutput(baos);
            bo.writeInt(length, expected);
            bo.align(4);
            baos.flush();

            final ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            final DataInputStream dis = new DataInputStream(bais);

            final int actual = dis.readInt() >> (32 - length);

            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = 64)
    public void testWriteFloat() throws IOException {

        final float expected = RANDOM.nextFloat();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        bo.writeFloat(expected);
        Assert.assertEquals(bo.align(1), 0);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final DataInputStream dis = new DataInputStream(bais);

        final float actual = dis.readFloat();

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 64)
    public void testWriteUnsignedLong() throws IOException {

        for (int length = 1; length < 64; length++) {

            final long expected = RANDOM.nextLong() >>> (64 - length);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final BitOutput bo = new BitOutput(baos);
            bo.writeUnsignedLong(length, expected);
            bo.align(8);
            baos.flush();

            final ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            final DataInputStream dis = new DataInputStream(bais);

            final long actual = dis.readLong() >>> (64 - length);

            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = 64)
    public void testWriteLong() throws IOException {

        for (int length = 2; length <= 64; length++) {

            final long expected = RANDOM.nextLong() >> (64 - length);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final BitOutput bo = new BitOutput(baos);
            bo.writeLong(length, expected);
            bo.align(8);
            baos.flush();

            final ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            final DataInputStream dis = new DataInputStream(bais);

            final long actual = dis.readLong() >> (64 - length);

            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = 64)
    public void testWriteDouble() throws IOException {

        final double expected = RANDOM.nextFloat();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        bo.writeDouble(expected);
        Assert.assertEquals(bo.align(1), 0);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final DataInputStream dis = new DataInputStream(bais);

        final double actual = dis.readDouble();

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 64)
    public void testWriteUTF() throws IOException {

        final String expected =
            RandomStringUtils.random(RANDOM.nextInt(64) + 64);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        bo.writeUTF(expected);
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final DataInputStream dis = new DataInputStream(bais);

        final String actual = dis.readUTF();

        Assert.assertEquals(actual, expected);
    }


}

