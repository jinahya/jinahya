/*
 *  Copyright 2009 onacit.
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

import static org.junit.Assert.*;
import org.junit.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class StringTest {


    @Test
    public void test() throws IOException {

        System.out.println("------------------------------------------ STRING");

        StringWriter writer = new StringWriter();
        try {
            int length = random.nextInt(21845);
            for (int i = 0; i < length; i++) {
                writer.write(random.nextInt(65535));
            }
            test(writer.toString());
        } finally {
            writer.close();
        }
    }


    private void test(final String expected) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        BitOutput bo = new BitOutput(baos);
        bo.writeUTF(expected);

        assertEquals(expected, new BitInput(new ByteArrayInputStream(baos.toByteArray())).readUTF());

        assertEquals(expected, new DataInputStream(new ByteArrayInputStream(baos.toByteArray())).readUTF());

        baos.reset();

        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeUTF(expected);

        assertEquals(expected, new BitInput(new ByteArrayInputStream(baos.toByteArray())).readUTF());
    }


    private Random random = new Random();
}
