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


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class PercentDecoderTest {


    private static final String DECODED = "~!@#$%^&*()_+";

    private static final String ENCODED = "~%21%40%23%24%25%5E%26%2A%28%29_%2B";


    @Test
    public void testDecode() throws IOException {

        final InputStream input = new ByteArrayInputStream(ENCODED.getBytes());
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        PercentDecoder.decode(input, output);

        final String actual = new String(output.toByteArray());

        Assert.assertEquals(actual, DECODED);
    }
}
