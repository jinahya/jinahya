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
public class AESJCETest extends AESTest<AESJCE> {


    @Test
    public void testConstructors() {

        try {
            final AES aes = new AESJCE(null);
            Assert.fail("passed: new AESJCE(null)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            final AES aes = new AESJCE(new byte[AES.KEY_SIZE_IN_BYTES + 1]);
            Assert.fail("passed: new AESJCE(byte[AES.KEY_SIZE_IN_BYTES + 1])");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        final AES aes = new AESJCE(new byte[AES.KEY_SIZE_IN_BYTES]);
    }


    @Override
    protected AESJCE newInstance(final byte[] key) {
        return new AESJCE(key);
    }


}

