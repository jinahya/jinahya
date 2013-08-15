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


package com.googlecode.jinahya.crypto;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class CiphersTest {


    private static final Logger LOGGER =
        Logger.getLogger(CiphersTest.class.getName());


    private static Key newKey(final String algorithm, final int keySize)
        throws NoSuchAlgorithmException {

        final KeyGenerator keygen = KeyGenerator.getInstance(algorithm);
        keygen.init(keySize);

        return keygen.generateKey();
    }


    private static AlgorithmParameterSpec newIv(final int blockSize) {

        if (blockSize == 0) {
            return null;
        }

        final byte[] iv = new byte[blockSize];
        ThreadLocalRandom.current().nextBytes(iv);

        return new IvParameterSpec(iv);
    }


    private static AlgorithmParameterSpec newIv(final Cipher cipher) {

        return newIv(cipher.getBlockSize());
    }


    private static void test1(final String transformation, final int keySize,
                              final boolean requiresIv, final boolean noPadding)
        throws NoSuchAlgorithmException, NoSuchPaddingException,
               InvalidKeyException, InvalidAlgorithmParameterException,
               IOException, IllegalBlockSizeException, BadPaddingException {

        LOGGER.log(Level.INFO, "test1(transformation: {0}, keySize: {1}, "
                               + "requiresIv: {2}, noPadding: {3})",
                   new Object[]{transformation, keySize, requiresIv,
                                noPadding});

        final Random random = ThreadLocalRandom.current();

        final String algorithm =
            transformation.substring(0, transformation.indexOf('/'));
        final Key key = newKey(algorithm, keySize);
        final Cipher cipher = Cipher.getInstance(transformation);
        final AlgorithmParameterSpec iv = requiresIv ? newIv(cipher) : null;

        final byte[] expected =
            noPadding
            ? new byte[cipher.getBlockSize() * random.nextInt(512)]
            : new byte[random.nextInt(65536)];
        random.nextBytes(expected);

        {
            final byte[] encrypted;
            final byte[] actual;
            {
                cipher.init(Cipher.ENCRYPT_MODE, key, iv);
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Ciphers.doFinal(cipher, new ByteArrayInputStream(expected),
                                baos, new byte[102]);
                encrypted = baos.toByteArray();
            }
            {
                cipher.init(Cipher.DECRYPT_MODE, key, iv);
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Ciphers.doFinal(cipher, new ByteArrayInputStream(encrypted),
                                baos, new byte[983]);
                actual = baos.toByteArray();
            }
            Assert.assertEquals(actual, expected);
        }

        {
            final byte[] encrypted;
            final byte[] actual;
            {
                cipher.init(Cipher.ENCRYPT_MODE, key, iv);
                final ReadableByteChannel input =
                    Channels.newChannel(new ByteArrayInputStream(expected));
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final WritableByteChannel output = Channels.newChannel(baos);
                Ciphers.doFinal(cipher, input, output, ByteBuffer.allocate(71));
                baos.flush();
                encrypted = baos.toByteArray();
            }
            {
                cipher.init(Cipher.DECRYPT_MODE, key, iv);
                final ReadableByteChannel input =
                    Channels.newChannel(new ByteArrayInputStream(encrypted));
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final WritableByteChannel output = Channels.newChannel(baos);
                Ciphers.doFinal(cipher, input, output, ByteBuffer.allocate(29));
                actual = baos.toByteArray();
            }
            Assert.assertEquals(actual, expected);
        }
    }


    @Test(invocationCount = 32)
    public void AES_CBC_NoPadding_128() throws Exception {

        test1("AES/CBC/NoPadding", 128, true, true);
    }


    @Test(invocationCount = 32)
    public void AES_CBC_PKCS5Padding_128() throws Exception {

        test1("AES/CBC/PKCS5Padding", 128, true, false);
    }


    @Test(invocationCount = 32)
    public void AES_ECB_NoPadding_128() throws Exception {

        test1("AES/ECB/NoPadding", 128, false, true);
    }


    @Test(invocationCount = 32)
    public void AES_ECB_PKCS5Padding_128() throws Exception {

        test1("AES/ECB/PKCS5Padding", 128, false, false);
    }


    // --------------------------------------------------------------------- DES
    @Test(invocationCount = 32)
    public void DES_CBC_NoPadding_56() throws Exception {

        test1("DES/CBC/NoPadding", 56, true, true);
    }


    @Test(invocationCount = 32)
    public void DES_CBC_PKCS5Padding_56() throws Exception {

        test1("DES/CBC/PKCS5Padding", 56, true, false);
    }


    @Test(invocationCount = 32)
    public void DES_ECB_NoPadding_56() throws Exception {

        test1("DES/ECB/NoPadding", 56, false, true);
    }


    @Test(invocationCount = 32)
    public void DES_ECB_PKCS5Padding_56() throws Exception {

        test1("DES/ECB/PKCS5Padding", 56, false, false);
    }


    // ------------------------------------------------------------------ DESede
    @Test(invocationCount = 32)
    public void DESede_CBC_NoPadding_168() throws Exception {

        test1("DESede/CBC/NoPadding", 168, true, true);
    }


    @Test(invocationCount = 32)
    public void DESede_CBC_PKCS5Padding_168() throws Exception {

        test1("DESede/CBC/PKCS5Padding", 168, true, false);
    }


    @Test(invocationCount = 32)
    public void DESede_ECB_NoPadding_168() throws Exception {

        test1("DESede/ECB/NoPadding", 168, false, true);
    }


    @Test(invocationCount = 32)
    public void DESede_ECB_PKCS5Padding_168() throws Exception {

        test1("DESede/ECB/PKCS5Padding", 168, false, false);
    }


    // --------------------------------------------------------------------- RSA
    public void RSA_ECB_PKCS1Padding_1024() throws Exception {

        test1("RSA/ECB/PKCS1Padding", 1024, false, false);
    }


    public void RSA_ECB_PKCS1Padding_2048() throws Exception {

        test1("RSA/ECB/PKCS1Padding", 2048, false, false);
    }


    public void RSA_ECB_OAEPWithSHA_1AndMGF1Padding_1024() throws Exception {

        test1("RSA/ECB/OAEPWithSHA-1AndMGF1Padding", 1024, false, false);
    }


    public void RSA_ECB_OAEPWithSHA_1AndMGF1Padding_2048() throws Exception {

        test1("RSA/ECB/OAEPWithSHA-1AndMGF1Padding", 2048, false, false);
    }


    public void RSA_ECB_OAEPWithSHA_256AndMGF1Padding_1024() throws Exception {

        test1("RSA/ECB/OAEPWithSHA-256AndMGF1Padding", 1024, false, false);
    }


    public void RSA_ECB_OAEPWithSHA_256AndMGF1Padding_2048() throws Exception {

        test1("RSA/ECB/OAEPWithSHA-256AndMGF1Padding", 2048, false, false);
    }


}

