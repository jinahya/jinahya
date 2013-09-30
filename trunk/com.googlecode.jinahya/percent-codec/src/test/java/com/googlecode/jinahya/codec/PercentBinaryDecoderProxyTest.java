/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
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


package com.googlecode.jinahya.codec;


import com.googlecode.jinahya.codec.PercentBinaryDecoderProxy;
import com.googlecode.jinahya.codec.PercentEncoder;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.binary.Base64;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class PercentBinaryDecoderProxyTest {


    @Test(invocationCount = 128)
    public void testDecode() throws Exception {

        final BinaryDecoder decoder =
            (BinaryDecoder) PercentBinaryDecoderProxy.newInstance();

        try {
            decoder.decode((Object) null);
            Assert.fail("passed: decode((Object) null)");
        } catch (NullPointerException npe) {
            // ok
        }

        try {
            decoder.decode((byte[]) null);
            Assert.fail("passed: decode((byte[]) null)");
        } catch (NullPointerException npe) {
            // ok
        }

        final Random random = ThreadLocalRandom.current();

        final byte[] expected = new byte[random.nextInt(128)];
        random.nextBytes(expected);
        System.out.println("original ----------------------------------------");
        System.out.println(Base64.encodeBase64String(expected));

        final byte[] encoded = PercentEncoder.encodeMultiple(expected);
        System.out.println("encoded -----------------------------------------");
        System.out.println(new String(encoded, "US-ASCII"));

        final byte[] actual = decoder.decode(encoded);
        System.out.println("decoded -----------------------------------------");
        System.out.println(Base64.encodeBase64String(actual));

        Assert.assertEquals(actual, expected);
    }


}
