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
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class MappedMortonTest {


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
        "baseball"
    };


    @Test(invocationCount = 1)
    public void testSaltyWithPasswords() {

        for (String password : PASSWORDS) {

            final MappedMorton morton = new MappedMorton();

            final byte[] bland = password.getBytes(StandardCharsets.UTF_8);
            final byte[] salty = morton.salty(bland);

            System.out.println("bland: " + password);
            System.out.println(
                "salty: " + DatatypeConverter.printHexBinary(salty));
        }
    }


    @Test(invocationCount = 128)
    public void testSaltyWithRandomBytes() {

        final Random random = new Random();

        final byte[] bland = new byte[random.nextInt(1024)];
        random.nextBytes(bland);

        final MappedMorton morton = new MappedMorton();

        final byte[] salty = morton.salty(bland);

//        System.out.println("bland: " + DatatypeConverter.printHexBinary(bland));
//        System.out.println("salty: " + DatatypeConverter.printHexBinary(salty));
    }


}

