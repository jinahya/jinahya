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
import org.junit.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Test(singleThreaded = true)
public class BossVsEngineerTest1 {


    private static final Logger LOGGER =
        Logger.getLogger(BossVsEngineerTest1.class.getName());


    private static long getUsedMemory(final boolean free) {

        if (free) {
            System.gc();
            System.runFinalization();
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException ie) {
                ie.printStackTrace(System.err);
            }
        }
        return Runtime.getRuntime().totalMemory()
               - Runtime.getRuntime().freeMemory();
    }


    private static byte[][] newDecodedArray() {

        final byte[][] decodedArray =
            new byte[ThreadLocalRandom.current().nextInt(128)][];

        for (int i = 0; i < decodedArray.length; i++) {
            decodedArray[i] = HexCodecTestUtil.newDecodedBytes();
        }

        return decodedArray;
    }


    private static byte[][] newEncodedArray() {

        final byte[][] encodedArray =
            new byte[ThreadLocalRandom.current().nextInt(128)][];

        for (int i = 0; i < encodedArray.length; i++) {
            encodedArray[i] = HexCodecTestUtil.newEncodedBytes();
        }

        return encodedArray;
    }


    private static long encodeLikeABoss(final byte[][] decodedArray) {
        final long start = System.currentTimeMillis();
        for (byte[] decoded : decodedArray) {
            new HexEncoder().encodeLikeABoss(decoded);
        }
        return System.currentTimeMillis() - start;
    }


    private static long encodeLikeAnEngineer(final byte[][] decodedArray) {
        final long start = System.currentTimeMillis();
        for (byte[] decoded : decodedArray) {
            new HexEncoder().encode(decoded);
        }
        return System.currentTimeMillis() - start;
    }


    private static void testEncodeLikeAnEngineer(final byte[][] decodedArray,
                                                 final double[] elapsed,
                                                 final double[] increased,
                                                 final int offset) {

        final long before = getUsedMemory(true);
        elapsed[offset] = encodeLikeAnEngineer(decodedArray);
        increased[offset] = getUsedMemory(false) - before;
    }


    private static void testEncodeLikeABoss(final byte[][] decodedArray,
                                            final double[] elapsed,
                                            final double[] increased,
                                            final int offset) {

        final long before = getUsedMemory(true);
        elapsed[offset] = encodeLikeABoss(decodedArray);
        increased[offset] = getUsedMemory(false) - before;
    }


    private static long decodeLikeABoss(final byte[][] encodedArray) {
        final long start = System.currentTimeMillis();
        for (byte[] encoded : encodedArray) {
            new HexDecoder().decodeLikeABoss(encoded);
        }
        return System.currentTimeMillis() - start;
    }


    private static long decodeLikeAnEngineer(final byte[][] encodedArray) {
        final long start = System.currentTimeMillis();
        for (byte[] encoded : encodedArray) {
            new HexDecoder().decode(encoded);
        }
        return System.currentTimeMillis() - start;
    }


    private static void testDecodeLikeABoss(final byte[][] encodedArray,
                                            final double[] elapsed,
                                            final double[] increased,
                                            final int offset) {

        final long before = getUsedMemory(true);
        elapsed[offset] = decodeLikeABoss(encodedArray);
        increased[offset] = getUsedMemory(false) - before;
    }


    private static void testDecodeLikeAnEngineer(final byte[][] encodedArray,
                                                 final double[] elapsed,
                                                 final double[] increased,
                                                 final int offset) {

        final long before = getUsedMemory(true);
        elapsed[offset] = decodeLikeAnEngineer(encodedArray);
        increased[offset] = getUsedMemory(false) - before;
    }


    @Test
    public void testEncode() {

        final int count = 5;
        Assert.assertTrue(count > 4);

        final double[] elapsedLikeABoss = new double[count];
        final double[] increasedLikeABoss = new double[count];

        final double[] elapsedLikeAnEngineer = new double[count];
        final double[] increasedLikeAnEngineer = new double[count];

        for (int i = 0; i < count; i++) {
            final byte[][] decodedArray = newDecodedArray();
            if (ThreadLocalRandom.current().nextBoolean()) {
                testEncodeLikeABoss(decodedArray, elapsedLikeABoss,
                                    increasedLikeABoss, i);
                testEncodeLikeAnEngineer(decodedArray, elapsedLikeAnEngineer,
                                         increasedLikeAnEngineer, i);
            } else {
                testEncodeLikeAnEngineer(decodedArray, elapsedLikeAnEngineer,
                                         increasedLikeAnEngineer, i);
                testEncodeLikeABoss(decodedArray, elapsedLikeABoss,
                                    increasedLikeABoss, i);
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
            "increased.like.a.boss: {0} [{1}, {2}, ..., {3}, {4}]",
            new Object[]{StatUtils.mean(increasedLikeABoss),
                         increasedLikeABoss[0],
                         increasedLikeABoss[1],
                         increasedLikeABoss[increasedLikeABoss.length - 2],
                         increasedLikeABoss[increasedLikeABoss.length - 1]});

        LOGGER.log(
            Level.INFO,
            "elapsed.like.an.engineer: {0} [{1}, {2}, ..., {3}, {4}]",
            new Object[]{
                StatUtils.mean(elapsedLikeAnEngineer),
                elapsedLikeAnEngineer[0],
                elapsedLikeAnEngineer[1],
                elapsedLikeAnEngineer[elapsedLikeAnEngineer.length - 2],
                elapsedLikeAnEngineer[elapsedLikeAnEngineer.length - 1]});

        LOGGER.log(
            Level.INFO,
            "increased.like.an.engineer: {0} [{1}, {2}, ..., {3}, {4}]",
            new Object[]{
                StatUtils.mean(increasedLikeAnEngineer),
                increasedLikeAnEngineer[0],
                increasedLikeAnEngineer[1],
                increasedLikeAnEngineer[increasedLikeAnEngineer.length - 2],
                increasedLikeAnEngineer[increasedLikeAnEngineer.length - 1]});
    }


    @Test
    public void testDecode() {

        final int count = 5;
        Assert.assertTrue(count > 4);

        final double[] elapsedLikeABoss = new double[count];
        final double[] increasedLikeABoss = new double[count];

        final double[] elapsedLikeAnEngineer = new double[count];
        final double[] increasedLikeAnEngineer = new double[count];

        for (int i = 0; i < count; i++) {
            final byte[][] encodedArray = newEncodedArray();
            if (ThreadLocalRandom.current().nextBoolean()) {
                testDecodeLikeABoss(encodedArray, elapsedLikeABoss,
                                    increasedLikeABoss, i);
                testDecodeLikeAnEngineer(encodedArray, elapsedLikeAnEngineer,
                                         increasedLikeAnEngineer, i);
            } else {
                testDecodeLikeAnEngineer(encodedArray, elapsedLikeAnEngineer,
                                         increasedLikeAnEngineer, i);
                testDecodeLikeABoss(encodedArray, elapsedLikeABoss,
                                    increasedLikeABoss, i);
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
            "increased.like.a.boss: {0} [{1}, {2}, ..., {3}, {4}]",
            new Object[]{StatUtils.mean(increasedLikeABoss),
                         increasedLikeABoss[0],
                         increasedLikeABoss[1],
                         increasedLikeABoss[increasedLikeABoss.length - 2],
                         increasedLikeABoss[increasedLikeABoss.length - 1]});

        LOGGER.log(
            Level.INFO,
            "elapsed.like.an.engineer: {0} [{1}, {2}, ..., {3}, {4}]",
            new Object[]{
                StatUtils.mean(elapsedLikeAnEngineer),
                elapsedLikeAnEngineer[0],
                elapsedLikeAnEngineer[1],
                elapsedLikeAnEngineer[elapsedLikeAnEngineer.length - 2],
                elapsedLikeAnEngineer[elapsedLikeAnEngineer.length - 1]});

        LOGGER.log(
            Level.INFO,
            "increased.like.an.engineer: {0} [{1}, {2}, ..., {3}, {4}]",
            new Object[]{
                StatUtils.mean(increasedLikeAnEngineer),
                increasedLikeAnEngineer[0],
                increasedLikeAnEngineer[1],
                increasedLikeAnEngineer[increasedLikeAnEngineer.length - 2],
                increasedLikeAnEngineer[increasedLikeAnEngineer.length - 1]});
    }


}

