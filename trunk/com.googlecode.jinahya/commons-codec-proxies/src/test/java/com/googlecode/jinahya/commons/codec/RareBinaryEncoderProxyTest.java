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


import java.lang.reflect.Method;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.EncoderException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class RareBinaryEncoderProxyTest
    extends AbstractEncoderProxyTest<RareBinaryEncoderProxy, RareBinaryEncoder, BinaryEncoder> {


    public RareBinaryEncoderProxyTest() {
        super(RareBinaryEncoderProxy.class, RareBinaryEncoder.class,
              BinaryEncoder.class);
    }


    @Override
    protected RareBinaryEncoder newRareEncoder() {

        return new RareBinaryEncoder();
    }


    @Override
    protected Object newProxy(final RareBinaryEncoder rareEncoder) throws Exception {
        final Method newInstance = BinaryEncoderProxy.class.getDeclaredMethod(
            "newInstance", Class.class, Class.class, Object.class);
        if (!newInstance.isAccessible()) {
            newInstance.setAccessible(true);
        }
        return newInstance.invoke(null, proxyType, rareEncoderType,
                                  rareEncoder);
    }


    @Override
    protected void invokeEncode(final BinaryEncoder commonsEncoder)
        throws EncoderException {

        commonsEncoder.encode(new byte[0]);
    }


}

