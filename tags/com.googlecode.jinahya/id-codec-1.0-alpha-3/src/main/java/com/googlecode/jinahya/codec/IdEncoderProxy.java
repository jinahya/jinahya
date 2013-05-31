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


package com.googlecode.jinahya.codec;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class IdEncoderProxy implements InvocationHandler {


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
    private static final Class<? extends Throwable> ENCODER_EXCEPTION;


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
     * Creates a new instance of
     * {@code org.apache.commons.codec.EncoderException}.
     *
     * @return a new instance.
     *
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private static Throwable newEncoderException()
        throws InstantiationException, IllegalAccessException {

        return ENCODER_EXCEPTION.newInstance();
    }


    private static Throwable newEncoderException(final String message)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return ENCODER_EXCEPTION.getConstructor(String.class).
            newInstance(message);
    }


    private static Throwable newEncoderException(final String message,
                                                 final Throwable cause)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return ENCODER_EXCEPTION.
            getConstructor(String.class, Throwable.class).
            newInstance(message, cause);
    }


    private static Throwable newEncoderException(final Throwable cause)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return ENCODER_EXCEPTION.getConstructor(Throwable.class).
            newInstance(cause);
    }


    /**
     * Returns a new proxy instance for
     * {@code org.apache.commons.codec.Encoder}.
     *
     * @return a new proxy instance.
     */
    public static Object newInstance() {

        return newInstance(new IdEncoder());
    }


    /**
     * Creates a new proxy instance for {@code org.apache.commons.codec.Encoder}
     * with given {@code encoder}.
     *
     * @param encoder the encoder to use
     *
     * @return a new proxy instance.
     */
    public static Object newInstance(final IdEncoder encoder) {

        if (encoder == null) {
            throw new NullPointerException("null encoder");
        }

        return Proxy.newProxyInstance(ENCODER.getClassLoader(),
                                      new Class<?>[]{ENCODER},
                                      new IdEncoderProxy(encoder));
    }


    /**
     * Creates a new instance.
     *
     * @param encoder the IdEncoder to use.
     */
    protected IdEncoderProxy(final IdEncoder encoder) {
        super();

        if (encoder == null) {
            throw new NullPointerException("null encoder");
        }

        this.encoder = encoder;
    }


    @Override
    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        if (ENCODE.equals(method)) {
            try {
                return encoder.encode((Long) args[0]);
            } catch (ClassCastException cce) {
                throw newEncoderException(cce);
            }
        }

        throw new UnsupportedOperationException("unsupported: " + method);
    }


    private final IdEncoder encoder;


}

