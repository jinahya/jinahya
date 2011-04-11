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


package jinahya.io;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        final List<Boolean> list = new ArrayList<Boolean>(count);
        for (int i = 0; i < count; i++) {
            list.add(RANDOM.nextBoolean());
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);
        for (boolean value : list) {
            output.writeBoolean(value);
        }
        output.aling(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput input = new BitInput(bais);
        for (boolean expected : list) {
            Assert.assertEquals(input.readBoolean(), expected);
        }
        input.aling(1);
    }


    @Test
    public void testUnsignedInt() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Integer> list = new ArrayList<Integer>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = RANDOM.nextInt(31) + 1;
            Assert.assertTrue(length >= 1);
            Assert.assertTrue(length < 32);
            list.add(length);
            final int value = RANDOM.nextInt() >>> (32 - length);
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);
        for (int i = 0; i < list.size(); i += 2) {
            output.writeUnsignedInt(list.get(i), list.get(i + 1));
        }
        output.aling(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput input = new BitInput(bais);
        for (int i = 0; i < list.size(); i += 2) {
            final int length = list.get(i);
            final int expected = list.get(i + 1);
            final int actual = input.readUnsignedInt(length);
            //System.out.println(length + " / " + expected + " / " + actual);
            Assert.assertEquals(actual, expected);
        }
        input.aling(1);
    }


    @Test
    public void testUnsignedInt31() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Integer> list = new ArrayList<Integer>(count);

        for (int i = 0; i < count; i++) {
            int value = RANDOM.nextInt();
            if (value < 0) {
                value >>>= 1;
            }
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);
        for (int value : list) {
            output.writeUnsignedInt(0x1F, value);
        }
        output.aling(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput input = new BitInput(bais);
        for (int expected : list) {
            Assert.assertEquals(input.readUnsignedInt(0x1F), expected);
        }
        input.aling(1);
    }


    @Test
    public void testInt() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Integer> list = new ArrayList<Integer>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = RANDOM.nextInt(31) + 2;
            Assert.assertTrue(length > 1);
            Assert.assertTrue(length <= 32);
            list.add(length);
            final int value = RANDOM.nextInt() >> (32 - length);
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);
        for (int i = 0; i < list.size(); i += 2) {
            output.writeInt(list.get(i), list.get(i + 1));
        }
        output.aling(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput input = new BitInput(bais);
        for (int i = 0; i < list.size(); i += 2) {
            final int length = list.get(i);
            final int expected = list.get(i + 1);
            final int actual = input.readInt(length);
            //System.out.println(length + " / " + expected + " / " + actual);
            Assert.assertEquals(actual, expected);
        }
        input.aling(1);
    }


    @Test
    public void testInt32() throws IOException {
        final int count = RANDOM.nextInt(128) + 128;
        final List<Integer> list = new ArrayList<Integer>(count);
        for (int i = 0; i < count; i++) {
            list.add(RANDOM.nextInt());
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);
        for (int value : list) {
            output.writeInt(32, value);
        }
        output.aling(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput input = new BitInput(bais);
        for (int expected : list) {
            Assert.assertEquals(input.readInt(32), expected);
        }
        input.aling(1);
    }


    @Test
    public void testUnsignedLong() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Long> list = new ArrayList<Long>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = RANDOM.nextInt(63) + 1;
            Assert.assertTrue(length >= 1);
            Assert.assertTrue(length < 64);
            list.add((long) length);
            final long value = RANDOM.nextLong() >>> (64 - length);
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);
        for (int i = 0; i < list.size(); i += 2) {
            output.writeUnsignedLong(list.get(i).intValue(), list.get(i + 1));
        }
        output.aling(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput input = new BitInput(bais);
        for (int i = 0; i < list.size(); i += 2) {
            final int length = list.get(i).intValue();
            final long expected = list.get(i + 1);
            final long actual = input.readUnsignedLong(length);
            //System.out.println(length + " / " + expected + " / " + actual);
            Assert.assertEquals(actual, expected);
        }
        input.aling(1);
    }


    @Test
    public void testUnsignedLong63() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Long> list = new ArrayList<Long>(count);

        for (int i = 0; i < count; i++) {
            long value = RANDOM.nextInt();
            if (value < 0) {
                value >>>= 1;
            }
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);
        for (long value : list) {
            output.writeUnsignedLong(0x3F, value);
        }
        output.aling(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput input = new BitInput(bais);
        for (long expected : list) {
            Assert.assertEquals(input.readUnsignedLong(0x3F), expected);
        }
        input.aling(1);
    }


    @Test
    public void testLong() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Long> list = new ArrayList<Long>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = RANDOM.nextInt(63) + 2;
            Assert.assertTrue(length > 1);
            Assert.assertTrue(length <= 64);
            list.add((long) length);
            final long value = RANDOM.nextLong() >> (64 - length);
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);
        for (int i = 0; i < list.size(); i += 2) {
            output.writeLong(list.get(i).intValue(), list.get(i + 1));
        }
        output.aling(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput input = new BitInput(bais);
        for (int i = 0; i < list.size(); i += 2) {
            final int length = list.get(i).intValue();
            final long expected = list.get(i + 1);
            final long actual = input.readLong(length);
            //System.out.println(length + " / " + expected + " / " + actual);
            Assert.assertEquals(actual, expected);
        }
        input.aling(1);
    }


    @Test
    public void testLong64() throws IOException {
        final int count = RANDOM.nextInt(128) + 128;
        final List<Long> list = new ArrayList<Long>(count);
        for (int i = 0; i < count; i++) {
            list.add(RANDOM.nextLong());
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);
        for (long value : list) {
            output.writeLong(0x40, value);
        }
        output.aling(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput input = new BitInput(bais);
        for (long expected : list) {
            Assert.assertEquals(input.readLong(0x40), expected);
        }
        input.aling(1);
    }
}
