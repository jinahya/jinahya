/*
 *  Copyright 2010 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package jinahya.io;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitIOTest {


    private static final Random RANDOM = new Random();


    @Test(invocationCount = 128)
    public void testBoolean() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);

        final boolean expected = RANDOM.nextBoolean();

        output.writeBoolean(expected);
        output.aling(1);

        baos.flush();
        final byte[] bytes = baos.toByteArray();
        Assert.assertTrue(bytes.length == 1);

        final BitInput input = new BitInput(new ByteArrayInputStream(bytes));

        final boolean actual = input.readBoolean();

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testUnsignedInt() throws IOException {

        final int count = 1024;

        final List<Number> list = new LinkedList<Number>();

        for (int i = 0; i < count; i++) {

            final int length = RANDOM.nextInt(31) + 1; // 1 - 31
            Assert.assertTrue(length >= 1);
            Assert.assertTrue(length < 32);
            list.add(length);

            final long value =
                (RANDOM.nextInt() & 0x7FFFFFFF) >> (32 - length);
            if (true) {
                if (value < 0) {
                    Assert.fail("negative value");
                } else {
                    Assert.assertEquals(value >> length, 0);
                }
            }
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final BitOutput output = new BitOutput(baos);
        for (int i = 0; i < count; i++) {
            final int length = list.get(i * 2).intValue();
            final int value = list.get(i * 2 + 1).intValue();
            output.writeUnsignedInt(length, value);
        }
        output.aling(1);

        baos.flush();
        final byte[] bytes = baos.toByteArray();

        final BitInput input = new BitInput(new ByteArrayInputStream(bytes));
        for (int i = 0; i < count; i++) {
            final int length = list.get(i * 2).intValue();
            final int expected = list.get(i * 2 + 1).intValue();
            final int actual = input.readUnsignedInt(length);
            Assert.assertEquals(actual, expected);
        }
        input.aling(1);
    }


    @Test
    public void testInt() throws IOException {


        final int count = 1024;

        final List<Number> list = new LinkedList<Number>();

        for (int i = 0; i < count; i++) {
            final int length = RANDOM.nextInt(31) + 2; // 2 - 32
            Assert.assertTrue(length > 1);
            Assert.assertTrue(length <= 32);
            list.add(length);

            final int value = RANDOM.nextInt() >> (32 - length);
            if (length < 32) {
                if (value < 0) {
                    Assert.assertEquals(value >> length, -1);
                } else {
                    Assert.assertEquals(value >> length, 0);
                }
            }
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final BitOutput output = new BitOutput(baos);
        for (int i = 0; i < count; i++) {
            final int length = list.get(i * 2).intValue();
            final int value = list.get(i * 2 + 1).intValue();
            output.writeInt(length, value);
        }
        output.aling(1);

        baos.flush();
        final byte[] bytes = baos.toByteArray();

        final BitInput input = new BitInput(new ByteArrayInputStream(bytes));
        for (int i = 0; i < count; i++) {
            final int length = list.get(i * 2).intValue();
            final int expected = list.get(i * 2 + 1).intValue();
            final int actual = input.readInt(length);
            Assert.assertEquals(actual, expected);
        }
        input.aling(1);
    }


    @Test
    public void testUnsignedLong() throws IOException {

        final int count = 1024;

        final List<Number> list = new LinkedList<Number>();

        for (int i = 0; i < count; i++) {

            final int length = RANDOM.nextInt(63) + 1; // 1 - 63
            Assert.assertTrue(length >= 1);
            Assert.assertTrue(length < 64);
            list.add(length);

            final long value =
                (RANDOM.nextLong() & 0x7FFFFFFFFFFFFFFFL) >> (64 - length);
            if (true) {
                if (value < 0L) {
                    Assert.fail("negative");
                } else {
                    Assert.assertEquals(value >> length, 0);
                }
            }
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final BitOutput output = new BitOutput(baos);
        for (int i = 0; i < count; i++) {
            final int length = list.get(i * 2).intValue();
            final long value = list.get(i * 2 + 1).longValue();
            output.writeUnsignedLong(length, value);
        }
        output.aling(1);

        baos.flush();
        final byte[] bytes = baos.toByteArray();

        final BitInput input = new BitInput(new ByteArrayInputStream(bytes));
        for (int i = 0; i < count; i++) {
            final int length = list.get(i * 2).intValue();
            final long expected = list.get(i * 2 + 1).longValue();
            final long actual = input.readUnsignedLong(length);
            Assert.assertEquals(actual, expected);
        }
        input.aling(1);
    }


    @Test
    public void testLong() throws IOException {

        final int count = 1024;

        final List<Number> list = new LinkedList<Number>();

        for (int i = 0; i < count; i++) {

            final int length = RANDOM.nextInt(63) + 2; // 2 - 64
            Assert.assertTrue(length > 1);
            Assert.assertTrue(length <= 64);
            list.add(length);

            final long value = RANDOM.nextLong() >> (64 - length);
            if (length < 64) {
                if (value < 0L) {
                    Assert.assertEquals(value >> length, -1L);
                } else {
                    Assert.assertEquals(value >> length, 0L);
                }
            }
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final BitOutput output = new BitOutput(baos);
        for (int i = 0; i < count; i++) {
            final int length = list.get(i * 2).intValue();
            final long value = list.get(i * 2 + 1).longValue();
            output.writeLong(length, value);
        }
        output.aling(1);

        baos.flush();
        final byte[] bytes = baos.toByteArray();

        final BitInput input = new BitInput(new ByteArrayInputStream(bytes));
        for (int i = 0; i < count; i++) {
            final int length = list.get(i * 2).intValue();
            final long expected = list.get(i * 2 + 1).longValue();
            final long actual = input.readLong(length);
            Assert.assertEquals(actual, expected);
        }
        input.aling(1);
    }


}
