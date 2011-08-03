/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
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


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class PercentEncoderTest {


    private static final Random RANDOM = new Random();


    @Test(invocationCount = 128)
    public void testEncodeComparingTwoMethods() throws IOException {

        final String string = RandomStringUtils.random(RANDOM.nextInt(1024));

        final String encoded1;
        {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PercentEncoder.encode(
                new ByteArrayInputStream(string.getBytes("UTF-8")), baos);
            baos.flush();
            encoded1 = new String(baos.toByteArray(), "US-ASCII");
        }

        final String encoded2;
        {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final Writer writer = new OutputStreamWriter(baos, "US-ASCII");
            PercentEncoder.encode(
                new ByteArrayInputStream(string.getBytes("UTF-8")), writer);
            writer.flush();
            baos.flush();
            encoded2 = new String(baos.toByteArray(), "US-ASCII");
        }

        Assert.assertEquals(encoded1, encoded2);
    }
}

