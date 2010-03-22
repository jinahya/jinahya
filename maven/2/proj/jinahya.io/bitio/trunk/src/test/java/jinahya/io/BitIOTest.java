/*
 *  Copyright 2010 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package jinahya.io;


import java.io.IOException;

import static org.junit.Assert.*;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitIOTest extends AbstractTest {


    //@org.junit.Ignore
    @org.junit.Test
    public void testBoolean() throws IOException {
        for (int i = 0; i < COUNT; i++) {
            _testBoolean();
        }
    }


    @org.testng.annotations.Test(invocationCount = 1024)
    public void _testBoolean() throws IOException {
        final boolean expected = RANDOM.nextBoolean();

        output.writeBoolean(expected);

        alignAndFlush();

        assertEquals(expected, input.readBoolean());
        input.align();
    }


    //@org.junit.Ignore
    @org.junit.Test
    public void testBytes() throws IOException {

        final byte[] expected = new byte[RANDOM.nextInt(65536)];
        final byte[] actual = new byte[expected.length];

        for (int i = 0; i < COUNT; i++) {
            RANDOM.nextBytes(expected);

            output.writeBytes(expected);
            input.readBytes(actual);
            assertArrayEquals(expected, actual);

            output.writeBytes(expected);
            dis.readFully(actual);
            assertArrayEquals(expected, actual);

            dos.write(expected);
            input.readBytes(actual);
            assertArrayEquals(expected, actual);
        }
    }


    @org.junit.Test
    public void testFloat() throws IOException {
        for (int i = 0; i < COUNT; i++) {
            final float expected = RANDOM.nextFloat();

            output.writeFloat(expected);
            assertEquals(expected, input.readFloat(), .0f);

            output.writeFloat(expected);
            assertEquals(expected, dis.readFloat(), .0f);

            dos.writeFloat(expected);
            assertEquals(expected, input.readFloat(), .0f);
        }
    }


    @org.junit.Test
    public void testDouble() throws IOException {
        for (int i = 0; i < COUNT; i++) {
            final double expected = RANDOM.nextDouble();

            output.writeDouble(expected);
            assertEquals(expected, input.readDouble(), .0d);

            output.writeDouble(expected);
            assertEquals(expected, dis.readDouble(), .0d);

            dos.writeDouble(expected);
            assertEquals(expected, input.readDouble(), .0d);
        }
    }


    //@org.junit.Ignore
    @org.junit.Test
    public void testInt() throws IOException {

        for (int i = 0; i < COUNT; i++) {

            int expected = RANDOM.nextInt();

            final int length = RANDOM.nextInt(32) + 1; // 1 - 32

            if (length == 1) { // unsigned int
                expected &= 0x01;

                output.writeUnsignedInt(length, expected);
                alignAndFlush(); // 8
                assertEquals(expected, input.readUnsignedInt(length));
                input.align();

            } else if (length == 32) { // signed int
                dos.writeInt(expected);
                dos.flush();
                assertEquals(expected, input.readInt());

                output.writeInt(expected);
                alignAndFlush();
                assertEquals(expected, input.readInt());

                output.writeInt(expected);
                alignAndFlush();
                assertEquals(expected, dis.readInt());

            } else {
                final int upper = 32 - length;
                expected <<= upper;
                expected >>= upper;

                if (expected >= 0) { // unsigned
                    output.writeUnsignedInt(length, expected);
                    alignAndFlush();
                    assertEquals(expected, input.readUnsignedInt(length));
                    input.align();

                } else { // sigend
                    output.writeInt(length, expected);
                    alignAndFlush();
                    assertEquals(expected, input.readInt(length));
                    input.align();
                }
            }
        }
    }


    //@org.junit.Ignore
    @org.junit.Test
    public void testLong() throws IOException {

        for (int i = 0; i < COUNT; i++) {

            long expected = RANDOM.nextLong();

            final int length = RANDOM.nextInt(64) + 1; // 1 - 64

            if (length == 1) { // unsigned long
                expected &= 0x01L;

                output.writeUnsignedLong(length, expected);
                alignAndFlush(); // 8
                assertEquals(expected, input.readUnsignedLong(length));
                input.align();

            } else if (length == 64) { // signed long
                dos.writeLong(expected);
                dos.flush();
                assertEquals(expected, input.readLong());

                output.writeLong(expected);
                alignAndFlush();
                assertEquals(expected, input.readLong());

            } else {
                final int upper = 64 - length;
                expected <<= upper;
                expected >>= upper;

                if (expected >= 0) { // unsigned
                    output.writeUnsignedLong(length, expected);
                    alignAndFlush();
                    assertEquals(expected, input.readUnsignedLong(length));
                    input.align();
                } else { // sigend
                    output.writeLong(length, expected);
                    alignAndFlush();
                    assertEquals(expected, input.readLong(length));
                    input.align();
                }
            }
        }
    }


    //@org.junit.Ignore
    @org.junit.Test
    public void testModifiedUTF8String() throws IOException {
        for (int i = 0; i < COUNT; i++) {
            final String expected = generateModifiedUTF8String();

            output.writeModifiedUTF8String(expected);
            alignAndFlush();
            assertEquals(expected, input.readModifiedUTF8String());

            output.writeModifiedUTF8String(expected);
            alignAndFlush();
            assertEquals(expected, dis.readUTF());

            dos.writeUTF(expected);
            alignAndFlush();
            assertEquals(expected, input.readModifiedUTF8String());
        }
    }


    @org.junit.Test
    public void testUSASCIIBytes() throws IOException {

        for (int i = 0; i < COUNT; i++) {
            byte[] expected = generateUSASCIIBytes();

            output.writeUSASCIIBytes(expected);
            alignAndFlush();

            assertArrayEquals(expected, input.readUSASCIIBytes());
            input.align();
        }
    }


    @org.junit.Test
    public void testUSASCIIString() throws IOException {

        for (int i = 0; i < COUNT; i++) {

            final String expected = generateUSASCIIString();

            output.writeUSASCIIString(expected);
            alignAndFlush();

            assertEquals(expected, input.readUSASCIIString());
            input.align();
        }
    }
}
