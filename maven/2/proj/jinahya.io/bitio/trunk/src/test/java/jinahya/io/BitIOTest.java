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
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitIOTest extends AbstractTest {


    @Test(invocationCount = INVOCATION_COUNT)
    public void testBoolean() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final BitOutput output = new BitOutput(baos);
        final boolean expected = RANDOM.nextBoolean();
        output.writeBoolean(expected);
        output.align();

        baos.flush();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));
        Assert.assertEquals(input.readBoolean(), expected);
        input.align();
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testBytes() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final BitOutput output = new BitOutput(baos);
        final byte[] expected = new byte[RANDOM.nextInt(BYTE_ARRAY_LENGTH)];
        RANDOM.nextBytes(expected);
        output.writeBytes(expected);
        output.align();

        baos.flush();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));
        Assert.assertEquals(input.readBytes(), expected);
        input.align();
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testUnsignedInt() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final BitOutput output = new BitOutput(baos);
        final int length = RANDOM.nextInt(31) + 1; // 1 - 31
        final int expected = unsignedIntValue(length);
        output.writeUnsignedInt(length, expected);
        output.align();

        baos.flush();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));
        Assert.assertEquals(input.readUnsignedInt(length), expected);
        input.align();
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testSignedInt() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final BitOutput output = new BitOutput(baos);
        final int length = RANDOM.nextInt(31) + 2; // 2 - 32
        final int expected = signedIntValue(length);
        output.writeInt(length, expected);
        output.align();

        baos.flush();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));
        Assert.assertEquals(input.readInt(length), expected);
        input.align();
    }
}
