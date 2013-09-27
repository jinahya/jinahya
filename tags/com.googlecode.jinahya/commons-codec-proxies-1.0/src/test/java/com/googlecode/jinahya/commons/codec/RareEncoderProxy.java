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


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class RareEncoderProxy extends EncoderProxy<RareEncoder> {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(RareEncoderProxy.class);


    protected static Object newInstance(final RareEncoder encoder) {

        if (encoder == null) {
            throw new NullPointerException("encoder");
        }

        return newInstance(RareEncoderProxy.class, RareEncoder.class, encoder);
    }


    protected static Object newInstance() {

        return newInstance(new RareEncoder());
    }


    protected RareEncoderProxy(final RareEncoder encoder) {

        super(encoder);
    }


    @Override
    protected Object encode(final Object source) throws Throwable {

        LOGGER.debug("<Object>encode({})", source);

        return encoder.encode(source);
    }


}
