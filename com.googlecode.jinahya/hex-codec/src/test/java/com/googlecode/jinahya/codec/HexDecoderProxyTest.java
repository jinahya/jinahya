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


package com.googlecode.jinahya.codec;


import java.util.Random;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.EncoderException;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HexDecoderProxyTest {


    private static final Random RANDOM = new Random();


    private static byte[] newRandomBytes() {

        synchronized (RANDOM) {
            final byte[] bytes = new byte[RANDOM.nextInt(1024)];
            RANDOM.nextBytes(bytes);
            return bytes;
        }
    }


    @Test
    public void testAsBinaryEncoder() throws EncoderException {

        final BinaryEncoder encoder =
            ((BinaryEncoder) HexEncoderProxy.newInstance());

        final byte[] encoded = encoder.encode(newRandomBytes());
    }


}

