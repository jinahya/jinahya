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


package com.googlecode.jinahya.rfc4648;


import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <B>
 */
public abstract class BaseTest<B extends Base> {


    protected static byte[] decoded(final int length) {

        final Random random = ThreadLocalRandom.current();

        final byte[] bytes = new byte[random.nextInt(length)];
        random.nextBytes(bytes);

        return bytes;
    }


    protected static byte[] decoded() {

        return decoded(1024);
    }


    protected static byte[] upper(final byte[] lower) {

        final byte[] upper = new byte[lower.length];

        for (int i = 0; i < upper.length; i++) {
            if (lower[i] >= 0x61 && lower[i] <= 0x7A) {
                upper[i] = (byte) (lower[i] - 0x20);
                continue;
            }
            upper[i] = lower[i];
        }

        return upper;
    }


    protected static byte[] lower(final byte[] upper) {

        final byte[] lower = new byte[upper.length];

        for (int i = 0; i < lower.length; i++) {
            if (upper[i] >= 0x41 && upper[i] <= 0x5A) {
                lower[i] = (byte) (upper[i] + 0x20);
                continue;
            }
            lower[i] = upper[i];
        }

        return lower;
    }


    public BaseTest(final Class<B> baseClass) {

        super();

        this.baseClass = Objects.requireNonNull(
            baseClass, "baseClass can not be null.");
    }


    protected B base() {
        try {
            return baseClass.newInstance();
        } catch (InstantiationException ie) {
            Assert.fail("failed to create an instance of " + baseClass, ie);
            throw new RuntimeException(ie);
        } catch (IllegalAccessException iae) {
            Assert.fail("failed to create an instance of " + baseClass, iae);
            throw new RuntimeException(iae);
        }
    }


    protected byte[] encoded(final byte[] decoded) throws IOException {

        return base().encode(decoded);
    }


    protected byte[] encoded() throws IOException {

        return encoded(decoded());
    }


    @Test(invocationCount = 32)
    public void testEncodingDecoding() throws IOException {

        final B base = base();

        final byte[] expected = decoded();

        final byte[] encoded = base.encode(expected);

        final byte[] actual = base.decode(encoded);

        Assert.assertEquals(actual, expected, "fail");

        boolean lower = true;
        try {
            final Field field = Base.class.getDeclaredField("lower");
            field.setAccessible(true);
            try {
                lower = (Boolean) field.getBoolean(base);
            } catch (IllegalAccessException iae) {
                Assert.fail(iae.toString());
            }
        } catch (NoSuchFieldException nsfe) {
            Assert.fail(nsfe.toString());
        }

        if (!lower) { // upper case characters only
            final byte[] actual2 = base.decode(lower(encoded));
            Assert.assertEquals(actual2, expected);
        }
    }


    /**
     * base class.
     */
    protected final Class<B> baseClass;


}
