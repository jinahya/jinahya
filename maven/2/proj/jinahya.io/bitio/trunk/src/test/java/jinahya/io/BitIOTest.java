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

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitIOTest {


    private static final Random RANDOM = new Random();


    //@Test(invocationCount = 1024)
    @Test
    public void testBoolean() throws IOException {

        System.out.println("here");

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final BitOutput output = new BitOutput(baos);

        final List<Boolean> list = new LinkedList<Boolean>();

        final int count = RANDOM.nextInt(1024) + 1024;
        for (int i = 0; i < count; i++) {
            list.add(0, RANDOM.nextBoolean());
            output.writeBoolean(list.get(0));
        }

        output.align();

        final BitInput input =
            new BitInput(new ByteArrayInputStream(baos.toByteArray()));

        for (int i = 0; i < count; i++) {
            Assert.assertEquals(input.readBoolean(), (boolean) list.remove(0));
        }
    }
}
