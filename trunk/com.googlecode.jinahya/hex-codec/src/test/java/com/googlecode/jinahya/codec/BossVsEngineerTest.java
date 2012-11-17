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
public class BossVsEngineerTest {


    private static final Logger LOGGER =
        Logger.getLogger(BossVsEngineerTest.class.getName());


    private static byte[][] newDecodedArray() {

        final byte[][] decodedArray =
            new byte[ThreadLocalRandom.current().nextInt(1024)][];

        for (int i = 0; i < decodedArray.length; i++) {
            decodedArray[i] = HexCodecTestUtil.newDecodedBytes();
        }

        return decodedArray;
    }


    private static byte[][] newEncodedArray() {

        final byte[][] encodedArray =
            new byte[ThreadLocalRandom.current().nextInt(1024)][];

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


    private static void free() {
        System.gc();
        System.gc();
        System.gc();
        System.gc();
        System.gc();
        System.runFinalization();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException ie) {
            ie.printStackTrace(System.err);
        }
    }


    @Test
    public void testEncode() {

        final int count = 128;
        Assert.assertTrue(count > 4);

        final double[] elapsedLikeABoss = new double[count];
        final double[] elapsedLikeAnEngineer = new double[count];

        for (int i = 0; i < count; i++) {
//            free();
            final byte[][] decodedArray = newDecodedArray();
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

        final int count = 128;
        Assert.assertTrue(count > 4);

        final double[] elapsedLikeABoss = new double[count];

        final double[] elapsedLikeAnEngineer = new double[count];

        for (int i = 0; i < count; i++) {
//            free();
            final byte[][] encodedArray = newEncodedArray();
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

