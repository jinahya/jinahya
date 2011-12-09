/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.io;


import java.io.IOException;
import junit.framework.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class BitIOBaseTest {


    private static class BitIOBaseImpl extends BitIOBase {


        @Override
        public int align(final int length) throws IOException {
            throw new UnsupportedOperationException("Not supported yet.");
        }


    }


    @Test
    public void testSetBit() {

        final BitIOBase base = new BitIOBaseImpl();

        try {
            base.setBit(-2, true);
            Assert.fail("passed: setBit(-1, )");
        } catch (IllegalArgumentException iae) {
            // expected
        }
        
        try {
            base.setBit(-1, true);
            Assert.fail("passed: setBit(-1, )");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            base.setBit(8, true);
            Assert.fail("passed: setBit(8, )");
        } catch (IllegalArgumentException iae) {
            // expected
        }
        
        try {
            base.setBit(9, true);
            Assert.fail("passed: setBit(9, )");
        } catch (IllegalArgumentException iae) {
            // expected
        }
    }


}

