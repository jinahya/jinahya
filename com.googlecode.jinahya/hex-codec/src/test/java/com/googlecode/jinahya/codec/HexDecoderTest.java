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


import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HexDecoderTest {


    @Test(expectedExceptions = NullPointerException.class)
    public void testDecodeWithNull() {
        new HexDecoder().decode(null);
    }


    @Test(enabled = false, expectedExceptions = IllegalArgumentException.class)
    public void testDecodeWithOddLengthArray() {
        new HexDecoder().decode(new byte[]{0x30}); // '0'
    }


    @Test(enabled = false, expectedExceptions = IllegalArgumentException.class)
    public void testDecodeWithIllegalSingle() {
        new HexDecoder().decode(new byte[]{0x29, 0x30}); // '/', '0'
    }


    @Test
    public void testDecode() {
        new HexDecoder().decode(new byte[0]);
        new HexDecoder().decode(HexCodecTestUtils.newEncodedBytes());
    }


    @Test(invocationCount = 128)
    public void testDecodeAgainstCommonsCodecHex() throws DecoderException {

        final byte[] encoded = HexCodecTestUtils.newEncodedBytes();

        final byte[] expected = new Hex().decode(encoded);

        final byte[] actual = new HexDecoder().decode(encoded);

        Assert.assertEquals(actual, expected);
    }


}

