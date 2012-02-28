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


package com.googlecode.jinahya.util;


import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class IdCodecTest {


    private static final Random RANDOM = new Random();


    @Test(invocationCount = 512)
    public void test() {

        final long decoded = RANDOM.nextLong() & Long.MAX_VALUE;

        final String encoded = IdEncoder.encodeId(decoded);

        Assert.assertEquals(IdDecoder.decodeId(encoded), decoded);

        System.out.println(decoded + " / " + encoded);
    }


}

