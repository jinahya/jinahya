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


import java.util.Objects;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.math3.stat.StatUtils;
import org.junit.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <B> base type parameter
 * @param <E> encoder type parameter
 */
@Test(singleThreaded = false)
public abstract class MeanEncodingTest<B extends Base, E extends BinaryEncoder>
    extends BaseTest<B> {


    private static <B extends Base, E extends BinaryEncoder> void test(
        final B base, final E encoder, final double[] baseElapsed,
        final double[] commonsElapsed, final int index,
        final boolean assertEquals)
        throws Exception {

        long start;

        //final byte[] decoded = decoded(1024);
        final byte[] decoded = decoded(8192);
        //final byte[] decoded = decoded(524288);
        //final byte[] decoded = decoded(1048576);

        start = System.nanoTime();
        final byte[] baseEncoded = base.encode(decoded);
        baseElapsed[index] = (System.nanoTime() - start);

        start = System.nanoTime();
        final byte[] commonsEncoded = encoder.encode(decoded);
        commonsElapsed[index] = (System.nanoTime() - start);

        if (assertEquals) {
            Assert.assertArrayEquals(commonsEncoded, baseEncoded);
        }
    }


    public MeanEncodingTest(final Class<B> baseClass,
                            final Class<E> encoderClass) {
        super(baseClass);

        this.encoderClass = Objects.requireNonNull(encoderClass);
    }


    protected abstract E encoder();


    @Test(invocationCount = 1)
    public void testEncoding() throws Exception {

        //final int count = 1024;
        //final int count = 2048;
        //final int count = 4096;
        final int count = 8192;
        //final int count = 16384;
        //final int count = 32768;
        //final int count = 65536;
        //final int count = 131072;
        //final int count = 262144;
        //final int count = 524288;
        //final int count = 1048576;

        final double baseElapsed[] = new double[count];
        final double commonsElapsed[] = new double[count];

        final B base = base();
        final E encoder = encoder();

        long start;

        // warm-up
        for (int i = 0; i < 1024; i++) {
            test(base, encoder, baseElapsed, commonsElapsed, i, true);
        }

        for (int i = 0; i < count; i++) {
            test(base, encoder, baseElapsed, commonsElapsed, i, false);
        }

        final double baseMean = StatUtils.mean(baseElapsed);
        final double commonsMean = StatUtils.mean(commonsElapsed);
        System.out.printf("%12s: %20.4f\n", baseClass.getSimpleName(),
                          baseMean);
        System.out.printf("%12s: %20.4f\n", encoderClass.getSimpleName(),
                          commonsMean);
        System.out.printf("base/commons: %20.4f\n", (baseMean / commonsMean));
    }


    protected final Class<E> encoderClass;


}
