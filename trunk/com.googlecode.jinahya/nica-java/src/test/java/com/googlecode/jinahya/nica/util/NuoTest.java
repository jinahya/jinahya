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


package com.googlecode.jinahya.nica.util;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Test(enabled = false)
public class NuoTest {


    private static final Logger LOGGER =
        Logger.getLogger(NuoTest.class.getName());


    private static final int COUNT = 1000;


    private static long average(final List<Integer> conflicts) {

        if (conflicts.isEmpty()) {
            return 0L;
        }

        long sum = 0L;

        for (Integer conflict : conflicts) {
            sum += conflict;
        }

        final long avg = sum / conflicts.size();

        return avg;
    }


    @Test
    public static void testGenerate() {

        final List<Integer> conflicts = new ArrayList<>(COUNT);

        for (int i = 0; i < COUNT; i++) {
            final Set<Long> values = new HashSet<>(COUNT);
            for (int j = 0; true; j++) {
                if (!values.add(Nuo.generate())) {
                    conflicts.add(j);
                    break;
                }
            }
        }

        LOGGER.log(Level.INFO, "generate(): {0}", average(conflicts));
    }


    @Test
    public static void testGenerateWithTimestamp() {

        final long timestamp = System.currentTimeMillis();

        final List<Integer> conflicts = new ArrayList<>(COUNT);

        for (int i = 0; i < COUNT; i++) {
            final Set<Long> values = new HashSet<>(COUNT);
            for (int j = 0; true; j++) {
                if (!values.add(Nuo.generate(timestamp))) {
                    conflicts.add(j);
                    break;
                }
            }
        }

        LOGGER.log(Level.INFO, "generate(timestamp): {0}", average(conflicts));
    }


    @Test
    public static void testGenerateWithTimestampAndRandom()
        throws NoSuchAlgorithmException {

        final long timestamp = System.currentTimeMillis();
        final Random random = SecureRandom.getInstance("SHA1PRNG");

        final List<Integer> conflicts = new ArrayList<>(COUNT);

        for (int i = 0; i < COUNT; i++) {
            final Set<Long> values = new HashSet<>(COUNT);
            for (int j = 0; true; j++) {
                if (!values.add(Nuo.generate(timestamp, random))) {
                    conflicts.add(j);
                    break;
                }
            }
        }

        LOGGER.log(Level.INFO, "generate(timestamp, random): {0}",
                   average(conflicts));
    }


}
