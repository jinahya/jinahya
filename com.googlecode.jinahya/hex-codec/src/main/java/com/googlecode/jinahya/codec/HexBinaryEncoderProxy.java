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
public class HexBinaryEncoderProxy implements InvocationHandler {


    /**
     * Class for {@code org.apache.commons.codec.BinaryEncoder}.
     */
    private static final Class<?> BINARY_ENCODER;


    static {
        try {
            BINARY_ENCODER = Class.forName(
                "org.apache.commons.codec.BinaryEncoder");
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    private static final Method ENCODE_OBJECT;


    static {
        try {
            ENCODE_OBJECT = BINARY_ENCODER.getMethod("encode", Object.class);
        } catch (NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }


    private static final Method ENCODE_BYTES;


    static {
        try {
            ENCODE_BYTES = BINARY_ENCODER.getMethod("encode", byte[].class);
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
     * {@code org.apache.commons.codec.BinaryEncoder}.
     *
     * @return a new proxy instance.
     */
    public static Object newInstance() {

        return newInstance(new HexEncoder());
    }


    /**
     * Returns a new proxy instance for
     * {@code org.apache.commons.codec.BinaryEncoder} with given
     * {@code encoder}.
     *
     * @param encoder the encoder to proxy
     *
     * @return a new proxy instance.
     */
    public static Object newInstance(final HexEncoder encoder) {

        if (encoder == null) {
            throw new NullPointerException("null encoder");
        }

        return Proxy.newProxyInstance(BINARY_ENCODER.getClassLoader(),
                                      new Class<?>[]{BINARY_ENCODER},
                                      new HexBinaryEncoderProxy(encoder));
    }


    /**
     * Creates a new instance.
     *
     * @param encoder the encoder to use.
     */
    protected HexBinaryEncoderProxy(final HexEncoder encoder) {
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

        if (ENCODE_BYTES.equals(method)) {
            return encoder.encode((byte[]) args[0]);
        }

        if (ENCODE_OBJECT.equals(method)) {

            if (args[0] instanceof byte[]) {
                return invoke(proxy, ENCODE_BYTES,
                              new Object[]{(byte[]) args[0]});
            }

            if (args[0] instanceof String) {
                final byte[] bytes = ((String) args[0]).getBytes("UTF-8");
                return invoke(proxy, ENCODE_BYTES, new Object[]{bytes});
            }

            throw newEncoderException("unacceptable argument: " + args[0]);
        }

        throw new UnsupportedOperationException("unsupported: " + method);
    }


    private final HexEncoder encoder;


}

