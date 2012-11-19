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


import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
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
public class BossVsEngineerTestCpuTime {


    private static final int ROUNDS = 1024;


    static {
        if (ROUNDS < 7) {
            throw new InstantiationError("ROUNDS(" + ROUNDS + ") < 7");
        }
    }


    private static final Logger LOGGER =
        Logger.getLogger(BossVsEngineerTestCpuTime.class.getName());


    private static long encodeLikeABoss(final byte[][] multipleDecoded) {
        final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        final long start = threadMXBean.getCurrentThreadCpuTime();
        for (byte[] decoded : multipleDecoded) {
            new HexEncoder().encodeLikeABoss(decoded);
        }
        return threadMXBean.getCurrentThreadCpuTime() - start;
    }


    private static long encodeLikeAnEngineer(final byte[][] multipleDecoded) {
        final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        final long start = threadMXBean.getCurrentThreadCpuTime();
        for (byte[] decoded : multipleDecoded) {
            new HexEncoder().encodeLikeAnEngineer(decoded);
        }
        return threadMXBean.getCurrentThreadCpuTime() - start;
    }


    private static long decodeLikeABoss(final byte[][] multipleEncoded) {
        final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        final long start = threadMXBean.getCurrentThreadCpuTime();
        for (byte[] encoded : multipleEncoded) {
            new HexDecoder().decodeLikeABoss(encoded);
        }
        return threadMXBean.getCurrentThreadCpuTime() - start;
    }


    private static long decodeLikeAnEngineer(final byte[][] multipleEncoded) {
        final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        final long start = threadMXBean.getCurrentThreadCpuTime();
        for (byte[] encoded : multipleEncoded) {
            new HexDecoder().decodeLikeAnEngineer(encoded);
        }
        return threadMXBean.getCurrentThreadCpuTime() - start;
    }


    @Test
    public void testEncode() {

        final double[] consumedLikeABoss = new double[ROUNDS];
        final double[] consumedLikeAnEngineer = new double[ROUNDS];

        for (int i = 0; i < ROUNDS; i++) {
            final byte[][] multipleDecodedBytes =
                HexCodecTestUtil.newMultipleDecodedBytes();
            if (ThreadLocalRandom.current().nextBoolean()) {
                consumedLikeABoss[i] = encodeLikeABoss(multipleDecodedBytes);
                consumedLikeAnEngineer[i] =
                    encodeLikeAnEngineer(multipleDecodedBytes);
            } else {
                consumedLikeAnEngineer[i] =
                    encodeLikeAnEngineer(multipleDecodedBytes);
                consumedLikeABoss[i] = encodeLikeABoss(multipleDecodedBytes);
            }
        }

        LOGGER.log(
            Level.INFO,
            "consumed.like.a.boss: {0} [{1}, {2}, {3}, ..., {4}, {5}, {6}]",
            new Object[]{StatUtils.mean(consumedLikeABoss),
                         consumedLikeABoss[0],
                         consumedLikeABoss[1],
                         consumedLikeABoss[2],
                         consumedLikeABoss[consumedLikeABoss.length - 3],
                         consumedLikeABoss[consumedLikeABoss.length - 2],
                         consumedLikeABoss[consumedLikeABoss.length - 1]});

        LOGGER.log(
            Level.INFO,
            "consumed.like.an.engineer: {0}"
            + " [{1}, {2}, {3}, ..., {4}, {5}, {6}]",
            new Object[]{
                StatUtils.mean(consumedLikeAnEngineer),
                consumedLikeAnEngineer[0],
                consumedLikeAnEngineer[1],
                consumedLikeAnEngineer[2],
                consumedLikeAnEngineer[consumedLikeAnEngineer.length - 3],
                consumedLikeAnEngineer[consumedLikeAnEngineer.length - 2],
                consumedLikeAnEngineer[consumedLikeAnEngineer.length - 1]});
    }


    @Test
    public void testDecode() {

        final double[] consumedLikeABoss = new double[ROUNDS];
        final double[] consumedLikeAnEngineer = new double[ROUNDS];

        for (int i = 0; i < ROUNDS; i++) {
            final byte[][] multipleEncodedBytes =
                HexCodecTestUtil.newMultipleEncodedBytes();
            if (ThreadLocalRandom.current().nextBoolean()) {
                consumedLikeABoss[i] = encodeLikeABoss(multipleEncodedBytes);
                consumedLikeAnEngineer[i] =
                    encodeLikeAnEngineer(multipleEncodedBytes);
            } else {
                consumedLikeAnEngineer[i] =
                    encodeLikeAnEngineer(multipleEncodedBytes);
                consumedLikeABoss[i] = encodeLikeABoss(multipleEncodedBytes);
            }
        }

        LOGGER.log(
            Level.INFO,
            "consumed.like.a.boss: {0} [{1}, {2}, {3}, ..., {4}, {5}, {6}]",
            new Object[]{
                StatUtils.mean(consumedLikeABoss),
                consumedLikeABoss[0],
                consumedLikeABoss[1],
                consumedLikeABoss[2],
                consumedLikeABoss[consumedLikeABoss.length - 3],
                consumedLikeABoss[consumedLikeABoss.length - 2],
                consumedLikeABoss[consumedLikeABoss.length - 1]});

        LOGGER.log(
            Level.INFO,
            "consumed.like.an.engineer: {0}"
            + " [{1}, {2}, {3}, ..., {4}, {5}, {6}]",
            new Object[]{
                StatUtils.mean(consumedLikeAnEngineer),
                consumedLikeAnEngineer[0],
                consumedLikeAnEngineer[1],
                consumedLikeAnEngineer[2],
                consumedLikeAnEngineer[consumedLikeAnEngineer.length - 3],
                consumedLikeAnEngineer[consumedLikeAnEngineer.length - 2],
                consumedLikeAnEngineer[consumedLikeAnEngineer.length - 1]});
    }


}

