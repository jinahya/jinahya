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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class FloatTest extends BitIOTest {


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
        Assert.assertEquals(bo.align(1), 0);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (float expected : values) {
            final float actual = bi.readFloat();
            Assert.assertEquals(actual, expected);
        }
        Assert.assertEquals(bi.align(1), 0);
    }


    @Test
    public void testFLOAT() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Float> values = new ArrayList<Float>(count);
        for (int i = 0; i < count; i++) {
            values.add(RANDOM.nextBoolean() ? null : RANDOM.nextFloat());
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (Float value : values) {
            bo.writeFLOAT(value);
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (Float expected : values) {
            final Float actual = bi.readFLOAT();
            Assert.assertEquals(actual, expected);
        }
        bi.align(1);
    }


    @Test
    public void testFLOATWithDefaultValue() throws IOException {

        final int count = RANDOM.nextInt(128) + 128;
        final List<Float> values = new ArrayList<Float>(count);
        for (int i = 0; i < count; i++) {
            values.add(RANDOM.nextBoolean() ? null : RANDOM.nextFloat());
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (Float value : values) {
            bo.writeFLOAT(value);
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (Float expected : values) {
            final Float defaultValue =
                RANDOM.nextBoolean() ? null : RANDOM.nextFloat();
            final Float actual = bi.readFLOAT(defaultValue);
            if (expected == null) {
                Assert.assertEquals(actual, defaultValue);
            } else {
                Assert.assertEquals(actual, expected);
            }
        }
        bi.align(1);
    }


    @Test
    public void testFloatForDataInput() throws IOException {

        final int count = newCount();

        final List<Float> values = new ArrayList<Float>(count);
        for (int i = 0; i < count; i++) {
            values.add(RANDOM.nextFloat());
        }

        final BufferedBitOutput bbo = new BufferedBitOutput();
        for (float value : values) {
            bbo.writeFloat(value);
        }
        Assert.assertEquals(bbo.align(1), 0);

        final DataInputStream dis =
            new DataInputStream(new ByteArrayInputStream(bbo.toByteArray()));
        for (float expected : values) {
            final float actual = dis.readFloat();
            Assert.assertEquals(actual, expected);
        }
    }


    @Test
    public void testFloatForDataOutput() throws IOException {

        final int count = newCount();

        final List<Float> values = new ArrayList<Float>(count);
        for (int i = 0; i < count; i++) {
            values.add(RANDOM.nextFloat());
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final DataOutputStream dos = new DataOutputStream(baos);
        for (float value : values) {
            dos.writeFloat(value);
        }

        final BufferedBitInput bbi = new BufferedBitInput(baos.toByteArray());
        for (float expected : values) {
            final float actual = bbi.readFloat();
            Assert.assertEquals(actual, expected);
        }
        Assert.assertEquals(bbi.align(1), 0);
    }


}

