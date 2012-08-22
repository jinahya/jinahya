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


package com.googlecode.jinahya.xml.bind;


import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <V> value type
 * @param <R> raw type
 */
public abstract class ValueTest<V extends Value<R>, R>
    extends NillableValueTest<V, R> {


    public ValueTest(final Class<V> valueType) {
        super(valueType);
    }


    protected abstract R generateRaw();


    @Test
    public void testSetRaw()
        throws InstantiationException, IllegalAccessException {

        final V value = valueType.newInstance();

        try {
            value.setRaw(null);
            Assert.fail("passed: setRaw(null)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        final R raw = generateRaw();
        if (raw == null) {
            Assert.fail("null geneated");
        }

        value.setRaw(raw);
    }


}

