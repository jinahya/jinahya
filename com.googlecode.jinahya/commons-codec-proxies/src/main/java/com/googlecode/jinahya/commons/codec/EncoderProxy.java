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
 * @param <E> encoder(delegate) type parameter
 */
public abstract class EncoderProxy<E> implements InvocationHandler {


    /**
     * Class for {@code org.apache.commons.codec.Encoder}.
     */
    private static final Class<?> ENCODER;


    static {
        try {
            ENCODER = Class.forName("org.apache.commons.codec.Encoder");
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    /**
     * Method for {@code encode(Ljava/lang/Object;)Ljava/lang/Object;}
     */
    private static final Method ENCODE;


    static {
        try {
            ENCODE = ENCODER.getMethod("encode", Object.class);
        } catch (NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }


    /**
     * Class for {@code org.apache.commons.codec.EncoderException}.
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


    protected static Throwable newEncoderException()
        throws InstantiationException, IllegalAccessException {

        return ENCODER_EXCEPTION.newInstance();
    }


    protected static Throwable newEncoderException(final String message)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return ENCODER_EXCEPTION
            .getConstructor(String.class)
            .newInstance(message);
    }


    protected static Throwable newEncoderException(final String message,
                                                   final Throwable cause)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return ENCODER_EXCEPTION
            .getConstructor(String.class, Throwable.class)
            .newInstance(message, cause);
    }


    protected static Throwable newEncoderException(final Throwable cause)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return ENCODER_EXCEPTION
            .getConstructor(Throwable.class)
            .newInstance(cause);
    }


    static <P extends EncoderProxy<E>, E> Object newInstance(
        final ClassLoader loader, final Class<?>[] interfaces,
        final Class<P> proxyType, final Class<E> encoderType, final E encoder) {

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
                return Proxy.newProxyInstance(loader, interfaces,
                                              constructor.newInstance(encoder));
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
     * Creates a new proxy instance for
     * {@code org.apache.commons.codec.Encoder}.
     *
     * @param <P> proxy type parameter
     * @param <E> encoder type parameter
     * @param proxyType proxy type
     * @param encoderType encoder type
     * @param encoder encoder instance
     *
     * @return
     *
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    protected static <P extends EncoderProxy<E>, E> Object newInstance(
        final Class<P> proxyType, final Class<E> encoderType, final E encoder) {

        return newInstance(ENCODER.getClassLoader(), new Class<?>[]{ENCODER},
                           proxyType, encoderType, encoder);
    }


    /**
     * Creates a new instance.
     *
     * @param encoder the delegate
     */
    protected EncoderProxy(final E encoder) {

        super();

//        if (encoder == null) {
//            throw new NullPointerException("null encoder");
//        }

        this.encoder = encoder;
    }


    @Override
    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        if (ENCODE.equals(method)) {
            return encode(encoder, args[0]);
        }

        throw new UnsupportedOperationException("unsupported: " + method);
    }


    protected abstract Object encode(final E encoder, final Object source)
        throws Throwable;


    /**
     * encoder instance.
     */
    protected final E encoder;


}

