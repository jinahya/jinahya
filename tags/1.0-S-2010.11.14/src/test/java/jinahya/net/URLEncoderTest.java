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


import java.io.IOException;
import java.util.Random;

import jinahya.lang.ModifiedUTF8;
import jinahya.lang.ModifiedUTF8.Acceptor;

import org.testng.Assert;
import org.testng.annotations.Test;



/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class URLEncoderTest {


    private static final Random RANDOM = new Random();


    private static final Acceptor ACCEPTOR = new Acceptor() {
        @Override
        public boolean accept(final char c) {
            return !Character.isISOControl(c) && !Character.isWhitespace(c);
        }
    };


    @Test(invocationCount = 128)
    public void testEncoding() throws IOException {

        final String generated = ModifiedUTF8.generate(RANDOM.nextInt(128) + 1);

        final String encoded = URLEncoder.encode(generated, "UTF-8");

        final String decoded = java.net.URLDecoder.decode(encoded, "UTF-8");

        Assert.assertEquals(decoded.getBytes("US-ASCII"),
                            generated.getBytes("US-ASCII"));
    }


    private void hex(String name, final byte[] bytes) {
        System.out.print(name + ": ");
        for (byte b : bytes) {
            final int i = b & 0xFF;
            if (i < 0x0A) {
                System.out.print("0");
            }
            System.out.print(Integer.toHexString(i).toUpperCase());
        }
        System.out.println();
    }
}
