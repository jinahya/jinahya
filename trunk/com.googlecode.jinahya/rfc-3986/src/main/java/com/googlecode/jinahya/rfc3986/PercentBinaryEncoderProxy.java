/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
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


package com.googlecode.jinahya.rfc3986;


import com.googlecode.jinahya.commons.codec.BinaryEncoderProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Proxy for
 * <code>org.apache.commons.codec.BinaryEncoder</code>.
 *
 * <blockquote><pre>
 * // create
 * final BinaryEncoder encoder = (BinaryEncoder)
 *     PercentBinaryEncoderProxy.newInstance();
 *
 * // encode
 * encoder.encode(byte[]);
 * </blockquote></pre>
 *
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @see <a href="http://goo.gl/SId47">BinaryEncoder (Apache Commons Codec)</a>
 */
public class PercentBinaryEncoderProxy
    extends BinaryEncoderProxy<PercentEncoder> {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(PercentBinaryEncoderProxy.class);


    public static Object newInstance(final PercentEncoder encoder) {

        if (encoder == null) {
            throw new NullPointerException("encoder");
        }

        return newInstance(
            PercentBinaryEncoderProxy.class, PercentEncoder.class, encoder);
    }


    public static Object newInstance() {

        return newInstance(new PercentEncoder());
    }


    /**
     * Creates a new instance.
     */
    protected PercentBinaryEncoderProxy(final PercentEncoder encoder) {

        super(encoder);
    }


    @Override
    protected Object encode(final Object source) throws Throwable {

        LOGGER.debug("<Object>encode({})", source);

        return super.encode(source);
    }


    @Override
    protected byte[] encode(final byte[] source) throws Throwable {

        LOGGER.debug("encode({})", source);

        return encoder.encode(source);
    }


}
