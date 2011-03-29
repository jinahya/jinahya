/*
 * Copyright 2011 Jin Kwon.
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


package jinahya.rfc4648;


import java.io.IOException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.shindig.common.util.Base32;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Base32HEXTest extends BaseTest<Base32HEX> {


    public Base32HEXTest() {
        super(new Base32HEX());
    }


    @Test(invocationCount = 128, enabled = false)
    public void testEncodingAgainstShindig()
        throws IOException, DecoderException {

        final byte[] original = generate(1024);

        final char[] encoded = base.encode(original);
        final byte[] encodedBytes = new byte[encoded.length];
        for (int i = 0; i < encodedBytes.length; i++) {
            encodedBytes[i] = (byte) Character.toLowerCase(encoded[i]);
        }

        final byte[] decoded = new Base32().decode(encodedBytes);

        Assert.assertEquals(decoded, original, "fail");
    }


    @Test(invocationCount = 128, enabled = false)
    public void testDecodingAgainstShindig()
        throws EncoderException, IOException {

        final byte[] original = generate(1024);

        final byte[] encodedBytes = new Base32().encode(original);
        final char[] encoded = new char[encodedBytes.length];
        for (int i = 0; i < encoded.length; i++) {
            encoded[i] = Character.toUpperCase((char) encodedBytes[i]);
        }

        final byte[] decoded = base.decode(encoded);

        Assert.assertEquals(decoded, original, "fail");
    }
}
