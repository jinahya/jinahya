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


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class KVPTest {


    private static final Random RANDOM = new Random();


    private void testEncodingDecoding(final Map<String, String> expected) {

        final String encoded = KVP.encode(expected);

        final Map<String, String> actual = KVP.decode(encoded);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 128)
    public void testEncodingDecoding() {

        final Map<String, String> expected = new HashMap<String, String>();

        expected.put("", "");
        testEncodingDecoding(expected);

        expected.clear();
        final int count = RANDOM.nextInt(128) + 1;
        for (int i = 0; i < count; i++) {
            final String key = RandomStringUtils.random(RANDOM.nextInt(16));
            final String value = RandomStringUtils.random(RANDOM.nextInt(16));
            expected.put(key, value);
        }

        testEncodingDecoding(expected);
    }


}

