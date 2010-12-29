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
import java.io.DataInputStream;
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
public class BitOutputTest {


    private static final Random RANDOM = new Random();


    private static final BitOutput EMPTY_INSTANCE =
        new BitOutput(new ByteArrayOutputStream());


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
    public void testAlignWithLengthZero() throws IOException {
        EMPTY_INSTANCE.aling(0);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testAlignWithLengthNegative() throws IOException {
        EMPTY_INSTANCE.aling(Integer.MIN_VALUE | RANDOM.nextInt());
    }


    @Test(invocationCount = 128)
    public void testWriteBoolean() throws IOException {

        final boolean expected = RANDOM.nextBoolean();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final BitOutput output = new BitOutput(baos);
        output.writeBoolean(expected);
        output.aling(1);

        baos.flush();
        final byte[] bytes = baos.toByteArray();
        Assert.assertTrue(bytes.length == 1);

        final boolean actual = (((bytes[0] >>> 8) & 0x01) == 0x01);

        Assert.assertEquals(actual, expected);
    }


    private void testWriteUnsignedInt(final int length, final int expected)
        throws IOException {

        Assert.assertTrue(length >= 1);
        Assert.assertTrue(length < 32);

        if (true) {
            if (expected < 0) {
                Assert.fail("negative value");
            } else {
                Assert.assertTrue((expected >> length) == 0);
            }
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final BitOutput output = new BitOutput(baos);
        output.writeUnsignedInt(length, expected);
        output.aling(1);

        baos.flush();
        final byte[] bytes = baos.toByteArray();

        int actual = 0x00;
        for (int i = 0; i < bytes.length; i++) {
            actual <<= 0x08;
            actual |= (bytes[i] & 0xFF);
        }
        actual >>>= ((8 * bytes.length) - length);

        Assert.assertEquals(actual, expected, " for length:<" + length + ">");
    }


    @Test(invocationCount = 128)
    public void testWriteUnsignedInt() throws IOException {

        final int length = RANDOM.nextInt(31) + 1; // 1 - 31

        final int value = (RANDOM.nextInt() & 0x7FFFFFFF) >> (32 - length);

        testWriteUnsignedInt(length, value);
    }


    @Test
    public void testWriteUnsignedIntWithLengthMin() throws IOException {
        testWriteUnsignedInt(1, 0);
        testWriteUnsignedInt(1, 1);
    }


    @Test(invocationCount = 128)
    public void testWriteUnsignedIntWithLengthMax() throws IOException {
        final int value = RANDOM.nextInt() & 0x7FFFFFFF;
        testWriteUnsignedInt(31, value);
    }


    @Test
    public void testWriteUnsignedIntWithValueMax() throws IOException {
        testWriteUnsignedInt(31, Integer.MAX_VALUE);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteUnsignedIntWithLengthZero() throws IOException {
        EMPTY_INSTANCE.writeUnsignedInt(0, 0);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteUnsignedIntWithLengthNegative() throws IOException {
        EMPTY_INSTANCE.writeUnsignedInt(
            Integer.MIN_VALUE | RANDOM.nextInt(), 0);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteUnsignedIntWithLengthTooLong() throws IOException {
        EMPTY_INSTANCE.writeUnsignedInt(32, 0);
    }


    private void testWriteInt(final int length, final int expected)
        throws IOException {

        Assert.assertTrue(length > 1);
        Assert.assertTrue(length <= 32);

        if (length < 0x20) {
            if (expected < 0x00) {
                Assert.assertTrue((expected >> length) == -1);
            } else {
                Assert.assertTrue((expected >> length) == 0);
            }
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
            actual |= (bytes[i] & 0xFF);
        }
        actual <<= ((4 - bytes.length) * 8);
        actual >>= (32 - length);

        Assert.assertEquals(actual, expected, "length:<" + length + ">");
    }


    @Test(invocationCount = 128)
    public void testWriteInt() throws IOException {

        final int length = RANDOM.nextInt(31) + 2; // 2 - 32

        final int value = RANDOM.nextInt() >> (32 - length);

        testWriteInt(length, value);
    }


    @Test
    public void testWriteIntWithLengthMin() throws IOException {
        testWriteInt(2, 0);
        testWriteInt(2, 1);
        testWriteInt(2, -2);
        testWriteInt(2, -1);
    }


    @Test(invocationCount = 128)
    public void testWriteIntWithLengthMax() throws IOException {
        testWriteInt(32, RANDOM.nextInt());
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteIntWithLengthOne() throws IOException {
        EMPTY_INSTANCE.writeInt(1, 0);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteIntWithLengthZero() throws IOException {
        EMPTY_INSTANCE.writeInt(0, 0);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteIntWithLengthNegative() throws IOException {
        EMPTY_INSTANCE.writeInt(Integer.MIN_VALUE | RANDOM.nextInt(), 0);
    }


    @Test
    public void testWriteIntWithValueMin() throws IOException {
        testWriteInt(32, Integer.MIN_VALUE);
    }


    @Test
    public void testWriteIntWithValueMax() throws IOException {
        testWriteInt(32, Integer.MAX_VALUE);
    }


    @Test
    public void testWriteIntWithDataInput() throws IOException {

        final List<Integer> values = new ArrayList<Integer>();
        for (int i = 0; i < 1024; i++) {
            values.add(RANDOM.nextInt());
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final BitOutput output = new BitOutput(baos);
        for (Integer value : values) {
            output.writeInt(32, value);
        }

        baos.flush();
        final byte[] bytes = baos.toByteArray();
        final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

        final DataInputStream dis = new DataInputStream(bais);
        for (Integer expected : values) {
            final int actual = dis.readInt();
            Assert.assertEquals(actual, expected.intValue());
        }
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


    private void testWriteUnsignedLong(final int length, final long expected)
        throws IOException {

        Assert.assertTrue(length >= 1);
        Assert.assertTrue(length < 64);

        if (true) {
            if (expected < 0) {
                Assert.fail("negative value");
            } else {
                Assert.assertEquals(expected >> length, 0);
            }
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final BitOutput output = new BitOutput(baos);
        output.writeUnsignedLong(length, expected);
        output.aling(1);

        baos.flush();
        final byte[] octets = baos.toByteArray();

        long actual = 0x00L;
        for (byte octet : octets) {
            actual <<= 8;
            actual |= (octet & 0xFF);
        }
        actual >>>= ((8 * octets.length) - length);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public void testWriteUnsignedLong() throws IOException {

        final int length = RANDOM.nextInt(63) + 1; // 1 - 63

        final long expected = RANDOM.nextLong() >>> (64 - length);

        testWriteUnsignedLong(length, expected);
    }


    @Test
    public void testWriteUnsignedLongWithLengthMin() throws IOException {

        testWriteUnsignedLong(1, 0L);
        testWriteUnsignedLong(1, 1L);
    }


    @Test(invocationCount = 128)
    public void testWriteUnsignedLongWithLengthMax() throws IOException {

        final int length = 63;

        final long expected = RANDOM.nextLong() >>> 1;

        testWriteUnsignedLong(length, expected);
    }


    @Test(invocationCount = 128)
    public void testWriteUnsignedLongWithValueMax() throws IOException {

        testWriteUnsignedLong(63, Long.MAX_VALUE);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteUnsignedLongWithIllegalLengthZero()
        throws IOException {

        EMPTY_INSTANCE.writeUnsignedLong(0, 0L);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteUnsignedLongWithIllegalLengthNegative()
        throws IOException {

        EMPTY_INSTANCE.writeUnsignedLong(
            RANDOM.nextInt() | Integer.MIN_VALUE, 0L);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteUnsignedLongWithIllegalLengthTooLong()
        throws IOException {

        EMPTY_INSTANCE.writeUnsignedLong(64, 0L);
    }


    private void testWriteLong(final int length, final long expected)
        throws IOException {

        Assert.assertTrue(length > 1);
        Assert.assertTrue(length <= 64);

        if (length < 64) {
            if (expected < 0) {
                Assert.assertEquals(expected >> length, -1L);
            } else {
                Assert.assertEquals(expected >> length, 0L);
            }
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final BitOutput output = new BitOutput(baos);
        output.writeLong(length, expected);
        output.aling(1);

        baos.flush();
        final byte[] bytes = baos.toByteArray();

        long actual = 0;
        for (int i = 0; i < bytes.length; i++) {
            actual <<= 8;
            actual |= bytes[i] & 0xFF;
        }
        actual <<= ((8 - bytes.length) * 8);
        actual >>= (64 - length);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public void testWriteLong() throws IOException {

        final int length = RANDOM.nextInt(63) + 2; // 2 - 64

        final long value = RANDOM.nextLong() >> (64 - length);

        testWriteLong(length, value);
    }


    @Test
    public void testWriteLongWithLengthMin() throws IOException {
        testWriteLong(2, 0L);
        testWriteLong(2, 1L);
        testWriteLong(2, -2L);
        testWriteLong(2, -1L);
    }


    @Test(invocationCount = 128)
    public void testWriteLongWithLengthMax() throws IOException {
        testWriteLong(64, RANDOM.nextLong());
    }


    @Test
    public void testWriteLongWithValueMin() throws IOException {
        testWriteLong(64, Long.MIN_VALUE);
    }


    @Test
    public void testWriteLongWithValueMax() throws IOException {
        testWriteLong(64, Long.MAX_VALUE);
    }


    @Test(invocationCount = 128)
    public void testWriteLongWithDataInput() throws IOException {

        final long expected = RANDOM.nextLong();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final BitOutput output = new BitOutput(baos);
        output.writeLong(64, expected);

        baos.flush();
        final byte[] bytes = baos.toByteArray();
        final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

        final DataInputStream dis = new DataInputStream(bais);

        final long actual = dis.readLong();

        Assert.assertEquals(actual, expected);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteLongWithLengthOne() throws IOException {
        EMPTY_INSTANCE.writeLong(1, 0L);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteLongWithLengthZero() throws IOException {
        EMPTY_INSTANCE.writeLong(0, 0L);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteLongWithLengthNegative() throws IOException {
        EMPTY_INSTANCE.writeLong(Integer.MIN_VALUE | RANDOM.nextInt(), 0L);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteLongWithLengthTooLong() throws IOException {
        EMPTY_INSTANCE.writeLong(65, 0L);
    }


    @Test(invocationCount = 128)
    public void testWriteBytes() throws IOException {

        final byte[] expected = new byte[RANDOM.nextInt(128) + 1];
        RANDOM.nextBytes(expected);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final BitOutput output = new BitOutput(baos);
        output.writeBytes(expected, 0, expected.length);

        baos.flush();
        final byte[] actual = baos.toByteArray();

        Assert.assertEquals(actual, expected);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteBytesWithNullBytes() throws IOException {
        EMPTY_INSTANCE.writeBytes(null, 0, 0);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteBytesWithOffsetNegative() throws IOException {
        EMPTY_INSTANCE.writeBytes(
            new byte[0], Integer.MIN_VALUE | RANDOM.nextInt(), 0);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteBytesWithOffsetTooBig() throws IOException {
        EMPTY_INSTANCE.writeBytes(new byte[0], 1, 0);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteBytesWithLengthNegative() throws IOException {
        EMPTY_INSTANCE.writeBytes(
            new byte[0], 0, Integer.MIN_VALUE | RANDOM.nextInt());
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteBytesWithLengthTooBig() throws IOException {
        EMPTY_INSTANCE.writeBytes(new byte[0], 0, 1);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteBytesWithLengthOffsetTooBig() throws IOException {
        EMPTY_INSTANCE.writeBytes(new byte[1], 1, 1);
    }
}
