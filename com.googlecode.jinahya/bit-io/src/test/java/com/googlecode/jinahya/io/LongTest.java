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

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class LongTest extends BitIOTest {


    @Test
    public void testUnsignedLong() throws IOException {

        final int count = newCount();
        final List<Long> values = new ArrayList<Long>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = newUnsignedLongLength();
            values.add((long) length);
            final long value = newUnsignedLongValue(length, false);
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
    public void testLong() throws IOException {

        final int count = newCount();
        final List<Long> list = new ArrayList<Long>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = newSignedLongLength();
            list.add((long) length);
            final long value = newSignedLongValue(length, false);
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (int i = 0; i < list.size(); i += 2) {
            bo.writeLong(list.get(i).intValue(), list.get(i + 1));
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (int i = 0; i < list.size(); i += 2) {
            final int length = list.get(i).intValue();
            final long expected = list.get(i + 1);
            final long actual = bi.readLong(length);
            Assert.assertEquals(actual, expected);
        }
        bi.align(1);
    }


    @Test
    public void testUnsignedLONG() throws IOException {

        final int count = newCount();
        final List<Long> list = new ArrayList<Long>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = newUnsignedLongLength();
            list.add((long) length);
            final Long value = newUnsignedLongValue(length, true);
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (int i = 0; i < list.size(); i += 2) {
            bo.writeUnsignedLONG(list.get(i).intValue(), list.get(i + 1));
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (int i = 0; i < list.size(); i += 2) {
            final int length = list.get(i).intValue();
            final Long expected = list.get(i + 1);
            final Long actual = bi.readUnsignedLONG(length);
            Assert.assertEquals(actual, expected);
        }
        bi.align(1);
    }


    @Test
    public void testUnsignedLONGWithDefaultValue() throws IOException {

        final int count = newCount();
        final List<Long> list = new ArrayList<Long>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = newUnsignedLongLength();
            list.add((long) length);
            final Long value = newUnsignedLongValue(length, true);
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (int i = 0; i < list.size(); i += 2) {
            bo.writeUnsignedLONG(list.get(i).intValue(), list.get(i + 1));
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (int i = 0; i < list.size(); i += 2) {
            final int length = list.get(i).intValue();
            final Long expected = list.get(i + 1);
            final Long defaultValue = newUnsignedLongValue(length, true);
            final Long actual = bi.readUnsignedLONG(length, defaultValue);
            if (expected == null) {
                Assert.assertEquals(actual, defaultValue);
            } else {
                Assert.assertEquals(actual, expected);
            }
        }
        bi.align(1);
    }


    @Test
    public void testLONG() throws IOException {

        final int count = newCount();
        final List<Long> list = new ArrayList<Long>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = newSignedLongLength();
            list.add((long) length);
            final Long value = newSignedLongValue(length, true);
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (int i = 0; i < list.size(); i += 2) {
            bo.writeLONG(list.get(i).intValue(), list.get(i + 1));
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (int i = 0; i < list.size(); i += 2) {
            final int length = list.get(i).intValue();
            final Long expected = list.get(i + 1);
            final Long actual = bi.readLONG(length);
            Assert.assertEquals(actual, expected);
        }
        bi.align(1);
    }


    @Test
    public void testLONGWithDefaultValue() throws IOException {

        final int count = newCount();
        final List<Long> list = new ArrayList<Long>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = newSignedLongLength();
            list.add((long) length);
            final Long value = newSignedLongValue(length, true);
            list.add(value);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (int i = 0; i < list.size(); i += 2) {
            bo.writeLONG(list.get(i).intValue(), list.get(i + 1));
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (int i = 0; i < list.size(); i += 2) {
            final int length = list.get(i).intValue();
            final Long expected = list.get(i + 1);
            final Long defaultValue = newSignedLongValue(length, true);
            final Long actual = bi.readLONG(length, defaultValue);
            if (expected == null) {
                Assert.assertEquals(actual, defaultValue);
            } else {
                Assert.assertEquals(actual, expected);
            }
        }
        bi.align(1);
    }


}

