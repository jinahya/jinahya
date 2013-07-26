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


import static com.googlecode.jinahya.commons.codec.EncoderProxy.newInstance;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class RareEncoderProxy extends EncoderProxy<RareEncoder> {


    protected static Object newInstance(final RareEncoder encoder) {

        try {
            return newInstance(
                RareEncoderProxy.class, RareEncoder.class, encoder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    protected static Object newInstance() {

        return newInstance(new RareEncoder());
    }


    protected RareEncoderProxy(final RareEncoder encoder) {

        super(encoder);
    }


    @Override
    protected Object encode(final RareEncoder encoder, final Object source)
        throws Throwable {

        return encoder.encode(source);
    }


}

