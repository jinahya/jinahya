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


    private static final long serialVersionUID = 4243525203653288446L;


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(MappedMorton.class.getName());


    static {
        LOGGER.setLevel(Level.OFF);
    }


    protected static final int DENSITY_MIN = 1;


    protected static final int DENSITY_MAX = 26;


    private static final int DENSITY = 16;


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


    public MappedMorton() {
        super();

        final byte[] rand = new byte[8];
        try {
            SecureRandom.getInstance("SHA1PRNG").nextBytes(rand);
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(nsae);
        }

        sodium = DatatypeConverter.printHexBinary(rand);
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

        if (bland.length == 0) {
            throw new IllegalArgumentException("bland.length == 0");
        }

        final char[] password = new char[bland.length];
        for (int i = 0; i < password.length; i++) {
            password[i] = (char) (bland[i] & 0xFF);
        }

        final byte[] salt = DatatypeConverter.parseHexBinary(sodium);

        final int degree = 0x01 << density;
        final int iterationCount =
            (new BigInteger(bland).intValue() & (degree - 1)) | degree;

        final byte[] salty = pbkdf2(password, salt, iterationCount, 160);

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
     * sodium; salt.
     */
    @Basic(optional = false)
    @Column(length = 16, name = "SODIUM", nullable = false, updatable = false)
    @NotNull
    @Size(min = 16, max = 16)
    @XmlTransient
    private String sodium;


}

