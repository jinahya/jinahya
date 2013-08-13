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


package com.googlecode.jinahya.io;


import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class BlackOutputStreamTest {


    @Test
    public void testWrite() throws IOException {

        final Random random = ThreadLocalRandom.current();

        final OutputStream out = new BlackOutputStream();

        for (int i = 0; i < 1024; i++) {
            out.write(random.nextInt());
        }

        out.flush();
        out.close();
    }


    @Test
    public void testWriteUnderLimit() throws IOException {

        final Random random = ThreadLocalRandom.current();

        final long limit = (long) (random.nextInt(1048576) + 1);

        final OutputStream out = new BlackOutputStream(limit);

        final int count = random.nextInt((int) limit);
        for (int i = 0; i < count; i++) {
            out.write(random.nextInt());
        }

        out.flush();
        out.close();
    }


    @Test(expectedExceptions = {IOException.class})
    public void testWriteOverLimit() throws IOException {

        final Random random = ThreadLocalRandom.current();

        final long limit = (long) random.nextInt(1048576);

        final OutputStream out = new BlackOutputStream(limit);

        for (int i = 0; i < limit + 1; i++) {
            out.write(random.nextInt());
        }

        out.flush();
        out.close();
    }


}

