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
import org.apache.commons.codec.Encoder;
import org.apache.commons.codec.EncoderException;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class AbstractEncoderProxyTest<P extends EncoderProxy<R>, R, C extends Encoder> {


    public AbstractEncoderProxyTest(final Class<P> proxyType,
                                    final Class<R> rareEncoderType,
                                    final Class<C> commonsEncoderType) {

        super();

        this.proxyType = proxyType;
        this.rareEncoderType = rareEncoderType;
        this.commonsEncoderType = commonsEncoderType;
    }


    @Test(enabled = true)
    public void test() throws Exception {

        final R rareEncoder = newRareEncoder();

        final Object proxy = newProxy(rareEncoder);

        final C commonsEncoder = commonsEncoderType.cast(proxy);

        invokeEncode(commonsEncoder);
    }


    protected Object newProxy(final R rareEncoder) throws Exception {
        final Method newInstance = EncoderProxy.class.getDeclaredMethod(
            "newInstance", Class.class, Class.class, Object.class);
        if (!newInstance.isAccessible()) {
            newInstance.setAccessible(true);
        }
        return newInstance.invoke(null, proxyType, rareEncoderType,
                                  rareEncoder);
    }


    protected abstract R newRareEncoder();


    protected abstract void invokeEncode(final C commonsEncoder)
        throws EncoderException;


    protected final Class<P> proxyType;


    protected final Class<R> rareEncoderType;


    private final Class<C> commonsEncoderType;


}

