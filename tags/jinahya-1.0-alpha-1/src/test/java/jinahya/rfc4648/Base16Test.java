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
import org.apache.commons.codec.binary.Hex;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Base16Test extends BaseTest<Base16> {


    public Base16Test() {
        super(new Base16());
    }


    @Test(invocationCount = 128)
    public void testEncodingAgainstCommonsCodec()
        throws IOException, DecoderException {

        final byte[] original = generate(1024);

        final char[] encoded = base.encode(original);

        final byte[] decoded = Hex.decodeHex(encoded);

        Assert.assertEquals(decoded, original, "fail");
    }


    @Test(invocationCount = 128)
    public void testDecodingAgainstCommonsCodec() throws IOException {

        final byte[] original = new byte[RANDOM.nextInt(1024)];
        RANDOM.nextBytes(original);

        final char[] encoded = Hex.encodeHex(original);
        for (int i = 0; i < encoded.length; i++) {
            encoded[i] = Character.toUpperCase(encoded[i]);
        }

        final byte[] decoded = base.decode(encoded);

        Assert.assertEquals(decoded, original, "fail");
    }
}
