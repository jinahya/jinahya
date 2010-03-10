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
 */

package jinahya.bitio;


import java.io.*;
import java.util.*;

import static org.junit.Assert.*;
import org.junit.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class UnsignedLongTest {


    @Test
    public void test() throws IOException {

        System.out.println("----------------------------------- UNSIGNED LONG");

        int length = random.nextInt(63) + 1; // 1 - 63;
        long expected = (random.nextLong() & (long) Math.pow(2, length - 1));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        BitOutput output = new BitOutput(baos);
        output.writeUnsignedLong(length, expected);
        output.align(8);

        BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));
        long actual = input.readUnsignedLong(length);
        input.align(1);

        assertEquals(expected, actual);
    }


    private Random random = new Random();
}
