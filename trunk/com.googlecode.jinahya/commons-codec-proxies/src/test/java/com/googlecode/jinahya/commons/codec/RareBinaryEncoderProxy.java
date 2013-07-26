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
public class RareBinaryEncoderProxy
    extends BinaryEncoderProxy<RareBinaryEncoder> {


    private static final Logger LOGGER =
        Logger.getLogger(RareBinaryEncoderProxy.class.getName());


    public static Object newInstance(final RareBinaryEncoder encoder) {

        return newInstance(
            RareBinaryEncoderProxy.class, RareBinaryEncoder.class, encoder);
    }


    public static Object newInstance() {

        return newInstance(new RareBinaryEncoder());
    }


    protected RareBinaryEncoderProxy(final RareBinaryEncoder encoder) {

        super(encoder);
    }


    @Override
    protected Object encode(final RareBinaryEncoder encoder,
                            final Object source)
        throws Throwable {

        LOGGER.log(Level.INFO, "<Object>encode({0}, {1})",
                   new Object[]{encoder, source});
        
        return super.encode(encoder, source);
    }


    @Override
    protected byte[] encode(final RareBinaryEncoder encoder,
                            final byte[] source)
        throws Throwable {

        LOGGER.log(Level.INFO, "<Binary>encode({0}, {1})",
                   new Object[]{encoder, source});

        return encoder.encode(source);
    }


}

