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


import java.util.concurrent.ThreadLocalRandom;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
class Generator {


    static int unsignedIntLength() {
        return ThreadLocalRandom.current().nextInt(31) + 1; // 1 - 31
    }


    static int unsignedIntValue(final int length) {
        return ThreadLocalRandom.current().nextInt()
               >>> (Integer.SIZE - length);
    }


    static int intLength() {
        return ThreadLocalRandom.current().nextInt(31) + 2; // 2 - 32
    }


    static int intValue(final int length) {
        return ThreadLocalRandom.current().nextInt() >> (Integer.SIZE - length);
    }


    static int unsignedLongLength() {
        return ThreadLocalRandom.current().nextInt(63) + 1; // 1 - 63
    }


    static long unsignedLongValue(final int length) {
        return ThreadLocalRandom.current().nextLong()
               >>> (Long.SIZE - length);
    }


    static int longLength() {
        return ThreadLocalRandom.current().nextInt(63) + 2; // 2 - 64
    }


    static long longValue(final int length) {
        return ThreadLocalRandom.current().nextLong() >> (Long.SIZE - length);
    }


    private Generator() {
        super();
    }


}

