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


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <E>
 */
public abstract class SingleValueEntityTest<E extends SingleValueEntity<?>> {


    public SingleValueEntityTest(final Class<E> entityClass) {
        super();

        this.entityClass = entityClass;
    }


    @Test
    public void testSingle()
        throws InstantiationException, IllegalAccessException, IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);

        final E expected = entityClass.newInstance();
        expected.write(output);

        output.align(1);
        baos.flush();

        final byte[] bytes = baos.toByteArray();
        System.out.println("bytes.length: " + bytes.length);
        System.out.println("bytes[0]: " + bytes[0]);

        final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        final BitInput input = new BitInput(bais);

        final E actual = entityClass.newInstance();
        actual.read(input);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testMultiple()
        throws InstantiationException, IllegalAccessException, IOException {

        final int count = ThreadLocalRandom.current().nextInt(1024) + 1024;

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(baos);

        final List<E> expected = new ArrayList<E>(count);
        for (int i = 0; i < count; i++) {
            final E entity = entityClass.newInstance();
            entity.write(output);
            expected.add(entity);
        }
        output.align(1);
        baos.flush();

        final byte[] bytes = baos.toByteArray();
        System.out.println("bytes.length: " + bytes.length);
        System.out.println("bytes[0]: " + bytes[0]);

        final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        final BitInput input = new BitInput(bais);

        final List<E> actual = new ArrayList<E>(expected.size());
        for (int i = 0; i < expected.size(); i++) {
            final E entity = entityClass.newInstance();
            entity.read(input);
            actual.add(entity);
        }

        Assert.assertEquals(actual, expected);
    }


    protected final Class<E> entityClass;


}

