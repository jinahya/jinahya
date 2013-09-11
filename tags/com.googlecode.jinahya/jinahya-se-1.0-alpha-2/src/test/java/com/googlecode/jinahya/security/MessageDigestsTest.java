/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


package com.googlecode.jinahya.security;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class MessageDigestsTest {


    public static MessageDigest newMessageDigestWithRandomAlgorithm()
        throws NoSuchAlgorithmException {

        return MessageDigest.getInstance(
            MessageDigests.SUPPORTED_ALGORITHMS
            .get(ThreadLocalRandom.current().nextInt(
            MessageDigests.SUPPORTED_ALGORITHMS.size())));
    }


    @Test(invocationCount = 32)
    public void test() throws NoSuchAlgorithmException, IOException {

        final Random random = ThreadLocalRandom.current();

        final byte[] data = new byte[random.nextInt(65536)];
        random.nextBytes(data);

        for (String algorithm : MessageDigests.SUPPORTED_ALGORITHMS) {

            final MessageDigest digest = MessageDigest.getInstance(algorithm);

            final byte[] hash1 = MessageDigests.digest(
                digest, new ByteArrayInputStream(data), new byte[17], -1L);

            final byte[] hash2 = MessageDigests.digest(
                digest, Channels.newChannel(new ByteArrayInputStream(data)),
                ByteBuffer.allocate(31), -1L);

            Assert.assertEquals(hash1, hash2);
        }

    }


}
