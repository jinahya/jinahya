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


import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Hex;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HexEncoderTest {


    @Test(expectedExceptions = NullPointerException.class)
    public void testEncodeWithNull() {
        new HexEncoder().encode(null);
    }


    @Test(invocationCount = 128)
    public void testEncode() {
        new HexEncoder().encode(new byte[0]);
        new HexEncoder().encode(HexCodecTestUtils.newDecodedBytes());
    }


    @Test(invocationCount = 128)
    public void testEncodeAgainstCommonsCodecHex() throws EncoderException {

        final byte[] decoded = HexCodecTestUtils.newDecodedBytes();

        final byte[] expected = new Hex().encode(decoded);

        final byte[] actual = new HexEncoder().encode(decoded);

        Assert.assertEquals(actual, expected);
    }


}

