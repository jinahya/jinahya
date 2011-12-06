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
public class BooleanTest extends BitIOTest {


    @Test
    public void testBoolean() throws IOException {

        final int count = newCount();
        final List<Boolean> values = new ArrayList<Boolean>(count);
        for (int i = 0; i < count; i++) {
            values.add(newBoolean(false));
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
    public void testBOOLEAN() throws IOException {

        final int count = newCount();
        final List<Boolean> values = new ArrayList<Boolean>(count);
        for (int i = 0; i < count; i++) {
            values.add(newBoolean(true));
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (Boolean value : values) {
            bo.writeBOOLEAN(value);
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (Boolean expected : values) {
            final Boolean actual = bi.readBOOLEAN();
            Assert.assertEquals(actual, expected);
        }
        bi.align(1);
    }


    @Test
    public void testBOOLEANWithDefaultValue() throws IOException {

        final int count = newCount();
        final List<Boolean> values = new ArrayList<Boolean>(count);
        for (int i = 0; i < count; i += 2) {
            values.add(newBoolean(true));
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        for (Boolean value : values) {
            bo.writeBOOLEAN(value);
        }
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        for (Boolean expected : values) {
            final Boolean defaultValue = newBoolean(true);
            final Boolean actual = bi.readBOOLEAN(defaultValue);
            if (expected == null) {
                Assert.assertEquals(actual, defaultValue);
            } else {
                Assert.assertEquals(actual, expected);
            }
        }
        bi.align(1);
    }


}

