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
public class HexEncoderProxy implements InvocationHandler {


    /**
     * Class for
     * <code>org.apache.commons.codec.BinaryEncoder class</code>.
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
     * Returns a new instance of BinaryEncoder proxy.
     *
     * @param base base instance to use
     *
     * @return a new instance of BinaryEncoder proxy.
     */
    public static Object newInstance() {

        return Proxy.newProxyInstance(BINARY_ENCODER_CLASS.getClassLoader(),
                                      new Class[]{BINARY_ENCODER_CLASS},
                                      new HexEncoderProxy());
    }


    @Override
    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        return HexEncoder.encodeMultiple((byte[]) args[0]);
    }


}

