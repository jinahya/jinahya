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


package com.googlecode.jinahya.codec;


import com.googlecode.jinahya.commons.codec.BinaryDecoderProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Proxy for
 * <code>org.apache.commons.codec.BinaryDecoder</code>.
 *
 * <blockquote><pre>
 * // create
 * final BinaryDecoder decoder = (BinaryDecoder)
 *     PercentBinaryDecoderProxy.newInstance();
 *
 * // decode
 * decoder.decode(byte[]);
 * </code></pre>
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @see <a href="http://goo.gl/l58d1"">BinaryDecoder (Apache Commons Codec)</a>
 */
public class PercentBinaryDecoderProxy
    extends BinaryDecoderProxy<PercentDecoder> {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(PercentBinaryDecoderProxy.class);


    /**
     *
     * @param decoder
     *
     * @return
     */
    public static Object newInstance(final PercentDecoder decoder) {

        if (decoder == null) {
            //throw new NullPointerException("decoder");
        }

        return newInstance(
            PercentBinaryDecoderProxy.class, PercentDecoder.class, decoder);
    }


    /**
     * Creates a new proxy instance.
     *
     * @return a new proxy instance.
     */
    public static Object newInstance() {

        return newInstance(new PercentDecoder());
    }


    /**
     * Creates a new instance wrapping given decoder instance.
     *
     * @param decoder the decoder instance to wrap
     */
    protected PercentBinaryDecoderProxy(final PercentDecoder decoder) {

        super(decoder);
    }


    @Override
    protected Object decode(final Object source) throws Throwable {

        LOGGER.debug("<Object>decode({})", source);

        return super.decode(source);
    }


    @Override
    protected byte[] decode(final byte[] source) throws Throwable {

        LOGGER.debug("<byet[]>decode({})", source);

        return decoder.decode(source);
    }


}
