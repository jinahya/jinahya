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


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.stat.StatUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Test(groups = {"benchmark"}, singleThreaded = true)
public class BossVsEngineerTestNanoTimeEncode {


    private static final int ROUNDS = 1024;


    static {
        if (ROUNDS < 7) {
            throw new InstantiationError("ROUNDS(" + ROUNDS + ") < 7");
        }
    }


    private static final Logger LOGGER =
        Logger.getLogger(BossVsEngineerTestNanoTimeEncode.class.getName());


    @BeforeClass
    private static void warmUp() throws IOException {
        LOGGER.info("warmUp()");

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final long freeMemory = Runtime.getRuntime().freeMemory();
        LOGGER.log(Level.INFO, "Runtime.freeMemory: {0}", freeMemory);

        for (int i = 0; i < 16; i++) {
            baos.reset();
            while (baos.size() < 1048576L) {
                baos.write(Tests.decodedBytes());
            }
            final byte[] decoded = baos.toByteArray();
            new HexEncoder().encodeLikeABoss(decoded);
            new HexEncoder().encodeLikeAnEngineer(decoded);
        }

        for (int i = 0; i < 16; i++) {
            baos.reset();
            while (baos.size() < 1048576L) {
                baos.write(Tests.encodedBytes());
            }
            final byte[] encoded = baos.toByteArray();
            new HexDecoder().decodeLikeABoss(encoded);
            new HexDecoder().decodeLikeAnEngineer(encoded);
        }

        for (int i = 0; i < 128; i++) {
            encodeLikeABoss(Tests.decodedBytes());
            encodeLikeAnEngineer(Tests.decodedBytes());
        }
    }


    @AfterClass
    private static void coolDown() {
        LOGGER.info("coolDown()");
    }


    private static long encodeLikeABoss(final byte[] decoded) {
        final long start = System.nanoTime();
        new HexEncoder().encodeLikeABoss(decoded);
        return System.nanoTime() - start;
    }


    private static long encodeLikeAnEngineer(final byte[] decoded) {
        final long start = System.nanoTime();
        new HexEncoder().encodeLikeAnEngineer(decoded);
        return System.nanoTime() - start;
    }


    @Test
    public void testEncode() {

        final double[] elapsedLikeABoss = new double[ROUNDS];
        final double[] elapsedLikeAnEngineer = new double[ROUNDS];

        for (int i = 0; i < ROUNDS; i++) {
            final byte[] decoded = Tests.decodedBytes();
            if (ThreadLocalRandom.current().nextBoolean()) {
                elapsedLikeABoss[i] = encodeLikeABoss(decoded);
                elapsedLikeAnEngineer[i] = encodeLikeAnEngineer(decoded);
            } else {
                elapsedLikeAnEngineer[i] = encodeLikeAnEngineer(decoded);
                elapsedLikeABoss[i] = encodeLikeABoss(decoded);
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


}

