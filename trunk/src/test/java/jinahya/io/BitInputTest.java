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
import java.io.DataOutputStream;
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
public class BitInputTest {


    private static final Random RANDOM = new Random();


    private static final BitInput EMPTY_INSTANCE =
        new BitInput(new ByteArrayInputStream(new byte[0]));


    @Test(invocationCount = 128)
    public void testReadBoolean() throws IOException {

        final boolean expected = RANDOM.nextBoolean();

        final byte[] bytes = new byte[]{expected ? Byte.MIN_VALUE : 0};

        final BitInput input = new BitInput(new ByteArrayInputStream(bytes));

        final boolean actual = input.readBoolean();

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public void testReadUnsignedInt() throws IOException {

        final int length = RANDOM.nextInt(31) + 1; // 1 - 31
        Assert.assertTrue(length > 0);
        Assert.assertTrue(length < 32);

        final int expected = RANDOM.nextInt() >>> (32 - length);
        Assert.assertTrue(expected >= 0);
        Assert.assertTrue(expected < Math.pow(2, length));

        final int trimmed = expected << (32 - length);
        int byteLength = length / 8;
        if (length % 8 > 0) {
            byteLength++;
        }
        final byte[] bytes = new byte[byteLength];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ((trimmed >>> ((3 - i) * 8)) & 0xFF);
        }

        final BitInput input = new BitInput(new ByteArrayInputStream(bytes));

        final int actual = input.readUnsignedInt(length);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public void testReadUnsignedIntWithLengthMax() throws IOException {

        final int length = 31;

        final int expected = RANDOM.nextInt() >>> (32 - length);
        Assert.assertTrue(expected >= 0);
        Assert.assertTrue(expected < Math.pow(2, length));

        final int trimmed = expected << (32 - length);
        final byte[] bytes = new byte[4];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ((trimmed >>> ((3 - i) * 8)) & 0xFF);
        }

        final BitInput input = new BitInput(new ByteArrayInputStream(bytes));

        final int actual = input.readUnsignedInt(length);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testReadUnsignedIntWithValueMax() throws IOException {

        final int length = 31;

        final int expected = Integer.MAX_VALUE;

        final int trimmed = expected << 1;
        final byte[] bytes = new byte[4];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ((trimmed >>> ((3 - i) * 8)) & 0xFF);
        }

        final BitInput input = new BitInput(new ByteArrayInputStream(bytes));

        final int actual = input.readUnsignedInt(length);

        Assert.assertEquals(actual, expected);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testReadUnsignedIntWithIllegalLengthNegative()
        throws IOException {

        EMPTY_INSTANCE.readUnsignedInt(-1);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testReadUnsignedIntWithIllegalLengthTooLong()
        throws IOException {

        EMPTY_INSTANCE.readUnsignedInt(32);
    }


    @Test(invocationCount = 128)
    public void testReadInt() throws IOException {

        final int length = RANDOM.nextInt(31) + 2; // 2 - 32
        Assert.assertTrue(length > 1);
        Assert.assertTrue(length <= 32);

        final int expected = RANDOM.nextInt() >> (32 - length);

        final int shifted = expected << (32 - length);
        int byteLength = length / 8;
        if (length % 8 > 0) {
            byteLength++;
        }
        final byte[] bytes = new byte[byteLength];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ((shifted >>> ((3 - i) * 8)) & 0xFF);
        }

        final BitInput input = new BitInput(new ByteArrayInputStream(bytes));

        final int actual = input.readInt(length);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public void testReadIntWithLengthMin() throws IOException {

        final int length = 2;

        final int expected = RANDOM.nextInt() >> 30;

        final byte[] bytes = new byte[]{(byte) ((expected << 6) & 0xFF)};

        Assert.assertEquals(expected < 0, bytes[0] < 0);

        final BitInput input = new BitInput(new ByteArrayInputStream(bytes));

        final int actual = input.readInt(length);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public void testReadIntWithLengthMax() throws IOException {

        final int length = 32;

        final int expected = RANDOM.nextInt();

        final byte[] bytes = new byte[4];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ((expected >>> ((3 - i) * 8)) & 0xFF);
        }

        final BitInput input = new BitInput(new ByteArrayInputStream(bytes));

        final int actual = input.readInt(length);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testReadIntWithValueMin() throws IOException {

        final int length = 32;

        final int expected = Integer.MIN_VALUE;

        final byte[] bytes = new byte[4];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ((expected >>> ((3 - i) * 8)) & 0xFF);
        }

        final BitInput input = new BitInput(new ByteArrayInputStream(bytes));

        final int actual = input.readInt(length);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testReadIntWithValueMax() throws IOException {

        final int length = 32;

        final int expected = Integer.MAX_VALUE;

        final byte[] bytes = new byte[4];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ((expected >>> ((3 - i) * 8)) & 0xFF);
        }

        final BitInput input = new BitInput(new ByteArrayInputStream(bytes));

        final int actual = input.readInt(length);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testReadIntWithDataOutput() throws IOException {

        final List<Integer> values = new ArrayList<Integer>();
        for (int i = 0; i < 1024; i++) {
            values.add(RANDOM.nextInt());
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final DataOutputStream dos = new DataOutputStream(baos);
        for (Integer value : values) {
            dos.writeInt(value);
        }

        baos.flush();
        final byte[] bytes = baos.toByteArray();

        final BitInput input = new BitInput(new ByteArrayInputStream(bytes));
        for (Integer expected : values) {
            final int actual = input.readInt(32);
            Assert.assertEquals(actual, expected.intValue());
        }
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testReadIntWithIllegalLengthOne() throws IOException {
        EMPTY_INSTANCE.readInt(1);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testReadIntWithIllegalLengthZero() throws IOException {
        EMPTY_INSTANCE.readInt(0);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testReadIntWithIllegalLengthNegative() throws IOException {
        EMPTY_INSTANCE.readInt(Integer.MIN_VALUE | RANDOM.nextInt());
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testReadIntWithIllegalLengthTooLong() throws IOException {
        EMPTY_INSTANCE.readInt(33);
    }


    private void testReadUnsignedLong(final int length, final long expected)
        throws IOException {

        Assert.assertTrue(length >= 1);
        Assert.assertTrue(length < 64);


        if (true) {
            if (expected < 0L) {
                Assert.fail("negative value");
            } else {
                Assert.assertEquals(expected >> length, 0L);
            }
        }

        final long shifted = expected << (64 - length);
        final byte[] octets = new byte[8];
        for (int i = 0; i < octets.length; i++) {
            octets[i] = (byte) ((shifted >> ((7 - i) * 8)) & 0xFF);
        }

        final BitInput input = new BitInput(new ByteArrayInputStream(octets));
        final long actual = input.readUnsignedLong(length);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public void testReadUnsignedLong() throws IOException {

        final int length = RANDOM.nextInt(63) + 1; // 1 - 63

        final long value = RANDOM.nextLong() >>> (64 - length);

        testReadUnsignedLong(length, value);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testReadUnsignedLongWithLengthOne() throws IOException {
        EMPTY_INSTANCE.readUnsignedLong(0);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testReadUnsignedLongWithLengthNegative() throws IOException {
        EMPTY_INSTANCE.readUnsignedLong(Integer.MIN_VALUE | RANDOM.nextInt());
    }


    @Test
    public void testReadUnsignedLongWithLengthMin() throws IOException {
        testReadUnsignedLong(1, 0L);
        testReadUnsignedLong(1, 1L);
    }


    @Test(invocationCount = 128)
    public void testReadUnsignedLongWithLengthMax() throws IOException {

        final long value = RANDOM.nextLong() & 0x7FFFFFFFFFFFFFFFL;

        testReadUnsignedLong(63, value);
    }


    @Test
    public void testReadUnsignedLongWithValueMax() throws IOException {

        testReadUnsignedLong(63, Long.MAX_VALUE);
    }


    private void testReadLong(final int length, final long expected)
        throws IOException {

        Assert.assertTrue(length > 1);
        Assert.assertTrue(length <= 64);

        if (length < 64) {
            if (expected < 0L) {
                Assert.assertEquals(expected >> length, -1L);
            } else {
                Assert.assertEquals(expected >> length, 0L);
            }
        }

        final long shifted = expected << (64 - length);
        final byte[] octets = new byte[8];
        for (int i = 0; i < octets.length; i++) {
            octets[i] = (byte) ((shifted >> ((7 - i) * 8)) & 0xFF);
        }

        final BitInput input = new BitInput(new ByteArrayInputStream(octets));
        final long actual = input.readLong(length);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public void testReadLong() throws IOException {

        final int length = RANDOM.nextInt(63) + 2; // 2 - 64

        final long value = RANDOM.nextLong() >> (64 - length);

        testReadLong(length, value);
    }


    @Test
    public void testReadLongWithLengthMin() throws IOException {
        testReadLong(2, 0L);
        testReadLong(2, 1L);
        testReadLong(2, -1L);
        testReadLong(2, -2L);
    }


    @Test(invocationCount = 128)
    public void testReadLongWithLengthMax() throws IOException {
        testReadLong(64, RANDOM.nextLong());
    }


    @Test
    public void testReadLongWithValueMin() throws IOException {
        testReadLong(64, Long.MIN_VALUE);
    }


    @Test
    public void testReadLongWithValueMax() throws IOException {
        testReadLong(64, Long.MAX_VALUE);
    }


    @Test
    public void testReadLongWithDataOutput() throws IOException {

        final long expected = RANDOM.nextLong();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final DataOutputStream dos = new DataOutputStream(baos);
        dos.writeLong(expected);
        dos.flush();

        baos.flush();
        final byte[] bytes = baos.toByteArray();

        final BitInput input = new BitInput(new ByteArrayInputStream(bytes));
        final long actual = input.readLong(64);

        Assert.assertEquals(actual, expected);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testReadLongWithLengthOne() throws IOException {

        EMPTY_INSTANCE.readLong(1);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testReadLongWithLengthZero() throws IOException {

        EMPTY_INSTANCE.readLong(0);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testReadLongWithLengthNegative() throws IOException {

        EMPTY_INSTANCE.readLong(Integer.MIN_VALUE | RANDOM.nextInt());
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testReadLongWithLengthTooLong() throws IOException {

        EMPTY_INSTANCE.readLong(65);
    }


}
