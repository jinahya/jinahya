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

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class IntegerTest {


    /** random. */
    protected static final Random RANDOM = new Random();


    private static final int unsignedLength() {
        return checkUnsignedLength(RANDOM.nextInt(31) + 1); // 1 - 31
    }


    private static final int checkUnsignedLength(final int length) {
        Assert.assertTrue(length >= 1);
        Assert.assertTrue(length < Integer.SIZE);
        return length;
    }


    private static final int signedLength() {
        return checkSignedLength(RANDOM.nextInt(31) + 2); // 2 - 32
    }


    private static final int checkSignedLength(final int length) {
        Assert.assertTrue(length > 1);
        Assert.assertTrue(length <= Integer.SIZE);
        return length;
    }


    private static final Integer unsignedValue(final int length,
                                               final boolean nullable) {

        checkUnsignedLength(length);

        if (nullable && RANDOM.nextBoolean()) {
            return null;
        }

        final int value = RANDOM.nextInt() >>> (Integer.SIZE - length);

        Assert.assertTrue((value >> length) == 0);

        return value;
    }


    private static final Integer signedValue(final int length,
                                             final boolean nullable) {

        checkSignedLength(length);

        if (nullable && RANDOM.nextBoolean()) {
            return null;
        }

        final int value = RANDOM.nextInt() >> (Integer.SIZE - length);

        if (length < Integer.SIZE) {
            if (value < 0L) {
                Assert.assertTrue((value >> length) == -1);
            } else {
                Assert.assertTrue((value >> length) == 0);
            }
        }

        return value;
    }


    @Test
    public void testUnsignedInt() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Integer> list = new ArrayList<Integer>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = unsignedLength();
            list.add(length);
            final int value = unsignedValue(length, false);
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (int i = 0; i < list.size(); i += 2) {
            bo.writeUnsignedInt(list.get(i), list.get(i + 1));
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (int i = 0; i < list.size(); i += 2) {
            final int length = list.get(i);
            final int expected = list.get(i + 1);
            final int actual = bi.readUnsignedInt(length);
            Assert.assertEquals(actual, expected);
        }
        bi.align(1);
    }


    @Test
    public void testInt() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Integer> list = new ArrayList<Integer>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = signedLength();
            list.add(length);
            final int value = signedValue(length, false);
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (int i = 0; i < list.size(); i += 2) {
            bo.writeInt(list.get(i), list.get(i + 1));
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (int i = 0; i < list.size(); i += 2) {
            final int length = list.get(i);
            final int expected = list.get(i + 1);
            final int actual = bi.readInt(length);
            Assert.assertEquals(actual, expected);
        }
        bi.align(1);
    }


    @Test
    public void testUnsignedINTEGER() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Integer> list = new ArrayList<Integer>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = unsignedLength();
            list.add(length);
            final Integer value = unsignedValue(length, true);
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (int i = 0; i < list.size(); i += 2) {
            bo.writeUnsignedINTEGER(list.get(i), list.get(i + 1));
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (int i = 0; i < list.size(); i += 2) {
            final int length = list.get(i);
            final Integer expected = list.get(i + 1);
            final Integer actual = bi.readUnsignedINTEGER(length);
            Assert.assertEquals(actual, expected);
        }
        bi.align(1);
    }


    @Test
    public void testUnsignedINTEGERWithDefaultValue() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Integer> list = new ArrayList<Integer>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = unsignedLength();
            list.add(length);
            final Integer value = unsignedValue(length, true);
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (int i = 0; i < list.size(); i += 2) {
            bo.writeUnsignedINTEGER(list.get(i), list.get(i + 1));
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (int i = 0; i < list.size(); i += 2) {
            final int length = list.get(i);
            final Integer expected = list.get(i + 1);
            final Integer defaultValue = unsignedValue(length, true);
            final Integer actual = bi.readUnsignedINTEGER(length, defaultValue);
            if (expected == null) {
                Assert.assertEquals(actual, defaultValue);
            } else {
                Assert.assertEquals(actual, expected);
            }
        }
        bi.align(1);
    }


    @Test
    public void testINTEGER() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Integer> list = new ArrayList<Integer>(count * 2);
        for (int i = 0; i < count; i++) {
            final int length = signedLength();
            list.add(length);
            final Integer value = signedValue(length, true);
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (int i = 0; i < list.size(); i += 2) {
            bo.writeINTEGER(list.get(i), list.get(i + 1));
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (int i = 0; i < list.size(); i += 2) {
            final int length = list.get(i);
            final Integer expected = list.get(i + 1);
            final Integer actual = bi.readINTEGER(length);
            Assert.assertEquals(actual, expected);
        }
        bi.align(1);
    }


    @Test
    public void testINTEGERWithDefaultValue() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Integer> list = new ArrayList<Integer>(count * 2);
        for (int i = 0; i < count; i++) {
            final int length = signedLength();
            list.add(length);
            final Integer value = signedValue(length, true);
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (int i = 0; i < list.size(); i += 2) {
            bo.writeINTEGER(list.get(i), list.get(i + 1));
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (int i = 0; i < list.size(); i += 2) {
            final int length = list.get(i);
            final Integer expected = list.get(i + 1);
            final Integer defaultValue = signedValue(length, true);
            final Integer actual = bi.readINTEGER(length, defaultValue);
            if (expected == null) {
                Assert.assertEquals(actual, defaultValue);
            } else {
                Assert.assertEquals(actual, expected);
            }
        }
        bi.align(1);
    }


}

