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


package com.googlecode.jinahya.util.rfc4648;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.commons.codec.BinaryDecoder;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <B>
 * @param <D> 
 */
@Test(singleThreaded = true)
public abstract class EncodingTest<B extends Base, D extends BinaryDecoder>
    extends BaseTest<B> {


    public EncodingTest(final B base, final D encoder,
                        final Modifier modifier) {
        super(base);

        this.decoder = encoder;
        this.modifier = modifier;
    }


    @Test
    public void testEncoding() throws Exception {

        final byte[] expected = generate();

        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        base.encode(new ByteArrayInputStream(expected), output);
        output.flush();

        final byte[] encoded = output.toByteArray();

        final byte[] actual = decoder.decode(modifier.modify(encoded));

        Assert.assertEquals(actual, expected);
    }


    protected final D decoder;


    protected final Modifier modifier;
}

