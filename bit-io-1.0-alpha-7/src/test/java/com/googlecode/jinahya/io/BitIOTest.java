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


package com.googlecode.jinahya.io;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitIOTest {


    /** random. */
    private static final Random RANDOM = new Random();


    @Test
    public void testBoolean() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Boolean> values = new ArrayList<Boolean>(count);
        for (int i = 0; i < count; i++) {
            values.add(RANDOM.nextBoolean());
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (boolean value : values) {
            bo.writeBoolean(value);
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (boolean expected : values) {
            Assert.assertEquals(bi.readBoolean(), expected);
        }
        bi.align(1);
    }


    @Test
    public void testUnsignedInt() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Integer> values = new ArrayList<Integer>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = RANDOM.nextInt(31) + 1;
            Assert.assertTrue(length >= 1);
            Assert.assertTrue(length < 32);
            values.add(length);
            final int value = RANDOM.nextInt() >>> (32 - length);
            values.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (int i = 0; i < values.size(); i += 2) {
            bo.writeUnsignedInt(values.get(i), values.get(i + 1));
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (int i = 0; i < values.size(); i += 2) {
            final int length = values.get(i);
            final int expected = values.get(i + 1);
            final int actual = bi.readUnsignedInt(length);
            Assert.assertEquals(actual, expected);
        }
        bi.align(1);
    }


    @Test
    public void testUnsignedInt31() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Integer> values = new ArrayList<Integer>(count);

        for (int i = 0; i < count; i++) {
            int value = RANDOM.nextInt();
            if (value < 0) {
                value >>>= 1;
            }
            values.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (int value : values) {
            bo.writeUnsignedInt(0x1F, value);
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (int expected : values) {
            Assert.assertEquals(bi.readUnsignedInt(0x1F), expected);
        }
        bi.align(1);
    }


    @Test
    public void testInt() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Integer> values = new ArrayList<Integer>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = RANDOM.nextInt(31) + 2;
            Assert.assertTrue(length > 1);
            Assert.assertTrue(length <= 32);
            values.add(length);
            final int value = RANDOM.nextInt() >> (32 - length);
            values.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (int i = 0; i < values.size(); i += 2) {
            bo.writeInt(values.get(i), values.get(i + 1));
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (int i = 0; i < values.size(); i += 2) {
            final int length = values.get(i);
            final int expected = values.get(i + 1);
            final int actual = bi.readInt(length);
            Assert.assertEquals(actual, expected);
        }
        bi.align(1);
    }


    @Test
    public void testInt32() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Integer> values = new ArrayList<Integer>(count);
        for (int i = 0; i < count; i++) {
            values.add(RANDOM.nextInt());
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (int value : values) {
            bo.writeInt(32, value);
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (int expected : values) {
            Assert.assertEquals(bi.readInt(32), expected);
        }
        bi.align(1);
    }


    @Test
    public void testFloat() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Float> values = new ArrayList<Float>(count);
        for (int i = 0; i < count; i++) {
            values.add(RANDOM.nextFloat());
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (float value : values) {
            bo.writeFloat(value);
        }
        Assert.assertEquals(bo.align(), 0);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (float expected : values) {
            final float actual = bi.readFloat();
            Assert.assertEquals(actual, expected);
        }
        Assert.assertEquals(bi.align(), 0);
    }


    @Test
    public void testUnsignedLong() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Long> values = new ArrayList<Long>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = RANDOM.nextInt(63) + 1;
            Assert.assertTrue(length >= 1);
            Assert.assertTrue(length < 64);
            values.add((long) length);
            final long value = RANDOM.nextLong() >>> (64 - length);
            values.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (int i = 0; i < values.size(); i += 2) {
            bo.writeUnsignedLong(values.get(i).intValue(), values.get(i + 1));
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (int i = 0; i < values.size(); i += 2) {
            final int length = values.get(i).intValue();
            final long expected = values.get(i + 1);
            final long actual = bi.readUnsignedLong(length);
            Assert.assertEquals(actual, expected);
        }
        bi.align(1);
    }


    @Test
    public void testUnsignedLong63() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Long> values = new ArrayList<Long>(count);

        for (int i = 0; i < count; i++) {
            long value = RANDOM.nextInt();
            if (value < 0) {
                value >>>= 1;
            }
            values.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (long value : values) {
            bo.writeUnsignedLong(0x3F, value);
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (long expected : values) {
            Assert.assertEquals(bi.readUnsignedLong(0x3F), expected);
        }
        bi.align(1);
    }


    @Test
    public void testLong() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Long> values = new ArrayList<Long>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = RANDOM.nextInt(63) + 2;
            Assert.assertTrue(length > 1);
            Assert.assertTrue(length <= 64);
            values.add((long) length);
            final long value = RANDOM.nextLong() >> (64 - length);
            values.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (int i = 0; i < values.size(); i += 2) {
            bo.writeLong(values.get(i).intValue(), values.get(i + 1));
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (int i = 0; i < values.size(); i += 2) {
            final int length = values.get(i).intValue();
            final long expected = values.get(i + 1);
            final long actual = bi.readLong(length);
            Assert.assertEquals(actual, expected);
        }
        bi.align(1);
    }


    @Test
    public void testLong64() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Long> values = new ArrayList<Long>(count);
        for (int i = 0; i < count; i++) {
            values.add(RANDOM.nextLong());
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (long value : values) {
            bo.writeLong(0x40, value);
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (long expected : values) {
            Assert.assertEquals(bi.readLong(0x40), expected);
        }
        bi.align(1);
    }


    @Test
    public void testDouble() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Double> values = new ArrayList<Double>(count);
        for (int i = 0; i < count; i++) {
            values.add(RANDOM.nextDouble());
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (double value : values) {
            bo.writeDouble(value);
        }
        Assert.assertEquals(bo.align(), 0);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (double expected : values) {
            final double actual = bi.readDouble();
            Assert.assertEquals(actual, expected);
        }
        Assert.assertEquals(bi.align(), 0);
    }


    @Test(invocationCount = 128)
    public void testBytes() throws IOException {

        final int length = RANDOM.nextInt(128) + 128;

        final byte[] expected = new byte[length];
        RANDOM.nextBytes(expected);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        bo.writeBytes(expected);
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        final byte[] actual = bi.readBytes();
        bi.align(1);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public void testASCII() throws IOException {

        final String expected =
            RandomStringUtils.randomAscii(RANDOM.nextInt(128) + 128);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        bo.writeASCII(expected);
        final int oa = bo.align();
        Assert.assertTrue(oa < 8);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        final String actual = bi.readASCII();
        Assert.assertTrue(bi.align() < 8);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public void testUTF() throws IOException {

        final String expected =
            RandomStringUtils.random(RANDOM.nextInt(128) + 128);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);
        output.writeUTF(expected);
        output.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput input = new BitInput(bais);
        final String actual = input.readUTF();
        input.align(1);

        Assert.assertEquals(actual, expected);
    }


}

