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


import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.XmlTransient;


/**
 * <figure>
 * <img src="http://www.mortonsalt.com/content/images/logo.png?v=1.1"/>
 * <figcaption>
 * <a href="http://www.mortonsalt.com/">"When It Rains It Pours&reg;".</a>
 * </figcaption>
 * </figure>
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@MappedSuperclass
@XmlTransient
public class MappedMorton implements Serializable {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(MappedMorton.class.getName());


    static {
        LOGGER.setLevel(Level.OFF);
    }


    private static final int DENSITY_MIN = 1;


    private static final int DENSITY_MAX = 26;


    private static final int DENSITY = 16;


    static final int WORD_LENGTH = 0x40; // 64 = 512 / 8;


    static final int CODE_LENGTH = WORD_LENGTH << 1; // 128 = 64 * 2


    private static final int SODIUM_LENGTH = CODE_LENGTH;


    private static final int SODIUM_LENGTH_IN_BYTES = SODIUM_LENGTH >> 1;


    private static final int SODIUM_SIZE_MIN = SODIUM_LENGTH;


    private static final int SODIUM_SIZE_MAX = SODIUM_LENGTH;


    protected static final int BLAND_LENGTH = WORD_LENGTH;


    private static final int BLAND_LENGTH_IN_BITS = BLAND_LENGTH << 3;


    private static final int SALTY_LENGTH = WORD_LENGTH;


    private static final int SALTY_LENGTH_IN_BITS = SALTY_LENGTH << 3;


    /**
     * Prints {@code parsed} as hex.
     *
     * @param parsed the bytes to print
     *
     * @return printed hex string.
     */
    static String printHex(final byte[] parsed) {

        Objects.requireNonNull(parsed, "null parsed");

        return DatatypeConverter.printHexBinary(parsed);
    }


    /**
     * Parses {@code printed} as hex.
     *
     * @param printed the string to parse.
     *
     * @return the parsed binary.
     */
    static byte[] parseHex(final String printed) {

        Objects.requireNonNull(printed, "null printed");

        return DatatypeConverter.parseHexBinary(printed);
    }


    public static byte[] sha512(byte[] word, final int iter) {

        final MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(nsae);
        }

        for (int i = 0; i < iter; i++) {
            word = digest.digest(word);
        }

        return word;
    }


    protected static byte[] pbkdf2(final char[] password, final byte[] salt,
                                   final int iterationCount,
                                   final int KeyLength) {

        try {
            final SecretKeyFactory secretKeyFactory =
                SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            final KeySpec keySpec = new PBEKeySpec(
                password, salt, iterationCount, KeyLength);
            final SecretKey secretKey =
                secretKeyFactory.generateSecret(keySpec);
            return secretKey.getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }


    private static byte[] pbkdf2(final char[] password, final byte[] salt,
                                 final int iterationCount) {

        return pbkdf2(password, salt, iterationCount, SALTY_LENGTH_IN_BITS);
    }


    private static byte[] pbkdf2(final byte[] word, final byte[] salt,
                                 final int iterationCount) {

        if (word == null) {
            throw new IllegalArgumentException("null word");
        }

        if (salt == null) {
            throw new IllegalArgumentException("null salt");
        }

        final char[] password = new char[word.length];
        for (int i = 0; i < password.length; i++) {
            password[i] = (char) (word[i] & 0xFF);
        }

        return pbkdf2(password, salt, iterationCount);
    }


    static String code(final MappedMorton salt, final byte[] word) {

        if (salt == null) {
            throw new IllegalArgumentException("null salt");
        }

        if (word == null) {
            throw new IllegalArgumentException("null word");
        }

        if (word.length != WORD_LENGTH) {
            throw new IllegalArgumentException(
                "word.length(" + word.length + ") != " + WORD_LENGTH);
        }

        return printHex(salt.salty(word));
    }


    public static String code(final byte[] word) {

        if (word == null) {
            throw new IllegalArgumentException("null word");
        }

        if (word.length != WORD_LENGTH) {
            throw new IllegalArgumentException(
                "word.length(" + word.length + ") != " + WORD_LENGTH);
        }

        final MappedMorton salt = new MappedMorton();
        salt.density = 1;
        salt.sodium = printHex(word);

        return code(salt, word);
    }


    public static void main(final String[] args) {

        if (args.length != 2) {
            return;
        }

        final long start = System.currentTimeMillis();

        final byte[] user = sha512(
            args[0].getBytes(StandardCharsets.UTF_8),
            Shadow.NAME_ITERATION_COUNT + Shadow.WORD_ITERATION_COUNT);
        final byte[] pass = sha512(
            args[1].getBytes(StandardCharsets.UTF_8),
            Shadow.NAME_ITERATION_COUNT + Shadow.WORD_ITERATION_COUNT);
        final MappedMorton salt = new MappedMorton();
        System.out.println("salt: " + salt.sodium);
        System.out.println("user: " + MappedMorton.code(user));
        System.out.println("pass: " + printHex(salt.salty(pass)));

        final long finish = System.currentTimeMillis();
        System.out.println("elap: " + (finish - start) + "ms");
    }


    public MappedMorton() {
        super();

        final byte[] rand = new byte[SODIUM_LENGTH_IN_BYTES];
        try {
            SecureRandom.getInstance("SHA1PRNG").nextBytes(rand);
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(nsae);
        }

        sodium = printHex(rand);
    }


    // -------------------------------------------------------------- CREATED_AT
    public Date getCreatedAt() {
        return (Date) createdAt.clone();
    }


    /**
     * Makes given {@code bland} salty.
     *
     * @param bland the bland input
     *
     * @return the salty output
     */
    public byte[] salty(final byte[] bland) {

        if (bland == null) {
            throw new NullPointerException("null bland");
        }

        if (bland.length != BLAND_LENGTH) {
            throw new IllegalArgumentException(
                "bland.length(" + bland.length + " != " + BLAND_LENGTH);
        }

        final int degree = 0x01 << density;
        final int iterationCount =
            (new BigInteger(bland).intValue() & (degree - 1)) | degree;

        final byte[] salty = pbkdf2(bland, parseHex(sodium), iterationCount);

        return salty;
    }


    /**
     * density.
     */
    @Basic(optional = false)
    @Column(name = "DENSITY", nullable = false, updatable = false)
    @Min(DENSITY_MIN)
    @Max(DENSITY_MAX)
    @XmlTransient
    private int density = DENSITY;


    /**
     * salt.
     */
    @Basic(optional = false)
    @Column(length = SODIUM_LENGTH, name = "SODIUM", nullable = false,
            updatable = false)
    @NotNull
    @Size(min = SODIUM_SIZE_MIN, max = SODIUM_SIZE_MAX)
    @XmlTransient
    private String sodium;


}

