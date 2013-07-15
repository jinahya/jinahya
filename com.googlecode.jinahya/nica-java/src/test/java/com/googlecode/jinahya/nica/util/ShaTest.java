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
 * @param <S> {@link Sha} type parameter
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


    protected static byte[] newBytesData() {

        final Random random = ThreadLocalRandom.current();

        final int length = random.nextInt(1024);
        final byte[] data = new byte[length];
        random.nextBytes(data);

        return data;
    }


    protected static String newStringData() {

        final Random random = ThreadLocalRandom.current();

        final int count = random.nextInt(1024);
        final String data = RandomStringUtils.random(count);

        return data;
    }


    /**
     * Creates a new instance.
     *
     * @return a new instance.
     */
    protected abstract S create();


    @Test(expectedExceptions = {NullPointerException.class})
    public void testHashWithNullBytes() {

        final S sha = create();

        sha.hash((byte[]) null);
    }


    @Test
    public void testHash() {

        final S sha = create();

        final byte[] data = newBytesData();
        final byte[] hash = sha.hash(data);

        Assert.assertTrue(hash.length == OUTPUT_SIZE_IN_BYTES);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void testHashWithNullString() {

        final S sha = create();

        sha.hash((String) null);
    }


    @Test
    public void testHashWithString() {

        final S sha = create();

        final String data = newStringData();
        final byte[] hash = sha.hash(data);

        Assert.assertTrue(hash.length == OUTPUT_SIZE_IN_BYTES);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void testHashToStringWithNullBytes() {

        final S sha = create();

        sha.hashToString((byte[]) null);
    }


    @Test
    public void testHashToString() {

        final S sha = create();

        final byte[] data = newBytesData();
        final String hash = sha.hashToString(data);

        Assert.assertTrue(hash.length() == OUTPUT_SIZE_IN_BYTES * 2);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void testHashToStringWithNullString() {

        final S sha = create();

        sha.hashToString((String) null);
    }


    @Test
    public void testHashToStringWithString() {

        final S sha = create();

        final String data = newStringData();
        final String hash = sha.hashToString(data);

        Assert.assertTrue(hash.length() == OUTPUT_SIZE_IN_BYTES * 2);
    }


}
