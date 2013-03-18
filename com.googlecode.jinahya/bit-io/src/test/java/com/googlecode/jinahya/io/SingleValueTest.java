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


import com.googlecode.jinahya.io.BitInput.StreamInput;
import com.googlecode.jinahya.io.BitOutput.StreamOutput;
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
 * @param <S>
 */
public abstract class SingleValueTest<S extends SingleValue<?>> {


    public SingleValueTest(final Class<S> valueClass) {
        super();

        this.valueClass = valueClass;
    }


    @Test(enabled = true)
    public void testSingle()
        throws InstantiationException, IllegalAccessException, IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(new StreamOutput(baos));

        final S expected = valueClass.newInstance();
        expected.write(output);

        output.align(1);
        baos.flush();

        final byte[] bytes = baos.toByteArray();
        final int bytesLength = bytes.length;
        System.out.println(valueClass.getSimpleName() + " bytes.length: "
                           + bytesLength);
        for (int j = 0; j < bytesLength; j++) {
            System.out.println(valueClass.getSimpleName() + " bytes[" + j
                               + "]: " + (bytes[j] & 0xFF));
        }

        final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        final BitInput input = new BitInput(new StreamInput(bais));

        final S actual = valueClass.newInstance();
        actual.read(input);
        input.align(1);

        Assert.assertEquals(actual, expected);
    }


    @Test(enabled = true)
    public void testMultiple()
        throws InstantiationException, IllegalAccessException, IOException {

        final int count = ThreadLocalRandom.current().nextInt(1024) + 1024;

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(new StreamOutput(baos));

        final List<S> expected = new ArrayList<S>(count);
        for (int i = 0; i < count; i++) {
            final S entity = valueClass.newInstance();
            entity.write(output);
            expected.add(entity);
        }
        output.align(1);
        baos.flush();

        final byte[] bytes = baos.toByteArray();
        final int bytesLength = bytes.length;
        System.out.println(valueClass.getSimpleName() + " bytes.length: "
                           + bytesLength);
        for (int j = 0; j < bytesLength; j++) {
            System.out.println(valueClass.getSimpleName() + " bytes[" + j
                               + "]: " + (bytes[j] & 0xFF));
        }

        final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        final BitInput input = new BitInput(new StreamInput(bais));

        final List<S> actual = new ArrayList<S>(expected.size());
        for (int i = 0; i < expected.size(); i++) {
            final S entity = valueClass.newInstance();
            entity.read(input);
            actual.add(entity);
        }
        input.align(1);

        Assert.assertEquals(actual, expected);
    }


    protected final Class<S> valueClass;


}

