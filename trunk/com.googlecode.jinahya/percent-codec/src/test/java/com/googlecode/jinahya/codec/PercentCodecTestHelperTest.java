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


package com.googlecode.jinahya.codec;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class PercentCodecTestHelperTest {


    /**
     * logger.
     */
    private static Logger LOGGER =
        LoggerFactory.getLogger(PercentCodecTestHelperTest.class);


    @Test
    public static void testDecodedString() {

        final String decodedString = PercentCodecTestHelper.decodedString(1024);
        LOGGER.debug("decodedString: {}", decodedString);
    }


    @Test
    public static void testDecodedBytes() {

        final byte[] decodedBytes = PercentCodecTestHelper.decodedBytes(1024);
        LOGGER.debug("decodedBytes: {}", decodedBytes);
    }


    @Test
    public static void testEncodedString() {

        final String encodedString = PercentCodecTestHelper.encodedString(1024);
        LOGGER.debug("encodedString: {}", encodedString);
    }


    @Test
    public static void testEncodedBytes() {

        final byte[] encodedBytes = PercentCodecTestHelper.encodedBytes(1024);
        LOGGER.debug("encodedBytes: {}", encodedBytes);
    }


}
