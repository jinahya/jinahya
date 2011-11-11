/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
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
import java.io.DataOutputStream;
import java.io.IOException;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Test(enabled = false)
public class SamplesTest {


    private static final Random RANDOM = new Random();


    @Test
    public void testReadWrite() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);

        final Samples expected = new Samples();

        final int count = RANDOM.nextInt(Byte.MAX_VALUE);
        for (int i = 0; i < count; i++) {
            expected.getAccessibles().add(Sample.newInstance(
                Long.toString(System.currentTimeMillis()),
                RANDOM.nextInt(Byte.MAX_VALUE - 1) + 1));
        }

        expected.write(output);
        output.align();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput input = new BitInput(bais);

        final Samples actual = new Samples();
        actual.read(input);
        input.align();

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void compareSize() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);

        final Samples samples = new Samples();

        final int count = RANDOM.nextInt(Byte.MAX_VALUE);
        for (int i = 0; i < count; i++) {
            samples.getAccessibles().add(Sample.newInstance(
                Long.toString(System.currentTimeMillis()),
                RANDOM.nextInt(Byte.MAX_VALUE - 1) + 1));
        }

        samples.write(bo);
        bo.align();

        final byte[] bitBytes = baos.toByteArray();
        System.out.println("bits: " + bitBytes.length);

        baos.reset();
        final DataOutputStream dos = new DataOutputStream(baos);
        samples.write(dos);
        dos.flush();

        final byte[] dosBytes = baos.toByteArray();

        System.out.println("byte: " + dosBytes.length);
    }


}

