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
import java.io.IOException;
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
        EMPTY_INSTANCE.readInt(-1 | RANDOM.nextInt());
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testReadIntWithIllegalLengthTooLong() throws IOException {
        EMPTY_INSTANCE.readInt(33);
    }


}
