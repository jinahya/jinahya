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
public class DoubleTest {


    /** random. */
    protected static final Random RANDOM = new Random();


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
        Assert.assertEquals(bo.align(1), 0);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (double expected : values) {
            final double actual = bi.readDouble();
            Assert.assertEquals(actual, expected);
        }
        Assert.assertEquals(bi.align(1), 0);
    }


    @Test
    public void testDOUBLE() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Double> values = new ArrayList<Double>(count);
        for (int i = 0; i < count; i++) {
            values.add(RANDOM.nextBoolean() ? null : RANDOM.nextDouble());
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (Double value : values) {
            bo.writeDOUBLE(value);
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (Double expected : values) {
            final Double actual = bi.readDOUBLE();
            Assert.assertEquals(actual, expected);
        }
        bi.align(1);
    }


    @Test
    public void testDOUBLEWithDefaultValue() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Double> values = new ArrayList<Double>(count);
        for (int i = 0; i < count; i++) {
            values.add(RANDOM.nextBoolean() ? null : RANDOM.nextDouble());
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (Double value : values) {
            bo.writeDOUBLE(value);
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (Double expected : values) {
            final Double defaultValue = RANDOM.nextBoolean() ? null : RANDOM.nextDouble();
            final Double actual = bi.readDOUBLE(defaultValue);
            if (expected == null) {
                Assert.assertEquals(actual, defaultValue);
            } else {
                Assert.assertEquals(actual, expected);
            }
        }
        bi.align(1);
    }


}

