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


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.commons.codec.BinaryEncoder;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <B>
 * @param <E> 
 */
public abstract class DecodingTest<B extends Base, E extends BinaryEncoder>
    extends BaseTest<B> {


    public DecodingTest(final B base, final E decoder,
                        final Modifier modifier) {
        super(base);

        this.encoder = decoder;
        this.modifier = modifier;
    }


    @Test
    public void testDecoding() throws Exception {

        final byte[] expected = generate();

        final byte[] encoded = modifier.modify(encoder.encode(expected));

        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        base.decode(new ByteArrayInputStream(encoded), output);
        output.flush();

        final byte[] actual = output.toByteArray();

        Assert.assertEquals(actual, expected);
    }


    protected final E encoder;


    protected final Modifier modifier;
}

