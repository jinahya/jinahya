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
public class HexDecoderProxy implements InvocationHandler {


    /**
     * Class for
     * <code>org.apache.commons.codec.BinaryEncoder</code>.
     */
    private static final Class BINARY_DECODER_CLASS;


    static {
        try {
            BINARY_DECODER_CLASS = Class.forName(
                "org.apache.commons.codec.BinaryDecoder");
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    /**
     * Class for
     * <code>org.apache.commons.codec.DecoderException</code>.
     */
    private static final Class<? extends Throwable> DNCODER_EXCEPTION_CLASS;


    static {
        try {
            DNCODER_EXCEPTION_CLASS = Class.forName(
                "org.apache.commons.codec.DecoderException").
                asSubclass(Throwable.class);
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    private static Throwable newDecoderException()
        throws InstantiationException, IllegalAccessException {

        return DNCODER_EXCEPTION_CLASS.newInstance();
    }


    private static Throwable newDecoderException(final String message)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return DNCODER_EXCEPTION_CLASS.getConstructor(String.class).
            newInstance(message);
    }


    private static Throwable newDecoderException(final String message,
                                                 final Throwable cause)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return DNCODER_EXCEPTION_CLASS.
            getConstructor(String.class, Throwable.class).
            newInstance(message, cause);
    }


    private static Throwable newDecoderException(final Throwable cause)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return DNCODER_EXCEPTION_CLASS.getConstructor(Throwable.class).
            newInstance(cause);
    }


    /**
     * Returns a new proxy for
     * <code>BinaryEncoder</code>.
     *
     * @return a new proxy for <code>BinaryEncoder</code>.
     */
    public static Object newInstance() {

        return Proxy.newProxyInstance(BINARY_DECODER_CLASS.getClassLoader(),
                                      new Class[]{BINARY_DECODER_CLASS},
                                      new HexDecoderProxy());
    }


    @Override
    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        return HexDecoder.decodeMultiple((byte[]) args[0]);
    }


}

