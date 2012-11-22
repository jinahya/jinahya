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
import java.nio.charset.StandardCharsets;
import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class PercentCodecTest {


    private static final Random RANDOM = new Random();


    @Test(invocationCount = 128)
    public void testEncodeDecode() throws IOException {

        final byte[] expected = new byte[RANDOM.nextInt(1024)];
        RANDOM.nextBytes(expected);

        final byte[] encoded = new PercentEncoder().encode(expected);

        final byte[] actual = new PercentDecoder().decode(encoded);

        Assert.assertEquals(actual, expected);
    }


}

