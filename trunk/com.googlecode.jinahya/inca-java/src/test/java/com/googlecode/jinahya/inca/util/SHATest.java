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


package com.googlecode.jinahya.inca.util;


import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class SHATest<S extends SHA> {


    protected static final Random RANDOM = new Random();


    protected static byte[] newUnhashed() {
        final byte[] unhashed = new byte[RANDOM.nextInt(1024)];
        RANDOM.nextBytes(unhashed);
        return unhashed;
    }


    protected static String newUnhashedString() {
        return RandomStringUtils.random(RANDOM.nextInt(1024));
    }


    protected abstract S newInstance();


    @Test
    public void testHash() {

        final S sha = newInstance();

        try {
            sha.hash((byte[]) null);
            Assert.fail("passed: hash((byte[]) null)");
        } catch (IllegalArgumentException iae) {
            // expected;
        }

        final byte[] unhashed = newUnhashed();
        final byte[] hashed = sha.hash(unhashed);
    }


    @Test
    public void testHashWithString() {

        final S sha = newInstance();

        try {
            sha.hash((String) null);
            Assert.fail("passed: hash((byte[]) null)");
        } catch (IllegalArgumentException iae) {
            // expected;
        }

        final String unhashed = newUnhashedString();
        final byte[] hashed = sha.hash(unhashed);
    }


    @Test
    public void testHashToString() {

        final S sha = newInstance();

        try {
            sha.hashToString((byte[]) null);
            Assert.fail("passed: hash((byte[]) null)");
        } catch (IllegalArgumentException iae) {
            // expected;
        }

        final byte[] unhashed = newUnhashed();
        final String hashed = sha.hashToString(unhashed);
    }


    @Test
    public void testHashToStringWithString() {

        final S sha = newInstance();

        try {
            sha.hashToString((String) null);
            Assert.fail("passed: hash((byte[]) null)");
        } catch (IllegalArgumentException iae) {
            // expected;
        }

        final String unhashed = newUnhashedString();
        final String hashed = sha.hashToString(unhashed);
    }


}

