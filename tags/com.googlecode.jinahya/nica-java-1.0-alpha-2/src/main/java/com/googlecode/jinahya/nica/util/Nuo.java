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


package com.googlecode.jinahya.nica.util;


import java.util.Random;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Nuo {


//    /**
//     * RNG algorithm.
//     */
//    private static final String ALGORITHM = "SHA1PRNG";
    /**
     * Random.
     */
    private static final Random RANDOM = new Random();


    /**
     * nonce random bits.
     */
    private static final int RANDOM_BIT = 20;


    /**
     * nonce random max.
     */
    private static final int RANDOM_MAX = 1 << RANDOM_BIT;


    /**
     * Generates a value.
     *
     * @return a new value
     */
    public static long generate() {

        return generate(System.currentTimeMillis());
    }


    /**
     * Generates a value.
     *
     * @param timestamp current timestamp
     *
     * @return a new value
     */
    public static long generate(final long timestamp) {

        synchronized (RANDOM) {
            return generate(timestamp, RANDOM);
        }
    }


    /**
     * Generates a value
     *
     * @param random random
     * @return a new value
     */
    public static long generate(final Random random) {

        return generate(System.currentTimeMillis(), random);
    }


    /**
     * Generates a value.
     *
     * @param timestamp current timestamp
     * @param random random
     *
     * @return a new value
     */
    public static long generate(final long timestamp, final Random random) {

        if (random == null) {
            throw new IllegalArgumentException("null random");
        }

        return (timestamp << RANDOM_BIT) | random.nextInt(RANDOM_MAX);
    }


    /**
     * Creates a new instance.
     */
    protected Nuo() {
        super();
    }


}

