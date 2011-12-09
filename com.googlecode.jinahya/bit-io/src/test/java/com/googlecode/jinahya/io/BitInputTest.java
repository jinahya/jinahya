/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
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
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitInputTest extends BitIOTest {


    protected <C extends Checksum> void testChecksum(final C c1, final C c2)
        throws IOException {

        final byte[] bytes = newBytes(false);

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(bytes);
        final BitInput bi = new BitInput(bais);
        bi.addChecksum(c1);

        for (int i = 0; i < bytes.length; i++) {
            Assert.assertEquals(bi.readUnsignedInt(Byte.SIZE), bytes[i] & 0xFF);
        }

        c2.update(bytes, 0, bytes.length);

        final long actual = c1.getValue();
        final long expected = c2.getValue();

        Assert.assertEquals(actual, expected);

        Assert.assertTrue(bi.removeChecksum(c1));
    }


    private static void testDigest(final String algorithm)
        throws NoSuchAlgorithmException, IOException {

        final byte[] bytes = newBytes(false);

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(bytes);
        final BitInput bi = new BitInput(bais);
        final MessageDigest ad = MessageDigest.getInstance(algorithm);
        bi.addDigest(ad);

        for (int i = 0; i < bytes.length; i++) {
            Assert.assertEquals(bi.readUnsignedInt(Byte.SIZE), bytes[i] & 0xFF);
        }

        final MessageDigest ed = MessageDigest.getInstance(algorithm);
        ed.update(bytes);

        final byte[] actual = ad.digest();
        final byte[] expected = ed.digest();

        Assert.assertEquals(actual, expected);

        Assert.assertTrue(bi.removeDigest(ad));
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void testConstructorWithNullStream() {

        final BitInput bi = new BitInput(null);
    }


    @Test
    public void testReadUnsignedByte() throws IOException {

        final BitInput bi = new BitInput(new ByteArrayInputStream(new byte[0]));

        try {
            bi.readUnsignedByte(-2);
            Assert.fail("passed: readUnsignedByte(-2)");
        } catch (IllegalArgumentException iae) {
            // expected
        }
        try {
            bi.readUnsignedByte(-1);
            Assert.fail("passed: readUnsignedByte(-1)");
        } catch (IllegalArgumentException iae) {
            // expected
        }
        try {
            bi.readUnsignedByte(9);
            Assert.fail("passed: readUnsignedByte(9)");
        } catch (IllegalArgumentException iae) {
            // expected
        }
        try {
            bi.readUnsignedByte(10);
            Assert.fail("passed: readUnsignedByte(10)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            bi.readUnsignedByte(8);
            Assert.fail("passed: an EOFException expected");
        } catch (EOFException eofe) {
            // expected
        }
    }


    @Test
    public void testReadUnsignedShort() throws IOException {

        final BitInput bi = new BitInput(new ByteArrayInputStream(new byte[0]));

        try {
            bi.readUnsignedShort(-2);
            Assert.fail("passed: readUnsignedByte(-2)");
        } catch (IllegalArgumentException iae) {
            // expected
        }
        try {
            bi.readUnsignedShort(-1);
            Assert.fail("passed: readUnsignedByte(-1)");
        } catch (IllegalArgumentException iae) {
            // expected
        }
        try {
            bi.readUnsignedShort(17);
            Assert.fail("passed: readUnsignedByte(17)");
        } catch (IllegalArgumentException iae) {
            // expected
        }
        try {
            bi.readUnsignedShort(18);
            Assert.fail("passed: readUnsignedByte(18)");
        } catch (IllegalArgumentException iae) {
            // expected
        }
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testReadUnsignedInt() throws IOException {

        final BitInput bi =
            new BitInput(new ByteArrayInputStream(new byte[0]));
        try {
            bi.readUnsignedInt(-1);
            Assert.fail("passed: readUnsignedInt(-1)");
        } catch (IllegalArgumentException iae) {
            // expected
        }
        try {
            bi.readUnsignedInt(0);
            Assert.fail("passed: readUnsignedInt(0)");
        } catch (IllegalArgumentException iae) {
            // expected
        }
        try {
            bi.readUnsignedInt(32);
            Assert.fail("passed: readUnsignedInt(32)");
        } catch (IllegalArgumentException iae) {
            // expected
        }
        try {
            bi.readUnsignedInt(33);
            Assert.fail("passed: readUnsignedInt(33)");
        } catch (IllegalArgumentException iae) {
            // expected
        }
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testReadInt() throws IOException {

        final BitInput bi = new BitInput(new ByteArrayInputStream(new byte[0]));
        try {
            bi.readInt(0);
            Assert.fail("passed: readInt(0)");
        } catch (IllegalArgumentException iae) {
            // expected
        }
        try {
            bi.readInt(1);
            Assert.fail("passed: readInt(1)");
        } catch (IllegalArgumentException iae) {
            // expected
        }
        try {
            bi.readInt(33);
            Assert.fail("passed: readInt(33)");
        } catch (IllegalArgumentException iae) {
            // expected
        }
        try {
            bi.readInt(34);
            Assert.fail("passed: readInt(34)");
        } catch (IllegalArgumentException iae) {
            // expected
        }
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testReadFloat() throws IOException {

        final float expected = RANDOM.nextFloat();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final DataOutputStream dos = new DataOutputStream(baos);
        dos.writeFloat(expected);
        dos.flush();
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        final float actual = bi.readFloat();
        Assert.assertEquals(bi.align(1), 0);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testReadUnsignedLong() throws IOException {

        {
            final BitInput bi =
                new BitInput(new ByteArrayInputStream(new byte[0]));
            try {
                bi.readUnsignedLong(-1);
                Assert.fail("passed: readUnsignedLong(-1)");
            } catch (IllegalArgumentException iae) {
                // expected
            }
            try {
                bi.readUnsignedLong(0);
                Assert.fail("passed: readUnsignedLong(0)");
            } catch (IllegalArgumentException iae) {
                // expected
            }
            try {
                bi.readUnsignedLong(64);
                Assert.fail("passed: readUnsignedLong(64)");
            } catch (IllegalArgumentException iae) {
                // expected
            }
            try {
                bi.readUnsignedLong(65);
                Assert.fail("passed: readUnsignedLong(65)");
            } catch (IllegalArgumentException iae) {
                // expected
            }
        }

        for (int length = 1; length < 64; length++) {

            final long expected = RANDOM.nextLong() >>> (64 - length);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final DataOutputStream dos = new DataOutputStream(baos);
            dos.writeLong(expected << (64 - length));
            dos.flush();
            baos.flush();

            final ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            final BitInput bi = new BitInput(bais);
            final long actual = bi.readUnsignedLong(length);
            bi.align(8);

            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testReadLong() throws IOException {

        {
            final BitInput bi =
                new BitInput(new ByteArrayInputStream(new byte[0]));
            try {
                bi.readLong(0);
                Assert.fail("passed: readLong(0)");
            } catch (IllegalArgumentException iae) {
                // expected
            }
            try {
                bi.readLong(1);
                Assert.fail("passed: readLong(1)");
            } catch (IllegalArgumentException iae) {
                // expected
            }
            try {
                bi.readLong(65);
                Assert.fail("passed: readLong(65)");
            } catch (IllegalArgumentException iae) {
                // expected
            }
            try {
                bi.readLong(66);
                Assert.fail("passed: readLong(66)");
            } catch (IllegalArgumentException iae) {
                // expected
            }
        }

        for (int length = 2; length <= 64; length++) {

            final long expected = RANDOM.nextLong() >> (64 - length);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final DataOutputStream dos = new DataOutputStream(baos);
            dos.writeLong(expected << (64 - length));
            dos.flush();
            baos.flush();

            final ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            final BitInput bi = new BitInput(bais);
            final long actual = bi.readLong(length);
            bi.align(8);

            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testReadDouble() throws IOException {

        final double expected = RANDOM.nextDouble();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final DataOutputStream dos = new DataOutputStream(baos);
        dos.writeDouble(expected);
        dos.flush();
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final BitInput bi = new BitInput(bais);
        final double actual = bi.readDouble();
        Assert.assertEquals(bi.align(1), 0);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testChecksum() throws IOException {

        final BitInput bi = new BitInput(new ByteArrayInputStream(new byte[0]));
        try {
            bi.addChecksum(null);
            Assert.fail("passed: addChecksum(null)");
        } catch (NullPointerException npe) {
            // expected
        }

        testChecksum(new CRC32(), new CRC32());
        testChecksum(new Adler32(), new Adler32());
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testDigest() throws NoSuchAlgorithmException, IOException {

        final BitInput bi = new BitInput(new ByteArrayInputStream(new byte[0]));
        try {
            bi.addDigest(null);
            Assert.fail("passed: addDigest(null)");
        } catch (NullPointerException npe) {
            // expected
        }

        testDigest("MD2");
        testDigest("MD5");
        testDigest("SHA-1");
        testDigest("SHA-256");
        testDigest("SHA-384");
        testDigest("SHA-512");

    }


}

