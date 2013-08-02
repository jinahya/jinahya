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
public class RareStringDecoderProxy
    extends StringDecoderProxy<RareStringDecoder> {


    private static final Logger LOGGER =
        Logger.getLogger(RareStringDecoderProxy.class.getName());


    public static Object newInstance(final RareStringDecoder decoder) {

        return newInstance(RareStringDecoderProxy.class,
                           RareStringDecoder.class, decoder);
    }


    public static Object newInstance() {

        return newInstance(new RareStringDecoder());
    }


    protected RareStringDecoderProxy(final RareStringDecoder decoder) {

        super(decoder);
    }


    @Override
    protected Object decode(final Object source) throws Throwable {

        LOGGER.log(Level.INFO, "<Object>decode({0})", source);

        return super.decode(source);
    }


    @Override
    protected String decode(final String source) throws Throwable {

        LOGGER.log(Level.INFO, "<String>decode({0})", source);

        return decoder.decode(source);
    }


}
