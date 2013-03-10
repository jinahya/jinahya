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


import java.io.IOException;
import java.util.Random;
import org.testng.Assert;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Entity {


    public Entity read(final BitInput input) throws IOException {

        b = input.readBoolean();
        f = input.readFloat();
        d = input.readDouble();

        unsignedInts = new int[31];
        for (int i = 0; i < unsignedInts.length; i++) {
            unsignedInts[i] = input.readUnsignedInt(i + 1);
        }

        signedInts = new int[31];
        for (int i = 0; i < signedInts.length; i++) {
            signedInts[i] = input.readInt(i + 2);
        }

        unsignedLongs = new long[63];
        for (int i = 0; i < unsignedLongs.length; i++) {
            unsignedLongs[i] = input.readUnsignedLong(i + 1);
        }

        signedLongs = new long[63];
        for (int i = 0; i < signedLongs.length; i++) {
            signedLongs[i] = input.readLong(i + 2);
        }

        return this;
    }


    public Entity write(final Random random, final BitOutput output)
        throws IOException {

        b = random.nextBoolean();
        output.writeBoolean(b);

        f = random.nextFloat();
        output.writeFloat(f);

        d = random.nextDouble();
        output.writeDouble(d);

        unsignedInts = new int[31]; // 1 ~ 31
        for (int i = 0; i < unsignedInts.length; i++) {
            unsignedInts[i] = random.nextInt() >>> (32 - i - 1); // 31 ~ 1
            Assert.assertEquals(unsignedInts[i] >> (i + 1), 0x00);
            output.writeUnsignedInt(i + 1, unsignedInts[i]);
        }

        signedInts = new int[31]; // 2 ~ 32
        for (int i = 0; i < signedInts.length; i++) {
            signedInts[i] = random.nextInt() >> (32 - i - 2); // 30 ~ 0
            if (i < 30) {
                Assert.assertTrue((signedInts[i] >> (i + 2)) == 0x00
                                  || (signedInts[i] >> (i + 2)) == -1);
            }
            output.writeInt(i + 2, signedInts[i]);
        }

        unsignedLongs = new long[63];
        for (int i = 0; i < unsignedLongs.length; i++) {
            unsignedLongs[i] = random.nextLong() >>> (64 - i - 1); // 63 ~ 1
            Assert.assertEquals(unsignedLongs[i] >> (i + 1), 0x00L);
            output.writeUnsignedLong(i + 1, unsignedLongs[i]);
        }

        signedLongs = new long[63];
        for (int i = 0; i < signedLongs.length; i++) {
            signedLongs[i] = random.nextLong() >> (63 - i - 2); // 62 ~ 0
            if (i < 62) {
                Assert.assertTrue((signedLongs[i] >> (i + 2)) == 0x00L
                                  || (signedLongs[i] >> (i + 2)) == -1L);
            }
            output.writeLong(i + 2, signedLongs[i]);
        }

        return this;
    }


    private boolean b;


    private float f;


    private double d;


    private int[] unsignedInts;


    private int[] signedInts;


    private long[] unsignedLongs;


    private long[] signedLongs;


}

