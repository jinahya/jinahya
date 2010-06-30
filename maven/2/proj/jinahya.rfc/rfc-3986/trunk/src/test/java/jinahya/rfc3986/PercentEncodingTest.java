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

package jinahya.rfc3986;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class PercentEncodingTest {


    private static final String ORIGINAL = "~!@#$%^&*()_+";

    private static final String ENCODED = "~%21%40%23%24%25%5E%26%2A%28%29_%2B";


    @Test
    public void testEncode() throws IOException {
        final String actual = PercentEncoder.encode(ORIGINAL);

        Assert.assertEquals(actual, ENCODED);
    }


    @Test
    public void testDecode() throws IOException {
        final String actual = PercentDecoder.decode(ENCODED);

        Assert.assertEquals(actual, ORIGINAL);
    }


    @Test
    public void testEncodeDecode() throws IOException {
        System.out.println(new java.io.File(".").getCanonicalPath());
        final BufferedReader reader = new BufferedReader(new InputStreamReader(
            getClass().getResourceAsStream("/original.txt"), "UTF-8"));
        try {
            String expected = null;
            while ((expected = reader.readLine()) != null) {
                final String encoded = PercentEncoder.encode(expected);
                final String actual = PercentDecoder.decode(encoded);
                Assert.assertEquals(actual, expected);
            }
        } finally {
            reader.close();
        }
    }
}
