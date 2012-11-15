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
     * Class for
     * <code>org.apache.commons.codec.BinaryEncoder</code>.
     */
    private static final Class<?> BINARY_ENCODER_CLASS;


    static {
        try {
            BINARY_ENCODER_CLASS = Class.forName(
                "org.apache.commons.codec.BinaryEncoder");
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    private static final Method ENCODE_OBJECT_METHOD;


    static {
        try {
            ENCODE_OBJECT_METHOD =
                BINARY_ENCODER_CLASS.getMethod("encode", Object.class);
        } catch (NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }


    private static final Method ENCODE_BYTES_METHOD;


    static {
        try {
            ENCODE_BYTES_METHOD =
                BINARY_ENCODER_CLASS.getMethod("encode", byte[].class);
        } catch (NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }


    /**
     * Class for
     * <code>org.apache.commons.codec.EncoderException</code>.
     */
    private static final Class<? extends Throwable> ENCODER_EXCEPTION_CLASS;


    static {
        try {
            ENCODER_EXCEPTION_CLASS = Class.forName(
                "org.apache.commons.codec.EncoderException").
                asSubclass(Throwable.class);
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    /**
     * Creates a new instance of
     * <code>org.apache.commons.codec.EncoderException</code>.
     *
     * @return a new instance.
     *
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private static Throwable newEncoderException()
        throws InstantiationException, IllegalAccessException {

        return ENCODER_EXCEPTION_CLASS.newInstance();
    }


    private static Throwable newEncoderException(final String message)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return ENCODER_EXCEPTION_CLASS.getConstructor(String.class).
            newInstance(message);
    }


    private static Throwable newEncoderException(final String message,
                                                 final Throwable cause)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return ENCODER_EXCEPTION_CLASS.
            getConstructor(String.class, Throwable.class).
            newInstance(message, cause);
    }


    private static Throwable newEncoderException(final Throwable cause)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return ENCODER_EXCEPTION_CLASS.getConstructor(Throwable.class).
            newInstance(cause);
    }


    /**
     * Returns a new proxy instance for
     * <code>org.apache.commons.codec.BinaryEncoder</code>.
     *
     * @return a new proxy instance.
     */
    public static Object newInstance() {

        return newInstance(new HexEncoder());
    }


    /**
     * Returns a new proxy instance for
     * <code>org.apache.commons.codec.BinaryEncoder</code> with given
     * <code>hexEncoder</code>.
     *
     * @param hexEncoder the HexEncoder to use
     *
     * @return a new proxy instance.
     */
    public static Object newInstance(final HexEncoder hexEncoder) {

        if (hexEncoder == null) {
            throw new IllegalArgumentException("null hexEncoder");
        }

        return Proxy.newProxyInstance(BINARY_ENCODER_CLASS.getClassLoader(),
                                      new Class<?>[]{BINARY_ENCODER_CLASS},
                                      new HexBinaryEncoderProxy(hexEncoder));
    }


    /**
     * Creates a new instance.
     *
     * @param hexEncoder the HexEncoder to use.
     */
    protected HexBinaryEncoderProxy(final HexEncoder hexEncoder) {
        super();

        if (hexEncoder == null) {
            throw new IllegalArgumentException("null hexEncoder");
        }

        this.hexEncoder = hexEncoder;
    }


    @Override
    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        if (ENCODE_BYTES_METHOD.equals(method)) {
            return hexEncoder.encode((byte[]) args[0]);
        }

        if (ENCODE_OBJECT_METHOD.equals(method)) {
            if (args[0] instanceof byte[]) {
                return invoke(proxy, ENCODE_BYTES_METHOD,
                              new Object[]{(byte[]) args[0]});
            } else {
                try {
                    final byte[] bytes = ((String) args[0]).getBytes("UTF-8");
                    return invoke(proxy, ENCODE_BYTES_METHOD,
                                  new Object[]{bytes});
                } catch (ClassCastException cce) {
                    throw newEncoderException(cce);
                }
            }
        }

        throw new UnsupportedOperationException(
            "unsupported: [" + method + "]");
    }


    private final HexEncoder hexEncoder;


}

