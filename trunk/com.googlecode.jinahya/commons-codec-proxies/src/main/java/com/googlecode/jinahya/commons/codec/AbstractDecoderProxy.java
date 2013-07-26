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
import java.lang.reflect.Proxy;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <T> decoder(delegate) type parameter.
 */
public abstract class AbstractDecoderProxy<T> implements InvocationHandler {


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

        try {
            return DECODER_EXCEPTION.newInstance();
        } catch (InstantiationException ie) {
            throw new RuntimeException(ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        }
    }


    protected static Throwable newDecoderException(final String message) {

        try {
            return DECODER_EXCEPTION.getConstructor(String.class)
                .newInstance(message);
        } catch (NoSuchMethodException nsme) {
            throw new RuntimeException(nsme);
        } catch (InstantiationException ie) {
            throw new RuntimeException(ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    protected static Throwable newDecoderException(final String message,
                                                   final Throwable cause) {

        try {
            return DECODER_EXCEPTION
                .getConstructor(String.class, Throwable.class)
                .newInstance(message, cause);
        } catch (NoSuchMethodException nsme) {
            throw new RuntimeException(nsme);
        } catch (InstantiationException ie) {
            throw new RuntimeException(ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    protected static Throwable newDecoderException(final Throwable cause) {

        try {
            return DECODER_EXCEPTION.getConstructor(Throwable.class)
                .newInstance(cause);
        } catch (NoSuchMethodException nsme) {
            throw new RuntimeException(nsme);
        } catch (InstantiationException ie) {
            throw new RuntimeException(ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    /**
     *
     * @param <P>
     * @param <T>
     * @param loader
     * @param interfaces
     * @param proxyType
     * @param decoderType
     * @param decoder
     *
     * @return
     */
    protected static <P extends AbstractDecoderProxy<T>, T> Object newInstance(
        final ClassLoader loader, final Class<?>[] interfaces,
        final Class<P> proxyType, final Class<T> decoderType, final T decoder) {

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
                proxyType.getDeclaredConstructor(decoderType);
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            try {
                return Proxy.newProxyInstance(
                    loader, interfaces, constructor.newInstance(decoder));
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
    protected AbstractDecoderProxy(final T decoder) {

        super();

        if (decoder == null) {
            // okd
        }

        this.decoder = decoder;
    }


    /**
     * decoder instance.
     */
    protected final T decoder;


}

