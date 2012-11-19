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


import java.math.BigInteger;
import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class WrongBigIntegerForHexTest {


    @Test
    public void seemsWork() {
        final byte[] expected = new byte[]{0x09, 0x1A};
        final String encoded = new BigInteger(expected).toString(16);
        Assert.assertEquals(encoded, "91a");
        final byte[] actual = new BigInteger(encoded, 16).toByteArray();
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void seemsWorkHuh() {
        final byte[] expected = new byte[]{0x00, 0x09, 0x1A};
        final String encoded = new BigInteger(expected).toString(16);
        System.out.println(encoded);
        final byte[] actual = new BigInteger(encoded, 16).toByteArray();
        Assert.assertNotEquals(actual, expected);
    }


    @Test(expectedExceptions = NumberFormatException.class)
    public void encodeFail() {
        new BigInteger(new byte[0]).toString(16);
    }


    @Test(expectedExceptions = NumberFormatException.class)
    public void decodeFail() {
        new BigInteger("", 16).toByteArray();
    }


}

