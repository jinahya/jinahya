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
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <D>
 */
public abstract class DecoderProxy<D> implements InvocationHandler {


    /**
     * Class for {@code org.apache.commons.codec.Decoder}.
     */
    private static final Class<?> DECODER;


    static {
        try {
            DECODER = Class.forName("org.apache.commons.codec.Decoder");
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    private static final Method DECODE;


    static {
        try {
            DECODE = DECODER.getMethod("decode", Object.class);
        } catch (NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }


    /**
     * Class for {@code org.apache.commons.codec.DecoderException}.
     */
    protected static final Class<? extends Throwable> DECODER_EXCEPTION;


    static {
        try {
            DECODER_EXCEPTION = Class.forName(
                "org.apache.commons.codec.DecoderException").
                asSubclass(Throwable.class);
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    protected static Throwable newDecoderException()
        throws InstantiationException, IllegalAccessException {

        return DECODER_EXCEPTION.newInstance();
    }


    protected static Throwable newDecoderException(final String message)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return DECODER_EXCEPTION
            .getConstructor(String.class)
            .newInstance(message);
    }


    protected static Throwable newDecoderException(final String message,
                                                   final Throwable cause)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return DECODER_EXCEPTION
            .getConstructor(String.class, Throwable.class)
            .newInstance(message, cause);
    }


    protected static Throwable newDecoderException(final Throwable cause)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return DECODER_EXCEPTION
            .getConstructor(Throwable.class)
            .newInstance(cause);
    }


    protected static <P extends DecoderProxy<D>, D> Object newInstance(
        final ClassLoader loader, final Class<?>[] interfaces,
        final Class<P> proxyType, final Class<D> decoderType, final D decoder) {

        if (loader == null) {
            throw new NullPointerException("loader");
        }

        if (interfaces == null) {
            throw new NullPointerException("interfaces");
        }

        if (proxyType == null) {
            throw new NullPointerException("proxyType");
        }

        if (decoderType == null) {
            throw new NullPointerException("decoderType");
        }

        if (decoder == null) {
            // ok?
        }

        try {
            final Constructor<P> constructor =
                proxyType.getConstructor(decoderType);
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            try {
                return Proxy.newProxyInstance(loader, interfaces,
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
     * Creates a new proxy instance for
     * {@code org.apache.commons.codec.Decoder}.
     *
     * @param <P> proxy type parameter
     * @param <D> decoder type parameter
     * @param proxyType proxy type
     * @param decoderType decoder type
     * @param decoder decoder instance
     *
     * @return
     *
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    protected static <P extends DecoderProxy<D>, D> Object newInstance(
        final Class<P> proxyType, final Class<D> decoderType, final D decoder) {

        return newInstance(DECODER.getClassLoader(), new Class<?>[]{DECODER},
                           proxyType, decoderType, decoder);
    }


    /**
     * Creates a new instance.
     *
     * @param decoder the decoder to use.
     */
    protected DecoderProxy(final D decoder) {

        super();

//        if (decoder == null) {
//            throw new NullPointerException("null decoder");
//        }

        this.decoder = decoder;
    }


    @Override
    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        if (DECODE.equals(method)) {
            return decode(decoder, args[0]);
        }

        throw new UnsupportedOperationException("unsupported: " + method);
    }


    protected abstract Object decode(final D decoder, final Object source)
        throws Throwable;


    /**
     * decoder instance.
     */
    protected final D decoder;


}

