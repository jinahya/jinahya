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


import java.util.ArrayList;
import java.util.List;
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
        if (ROUNDS < 7) {
            throw new InstantiationError("ROUNDS(" + ROUNDS + ") < 7");
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
            new HexEncoder().encodeLikeAnEngineer(decoded);
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
            new HexDecoder().decodeLikeAnEngineer(encoded);
        }
        return System.currentTimeMillis() - start;
    }


    @Test
    public void testEncode() {

        final double[] elapsedLikeABoss = new double[ROUNDS];
        final double[] elapsedLikeAnEngineer = new double[ROUNDS];

        for (int i = 0; i < ROUNDS; i++) {
            final byte[][] multipleDecodedBytes =
                HexCodecTestUtil.newMultipleDecodedBytes();
            if (ThreadLocalRandom.current().nextBoolean()) {
                elapsedLikeABoss[i] = encodeLikeABoss(multipleDecodedBytes);
                elapsedLikeAnEngineer[i] =
                    encodeLikeAnEngineer(multipleDecodedBytes);
            } else {
                elapsedLikeAnEngineer[i] =
                    encodeLikeAnEngineer(multipleDecodedBytes);
                elapsedLikeABoss[i] = encodeLikeABoss(multipleDecodedBytes);
            }
        }

        LOGGER.log(
            Level.INFO,
            "elapsed.like.a.boss: {0} [{1}, {2}, {3}, ..., {4}, {5}, {6}]",
            new Object[]{StatUtils.mean(elapsedLikeABoss, 1,
                                        elapsedLikeABoss.length - 1),
                         elapsedLikeABoss[0],
                         elapsedLikeABoss[1],
                         elapsedLikeABoss[2],
                         elapsedLikeABoss[elapsedLikeABoss.length - 3],
                         elapsedLikeABoss[elapsedLikeABoss.length - 2],
                         elapsedLikeABoss[elapsedLikeABoss.length - 1]});

        LOGGER.log(
            Level.INFO,
            "elapsed.like.an.engineer: {0} [{1}, {2}, {3}, ..., {4}, {5}, {6}]",
            new Object[]{
                StatUtils.mean(elapsedLikeAnEngineer, 1,
                               elapsedLikeAnEngineer.length - 1),
                elapsedLikeAnEngineer[0],
                elapsedLikeAnEngineer[1],
                elapsedLikeAnEngineer[2],
                elapsedLikeAnEngineer[elapsedLikeAnEngineer.length - 3],
                elapsedLikeAnEngineer[elapsedLikeAnEngineer.length - 2],
                elapsedLikeAnEngineer[elapsedLikeAnEngineer.length - 1]});
    }


    @Test
    public void testDecode() {

        final double[] elapsedLikeABoss = new double[ROUNDS];
        final double[] elapsedLikeAnEngineer = new double[ROUNDS];

        for (int i = 0; i < ROUNDS; i++) {
            final byte[][] multipleEncodedBytes =
                HexCodecTestUtil.newMultipleEncodedBytes();
            if (ThreadLocalRandom.current().nextBoolean()) {
                elapsedLikeABoss[i] = encodeLikeABoss(multipleEncodedBytes);
                elapsedLikeAnEngineer[i] =
                    encodeLikeAnEngineer(multipleEncodedBytes);
            } else {
                elapsedLikeAnEngineer[i] =
                    encodeLikeAnEngineer(multipleEncodedBytes);
                elapsedLikeABoss[i] = encodeLikeABoss(multipleEncodedBytes);
            }
        }

        LOGGER.log(
            Level.INFO,
            "elapsed.like.a.boss: {0} [{1}, {2}, {3}, ..., {4}, {5}, {6}]",
            new Object[]{
                StatUtils.mean(elapsedLikeABoss, 1,
                               elapsedLikeABoss.length - 1),
                elapsedLikeABoss[0],
                elapsedLikeABoss[1],
                elapsedLikeABoss[2],
                elapsedLikeABoss[elapsedLikeABoss.length - 3],
                elapsedLikeABoss[elapsedLikeABoss.length - 2],
                elapsedLikeABoss[elapsedLikeABoss.length - 1]});

        LOGGER.log(
            Level.INFO,
            "elapsed.like.an.engineer: {0} [{1}, {2}, {3}, ..., {4}, {5}, {6}]",
            new Object[]{
                StatUtils.mean(elapsedLikeAnEngineer, 1,
                               elapsedLikeAnEngineer.length - 1),
                elapsedLikeAnEngineer[0],
                elapsedLikeAnEngineer[1],
                elapsedLikeAnEngineer[2],
                elapsedLikeAnEngineer[elapsedLikeAnEngineer.length - 3],
                elapsedLikeAnEngineer[elapsedLikeAnEngineer.length - 2],
                elapsedLikeAnEngineer[elapsedLikeAnEngineer.length - 1]});
    }


}

