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


import com.googlecode.jinahya.commons.codec.EncoderProxy;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class IdEncoderProxy extends EncoderProxy<IdEncoder> {


    /**
     * Creates a new proxy instance for {@code org.apache.commons.codec.Encoder}
     * with given {@code encoder}.
     *
     * @param encoder the encoder to use
     *
     * @return a new proxy instance.
     */
    public static Object newInstance(final IdEncoder encoder) {

        if (encoder == null) {
            throw new NullPointerException("null encoder");
        }

        return EncoderProxy.newInstance(IdEncoderProxy.class, IdEncoder.class,
                                        encoder);
    }


    /**
     * Returns a new proxy instance for
     * {@code org.apache.commons.codec.Encoder}.
     *
     * @return a new proxy instance.
     */
    public static Object newInstance() {

        return newInstance(new IdEncoder());
    }


    /**
     * Creates a new instance.
     *
     * @param encoder the IdEncoder to use.
     */
    protected IdEncoderProxy(final IdEncoder encoder) {

        super(encoder);
    }


    @Override
    protected Object encode(final Object source) throws Throwable {

        if (source == null) {
            throw new NullPointerException("source");
        }

        if (!(source instanceof Long)) {
            final String message =
                source + " is not an instance of " + Long.class;
            throw new IllegalArgumentException(message);
            //throw newEncoderException(message);
        }

        return encoder.encode((Long) source);
    }


}
