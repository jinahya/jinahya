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

package jinahya.io.bitio;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.testng.Assert;

import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitOutputTest extends AbstractTest {


    @Test(invocationCount = INVOCATION_COUNT)
    public void testBoolean() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final BitOutput output = new BitOutput(baos);
        final boolean expected = RANDOM.nextBoolean();
        output.writeUnsignedInt(7, 0);
        output.writeBoolean(expected);

        baos.flush();

        final DataInputStream input =
            new DataInputStream(new ByteArrayInputStream(baos.toByteArray()));
        final boolean actual = input.readBoolean();

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testBytes() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final byte[] expected = bytesValue();
        final BitOutput output = new BitOutput(baos);
        output.writeBytes(expected);

        baos.flush();

        final DataInputStream input =
            new DataInputStream(new ByteArrayInputStream(baos.toByteArray()));
        final byte[] actual = new byte[input.readInt()];
        input.readFully(actual);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testUnsignedInt() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final int length = unsignedIntLength();
        final int expected = unsignedIntValue(length);
        final BitOutput output = new BitOutput(baos);
        output.writeUnsignedInt(32 - length, 0);
        output.writeUnsignedInt(length, expected);

        final DataInputStream input =
            new DataInputStream(new ByteArrayInputStream(baos.toByteArray()));
        final int actual = input.readInt();

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testSignedInt() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final int length = signedIntLength();
        final int expected = signedIntValue(length);
        final BitOutput output = new BitOutput(baos);
        final int shift = 32 - length;
        output.writeInt((expected << shift) >> shift);

        final DataInputStream input =
            new DataInputStream(new ByteArrayInputStream(baos.toByteArray()));
        final int actual = input.readInt();

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testFloat() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final float expected = RANDOM.nextFloat();
        final BitOutput output = new BitOutput(baos);
        output.writeFloat(expected);

        final DataInputStream input =
            new DataInputStream(new ByteArrayInputStream(baos.toByteArray()));
        final float actual = input.readFloat();

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testUnsignedLong() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final int length = unsignedLongLength();
        final long expected = unsignedLongValue(length);
        final BitOutput output = new BitOutput(baos);
        output.writeUnsignedLong(64 - length, 0L);
        output.writeUnsignedLong(length, expected);

        final DataInputStream input =
            new DataInputStream(new ByteArrayInputStream(baos.toByteArray()));
        final long actual = input.readLong();

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testSignedLong() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final int length = signedLongLength();
        final long expected = signedLongValue(length);
        final BitOutput output = new BitOutput(baos);
        final int shift = 64 - length;
        output.writeLong((expected << shift) >> shift);

        final DataInputStream input =
            new DataInputStream(new ByteArrayInputStream(baos.toByteArray()));
        final long actual = input.readLong();

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testDouble() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final double expected = RANDOM.nextDouble();
        final BitOutput output = new BitOutput(baos);
        output.writeDouble(expected);

        final DataInputStream input =
            new DataInputStream(new ByteArrayInputStream(baos.toByteArray()));
        final double actual = input.readDouble();

        Assert.assertEquals(actual, expected);
    }
}
