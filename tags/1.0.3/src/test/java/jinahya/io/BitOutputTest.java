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
import org.testng.Assert;

import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitOutputTest {


    @Test
    public void testAlign() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);

        Assert.assertEquals(output.count(), 0);

        output.writeUnsignedByte(3, 0);

        Assert.assertEquals(output.count(), 0);

        output.aling(4);

        Assert.assertEquals(output.count(), 4);
    }


    @Test
    public void testAlignForNegativeCount() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);

        output.count(-1);
        output.writeUnsignedByte(1, 0);

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
}
