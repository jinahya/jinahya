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


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * An abstract class for {@code org.apache.commons.codec.Encoder} proxies.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <T> encoder (delegate) type parameter.
 */
public abstract class EncoderProxy<T> extends AbstractEncoderProxy<T> {


    /**
     * The class for {@code org.apache.commons.codec.Encoder}.
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
     * The method for {@code encode(Ljava/lang/Object;)Ljava/lang/Object;}.
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
     * Creates a new proxy instance for
     * {@code org.apache.commons.codec.Encoder}.
     *
     * @param <P> proxy type parameter
     * @param <T> encoder (delegate) type parameter
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
    protected static <P extends AbstractEncoderProxy<T>, T> Object newInstance(
        final Class<P> proxyType, final Class<T> encoderType, final T encoder) {

        if (proxyType != null
            && !EncoderProxy.class.isAssignableFrom(proxyType)) {
            throw new IllegalArgumentException(
                "proxyType(" + proxyType + ") is not assignable to "
                + EncoderProxy.class);
        }

        return newInstance(ENCODER.getClassLoader(), new Class<?>[]{ENCODER},
                           proxyType, encoderType, encoder);
    }


    /**
     * Creates a new instance.
     *
     * @param encoder the delegate
     */
    protected EncoderProxy(final T encoder) {

        super(encoder);
    }


    @Override
    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        if (ENCODE.equals(method)) {
            return encode(args[0]);
        }

        throw new UnsupportedOperationException("unsupported: " + method);
    }


    /**
     * Encodes given {@code source}.
     *
     * @param source source to encode.
     *
     * @return encoding result.
     *
     * @throws Throwable if failed to encode.
     */
    protected abstract Object encode(final Object source) throws Throwable;


}

