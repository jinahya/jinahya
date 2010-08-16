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
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitInputTest extends AbstractTest {


    @Test(invocationCount = INVOCATION_COUNT)
    public void testBoolean() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final DataOutputStream dos = new DataOutputStream(baos);

        final boolean expected = RANDOM.nextBoolean();

        dos.writeBoolean(expected);

        dos.flush();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));

        input.readInt(7);
        Assert.assertEquals(input.readBoolean(), expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testBytes() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final DataOutputStream dos = new DataOutputStream(baos);

        final byte[] expected = new byte[RANDOM.nextInt(BYTE_ARRAY_LENGTH)];
        RANDOM.nextBytes(expected);

        dos.writeInt(expected.length);
        dos.write(expected);

        dos.flush();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));

        Assert.assertEquals(input.readBytes(), expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testUnsignedInt() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final DataOutputStream dos = new DataOutputStream(baos);

        final int length = unsignedIntLength();
        final int expected = unsignedIntValue(length);
        dos.writeInt(expected);

        dos.flush();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));
        input.readUnsignedInt(32 - length);
        final int actual = input.readUnsignedInt(length);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testSignedInt() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final DataOutputStream dos = new DataOutputStream(baos);

        final int length = signedIntLength();
        final int expected = signedIntValue(length);
        dos.writeInt(expected);

        dos.flush();
        baos.flush();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));
        if (length != 32) {
            input.readUnsignedInt(32 - length);
        }
        final int actual = input.readInt(length);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testFloat() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final DataOutputStream dos = new DataOutputStream(baos);

        final float expected = RANDOM.nextFloat();
        dos.writeFloat(expected);

        dos.flush();
        baos.flush();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));

        Assert.assertEquals(input.readFloat(), expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testUnsignedLong() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final DataOutputStream dos = new DataOutputStream(baos);

        final int length = unsignedLongLength();
        final long expected = unsignedLongValue(length);
        dos.writeLong(expected);

        dos.flush();
        baos.flush();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));
        input.readUnsignedLong(64 - length);
        final long actual = input.readUnsignedLong(length);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testSignedLong() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final DataOutputStream dos = new DataOutputStream(baos);

        final int length = signedLongLength();
        final long expected = signedLongValue(length);
        dos.writeLong(expected);

        dos.flush();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));
        if (length != 64) {
            input.readUnsignedLong(64 - length);
        }
        final long actual = input.readLong(length);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testDouble() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final DataOutputStream dos = new DataOutputStream(baos);

        final double expected = RANDOM.nextDouble();
        dos.writeDouble(expected);

        dos.flush();
        baos.flush();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));

        Assert.assertEquals(input.readDouble(), expected);
    }
}
