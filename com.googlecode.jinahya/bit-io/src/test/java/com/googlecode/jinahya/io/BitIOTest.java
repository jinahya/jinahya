/*
 * Copyright 2011 Jin Kwon.
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
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitIOTest {


    /** random. */
    protected static final Random RANDOM = new Random();


    /** count. */
    protected static final int INVOCATION_COUNT = 128;


    protected static final int STRING_LENGTH = 1024;


    protected static final int BYTES_LENGTH = 1024;


    protected static final int newStringLength() {

        return RANDOM.nextInt(STRING_LENGTH) + STRING_LENGTH;
    }


    protected static final int newBytesLength() {

        return RANDOM.nextInt(BYTES_LENGTH) + BYTES_LENGTH;
    }


    protected static final String newASCII(final boolean nullable) {

        if (nullable && RANDOM.nextBoolean()) {
            return null;
        }

        if (RANDOM.nextBoolean()) {
            return "";
        }

        return RandomStringUtils.randomAscii(newStringLength());
    }


    protected static final String newString(final boolean nullable) {

        if (nullable && RANDOM.nextBoolean()) {
            return null;
        }

        if (RANDOM.nextBoolean()) {
            return "";
        }

        return RandomStringUtils.random(newStringLength());
    }


    /**
     * Returns a random count.
     *
     * @return new random count
     */
    protected static final int newCount() {
        return RANDOM.nextInt(INVOCATION_COUNT) + INVOCATION_COUNT;
    }


    protected static final Boolean newBoolean(final boolean nullable) {

        if (nullable && RANDOM.nextBoolean()) {
            return null;
        }

        return RANDOM.nextBoolean();
    }


    protected static final byte[] newBytes(final boolean nullable) {

        if (nullable && RANDOM.nextBoolean()) {
            return null;
        }

        if (RANDOM.nextBoolean()) {
            return new byte[0];
        }

        final byte[] bytes = new byte[newBytesLength()];
        RANDOM.nextBytes(bytes);
        return bytes;
    }


    @Test(invocationCount = 64)
    public void testUTF() throws IOException {

        final String expected = newString(false);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);
        output.writeUTF(expected);
        output.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput input = new BitInput(bais);
        final String actual = input.readUTF();
        input.align(1);

        Assert.assertEquals(actual, expected);
    }


}

