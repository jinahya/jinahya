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


import com.googlecode.jinahya.commons.codec.DecoderProxy;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class IdDecoderProxy extends DecoderProxy<IdDecoder> {


    /**
     * Creates a new proxy instance for {@code org.apache.commons.codec.Decoder}
     * with given {@code decoder}.
     *
     * @param decoder the decoder to use.
     *
     * @return a new proxy instance.
     */
    public static Object newInstance(final IdDecoder decoder) {

        if (decoder == null) {
            throw new NullPointerException("decoder");
        }

        return newInstance(IdDecoderProxy.class, IdDecoder.class, decoder);
    }


    /**
     * Returns a new proxy for {@code org.apache.commons.codec.Decoder}.
     *
     * @return a new proxy for {@code org.apache.commons.codec.Decoder}.
     */
    public static Object newInstance() {

        return newInstance(new IdDecoder());
    }


    /**
     * Creates a new instance.
     *
     * @param decoder the decoder to use.
     */
    protected IdDecoderProxy(final IdDecoder decoder) {

        super(decoder);
    }


    @Override
    protected Object decode(final Object source) throws Throwable {

        if (source == null) {
            throw new NullPointerException("source");
        }

        if (!(source instanceof String)) {
            final String message =
                source + " is not an instance of " + String.class;
            throw new IllegalArgumentException(message);
            //throw newDecoderException(message);
        }

        return decoder.decode((String) source);
    }


}
