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
     * Class for {@code org.apache.commons.codec.BinaryDecoder}.
     */
    private static final Class<?> BINARY_DECODER;


    static {
        try {
            BINARY_DECODER = Class.forName(
                "org.apache.commons.codec.BinaryDecoder");
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    private static final Method DECODE_OBJECT;


    static {
        try {
            DECODE_OBJECT = BINARY_DECODER.getMethod("decode", Object.class);
        } catch (NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }


    private static final Method DECODE_BYTES;


    static {
        try {
            DECODE_BYTES = BINARY_DECODER.getMethod("decode", byte[].class);
        } catch (NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }


    /**
     * Class for {@code org.apache.commons.codec.DecoderException}.
     */
    private static final Class<? extends Throwable> DECODER_EXCEPTION;


    static {
        try {
            DECODER_EXCEPTION = Class.forName(
                "org.apache.commons.codec.DecoderException").
                asSubclass(Throwable.class);
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    private static Throwable newDecoderException()
        throws InstantiationException, IllegalAccessException {

        return DECODER_EXCEPTION.newInstance();
    }


    private static Throwable newDecoderException(final String message)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return DECODER_EXCEPTION.getConstructor(String.class).
            newInstance(message);
    }


    private static Throwable newDecoderException(final String message,
                                                 final Throwable cause)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return DECODER_EXCEPTION.
            getConstructor(String.class, Throwable.class).
            newInstance(message, cause);
    }


    private static Throwable newDecoderException(final Throwable cause)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return DECODER_EXCEPTION.getConstructor(Throwable.class).
            newInstance(cause);
    }


    /**
     * Returns a new proxy for {@code org.apache.commons.codec.BinaryDecoder}.
     *
     * @return a new proxy for {@code org.apache.commons.codec.BinaryDecoder}.
     */
    public static Object newInstance() {

        return newInstance(new HexDecoder());
    }


    /**
     * Creates a new proxy instance for
     * {@code org.apache.commons.codec.BinaryDecoder}.
     *
     * @param decoder the decoder to proxy.
     *
     * @return a new proxy instance.
     */
    public static Object newInstance(final HexDecoder decoder) {

        if (decoder == null) {
            throw new NullPointerException("null decoder");
        }

        return Proxy.newProxyInstance(BINARY_DECODER.getClassLoader(),
                                      new Class<?>[]{BINARY_DECODER},
                                      new HexBinaryDecoderProxy(decoder));
    }


    /**
     * Creates a new instance.
     *
     * @param decoder the decoder to proxy.
     */
    protected HexBinaryDecoderProxy(final HexDecoder decoder) {
        super();

        if (decoder == null) {
            throw new NullPointerException("null decoder");
        }

        this.decoder = decoder;
    }


    @Override
    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        if (DECODE_BYTES.equals(method)) {
            return decoder.decode((byte[]) args[0]);
        }

        if (DECODE_OBJECT.equals(method)) {

            if (args[0] instanceof byte[]) {
                return invoke(proxy, DECODE_BYTES,
                              new Object[]{(byte[]) args[0]});
            }

            if (args[0] instanceof String) {
                final byte[] bytes = ((String) args[0]).getBytes("US-ASCII");
                return invoke(proxy, DECODE_BYTES, new Object[]{bytes});
            }

            throw newDecoderException("unacceptable argument: " + args[0]);
        }

        throw new UnsupportedOperationException("unsupported: " + method);
    }


    private final HexDecoder decoder;


}

