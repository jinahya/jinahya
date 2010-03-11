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


import java.io.IOException;

import static org.junit.Assert.*;
import org.junit.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class LongTest extends AbstractTest {


    @Test
    public void test() throws IOException {
        System.out.println("testing long");
        for (int i = 0; i < COUNT; i++) {
            final int length = RANDOM.nextInt(63) + 2; // 2 - 64;
            final long expected = RANDOM.nextLong() >> (64 - length);
            output.writeLong(length, expected);
            alignAndFlush();
            assertEquals(expected, input.align().readLong(length));
        }
    }
}
