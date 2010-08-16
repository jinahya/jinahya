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


import java.io.DataOutput;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.testng.Assert;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class AbstractTest {


    protected static final int INVOCATION_COUNT = 128;

    protected static final int BYTE_ARRAY_LENGTH = 128;

    protected static final Random RANDOM = new Random();


    protected static byte[] bytesValue() {
        final byte[] bytes = new byte[RANDOM.nextInt(BYTE_ARRAY_LENGTH)];
        RANDOM.nextBytes(bytes);
        return bytes;
    }


    //protected static final int TYPE_BOOLEAN = 0x01;

    //protected static final int TYPE_BYTES = TYPE_BOOLEAN << 1;

    //protected static final int TYPE_UNSIGNED_INT = TYPE_BYTES << 1;

    //protected static final int TYPE_SIGNED_INT = TYPE_UNSIGNED_INT << 1;

    //protected static final int TYPE_FLOAT = TYPE_SIGNED_INT << 1;

    //protected static final int TYPE_UNSIGNED_LONG = TYPE_FLOAT << 1;

    //protected static final int TYPE_SIGNED_LONG = TYPE_UNSIGNED_LONG << 1;

    //protected static final int TYPE_DOUBLE = TYPE_SIGNED_LONG << 1;



    protected static int unsignedIntLength() {
        return RANDOM.nextInt(31) + 1; // 1 - 31
    }


    protected static int signedIntLength() {
        return RANDOM.nextInt(31) + 2; // 2 - 32
    }


    protected static int unsignedLongLength() {
        return RANDOM.nextInt(63) + 1; // 1 - 63
    }


    protected static int signedLongLength() {
        return RANDOM.nextInt(63) + 2; // 2 - 64
    }


    protected static int unsignedIntValue(final int length) {

        if (length < 1) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " < 1");
        }

        if (length >= 32) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " >= 32");
        }

        final int shift = 32 - length;
        final int value = (RANDOM.nextInt() << shift) >>> shift;

        return value;
    }


    protected static int signedIntValue(final int length) {

        if (length <= 1) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " <= 1");
        }

        if (length > 32) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " > 32");
        }

        final int shift = 32 - length;
        final int value = (RANDOM.nextInt() << shift) >> shift;

        return value;
    }


    protected static long unsignedLongValue(final int length) {

        if (length < 1) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " < 1");
        }

        if (length >= 64) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " >= 64");
        }

        final int shift = 64 - length;
        final long value = (RANDOM.nextLong() << shift) >>> shift;

        return value;
    }


    protected static long signedLongValue(final int length) {

        if (length <= 1) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " <= 1");
        }

        if (length > 64) {
            throw new IllegalArgumentException(
                "illegal length: " + length + " > 64");
        }

        final int shift = 64 - length;
        final long value = (RANDOM.nextLong() << shift) >> shift;

        return value;
    }
}
