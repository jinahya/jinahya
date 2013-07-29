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
 * @param <D> decoder type parameter
 */
public abstract class BinaryDecoderProxy<D> extends DecoderProxy<D> {


    /**
     * Class for {@code org.apache.commons.codec.BinaryDecoder}.
     */
    private static final Class<?> DECODER;


    static {
        try {
            DECODER = Class.forName("org.apache.commons.codec.BinaryDecoder");
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    /**
     * {@code decode([b)[b}.
     */
    private static final Method DECODE;


    static {
        try {
            DECODE = DECODER.getMethod("decode", byte[].class);
        } catch (NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }


    /**
     * Creates a new (proxy) instance.
     *
     * @param <P> proxy type parameter.
     * @param <D> decoder type parameter
     * @param proxyType proxy type
     * @param decoderType decoder type
     * @param decoder decoder
     *
     * @return a new (proxy) instance.
     */
    protected static <P extends DecoderProxy<D>, D> Object newInstance(
        final Class<P> proxyType, final Class<D> decoderType, final D decoder) {

        if (proxyType == null) {
            throw new NullPointerException("proxyType");
        }

        if (decoderType == null) {
            throw new NullPointerException("decoderType");
        }

        if (decoder == null) {
            // ok
            //throw new IllegalArgumentException("decoder");
        }

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
     * @param decoder the decoder to delegate.
     */
    protected BinaryDecoderProxy(final D decoder) {

        super(decoder);
    }


    @Override
    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        if (DECODE.equals(method)) {
            return decode((byte[]) args[0]);
        }

        return super.invoke(proxy, method, args);
    }


    @Override
    protected Object decode(final Object source) throws Throwable {

        if (source == null) {
            //throw new NullPointerException("source");
            throw newDecoderException("null source"); // documented?
        }

        try {
            return decode((byte[]) source);
        } catch (ClassCastException cce) {
            throw newDecoderException(cce);
        }
    }


    protected abstract byte[] decode(final byte[] source) throws Throwable;


}

