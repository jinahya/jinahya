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
import java.io.DataInputStream;
import java.io.IOException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitOutputTest extends BitIOTest {


    @Test(expectedExceptions = {NullPointerException.class})
    public void testConstructorWithNullStream() {

        final BitOutput bo = new BitOutput(null);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testWriteUnsignedByte() throws IOException {

        for (int length = 1; length <= 8; length++) {

            final int expected = RANDOM.nextInt() >>> (32 - length);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final BitOutput bo = new BitOutput(baos);
            bo.writeUnsignedByte(length, expected);
            bo.align(1);
            baos.flush();

            final ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            final DataInputStream dis = new DataInputStream(bais);

            final int actual = dis.readUnsignedByte() >> (8 - length);

            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testWriteUnsignedShort() throws IOException {

        for (int length = 2; length <= 16; length++) {

            final int expected = RANDOM.nextInt() >>> (32 - length);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final BitOutput bo = new BitOutput(baos);
            bo.writeUnsignedShort(length, expected);
            bo.align(2);
            baos.flush();

            final ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            final DataInputStream dis = new DataInputStream(bais);

            final int actual = dis.readUnsignedShort() >> (16 - length);

            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testWriteUnsignedInt() throws IOException {

        for (int length = 1; length < 32; length++) {

            final int expected = RANDOM.nextInt() >>> (32 - length);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final BitOutput bo = new BitOutput(baos);
            bo.writeUnsignedInt(length, expected);
            bo.align(4);
            baos.flush();

            final ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            final DataInputStream dis = new DataInputStream(bais);

            final int actual = dis.readInt() >>> (32 - length);

            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testWriteInt() throws IOException {

        for (int length = 2; length <= 32; length++) {

            final int expected = RANDOM.nextInt() >> (32 - length);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final BitOutput bo = new BitOutput(baos);
            bo.writeInt(length, expected);
            bo.align(4);
            baos.flush();

            final ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            final DataInputStream dis = new DataInputStream(bais);

            final int actual = dis.readInt() >> (32 - length);

            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testWriteFloat() throws IOException {

        final float expected = RANDOM.nextFloat();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        bo.writeFloat(expected);
        Assert.assertEquals(bo.align(1), 0);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final DataInputStream dis = new DataInputStream(bais);

        final float actual = dis.readFloat();

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testWriteUnsignedLong() throws IOException {

        for (int length = 1; length < 64; length++) {

            final long expected = RANDOM.nextLong() >>> (64 - length);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final BitOutput bo = new BitOutput(baos);
            bo.writeUnsignedLong(length, expected);
            bo.align(8);
            baos.flush();

            final ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            final DataInputStream dis = new DataInputStream(bais);

            final long actual = dis.readLong() >>> (64 - length);

            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testWriteLong() throws IOException {

        for (int length = 2; length <= 64; length++) {

            final long expected = RANDOM.nextLong() >> (64 - length);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final BitOutput bo = new BitOutput(baos);
            bo.writeLong(length, expected);
            bo.align(8);
            baos.flush();

            final ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            final DataInputStream dis = new DataInputStream(bais);

            final long actual = dis.readLong() >> (64 - length);

            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testWriteDouble() throws IOException {

        final double expected = RANDOM.nextFloat();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        bo.writeDouble(expected);
        Assert.assertEquals(bo.align(1), 0);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final DataInputStream dis = new DataInputStream(bais);

        final double actual = dis.readDouble();

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testWriteUTF() throws IOException {

        final String expected = newString(false);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        bo.writeUTF(expected);
        bo.align(1);
        baos.flush();

        final ByteArrayInputStream bais =
            new ByteArrayInputStream(baos.toByteArray());
        final DataInputStream dis = new DataInputStream(bais);

        final String actual = dis.readUTF();

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testChecksum() throws IOException {

        final byte[] bytes = newBytes(false);

        final ByteArrayOutputStream baos =
            new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        final Checksum ac = new CRC32();
        bo.addChecksum(ac);

        for (int i = 0; i < bytes.length; i++) {
            bo.writeUnsignedInt(Byte.SIZE, bytes[i] & 0xFF);
        }

        final Checksum ec = new CRC32();
        ec.update(bytes, 0, bytes.length);

        final long actual = ac.getValue();
        final long expected = ec.getValue();

        Assert.assertEquals(actual, expected);

        Assert.assertTrue(bo.removeChecksum(ac));
    }


    @Test(invocationCount = INVOCATION_COUNT)
    public void testDigest() throws NoSuchAlgorithmException, IOException {

        testDigest("MD2");
        testDigest("MD5");
        testDigest("SHA-1");
        testDigest("SHA-256");
        testDigest("SHA-384");
        testDigest("SHA-512");

    }


    private void testDigest(final String algorithm)
        throws NoSuchAlgorithmException, IOException {

        final byte[] bytes = newBytes(false);

        final ByteArrayOutputStream baos =
            new ByteArrayOutputStream();
        final BitOutput bo = new BitOutput(baos);
        final MessageDigest ad = MessageDigest.getInstance(algorithm);
        bo.addDigest(ad);

        for (int i = 0; i < bytes.length; i++) {
            bo.writeUnsignedInt(Byte.SIZE, bytes[i] & 0xFF);
        }

        final MessageDigest ed = MessageDigest.getInstance(algorithm);
        ed.update(bytes);

        final byte[] actual = ad.digest();
        final byte[] expected = ed.digest();

        Assert.assertEquals(actual, expected);

        Assert.assertTrue(bo.removeDigest(ad));
    }


}

