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


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class RandomEntity {


    public RandomEntity() {
        super();

        final Random random = ThreadLocalRandom.current();

        booleanCount = random.nextInt(128);
        booleanValues = new boolean[booleanCount];
        for (int i = 0; i < booleanValues.length; i++) {
            booleanValues[i] = random.nextBoolean();
        }

        unsignedIntCount = random.nextInt(128);
        unsignedIntLengths = new int[unsignedIntCount];
        unsignedIntValues = new int[unsignedIntLengths.length];
        for (int i = 0; i < unsignedIntValues.length; i++) {
            unsignedIntLengths[i] = Generator.newLengthIntUnsigned();
            unsignedIntValues[i] =
                Generator.newValueIntUnsigned(unsignedIntLengths[i]);
        }

        signedIntCount = random.nextInt(128);
        signedIntLengths = new int[signedIntCount];
        signedIntValues = new int[signedIntLengths.length];
        for (int i = 0; i < signedIntValues.length; i++) {
            signedIntLengths[i] = Generator.newLengthInt();
            signedIntValues[i] = Generator.newValueInt(signedIntLengths[i]);
        }

        unsignedLongCount = random.nextInt(128);
        unsignedLongLengths = new int[unsignedLongCount];
        unsignedLongValues = new long[unsignedLongLengths.length];
        for (int i = 0; i < unsignedLongValues.length; i++) {
            unsignedLongLengths[i] = Generator.newLengthLongUnsigned();
            unsignedLongValues[i] =
                Generator.newValueLongUnsigned(unsignedLongLengths[i]);
        }

        signedLongCount = random.nextInt(128);
        signedLongLengths = new int[signedLongCount];
        signedLongValues = new long[signedLongLengths.length];
        for (int i = 0; i < signedLongValues.length; i++) {
//            signedLongLengths[i] = Generator.newLengthLong();
//            signedLongValues[i] = Generator.newValueLong(signedLongLengths[i]);
        }
    }


    public RandomEntity read(final BitInput input) throws IOException {

        return this;
    }


    public RandomEntity write(final BitOutput output) throws IOException {


        return this;
    }


    public RandomEntity read(final DataInput input) throws IOException {

        return this;
    }


    public RandomEntity write(final DataOutput output) throws IOException {


        return this;
    }


    private int booleanCount;


    private boolean[] booleanValues;


    private int unsignedIntCount;


    private int[] unsignedIntLengths;


    private int[] unsignedIntValues;


    private int signedIntCount;


    private int[] signedIntLengths;


    private int[] signedIntValues;


    private int unsignedLongCount;


    private int[] unsignedLongLengths;


    private long[] unsignedLongValues;


    private int signedLongCount;


    private int[] signedLongLengths;


    private long[] signedLongValues;


}

