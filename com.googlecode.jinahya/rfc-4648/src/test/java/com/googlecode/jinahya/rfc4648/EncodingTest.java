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


import static com.googlecode.jinahya.rfc4648.BaseTest.decoded;
import java.util.Objects;
import org.apache.commons.codec.BinaryDecoder;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <B> base type parameter
 * @param <D> decoder type parameter
 */
@Test(singleThreaded = false)
public abstract class EncodingTest<B extends Base, D extends BinaryDecoder>
    extends BaseTest<B> {


    public EncodingTest(final Class<B> baseClass, final Class<D> decoderClass) {

        super(baseClass);

        this.decoderClass = Objects.requireNonNull(decoderClass);
    }


    protected abstract D decoder();


    protected abstract byte[] decoding(final byte[] encoded);


    @Test(invocationCount = 32)
    public void testEncoding() throws Exception {

        final byte[] expected = decoded();

        final byte[] encoded = base().encode(expected);

        final byte[] actual = decoder().decode(decoding(encoded));

        Assert.assertEquals(actual, expected);
    }


    protected final Class<D> decoderClass;


}
