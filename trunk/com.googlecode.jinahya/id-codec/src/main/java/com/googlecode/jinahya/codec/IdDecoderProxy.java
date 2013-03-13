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


import com.googlecode.jinahya.util.IdDecoder;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class IdDecoderProxy implements InvocationHandler {


    /**
     * Class for
     * <code>org.apache.commons.codec.BinaryEncoder</code>.
     */
    private static final Class<?> CLASS_DECODER;


    static {
        try {
            CLASS_DECODER = Class.forName(
                "org.apache.commons.codec.Decoder");
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    private static final Method METHOD_DECODE;


    static {
        try {
            METHOD_DECODE = CLASS_DECODER.getMethod("decode", Object.class);
        } catch (NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }


    /**
     * Class for {@code org.apache.commons.codec.DecoderException}.
     */
    private static final Class<? extends Throwable> CLASS_DECODER_EXCEPTION;


    static {
        try {
            CLASS_DECODER_EXCEPTION = Class.forName(
                "org.apache.commons.codec.DecoderException").
                asSubclass(Throwable.class);
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    private static Throwable newDecoderException()
        throws InstantiationException, IllegalAccessException {

        return CLASS_DECODER_EXCEPTION.newInstance();
    }


    private static Throwable newDecoderException(final String message)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return CLASS_DECODER_EXCEPTION.getConstructor(String.class).
            newInstance(message);
    }


    private static Throwable newDecoderException(final String message,
                                                 final Throwable cause)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return CLASS_DECODER_EXCEPTION.
            getConstructor(String.class, Throwable.class).
            newInstance(message, cause);
    }


    private static Throwable newDecoderException(final Throwable cause)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return CLASS_DECODER_EXCEPTION.getConstructor(Throwable.class).
            newInstance(cause);
    }


    /**
     * Returns a new proxy for
     * <code>BinaryEncoder</code>.
     *
     * @return a new proxy for <code>BinaryEncoder</code>.
     */
    public static Object newInstance() {

        return newInstance(new IdDecoder());
    }


    /**
     * Creates a new proxy instance for
     * {@code org.apache.commons.codec.Decoder}.
     *
     * @param idDecoder the HexDecoder instance to use.
     *
     * @return a new proxy instance.
     */
    public static Object newInstance(final IdDecoder idDecoder) {

        if (idDecoder == null) {
            throw new IllegalArgumentException("null idDecoder");
        }

        return Proxy.newProxyInstance(CLASS_DECODER.getClassLoader(),
                                      new Class<?>[]{CLASS_DECODER},
                                      new IdDecoderProxy(idDecoder));
    }


    /**
     * Creates a new instance.
     *
     * @param idDecoder the IdDecoder to use.
     */
    protected IdDecoderProxy(final IdDecoder idDecoder) {
        super();

        if (idDecoder == null) {
            throw new NullPointerException("null idDecoder");
        }

        this.idDecoder = idDecoder;
    }


    @Override
    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        if (METHOD_DECODE.equals(method)) {
            try {
                return idDecoder.decode(String.class.cast(args[0]));
            } catch (ClassCastException cce) {
                throw newDecoderException(cce);
            }
        }

        throw new UnsupportedOperationException(
            "unsupported: [" + method + "]");
    }


    private final IdDecoder idDecoder;


}

