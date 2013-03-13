/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class SampleEntityTest {


    @Test
    public void testBit() throws IOException {

        final Random random = ThreadLocalRandom.current();
        final int count = random.nextInt(1024) + 1024;

        final SampleEntity[] entities = new SampleEntity[count];
        for (int i = 0; i < entities.length; i++) {
            entities[i] = SampleEntity.newInstance(random);
        }

        // bit-io
        {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final BitOutput output = new BitOutput(baos);
            for (SampleEntity entity : entities) {
                entity.write(output);
            }
            output.align(1);
            baos.flush();
            final byte[] bytes = baos.toByteArray();
            System.out.println("bit-io bytes.length: " + bytes.length);
        }

        // data-io
        {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final DataOutputStream output = new DataOutputStream(baos);
            for (SampleEntity entity : entities) {
                entity.write(output);
            }
            baos.flush();
            final byte[] bytes = baos.toByteArray();
            System.out.println("data-io bytes.length: " + bytes.length);
        }
    }


}

