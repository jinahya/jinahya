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


import java.io.DataOutput;
import java.io.IOException;
import java.util.Random;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Sample implements BitAccessible {


    private static final String NAME_ENCODING = "UTF-8";


    private static final int AGE_LENGTH = 7;


    private static final Random RANDOM = new Random();


    public static Sample newInstance(final String name, final int age) {
        return new Sample(name, age);
    }


    public Sample() {
        this("name", 1);
    }


    private Sample(final String name, final int age) {
        super();

        if (name == null) {
            throw new NullPointerException("null name");
        }
        if (name.trim().length() == 0) {
            throw new IllegalArgumentException("empty name");
        }

        if (age <= 0) {
            throw new IllegalArgumentException("age(" + age + ") <= 0");
        }
        if (age > Byte.MAX_VALUE) {
            throw new IllegalArgumentException(
                "age(" + age + ") > " + Byte.MAX_VALUE);
        }

        this.name = name;
        this.age = age;
    }


    @Override
    public void read(final BitInput input) throws IOException {

        name = new String(input.readBytes(), NAME_ENCODING);
        age = input.readUnsignedInt(AGE_LENGTH);
        married = input.readBoolean();
    }


    @Override
    public void write(final BitOutput output) throws IOException {

        output.writeBytes(name.getBytes(NAME_ENCODING));
        output.writeUnsignedInt(AGE_LENGTH, age);
        output.writeBoolean(married);
    }


    public void write(final DataOutput output) throws IOException {
        output.writeUTF(name);
        output.writeByte(age);
        output.writeBoolean(married);
    }


    public int getAge() {
        return age;
    }


    public String getName() {
        return name;
    }


    public boolean isMarried() {
        return married;
    }


    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Sample)) {
            return false;
        }

        final Sample sample = (Sample) obj;

        if (!(name == sample.name
              || (name != null && name.equals(sample.name)))) {
            return false;
        }

        if (age != sample.age) {
            return false;
        }

        if (married != sample.married) {
            return false;
        }

        return true;
    }


    @Override
    public int hashCode() {

        int result = 17;

        result = 37 * result + name.hashCode();

        result = 37 * result + age;

        result = 37 * result + (married ? 0 : 1);

        return result;
    }


    @Override
    public String toString() {
        return super.toString() + "/" + name + "(" + age + "/" + married + ")";
    }


    private String name;


    private int age;


    private boolean married = RANDOM.nextBoolean();


}

