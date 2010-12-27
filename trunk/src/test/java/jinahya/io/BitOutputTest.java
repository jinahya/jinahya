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


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import org.testng.Assert;

import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitOutputTest {


    private static final Random RANDOM = new Random();


    private static final BitOutput EMPTY_INSTANCE =
        new BitOutput(new ByteArrayOutputStream());


    //@Test(invocationCount = 128)
    public void testWriteUnsignedByte() throws IOException {

        final int length = RANDOM.nextInt(8) + 1; // 1 - 8

        final int expected = RANDOM.nextInt() >>> (32 - length);

        testWriteUnsignedByte(length, expected);
    }


    //@Test
    public void testWriteUnsignedByteMax() throws IOException {

        testWriteUnsignedByte(8, 255);
    }


    private void testWriteUnsignedByte(final int length, final int expected)
        throws IOException {

        Assert.assertTrue(length > 0);
        Assert.assertTrue(length <= 8);

        Assert.assertTrue(expected >= 0);
        Assert.assertTrue(expected < Math.pow(2, length));

        final ByteArrayOutputStream out = new ByteArrayOutputStream();

        final BitOutput output = new BitOutput(out);

        //output.writeUnsignedByte(length, expected);
        output.aling(1);
        out.flush();

        final byte[] bytes = out.toByteArray();
        Assert.assertTrue(bytes.length == 1);

        final int actual = (bytes[0] & 0xFF) >>> (8 - length);

        Assert.assertEquals(actual, expected);
    }


    //@Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteUnsignedByteWithLengthZero() throws IOException {
        //EMPTY_INSTANCE.writeUnsignedByte(0, 0);
    }


    //@Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteUnsignedByteWithLengthNegative() throws IOException {

        /*
        EMPTY_INSTANCE.writeUnsignedByte(
        Integer.MIN_VALUE | RANDOM.nextInt(), 0);
         */
    }


    //@Test
    public void testAlign() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);

        Assert.assertEquals(output.count(), 0);

        //output.writeUnsignedByte(3, 0);

        Assert.assertEquals(output.count(), 0);

        output.aling(4);

        Assert.assertEquals(output.count(), 4);
    }


    //@Test
    public void testAlignForNegativeCount() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);

        output.count(-1);
        //output.writeUnsignedByte(1, 0);

        output.aling(4);

        baos.flush();
        Assert.assertEquals(baos.size(), 1);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testAlignWithIllegalLengthZero() throws IOException {

        new BitOutput(new ByteArrayOutputStream()).aling(0);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testAlignWithIllegalLengthNegative() throws IOException {

        new BitOutput(new ByteArrayOutputStream()).aling(-1);
    }


    @Test(invocationCount = 128)
    public void testWriteInt() throws IOException {

        final int length = RANDOM.nextInt(31) + 2; // 2 - 32
        Assert.assertTrue(length > 1);
        Assert.assertTrue(length <= 32);

        final int expected = RANDOM.nextInt() >> (32 - length);
        if (expected < 0) {
            Assert.assertEquals(expected >> length, -1);
        } else {
            Assert.assertEquals(expected >> length, 0);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);
        output.writeInt(length, expected);
        output.aling(1);
        baos.flush();
        final byte[] bytes = baos.toByteArray();

        int actual = 0;
        for (int i = 0; i < bytes.length; i++) {
            actual <<= 8;
            actual |= bytes[i] & 0xFF;
        }
        actual <<= ((4 - bytes.length) * 8);
        actual >>= (32 - length);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public void testWriteIntWithLengthMin() throws IOException {

        final int length = 2;

        final int expected = RANDOM.nextInt() >> 30;
        if (expected < 0) {
            Assert.assertEquals(expected >> length, -1);
        } else {
            Assert.assertEquals(expected >> length, 0);
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);
        output.writeInt(length, expected);
        output.aling(1);
        baos.flush();
        final byte[] bytes = baos.toByteArray();

        int actual = 0;
        for (int i = 0; i < bytes.length; i++) {
            actual <<= 8;
            actual |= bytes[i] & 0xFF;
        }
        actual <<= ((4 - bytes.length) * 8);
        actual >>= (32 - length);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public void testWriteIntWithLengthMax() throws IOException {

        final int length = 32;

        final int expected = RANDOM.nextInt();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);
        output.writeInt(length, expected);
        output.aling(1);
        baos.flush();
        final byte[] bytes = baos.toByteArray();

        int actual = 0;
        for (int i = 0; i < bytes.length; i++) {
            actual <<= 8;
            actual |= (bytes[i] & 0xFF);
        }
        actual <<= ((4 - bytes.length) * 8);
        actual >>= (32 - length);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testWriteIntWithValueMin() throws IOException {

        final int length = 32;

        final int expected = Integer.MIN_VALUE;

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);
        output.writeInt(length, expected);
        baos.flush();
        final byte[] bytes = baos.toByteArray();

        int actual = 0;
        for (int i = 0; i < bytes.length; i++) {
            actual <<= 8;
            actual |= (bytes[i] & 0xFF);
        }

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testWriteIntWithValueMax() throws IOException {

        final int length = 32;

        final int expected = Integer.MAX_VALUE;

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);
        output.writeInt(length, expected);
        baos.flush();
        final byte[] bytes = baos.toByteArray();

        int actual = 0;
        for (int i = 0; i < bytes.length; i++) {
            actual <<= 8;
            actual |= (bytes[i] & 0xFF);
        }

        Assert.assertEquals(actual, expected);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteIntWithIllegalLengthZero() throws IOException {
        EMPTY_INSTANCE.writeInt(0, 0);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteIntWithIllegalLengthOne() throws IOException {
        EMPTY_INSTANCE.writeInt(1, 0);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteIntWithIllegalLengthNegative() throws IOException {
        EMPTY_INSTANCE.writeInt(Integer.MIN_VALUE | RANDOM.nextInt(), 0);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteIntWithIllegalLengthTooLong() throws IOException {
        EMPTY_INSTANCE.writeInt(33, 0);
    }


}
