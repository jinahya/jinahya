/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
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
import java.io.ObjectOutput;

import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Sample {


    private static final String NAME_ENCODING = "UTF-8";


    private static final int AGE_LENGTH = 7;


    private static final Random RANDOM = new Random();


    public static Sample newInstance() {

        final Sample sample = new Sample();

        sample.boolean_ = RANDOM.nextBoolean();
        if (RANDOM.nextBoolean()) {
            sample.BOOLEAN_ = RANDOM.nextBoolean();
        }

        sample.int_ = RANDOM.nextInt();
        if (RANDOM.nextBoolean()) {
            sample.INTEGER_ = RANDOM.nextInt();
        }

        sample.FLOAT_ = RANDOM.nextFloat();
        if (RANDOM.nextBoolean()) {
            sample.FLOAT_ = RANDOM.nextFloat();
        }

        sample.long_ = RANDOM.nextLong();
        if (RANDOM.nextBoolean()) {
            sample.LONG_ = RANDOM.nextLong();
        }

        sample.double_ = RANDOM.nextDouble();
        if (RANDOM.nextBoolean()) {
            sample.DOUBLE_ = RANDOM.nextDouble();
        }

        sample.ascii_ = RandomStringUtils.randomAscii(RANDOM.nextInt(1024));
        if (RANDOM.nextBoolean()) {
            sample.ASCII_ = RandomStringUtils.randomAscii(RANDOM.nextInt(1024));
        }

        sample.string_ = RandomStringUtils.random(RANDOM.nextInt(1024));
        if (RANDOM.nextBoolean()) {
            sample.STRING_ = RandomStringUtils.random(RANDOM.nextInt(1024));
        }

        sample.bytes_ = new byte[RANDOM.nextInt(1024)];
        RANDOM.nextBytes(sample.bytes_);
        if (RANDOM.nextBoolean()) {
            sample.BYTES_ = new byte[RANDOM.nextInt(1024)];
            RANDOM.nextBytes(sample.BYTES_);
        }

        return sample;
    }


    public void write(final BitOutput bo) throws IOException {

        bo.writeBoolean(boolean_);
        bo.writeBOOLEAN(BOOLEAN_);

        bo.writeInt(32, int_);
        bo.writeINTEGER(32, INTEGER_);

        bo.writeFloat(float_);
        bo.writeFLOAT(FLOAT_);

        bo.writeLong(64, long_);
        bo.writeLONG(64, LONG_);

        bo.writeDouble(double_);
        bo.writeDOUBLE(DOUBLE_);

        bo.writeASCII(ascii_);
        bo.writeASCII(ASCII_);

        bo.writeSTRING(string_, "UTF-8");
        bo.writeSTRING(STRING_, "UTF-8");

        bo.writeBytes(bytes_);
        bo.writeBYTES(BYTES_);
    }


    public void write(final ObjectOutput oo) throws IOException {

        oo.writeBoolean(boolean_);
        oo.writeObject(BOOLEAN_);

        oo.writeInt(int_);
        oo.writeObject(INTEGER_);

        oo.writeFloat(float_);
        oo.writeObject(FLOAT_);

        oo.writeLong(long_);
        oo.writeObject(LONG_);

        oo.writeDouble(double_);
        oo.writeObject(DOUBLE_);

        oo.writeUTF(ascii_);
        oo.writeObject(ASCII_);

        oo.writeUTF(string_);
        oo.writeObject(STRING_);

        oo.write(bytes_);
        oo.writeObject(BYTES_);
    }


    public boolean boolean_;


    public Boolean BOOLEAN_;


    public int int_;


    public Integer INTEGER_;


    public float float_;


    public Float FLOAT_;


    public long long_;


    public Long LONG_;


    public double double_;


    public Double DOUBLE_;


    public String ascii_;


    public String ASCII_;


    public String string_;


    public String STRING_;


    public byte[] bytes_;


    public byte[] BYTES_;


}

