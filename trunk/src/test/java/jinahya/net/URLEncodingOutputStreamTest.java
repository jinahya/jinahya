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
import java.util.Random;
import jinahya.lang.ModifiedUTF8;
import org.apache.commons.lang.RandomStringUtils;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class URLEncodingOutputStreamTest {


    private static final Random RANDOM = new Random();


    @Test(invocationCount = 128)
    public void testEncoding() throws IOException {

        final String generated = RandomStringUtils.random(RANDOM.nextInt(1024));

        final String expected = URLEncoder.encode(generated, "UTF-8");

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final OutputStream output = new URLEncodingOutputStream(baos);
        output.write(generated.getBytes("UTF-8"));
        output.flush();

        final String actual = new String(baos.toByteArray(), "US-ASCII");

        Assert.assertEquals(actual, expected);
    }


    private void hex(String name, final byte[] bytes) {
        System.out.print(name + ": ");
        for (byte b : bytes) {
            final int i = b & 0xFF;
            System.out.print(" ");
            if (i < 0x0A) {
                System.out.print("0");
            }
            System.out.print(Integer.toHexString(i).toUpperCase());
        }
        System.out.println(" (" + bytes.length + ")");
    }
}
