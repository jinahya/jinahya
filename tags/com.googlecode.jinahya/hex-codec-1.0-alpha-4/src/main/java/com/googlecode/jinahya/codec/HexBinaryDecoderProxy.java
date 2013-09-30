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


import com.googlecode.jinahya.commons.codec.BinaryDecoderProxy;


/**
 * A proxy class for {@code org.apache.commons.codec.BinaryEncoder}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HexBinaryDecoderProxy extends BinaryDecoderProxy<HexDecoder> {


    public static Object newInstance(final HexDecoder decoder) {

        if (decoder == null) {
            throw new NullPointerException("decoder");
        }

        return newInstance(
            HexBinaryDecoderProxy.class, HexDecoder.class, decoder);
    }


    /**
     * Returns a new proxy for {@code org.apache.commons.codec.BinaryDecoder}.
     *
     * @return a new proxy for {@code org.apache.commons.codec.BinaryDecoder}.
     */
    public static Object newInstance() {

        return newInstance(new HexDecoder());
    }


    /**
     * Creates a new instance.
     *
     * @param decoder the decoder to proxy.
     */
    protected HexBinaryDecoderProxy(final HexDecoder decoder) {

        super(decoder);
    }


    @Override
    protected byte[] decode(final byte[] source) throws Throwable {

        if (source == null) {
            throw new NullPointerException("source");
        }

        return decoder.decode(source);
    }


}
