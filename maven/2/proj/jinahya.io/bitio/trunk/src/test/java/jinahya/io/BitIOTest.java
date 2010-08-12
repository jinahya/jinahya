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


    private static final Random RANDOM = new Random();


    @Test(invocationCount = 128)
    public void testBoolean() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final List<Boolean> list = new ArrayList<Boolean>();
        final int count = RANDOM.nextInt(1024) + 1024;

        final BitOutput output = new BitOutput(baos);
        for (int i = 0; i < count; i++) {
            list.add(RANDOM.nextBoolean());
            output.writeBoolean(list.get(list.size() - 1));
        }
        output.align();

        baos.flush();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));
        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(input.readBoolean(), (boolean) list.get(i));
        }
        input.align();
    }


    @Test(invocationCount = 128)
    public void testBytes() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final List<byte[]> list = new ArrayList<byte[]>();
        final int count = RANDOM.nextInt(128) + 128;

        final BitOutput output = new BitOutput(baos);
        for (int i = 0; i < count; i++) {
            final byte[] bytes = new byte[RANDOM.nextInt(128)];
            RANDOM.nextBytes(bytes);
            list.add(bytes);
            output.writeBytes(list.get(list.size() - 1));
        }
        output.align();

        baos.flush();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));
        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(input.readBytes(), list.get(i));
        }
        input.align();
    }


    @Test(invocationCount = 128)
    public void testUnsignedInt() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final List<Integer> list = new ArrayList<Integer>();
        final int count = RANDOM.nextInt(128) + 128;

        final BitOutput output = new BitOutput(baos);
        for (int i = 0; i < count; i++) {
            final int length = RANDOM.nextInt(30) + 1; // 1 - 30
            list.add(length);
            final int value = RANDOM.nextInt((int) Math.pow(2, length));
            list.add(value);
            output.writeUnsignedInt(length, value);
        }
        output.align();

        baos.flush();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));
        for (int i = 0; i < count; i++) {
            final int length = list.get(i * 2);
            final int expected = list.get(i * 2 + 1);
            Assert.assertEquals(input.readUnsignedInt(length), expected);
        }
        input.align();
    }


    @Test(invocationCount = 128)
    public void testUnsignedInt31() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final List<Integer> list = new ArrayList<Integer>();
        final int count = RANDOM.nextInt(128) + 128;

        final BitOutput output = new BitOutput(baos);
        for (int i = 0; i < count; i++) {
            final int value = RANDOM.nextInt(Integer.MAX_VALUE);
            list.add(value);
            output.writeUnsignedInt(31, value);
        }
        output.align();

        baos.flush();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));
        for (int i = 0; i < count; i++) {
            final int expected = list.get(i);
            Assert.assertEquals(input.readUnsignedInt(31), expected);
        }
        input.align();
    }


    //@Test(invocationCount = 128)
    public void testInt() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final List<Integer> list = new ArrayList<Integer>();
        final int count = RANDOM.nextInt(1024) + 1024;

        final BitOutput output = new BitOutput(baos);
        for (int i = 0; i < count; i++) {
            final int length = RANDOM.nextInt(30) + 1; // 1 - 30
            list.add(length);
            final int value = RANDOM.nextInt((int) Math.pow(2, length));
            list.add(value);
            output.writeUnsignedInt(length, value);
        }
        output.align();

        baos.flush();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));
        for (int i = 0; i < count; i++) {
            final int length = list.get(i * 2);
            final int expected = list.get(i * 2 + 1);
            Assert.assertEquals(input.readUnsignedInt(length), expected);
        }
        input.align();
    }


    //@Test(invocationCount = 128)
    public void testInt32() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final List<Integer> list = new ArrayList<Integer>();
        final int count = RANDOM.nextInt(1024) + 1024;

        final BitOutput output = new BitOutput(baos);
        for (int i = 0; i < count; i++) {
            final int value = RANDOM.nextInt(Integer.MAX_VALUE);
            list.add(value);
            output.writeUnsignedInt(31, value);
        }
        output.align();

        baos.flush();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));
        for (int i = 0; i < count; i++) {
            final int expected = list.get(i);
            Assert.assertEquals(input.readUnsignedInt(31), expected);
        }
        input.align();
    }
}
