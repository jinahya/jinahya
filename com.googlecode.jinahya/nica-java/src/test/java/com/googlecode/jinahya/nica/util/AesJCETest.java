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
public class AesJCETest extends AesTest<AesJCE> {


    @Test(expectedExceptions = IllegalArgumentException.class,
          invocationCount = 128)
    public void testConstructorWithWrongKey() {
        new AesJCE(newWrongKey());
    }


    @Test
    public void testConstructor() {
        final Aes aes = new AesJCE(new byte[Aes.KEY_SIZE_IN_BYTES]);
    }


    @Override
    protected AesJCE newInstance(final byte[] key) {
        return new AesJCE(key);
    }


    @Test(invocationCount = 128)
    public void testDecryptAaginstBC() {

        final byte[] key = newKey();
        final byte[] iv = newIv();

        final byte[] expected = newInput();

        final byte[] encrypted = new AesBC(key).encrypt(iv, expected);

        final byte[] actual = new AesJCE(key).decrypt(iv, encrypted);

        Assert.assertEquals(actual, expected);
    }


}

