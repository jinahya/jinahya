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


import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <S> Sha type parameter
 */
public abstract class ShaTest<S extends Sha> {


    /**
     * Output size in bits.
     */
    public static final int OUTPUT_SIZE = 0xA0; // 160


    /**
     * Output size in bytes.
     */
    public static final int OUTPUT_SIZE_IN_BYTES = OUTPUT_SIZE / 0x08; // 20


    protected static byte[] newData() {

        final Random random = ThreadLocalRandom.current();

        final byte[] data = new byte[random.nextInt(1024)];

        random.nextBytes(data);

        return data;
    }


    protected static String newDataAsString() {

        return RandomStringUtils.random(
            ThreadLocalRandom.current().nextInt(1024));
    }


    /**
     * Creates a new instance.
     *
     * @return a new instance.
     */
    protected abstract S newInstance();


    @Test
    public void testHash() {

        final S sha = newInstance();

        try {
            sha.hash((byte[]) null);
            Assert.fail("passed: hash((byte[]) null)");
        } catch (NullPointerException iae) {
            // expected;
        }

        final byte[] unhashed = newData();
        final byte[] hashed = sha.hash(unhashed);

        Assert.assertTrue(hashed.length == OUTPUT_SIZE_IN_BYTES);
    }


    @Test
    public void testHashWithString() {

        final S sha = newInstance();

        try {
            sha.hash((String) null);
            Assert.fail("passed: hash((String) null)");
        } catch (NullPointerException iae) {
            // expected;
        }

        final String unhashed = newDataAsString();
        final byte[] hashed = sha.hash(unhashed);
        Assert.assertTrue(hashed.length == OUTPUT_SIZE_IN_BYTES);
    }


    @Test
    public void testHashToString() {

        final S sha = newInstance();

        try {
            sha.hashToString((byte[]) null);
            Assert.fail("passed: hash((byte[]) null)");
        } catch (NullPointerException iae) {
            // expected;
        }

        final byte[] unhashed = newData();
        final String hashed = sha.hashToString(unhashed);
        Assert.assertTrue(hashed.length() == OUTPUT_SIZE_IN_BYTES * 2);
    }


    @Test
    public void testHashToStringWithString() {

        final S sha = newInstance();

        try {
            sha.hashToString((String) null);
            Assert.fail("passed: hash((String) null)");
        } catch (NullPointerException iae) {
            // expected;
        }

        final String unhashed = newDataAsString();
        final String hashed = sha.hashToString(unhashed);
        Assert.assertTrue(hashed.length() == OUTPUT_SIZE_IN_BYTES * 2);
    }


}
