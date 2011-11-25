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
public class IntegerTest extends BitIOTest {


    @Test
    public void testUnsignedInt() throws IOException {

        final int count = newCount();
        final List<Integer> list = new ArrayList<Integer>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = newUnsignedIntLength();
            list.add(length);
            final int value = newUnsignedIntValue(length, false);
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

        final int count = newCount();
        final List<Integer> list = new ArrayList<Integer>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = newSignedIntLength();
            list.add(length);
            final int value = newSignedIntValue(length, false);
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

        final int count = newCount();
        final List<Integer> list = new ArrayList<Integer>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = newUnsignedIntLength();
            list.add(length);
            final Integer value = newUnsignedIntValue(length, true);
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

        final int count = newCount();
        final List<Integer> list = new ArrayList<Integer>(count * 2);

        for (int i = 0; i < count; i++) {
            final int length = newUnsignedIntLength();
            list.add(length);
            final Integer value = newUnsignedIntValue(length, true);
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
            final Integer defaultValue = newUnsignedIntValue(length, true);
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

        final int count = newCount();
        final List<Integer> list = new ArrayList<Integer>(count * 2);
        for (int i = 0; i < count; i++) {
            final int length = newSignedIntLength();
            list.add(length);
            final Integer value = newSignedIntValue(length, true);
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

        final int count = newCount();
        final List<Integer> list = new ArrayList<Integer>(count * 2);
        for (int i = 0; i < count; i++) {
            final int length = newSignedIntLength();
            list.add(length);
            final Integer value = newSignedIntValue(length, true);
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
            final Integer defaultValue = newSignedIntValue(length, true);
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

