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


package com.googlecode.jinahya.stackoverflow.q11887278;


import java.util.Random;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Specifier {


    public static Specifier newInstance(final Random random) {

        final Specifier instance = new Specifier();

        if (random.nextBoolean()) {
            instance.value1 = "value1";
        }

        if (random.nextBoolean()) {
            instance.value2 = "value2";
        }

        if (random.nextBoolean()) {
            instance.value3 = "value3";
        }

        return instance;
    }


    @Override
    public String toString() {
        return value1 + "," + value2 + "," + value3;
    }


    public void fromString(final String string) {
        final String[] split = string.split(",");
        try {
            value1 = String.valueOf(split[0]);
            if ("null".equals(value1)) {
                value1 = null;
            }
            value2 = String.valueOf(split[1]);
            if ("null".equals(value2)) {
                value2 = null;
            }
            value3 = String.valueOf(split[2]);
            if ("null".equals(value3)) {
                value3 = null;
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
        }
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.value1 != null ? this.value1.hashCode() : 0);
        hash = 59 * hash + (this.value2 != null ? this.value2.hashCode() : 0);
        hash = 59 * hash + (this.value3 != null ? this.value3.hashCode() : 0);
        return hash;
    }


    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Specifier other = (Specifier) obj;
        if ((this.value1 == null) ? (other.value1 != null)
            : !this.value1.equals(other.value1)) {
            return false;
        }
        if ((this.value2 == null) ? (other.value2 != null)
            : !this.value2.equals(other.value2)) {
            return false;
        }
        if ((this.value3 == null) ? (other.value3 != null)
            : !this.value3.equals(other.value3)) {
            return false;
        }
        return true;
    }


    public String value1;


    public String value2;


    public String value3;


}

