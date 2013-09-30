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


package com.googlecode.jinahya.codec;


import com.googlecode.jinahya.codec.PercentEncoder;
import com.googlecode.jinahya.codec.PercentDecoder;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class PercentCodecTest {


    @Test(invocationCount = 128)
    public void testEncodeDecode() throws IOException {

        final byte[] decoded = PercentDecoderTest.decodedBytes();

        final byte[] encoded = new PercentEncoder().encode(decoded);

        final byte[] actual = new PercentDecoder().decode(encoded);

        Assert.assertEquals(actual, decoded);
    }


}
