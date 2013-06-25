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
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <V>
 */
public abstract class ValueTest<V extends Value<?>> {


    private static <V extends Value<?>> void test(final Class<V> valueClass,
                                                  final List<V> expected)
        throws IOException, InstantiationException, IllegalAccessException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final BitOutput output = new BitOutput(new StreamOutput(baos));

        for (V value : expected) {
            value.write(output);
        }
        output.align(1);
        baos.flush();

        final byte[] bytes = baos.toByteArray();
//        System.out.println(valueClass.getSimpleName() + " bytes.length: "
//                           + bytes.length);
//        for (int i = 0; i < bytes.length; i++) {
//            System.out.println(valueClass.getSimpleName() + " bytes[" + i
//                               + "]: " + (bytes[i] & 0xFF));
//        }

        final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        final BitInput input = new BitInput(new StreamInput(bais));

        final List<V> actual = new ArrayList<V>(expected.size());
        for (int i = 0; i < expected.size(); i++) {
            final V value = valueClass.newInstance();
            value.length = expected.get(i).length;
            value.read(input);
            actual.add(value);
        }
        input.align(1);

        Assert.assertEquals(actual, expected);
    }


    public ValueTest(final Class<V> valueClass) {
        super();

        if (valueClass == null) {
            throw new NullPointerException("null valueClass");
        }

        this.valueClass = valueClass;
    }


    @Test(enabled = true, invocationCount = 128)
    public void test() throws IOException, InstantiationException,
                              IllegalAccessException {

        test(valueClass, Collections.<V>emptyList());

        final int count = ThreadLocalRandom.current().nextInt(1024);
        final List<V> expected = new ArrayList<V>(count);
        for (int i = 0; i < count; i++) {
            final V value = valueClass.newInstance();
            expected.add(value);
        }
        test(valueClass, expected);
    }


    protected final Class<V> valueClass;


}

