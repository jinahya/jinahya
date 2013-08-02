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


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class RareStringEncoderProxy
    extends StringEncoderProxy<RareStringEncoder> {


    public static Object newInstance(final RareStringEncoder encoder) {

        return newInstance(RareStringEncoderProxy.class,
                           RareStringEncoder.class, encoder);
    }


    public static Object newInstance() {

        return newInstance(new RareStringEncoder());
    }


    protected RareStringEncoderProxy(final RareStringEncoder encoder) {

        super(encoder);
    }


    @Override
    protected Object encode(final Object source) throws Throwable {

        return super.encode(source);
    }


    @Override
    protected String encode(final String source) throws Throwable {

        return encoder.encode(source);
    }


}

