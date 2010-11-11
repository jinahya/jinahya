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

package jinahya.net;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class URLEncodingOutputStreamTest {


    @Test
    public void testEncoding() throws IOException {

        final String expected = " `1234567890-=~!@#$%^&*()_+";

        final byte[] decoded = expected.getBytes("UTF-8");

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final OutputStream output = new URLEncodingOutputStream(baos);
        output.write(decoded);

        final byte[] encoded = baos.toByteArray();

        final String actual = URLDecoder.decode(
            new String(encoded, "US-ASCII"), "UTF-8");

        Assert.assertEquals(actual, expected);
    }
}
