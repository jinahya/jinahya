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
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitIOTest {


    private static final Random RANDOM = new Random();


    public void testUnsignedByte() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);

        final int length = RANDOM.nextInt(8) + 1;

        int value = 0x00;
        for (int i = 0; i < length; i++) {
            value <<= 1;
            value |= (RANDOM.nextBoolean() ? 0x01 : 0x00);
        }

        //output.writeUnsignedByte(length, value);
        output.aling(1);

        baos.flush();
        Assert.assertEquals(baos.size(), 1);

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));

        //Assert.assertEquals(input.readUnsignedByte(length), value);
    }


    @Test(invocationCount = 128)
    public void testBoolean() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);

        final boolean expected = RANDOM.nextBoolean();

        output.writeBoolean(expected);
        output.aling(1);

        baos.flush();
        Assert.assertEquals(baos.size(), 1);

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));

        final boolean actual = input.readBoolean();

        Assert.assertEquals(actual, expected);
    }


    public void testUnsignedShort() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);

        final int length = RANDOM.nextInt(16) + 1;

        int value = RANDOM.nextInt(65536);
        value <<= (32 - length);
        value >>>= (32 - length);

        long start = System.currentTimeMillis();
        //output.writeUnsignedShort(length, value);
        output.aling(1);

        //System.out.println(length + "/ " + value + " / " + (System.currentTimeMillis() - start));

        baos.flush();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));

        //final int actual = input.readUnsignedShort(length);

        //Assert.assertEquals(actual, value);
    }
}
