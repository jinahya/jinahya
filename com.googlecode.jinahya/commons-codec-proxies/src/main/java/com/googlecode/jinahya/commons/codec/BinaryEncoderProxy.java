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
 * @param <E> encoder type parameter
 */
public abstract class BinaryEncoderProxy<E> extends EncoderProxy<E> {


    /**
     * Class for {@code org.apache.commons.codec.BinaryEncoder}.
     */
    private static final Class<?> ENCODER;


    static {
        try {
            ENCODER = Class.forName(
                "org.apache.commons.codec.BinaryEncoder");
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    private static final Method ENCODE;


    static {
        try {
            ENCODE = ENCODER.getMethod("encode", byte[].class);
        } catch (NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }


    protected static <P extends EncoderProxy<E>, E> Object newInstance(
        final Class<P> proxyType, final Class<E> encoderType, final E encoder) {

//        if (decoder == null) {
//            throw new IllegalArgumentException("null decoder");
//        }

        try {
            final Constructor<P> constructor =
                proxyType.getDeclaredConstructor(encoderType);
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            try {
                return Proxy.newProxyInstance(ENCODER.getClassLoader(),
                                              new Class<?>[]{ENCODER},
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
     * Creates a new instance.
     *
     * @param encoder delegate
     */
    protected BinaryEncoderProxy(final E encoder) {

        super(encoder);
    }


    @Override
    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        if (ENCODE.equals(method)) {
            return encode(encoder, (byte[]) args[0]);
        }

        return super.invoke(proxy, method, args);
    }


    @Override
    protected Object encode(final E encoder, final Object source)
        throws Throwable {

        if (source == null) {
            //throw new NullPointerException("source");
            throw newEncoderException("null source"); // documented
        }

        try {
            return encode(encoder, (byte[]) source);
        } catch (ClassCastException cce) {
            throw newEncoderException(cce);
        }
    }


    protected abstract byte[] encode(final E decoder, final byte[] source)
        throws Throwable;


}

