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


package com.googlecode.jinahya.nica.util;


import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class AesStaticTest {


    @Test(invocationCount = 16)
    public static void testNewKey() {
        final byte[] key = Aes.newKey();
        Assert.assertNotNull(key);
        Assert.assertEquals(key.length, Aes.KEY_SIZE_IN_BYTES);
        System.out.println("Aes.newKey: " + Hex.encodeToString(key));
        Aes.checkKey(key);
    }


    @Test(invocationCount = 16)
    public static void testNewIv() {
        final byte[] iv = Aes.newIv();
        Assert.assertNotNull(iv);
        Assert.assertEquals(iv.length, Aes.BLOCK_SIZE_IN_BYTES);
        System.out.println("Aes.newIv: " + Hex.encodeToString(iv));
        Aes.checkIv(iv);
    }


}

