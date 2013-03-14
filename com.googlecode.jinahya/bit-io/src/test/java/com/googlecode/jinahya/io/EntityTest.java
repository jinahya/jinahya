/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.io;


import com.googlecode.jinahya.io.BitInput.ByteInputStream;
import com.googlecode.jinahya.io.BitOutput.ByteOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class EntityTest {


    @Test
    public void test() throws IOException {

        final Random random = ThreadLocalRandom.current();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(new ByteOutputStream(baos));

        final Entity[] expected = new Entity[1]; //random.nextInt(1024) + 1024];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = new Entity().write(random, output);
        }
        output.align(1);
        baos.flush();

        final byte[] bytes = baos.toByteArray();
        System.out.println("bytes.length: " + bytes.length);

        final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        final BitInput input = new BitInput(new ByteInputStream(bais));

        final Entity[] actual = new Entity[expected.length];
        for (int i = 0; i < actual.length; i++) {
            actual[i] = new Entity().read(input);
        }

        System.out.println(actual[0].hashCode());
        System.out.println(expected[0].hashCode());

        Assert.assertArrayEquals(actual, expected);
    }


}

