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


import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.*;
import org.junit.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class StringTest extends AbstractTest {


    @Test
    public void test() throws IOException {
        System.out.println("testing string");
        for (int i = 0; i < COUNT; i++) {
            final StringWriter writer = new StringWriter();
            try {
                final int length = RANDOM.nextInt(21845);
                for (int j = 0; j < length; j++) {
                    writer.write(RANDOM.nextInt(65535));
                }
                writer.flush();
                final String expected = writer.toString();
                output.writeUTF(expected);

                alignAndFlush();

                assertEquals(expected, input.align().readUTF());
            } finally {
                writer.close();
            }
        }
    }
}
