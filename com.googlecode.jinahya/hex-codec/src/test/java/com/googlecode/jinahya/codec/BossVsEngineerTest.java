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


package com.googlecode.jinahya.codec;


import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.stat.StatUtils;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Test(singleThreaded = true)
public class BossVsEngineerTest {


    private static final int ROUNDS = 128;


    static {
        if (ROUNDS < 5) {
            throw new InstantiationError("ROUNDS(" + ROUNDS + ") < 5");
        }
    }


    private static final Logger LOGGER =
        Logger.getLogger(BossVsEngineerTest.class.getName());


    private static long encodeLikeABoss(final byte[][] multipleDecoded) {
        final long start = System.currentTimeMillis();
        for (byte[] decoded : multipleDecoded) {
            new HexEncoder().encodeLikeABoss(decoded);
        }
        return System.currentTimeMillis() - start;
    }


    private static long encodeLikeAnEngineer(final byte[][] multipleDecoded) {
        final long start = System.currentTimeMillis();
        for (byte[] decoded : multipleDecoded) {
            new HexEncoder().encode(decoded);
        }
        return System.currentTimeMillis() - start;
    }


    private static long decodeLikeABoss(final byte[][] multipleEncoded) {
        final long start = System.currentTimeMillis();
        for (byte[] encoded : multipleEncoded) {
            new HexDecoder().decodeLikeABoss(encoded);
        }
        return System.currentTimeMillis() - start;
    }


    private static long decodeLikeAnEngineer(final byte[][] multipleEncoded) {
        final long start = System.currentTimeMillis();
        for (byte[] encoded : multipleEncoded) {
            new HexDecoder().decode(encoded);
        }
        return System.currentTimeMillis() - start;
    }


    @Test
    public void testEncode() {

        final double[] elapsedLikeABoss = new double[ROUNDS];
        final double[] elapsedLikeAnEngineer = new double[ROUNDS];

        for (int i = 0; i < ROUNDS; i++) {
            final byte[][] decodedArray =
                HexCodecTestUtil.newMultipleDecodedBytes();
            if (ThreadLocalRandom.current().nextBoolean()) {
                elapsedLikeABoss[i] = encodeLikeABoss(decodedArray);
                elapsedLikeAnEngineer[i] = encodeLikeAnEngineer(decodedArray);
            } else {
                elapsedLikeAnEngineer[i] = encodeLikeAnEngineer(decodedArray);
                elapsedLikeABoss[i] = encodeLikeABoss(decodedArray);
            }
        }

        LOGGER.log(Level.INFO,
                   "elapsed.like.a.boss: {0} [{1}, {2}, ..., {3}, {4}]",
                   new Object[]{StatUtils.mean(elapsedLikeABoss),
                                elapsedLikeABoss[0],
                                elapsedLikeABoss[1],
                                elapsedLikeABoss[elapsedLikeABoss.length - 2],
                                elapsedLikeABoss[elapsedLikeABoss.length - 1]});

        LOGGER.log(
            Level.INFO,
            "elapsed.like.an.engineer: {0} [{1}, {2}, ..., {3}, {4}]",
            new Object[]{
                StatUtils.mean(elapsedLikeAnEngineer),
                elapsedLikeAnEngineer[0],
                elapsedLikeAnEngineer[1],
                elapsedLikeAnEngineer[elapsedLikeAnEngineer.length - 2],
                elapsedLikeAnEngineer[elapsedLikeAnEngineer.length - 1]});
    }


    @Test
    public void testDecode() {

        final double[] elapsedLikeABoss = new double[ROUNDS];
        final double[] elapsedLikeAnEngineer = new double[ROUNDS];

        for (int i = 0; i < ROUNDS; i++) {
            final byte[][] encodedArray =
                HexCodecTestUtil.newMultipleEncodedBytes();
            if (ThreadLocalRandom.current().nextBoolean()) {
                elapsedLikeABoss[i] = encodeLikeABoss(encodedArray);
                elapsedLikeAnEngineer[i] = encodeLikeAnEngineer(encodedArray);
            } else {
                elapsedLikeAnEngineer[i] = encodeLikeAnEngineer(encodedArray);
                elapsedLikeABoss[i] = encodeLikeABoss(encodedArray);
            }
        }

        LOGGER.log(Level.INFO,
                   "elapsed.like.a.boss: {0} [{1}, {2}, ..., {3}, {4}]",
                   new Object[]{StatUtils.mean(elapsedLikeABoss),
                                elapsedLikeABoss[0],
                                elapsedLikeABoss[1],
                                elapsedLikeABoss[elapsedLikeABoss.length - 2],
                                elapsedLikeABoss[elapsedLikeABoss.length - 1]});

        LOGGER.log(
            Level.INFO,
            "elapsed.like.an.engineer: {0} [{1}, {2}, ..., {3}, {4}]",
            new Object[]{
                StatUtils.mean(elapsedLikeAnEngineer),
                elapsedLikeAnEngineer[0],
                elapsedLikeAnEngineer[1],
                elapsedLikeAnEngineer[elapsedLikeAnEngineer.length - 2],
                elapsedLikeAnEngineer[elapsedLikeAnEngineer.length - 1]});
    }


}

