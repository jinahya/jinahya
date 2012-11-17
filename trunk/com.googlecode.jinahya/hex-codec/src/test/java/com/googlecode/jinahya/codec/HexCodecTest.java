/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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


import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HexCodecTest {


    @Test(invocationCount = 128)
    public void testEncodeDecodeLikeABoss() {

        final byte[] expected =
            new byte[ThreadLocalRandom.current().nextInt(1024)];
        ThreadLocalRandom.current().nextBytes(expected);

        final byte[] encoded = new HexEncoder().encodeLikeABoss(expected);
        for (int b : encoded) {
            Assert.assertTrue((b >= 0x30 && b <= 0x39)
                              || (b >= 0x61 && b <= 0x66));
        }

        final byte[] actual = new HexDecoder().decodeLikeABoss(encoded);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testEncode() {
        final byte[] expected = HexCodecTestUtil.newDecodedBytes();
        final byte[] encoded = new HexEncoder().encodeLikeABoss(expected);
        final byte[] actual = new HexDecoder().decode(encoded);
        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public void testDecode() {
        final byte[] expected = HexCodecTestUtil.newDecodedBytes();
        final byte[] encoded = new HexEncoder().encode(expected);
        final byte[] actual = new HexDecoder().decodeLikeABoss(encoded);
        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public void testEncodeDecode() {
        final byte[] expected = HexCodecTestUtil.newDecodedBytes();
        final byte[] encoded = new HexEncoder().encode(expected);
        final byte[] actual = new HexDecoder().decode(encoded);
        Assert.assertEquals(actual, expected);
    }


}

