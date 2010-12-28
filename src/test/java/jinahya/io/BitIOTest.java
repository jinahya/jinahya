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


    public void testUnsignedByte() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);

        final int length = RANDOM.nextInt(8) + 1;

        int value = 0x00;
        for (int i = 0; i < length; i++) {
            value <<= 1;
            value |= (RANDOM.nextBoolean() ? 0x01 : 0x00);
        }

        //output.writeUnsignedByte(length, value);
        output.aling(1);

        baos.flush();
        Assert.assertEquals(baos.size(), 1);

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));

        //Assert.assertEquals(input.readUnsignedByte(length), value);
    }


    @Test(invocationCount = 128)
    public void testBoolean() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);

        final boolean expected = RANDOM.nextBoolean();

        output.writeBoolean(expected);
        output.aling(1);

        baos.flush();
        Assert.assertEquals(baos.size(), 1);

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));

        final boolean actual = input.readBoolean();

        Assert.assertEquals(actual, expected);
    }


    public void testUnsignedShort() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);

        final int length = RANDOM.nextInt(16) + 1;

        int value = RANDOM.nextInt(65536);
        value <<= (32 - length);
        value >>>= (32 - length);

        long start = System.currentTimeMillis();
        //output.writeUnsignedShort(length, value);
        output.aling(1);

        //System.out.println(length + "/ " + value + " / " + (System.currentTimeMillis() - start));

        baos.flush();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));

        //final int actual = input.readUnsignedShort(length);

        //Assert.assertEquals(actual, value);
    }


    @Test
    public void testInt() throws IOException {


        final int count = 1024;

        final List<Integer> list = new LinkedList<Integer>();

        for (int i = 0; i < count; i++) {
            final int length = RANDOM.nextInt(31) + 2; // 2 - 32
            Assert.assertTrue(length > 1);
            Assert.assertTrue(length <= 32);
            list.add(length);

            final int expected = RANDOM.nextInt() >> (32 - length);
            if (length < 32) {
                if (expected < 0) {
                    Assert.assertEquals(expected >> length, -1);
                } else {
                    Assert.assertEquals(expected >> length, 0);
                }
            }
            list.add(expected);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final BitOutput output = new BitOutput(baos);
        for (int i = 0; i < count; i++) {
            output.writeInt(list.get(i * 2), list.get(i * 2 + 1));
        }
        output.aling(1);

        baos.flush();
        final byte[] bytes = baos.toByteArray();

        final BitInput input = new BitInput(new ByteArrayInputStream(bytes));
        for (int i = 0; i < count; i++) {

            final int length = list.get(i * 2);
            final int expected = list.get(i * 2 + 1);

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

            final long expected = RANDOM.nextLong() >>> (64 - length);
            if (expected < 0L) {
                Assert.fail("negative");
            } else {
                Assert.assertEquals(expected >> length, 0);
            }
            list.add(expected);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final BitOutput output = new BitOutput(baos);
        for (int i = 0; i < count; i++) {
            final int length = list.get(i * 2).intValue();
            final long expected = list.get(i * 2 + 1).longValue();
            output.writeUnsignedLong(length, expected);
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


}
