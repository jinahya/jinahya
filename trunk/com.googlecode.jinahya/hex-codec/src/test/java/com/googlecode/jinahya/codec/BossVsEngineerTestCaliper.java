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


import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;
import java.util.logging.Logger;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Test(singleThreaded = true)
public class BossVsEngineerTestCaliper extends SimpleBenchmark {


    private static final Logger LOGGER =
        Logger.getLogger(BossVsEngineerTestCaliper.class.getName());


    @Test
    public void run() {
        try {
            Runner.main(BossVsEngineerTestCaliper.class, new String[0]);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        multipleDecodedBytes = HexCodecTestUtil.newMultipleDecodedBytes();
        multipleEncodedBytes = HexCodecTestUtil.newMultipleEncodedBytes();
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


    public void timeEncodeLikeABoss(final int reps) {

        for (int i = 0; i < reps; i++) {
            for (byte[] decoded : multipleDecodedBytes) {
                new HexEncoder().encodeLikeABoss(decoded);
            }
        }
    }


    public void timeEncodeLikeAnEngineer(final int reps) {

        for (int i = 0; i < reps; i++) {
            for (byte[] decoded : multipleDecodedBytes) {
                new HexEncoder().encodeLikeAnEngineer(decoded);
            }
        }
    }


    public void timeDecodeLikeABoss(final int reps) {

        for (int i = 0; i < reps; i++) {
            for (byte[] encoded : multipleEncodedBytes) {
                new HexDecoder().decodeLikeABoss(encoded);
            }
        }
    }


    public void timeDecodeLikeAnEngineer(final int reps) {

        for (int i = 0; i < reps; i++) {
            for (byte[] encoded : multipleEncodedBytes) {
                new HexDecoder().decodeLikeAnEngineer(encoded);
            }
        }
    }


    private byte[][] multipleEncodedBytes;


    private byte[][] multipleDecodedBytes;


}

