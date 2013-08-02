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
 * Abstract class for encoder proxies.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <T> encoder (delegate) type parameter.
 */
public abstract class AbstractEncoderProxy<T> implements InvocationHandler {


    /**
     * the class for {@code org.apache.commons.codec.EncoderException}.
     */
    protected static final Class<? extends Throwable> ENCODER_EXCEPTION;


    static {
        try {
            ENCODER_EXCEPTION = Class.forName(
                "org.apache.commons.codec.EncoderException").
                asSubclass(Throwable.class);
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    /**
     * Creates a new {@code EncoderException}.
     *
     * @return a new instance of {@code EncoderException}.
     */
    protected static Throwable newEncoderException() {

        try {
            return ENCODER_EXCEPTION.newInstance();
        } catch (InstantiationException ie) {
            throw new RuntimeException(ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        }
    }


    /**
     * Creates a new instance of
     * {@code org.apache.commons.codec.EncoderException} with given
     * {@code message}.
     *
     * @param message message
     *
     * @return a new instance of
     * {@code org.apache.commons.codec.EncoderException}.
     */
    protected static Throwable newEncoderException(final String message) {

        try {
            return ENCODER_EXCEPTION.getConstructor(String.class)
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


    /**
     * Creates a new instance of
     * {@code org.apache.commons.codec.EncoderException} with given
     * {@code cause}.
     *
     * @param cause cause
     *
     * @return a new instance of
     * {@code org.apache.commons.codec.EncoderException}.
     */
    protected static Throwable newEncoderException(final Throwable cause) {

        try {
            return ENCODER_EXCEPTION.getConstructor(Throwable.class)
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
     * Creates a new instance of
     * {@code org.apache.commons.codec.EncoderException} with given
     * {@code message} and {@code cause}.
     *
     * @param message message
     * @param cause cause
     *
     * @return a new instance of
     * {@code org.apache.commons.codec.EncoderException}.
     */
    protected static Throwable newEncoderException(final String message,
                                                   final Throwable cause) {

        try {
            return ENCODER_EXCEPTION
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


    /**
     * Creates a new proxy instance.
     *
     * @param <P> proxy type parameter
     * @param <T> encoder type parameter
     * @param loader class loader
     * @param interfaces interfaces
     * @param proxyType proxy type
     * @param decoderType encoder type
     * @param decoder encoder
     *
     * @return a new proxy instance.
     */
    protected static <P extends AbstractEncoderProxy<T>, T> Object newInstance(
        final ClassLoader loader, final Class<?>[] interfaces,
        final Class<P> proxyType, final Class<T> encoderType, final T encoder) {

        if (loader == null) {
            throw new NullPointerException("loader");
        }

        if (interfaces == null) {
            throw new NullPointerException("interfaces");
        }

        if (proxyType == null) {
            throw new NullPointerException("proxyType");
        }

        if (encoderType == null) {
            throw new NullPointerException("encoderType");
        }

        if (encoder == null) {
            // ok
        }

        try {
            final Constructor<P> constructor =
                proxyType.getDeclaredConstructor(encoderType);
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            try {
                return Proxy.newProxyInstance(
                    loader, interfaces, constructor.newInstance(encoder));
            } catch (IllegalAccessException iae) {
                throw new RuntimeException(iae);
            } catch (InstantiationException ie) {
                throw new RuntimeException(ie);
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
     * @param encoder the delegate. Maybe {@code null}.
     */
    protected AbstractEncoderProxy(final T encoder) {

        super();

        if (encoder == null) {
            // ok
        }

        this.encoder = encoder;
    }


    /**
     * The encoder instance passed in constructor. Maybe {@code null}.
     */
    protected final T encoder;


}
