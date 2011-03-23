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


package jinahya.rfc4648;


import java.io.IOException;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class BaseTest<T extends Base> {


    protected static final Random RANDOM = new Random();


    protected static final byte[] generate(final int length) {

        synchronized (RANDOM) {
            final byte[] bytes = new byte[RANDOM.nextInt(length)];
            RANDOM.nextBytes(bytes);
            return bytes;
        }
    }


    public BaseTest(final T base) {
        super();

        this.base = base;
    }


    @Test(invocationCount = 128)
    public void testEncodingDecoding() throws IOException {

        final byte[] original = generate(1024);

        final char[] encoded = base.encode(original);

        final byte[] decoded = base.decode(encoded);

        Assert.assertEquals(decoded, original, "fail");
    }


    protected final T base;
}
