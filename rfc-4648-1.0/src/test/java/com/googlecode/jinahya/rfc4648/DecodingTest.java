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


package com.googlecode.jinahya.rfc4648;


import org.apache.commons.codec.BinaryEncoder;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <B> base type parameter
 * @param <E> encoder type parameter
 */
public abstract class DecodingTest<B extends Base, E extends BinaryEncoder>
    extends BaseTest<B> {


    public DecodingTest(final Class<B> baseClass, final Class<E> encoderClass) {
        super(baseClass);

        this.encoderClass = encoderClass;
    }


    protected abstract E newEncoder();


    protected abstract byte[] forBaseDecoding(byte[] commonsEncoded);


    @Test(invocationCount = 32)
    public void testDecoding() throws Exception {

        final byte[] expected = decoded();

        final byte[] encoded = newEncoder().encode(expected);

        final byte[] actual = newBase().decode(forBaseDecoding(encoded));

        Assert.assertEquals(actual, expected);
    }


    protected final Class<E> encoderClass;


}

