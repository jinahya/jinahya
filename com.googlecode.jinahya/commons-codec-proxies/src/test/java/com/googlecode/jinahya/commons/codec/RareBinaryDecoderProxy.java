/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.commons.codec;


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class RareBinaryDecoderProxy
    extends BinaryDecoderProxy<RareBinaryDecoder> {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(RareBinaryDecoderProxy.class.getName());


    public static Object newInstance(final RareBinaryDecoder decoder) {

        return newInstance(
            RareBinaryDecoderProxy.class, RareBinaryDecoder.class, decoder);
    }


    public static Object newInstance() {

        return newInstance(new RareBinaryDecoder());
    }


    protected RareBinaryDecoderProxy(final RareBinaryDecoder decoder) {

        super(decoder);
    }


    @Override
    protected byte[] decode(final byte[] source) throws Throwable {

        LOGGER.log(Level.INFO, "<Binary>decode({0}, {1})",
                   new Object[]{decoder, source});

        return decoder.decode(source);
    }


}

