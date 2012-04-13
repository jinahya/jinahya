/*
 * Copyright 2011 Jin Kwon.
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


package com.googlecode.jinahya.rfc3986;


import java.io.IOException;
import java.util.Random;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class PercentCodecTest {


    private static final Random RANDOM = new Random();


    @Test(invocationCount = 128)
    public void test() throws IOException {

        final String expected = RandomStringUtils.random(RANDOM.nextInt(1024));

        final byte[] original = expected.getBytes("UTF-8");
        final byte[] encoded = PercentEncoder.encode(original);
        final byte[] decoded = PercentDecoder.decode(encoded);

        final String actual = new String(decoded, "UTF-8");

        Assert.assertEquals(actual, expected);
    }
}

