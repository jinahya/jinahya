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

import org.testng.Assert;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class LongTest {


    /**
     *
     * @throws IOException
     * @testng.test invocationCount="1024"
     */
    public void test() throws IOException {
        System.out.println("-------------------------------------------- LONG");

        final int length = random.nextInt(63) + 2; // 2 - 64;
        long max = (long) Math.pow(2, length - 1);
        long expected = (random.nextLong() & max) - max;

        test(length, expected);
    }


    /**
     *
     * @throws IOException
     * @testng.test invocationCount="1024"
     */
    public void test64() throws IOException {
        System.out.println("---------------------------------------- Long(64)");

        test(64, random.nextLong());
    }


    /**
     *
     * @throws IOException
     * @testng.test invocationCount="1024"
     */
    public void testLE() throws IOException {
        System.out.println("----------------------------------------- LONG LE");

        final int length = random.nextInt(7) + 1; // 1 - 7;
        long expected = (long) (Math.random() * Math.pow(2, length * 8));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BitOutput output = new BitOutput(baos);
        output.writeLongLE(length, expected);
        output.align(8);

        BitInput input = new BitInput(new ByteArrayInputStream(baos.toByteArray()));
        long actual = input.readLongLE(length);
        input.alignOctets(1);

        Assert.assertEquals(actual, expected);
    }


    /**
     *
     * @throws IOException
     * @testng.test invocationCount="1024"
     */
    public void testLE64() throws IOException {
        System.out.println("------------------------------------- LONG LE(64)");

        long expected = random.nextLong();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BitOutput output = new BitOutput(baos);
        output.writeLongLE(8, expected);
        output.align(8);

        BitInput input = new BitInput(new ByteArrayInputStream(baos.toByteArray()));
        long actual = input.readLongLE(8);
        input.alignOctets(1);

        Assert.assertEquals(actual, expected);
    }


    /**
     *
     * @throws IOException
     */
    private void test(final int length, final long expected) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BitOutput output = new BitOutput(baos);
        output.writeLong(length, expected);
        output.align(8);

        BitInput input = new BitInput(new ByteArrayInputStream(baos.toByteArray()));
        long actual = input.readLong(length);
        input.alignOctets(1);

        Assert.assertEquals(actual, expected);
    }


    private Random random = new Random();
}
