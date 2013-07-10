/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <D>
 */
public abstract class StringDecoderProxy<D> extends DecoderProxy<D> {


    /**
     * Class for {@code org.apache.commons.codec.StringDecoder}.
     */
    private static final Class<?> DECODER;


    static {
        try {
            DECODER = Class.forName("org.apache.commons.codec.StringDecoder");
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    /**
     * {@code decode(Ljava/lang/String;)Ljava/lang/String;}.
     */
    private static final Method DECODE;


    static {
        try {
            DECODE = DECODER.getMethod("decode", String.class);
        } catch (NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }


    protected static <P extends DecoderProxy<D>, D> Object newInstance(
        final Class<P> proxyType, final Class<D> decoderType, final D decoder) {

//        if (decoder == null) {
//            throw new IllegalArgumentException("decoder");
//        }

        try {
            final Constructor<P> constructor =
                proxyType.getConstructor(decoderType);
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            try {
                return Proxy.newProxyInstance(DECODER.getClassLoader(),
                                              new Class<?>[]{DECODER},
                                              constructor.newInstance(decoder));
            } catch (InstantiationException ie) {
                throw new RuntimeException(ie);
            } catch (IllegalAccessException iae) {
                throw new RuntimeException(iae);
            } catch (InvocationTargetException ite) {
                throw new RuntimeException(ite);
            }
        } catch (NoSuchMethodException nsme) {
            throw new RuntimeException(nsme);
        }
    }


    /**
     * Creates a new instance.
     *
     * @param decoder the decoder to use.
     */
    protected StringDecoderProxy(final D decoder) {

        super(decoder);
    }


    @Override
    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        if (DECODE.equals(method)) {
            return decode(decoder, (String) args[0]);
        }

        return super.invoke(proxy, method, args);
    }


    @Override
    protected Object decode(final D decoder, final Object source)
        throws Throwable {

        if (source == null) {
            //throw new NullPointerException("source");
            throw newDecoderException("null source"); // documented
        }

        try {
            return decode(decoder, (String) source);
        } catch (ClassCastException cce) {
            throw newDecoderException(cce);
        }
    }


    protected abstract String decode(final D decoder, final String source)
        throws Throwable;


}

