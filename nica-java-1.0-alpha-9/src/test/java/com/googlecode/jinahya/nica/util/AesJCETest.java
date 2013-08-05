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


import static com.googlecode.jinahya.nica.util.AesTest.newKey;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class AesJCETest extends AesTest<AesJCE> {


    @Override
    protected AesJCE create(final byte[] key) {
        return new AesJCE(key);
    }


    @Test(invocationCount = 32)
    public void testDecryptAaginstBC() {

        final byte[] key = newKey();
        final byte[] iv = newIv();

        final byte[] expected = newInput();

        final byte[] encrypted = new AesBC(key).encrypt(iv, expected);

        final byte[] actual = create(key).decrypt(iv, encrypted);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 32)
    public void testEncryptAaginstBC() {

        final byte[] key = newKey();
        final byte[] iv = newIv();

        final byte[] expected = newInput();

        final byte[] encrypted = create(key).encrypt(iv, expected);

        final byte[] actual = new AesBC(key).decrypt(iv, encrypted);

        Assert.assertEquals(actual, expected);
    }


}
