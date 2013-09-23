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


package com.googlecode.jinahya.persistence;


import java.nio.charset.StandardCharsets;
import java.util.Random;
import javax.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class MortonTest {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(MortonTest.class);


    private static final String[] PASSWORDS = new String[]{
        "password",
        "123456",
        "12345678",
        "abc123",
        "qwerty",
        "monkey",
        "letmein",
        "dragon",
        "111111",
        "baseball",
        "사랑"
    };


    @Test(invocationCount = 1)
    public static void testSaltyWithPasswords() {

        final byte[][] basswords = new byte[PASSWORDS.length][];
        for (int i = 0; i < basswords.length; i++) {
            basswords[i] = PASSWORDS[i].getBytes(StandardCharsets.UTF_16);
        }

        final char[][] casswords = new char[basswords.length][];
        for (int i = 0; i < casswords.length; i++) {
            casswords[i] = MappedMorton.cassword(basswords[i]);
        }

        for (String password : PASSWORDS) {

            final Morton morton = new Morton();

            final byte[] bland = password.getBytes(StandardCharsets.UTF_8);
            final byte[] salty = morton.salty(bland);

            LOGGER.debug("bland: {}", password);
            LOGGER.debug("salty: {}", DatatypeConverter.printHexBinary(salty));
        }
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public void testSaltyWithNullBland() {

        new Morton().salty(null);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testSaltyWithEmptyBland() {

        new Morton().salty(new byte[0]);
    }


    @Test(invocationCount = 32)
    public void testSaltyWithRandomBland() {

        final Random random = new Random();

        final byte[] bland = new byte[random.nextInt(128) + 1];
        random.nextBytes(bland);

        final Morton morton = new Morton();

        final long start = System.nanoTime();
        final byte[] salty = morton.salty(bland);
        final long finish = System.nanoTime();
        LOGGER.debug("elapsed: {} ns", (finish - start));
    }


}
