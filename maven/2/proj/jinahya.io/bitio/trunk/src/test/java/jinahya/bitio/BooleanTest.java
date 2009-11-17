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
public class BooleanTest {


    /**
     *
     * @throws IOException
     * @testng.test invocationCount="1024"
     */
    public void test() throws IOException {
        System.out.println("----------------------------------------- BOOLEAN");

        boolean expected = random.nextBoolean();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BitOutput output = new BitOutput(baos);
        output.writeBoolean(expected);
        output.alignOctets(1);

        BitInput input = new BitInput(new ByteArrayInputStream(baos.toByteArray()));
        boolean actual = input.readBoolean();
        input.alignOctets(1);

        Assert.assertEquals(actual, expected);
    }


    private Random random = new Random();
}
