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
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
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
     * generated.
     */
    private static final long serialVersionUID = 4243525203653288446L;


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(MappedMorton.class.getName());


    static {
        LOGGER.setLevel(Level.OFF);
    }


    /**
     * The minimum value of {@code density}.
     */
    public static final int DENSITY_MIN = 1;


    /**
     * The maximum value of {@code density}.
     */
    public static final int DENSITY_MAX = 26;


    /**
     * The default value fo {@code density}.
     */
    protected static final int MAPPED_DENSITY = 16;


    /**
     * The minimum length of {@code sodium}.
     */
    protected static final int SODIUM_SIZE_MIN = 8; // = 64 / 8


    /**
     * The maximum length of {@code sodium}.
     */
    protected static final int SODIUM_SIZE_MAX = 64; // = 512 / 8


    /**
     * The default length of {@code sodium}.
     */
    protected static final int MAPPED_SODIUM_LENGTH = 32; // = 256 / 8


    /**
     * Does {@code PBKDF2}.
     *
     * @param password password
     * @param salt salt
     * @param iterationCount iteration count
     * @param keyLength key length (in bits).
     *
     * @return generated output.
     *
     * @see <a href="http://goo.gl/uqsdd">PBEKeySpec(char[] password, byte[]
     * salt, int iterationCount, int keyLength)</a>
     */
    protected static byte[] pbkdf2(final char[] password, final byte[] salt,
                                   final int iterationCount,
                                   final int keyLength) {

        try {
            final SecretKeyFactory secretKeyFactory =
                SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            final KeySpec keySpec = new PBEKeySpec(
                password, salt, iterationCount, keyLength);
            try {
                final SecretKey secretKey =
                    secretKeyFactory.generateSecret(keySpec);
                return secretKey.getEncoded();
            } catch (InvalidKeySpecException ikse) {
                throw new RuntimeException(ikse);
            }
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(nsae);
        }
    }


    /**
     * Converts given {@code bassword} into a {@code char[]}.
     *
     * @param bassword the byte array
     *
     * @return the char array
     */
    protected static char[] cassword(final byte[] bassword) {

        final char[] cassword = new char[bassword.length];

        for (int i = 0; i < cassword.length; i++) {
            cassword[i] = (char) (bassword[i] & 0xFF);
        }

        return cassword;
    }


    /**
     * Generates a {@code sodium}.
     *
     * @param length number of bytes
     *
     * @return a new sodium.
     */
    protected static byte[] sodium(final int length) {

        final byte[] sodium = new byte[length];

        try {
            SecureRandom.getInstance("SHA1PRNG").nextBytes(sodium);
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(nsae);
        }

        return sodium;
    }


    /**
     * Creates a new instance.
     *
     * @param density density
     * @param sodium sodium
     */
    protected MappedMorton(final int density, final byte[] sodium) {
        super();

        this.density = density;
        this.sodium = Arrays.copyOf(sodium, sodium.length);
    }


    /**
     * Creates a new instance.
     */
    public MappedMorton() {
        this(MAPPED_DENSITY, sodium(MAPPED_SODIUM_LENGTH));
    }


    /**
     * Makes given {@code bland} salty.
     *
     * @param bland the bland input; must be not null nor empty.
     *
     * @return the salty output
     */
    public byte[] salty(final byte[] bland) {

        if (bland == null) {
            throw new NullPointerException("null bland");
        }

        if (bland.length == 0) {
            throw new IllegalArgumentException("bland.length == 0");
        }

        final char[] password = cassword(bland);

        final int degree = 0x01 << density;
        final int iterationCount =
            (new BigInteger(bland).intValue() & (degree - 1)) | degree;
        LOGGER.log(Level.INFO, "iterationCount: {0}", iterationCount);

        final byte[] salty = pbkdf2(password, sodium, iterationCount,
                                    sodium.length * 8);

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
    private int density;


    /**
     * sodium.
     */
    @Basic(optional = false)
    @Column(name = "SODIUM", nullable = false, updatable = false)
    @NotNull
    @Size(min = SODIUM_SIZE_MIN, max = SODIUM_SIZE_MAX)
    @XmlTransient
    private byte[] sodium;


}

