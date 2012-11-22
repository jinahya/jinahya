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
public class HexBinaryDecoderProxy implements InvocationHandler {


    /**
     * Class for
     * <code>org.apache.commons.codec.BinaryEncoder</code>.
     */
    private static final Class<?> CLASS_BINARY_DECODER;


    static {
        try {
            CLASS_BINARY_DECODER = Class.forName(
                "org.apache.commons.codec.BinaryDecoder");
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    static {
        try {
            METHOD_DECODE_OBJECT =
                CLASS_BINARY_DECODER.getMethod("decode", Object.class);
        } catch (NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }


    private static final Method METHOD_DECODE_BYTES;


    static {
        try {
            METHOD_DECODE_BYTES =
                CLASS_BINARY_DECODER.getMethod("decode", byte[].class);
        } catch (NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }


    private static final Method METHOD_DECODE_OBJECT;


    /**
     * Class for
     * <code>org.apache.commons.codec.DecoderException</code>.
     */
    private static final Class<? extends Throwable> CLASS_DNCODER_EXCEPTION;


    static {
        try {
            CLASS_DNCODER_EXCEPTION = Class.forName(
                "org.apache.commons.codec.DecoderException").
                asSubclass(Throwable.class);
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    private static Throwable newDecoderException()
        throws InstantiationException, IllegalAccessException {

        return CLASS_DNCODER_EXCEPTION.newInstance();
    }


    private static Throwable newDecoderException(final String message)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return CLASS_DNCODER_EXCEPTION.getConstructor(String.class).
            newInstance(message);
    }


    private static Throwable newDecoderException(final String message,
                                                 final Throwable cause)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return CLASS_DNCODER_EXCEPTION.
            getConstructor(String.class, Throwable.class).
            newInstance(message, cause);
    }


    private static Throwable newDecoderException(final Throwable cause)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return CLASS_DNCODER_EXCEPTION.getConstructor(Throwable.class).
            newInstance(cause);
    }


    /**
     * Returns a new proxy for
     * <code>BinaryEncoder</code>.
     *
     * @return a new proxy for <code>BinaryEncoder</code>.
     */
    public static Object newInstance() {

        return newInstance(new HexDecoder());
    }


    /**
     * Creates a new proxy instance for
     * <code>org.apache.commons.codec.BinaryDecoder</code>.
     *
     * @param hexDecoder the HexDecoder instance to use.
     *
     * @return a new proxy instance.
     */
    public static Object newInstance(final HexDecoder hexDecoder) {

        if (hexDecoder == null) {
            throw new IllegalArgumentException("null hexDecoder");
        }

        return Proxy.newProxyInstance(CLASS_BINARY_DECODER.getClassLoader(),
                                      new Class<?>[]{CLASS_BINARY_DECODER},
                                      new HexBinaryDecoderProxy(hexDecoder));
    }


    /**
     * Creates a new instance.
     *
     * @param hexDecoder the HexDecoder to use.
     */
    protected HexBinaryDecoderProxy(final HexDecoder hexDecoder) {
        super();

        if (hexDecoder == null) {
            throw new IllegalArgumentException("null hexDecoder");
        }

        this.hexDecoder = hexDecoder;
    }


    @Override
    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        if (METHOD_DECODE_BYTES.equals(method)) {
            return hexDecoder.decode((byte[]) args[0]);
        }

        if (METHOD_DECODE_OBJECT.equals(method)) {
            if (args[0] instanceof byte[]) {
                return invoke(proxy, METHOD_DECODE_BYTES,
                              new Object[]{(byte[]) args[0]});
            } else {
                try {
                    return invoke(
                        proxy, METHOD_DECODE_BYTES,
                        new Object[]{((String) args[0]).getBytes("US-ASCII")});
                } catch (ClassCastException cce) {
                    throw newDecoderException(cce);
                }
            }
        }

        throw new UnsupportedOperationException(
            "unsupported: [" + method + "]");
    }


    private final HexDecoder hexDecoder;


}

