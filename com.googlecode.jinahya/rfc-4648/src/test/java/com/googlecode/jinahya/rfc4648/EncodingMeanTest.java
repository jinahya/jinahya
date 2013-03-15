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
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.math3.stat.StatUtils;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <B> base type parameter
 * @param <E> encoder type parameter
 */
@Test(singleThreaded = true)
public abstract class EncodingMeanTest<B extends Base, E extends BinaryEncoder>
    extends BaseTest<B> {


    public EncodingMeanTest(final Class<B> baseClass,
                            final Class<E> encoderClass) {
        super(baseClass);

        this.encoderClass = encoderClass;
    }


    protected abstract E newEncoder();


    @Test(invocationCount = 1)
    public void testEncoding() throws Exception {

        final int count = ThreadLocalRandom.current().nextInt(1024) + 1024;

        final double bases[] = new double[count];
        final double commons[] = new double[count];

        final B base = newBase();
        final E encoder = newEncoder();

        long start;

        for (int i = 0; i < count; i++) {

            final byte[] decoded = decoded(10);

            start = System.nanoTime();
            base.encode(decoded);
            bases[i] = (System.nanoTime() - start);

            start = System.nanoTime();
            encoder.encode(decoded);
            commons[i] = (System.nanoTime() - start);
        }

        System.out.println(baseClass.getName() + ": " + StatUtils.mean(bases));
        System.out.println(encoderClass.getName() + ": "
                           + StatUtils.mean(commons));
    }


    protected final Class<E> encoderClass;


}

