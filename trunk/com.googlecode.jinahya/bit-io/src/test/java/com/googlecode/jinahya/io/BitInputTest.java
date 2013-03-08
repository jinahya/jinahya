/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class BitInputTest {


    @Test
    public void testReadBoolean() throws IOException {

        final BitInput input = new BitInput(new ByteArrayInputStream(
            new byte[]{(byte) 0x80})); // 1000 0000

        Assert.assertTrue(input.readBoolean());
        for (int i = 0; i < 7; i++) {
            Assert.assertFalse(input.readBoolean());
        }
    }


}

