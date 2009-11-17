/*
 *  Copyright 2009 Jin Kwon.
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
 *  under the License.
 */

package jinahya.bitio;


import java.io.*;
import java.util.*;

import org.testng.Assert;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class UnsignedIntTest {


    /**
     *
     * @throws IOException
     * @testng.test invocationCount="1024"
     */
    public void test() throws IOException {

        System.out.println("------------------------------------ UNSIGNED INT");

        final int length = random.nextInt(31) + 1; // 1 - 31;
        int expected = random.nextInt((int) Math.pow(2, length));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        BitOutput output = new BitOutput(baos);
        output.writeUnsignedInt(length, expected);
        output.alignOctets(1);

        BitInput input = new BitInput(new ByteArrayInputStream(baos.toByteArray()));
        int actual = input.readUnsignedInt(length);
        input.alignOctets(1);

        Assert.assertEquals(actual, expected);
    }


    private Random random = new Random();
}
