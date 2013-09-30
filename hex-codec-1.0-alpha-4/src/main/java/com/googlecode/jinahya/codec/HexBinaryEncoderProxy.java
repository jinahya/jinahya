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


import com.googlecode.jinahya.commons.codec.BinaryEncoderProxy;


/**
 * A proxy class for {@code org.apache.commons.codec.BinaryEncoder}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HexBinaryEncoderProxy extends BinaryEncoderProxy<HexEncoder> {


    /**
     * Returns a new proxy instance for
     * {@code org.apache.commons.codec.BinaryEncoder} with given
     * {@code encoder}.
     *
     * @param encoder the encoder to proxy
     *
     * @return a new proxy instance.
     */
    public static Object newInstance(final HexEncoder encoder) {

        if (encoder == null) {
            throw new NullPointerException("encoder");
        }

        return newInstance(
            HexBinaryEncoderProxy.class, HexEncoder.class, encoder);
    }


    /**
     * Returns a new proxy instance for
     * {@code org.apache.commons.codec.BinaryEncoder}.
     *
     * @return a new proxy instance.
     */
    public static Object newInstance() {

        return newInstance(new HexEncoder());
    }


    /**
     * Creates a new instance.
     *
     * @param encoder the encoder to use.
     */
    protected HexBinaryEncoderProxy(final HexEncoder encoder) {

        super(encoder);
    }


    @Override
    protected byte[] encode(final byte[] source) throws Throwable {

        if (source == null) {
            throw new NullPointerException("source");
        }

        return encoder.encode(source);
    }


}
