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


import com.googlecode.jinahya.util.IdEncoder;
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
     * Class for
     * <code>org.apache.commons.codec.BinaryEncoder</code>.
     */
    private static final Class<?> CLASS_ENCODER;


    static {
        try {
            CLASS_ENCODER = Class.forName(
                "org.apache.commons.codec.Encoder");
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    private static final Method METHOD_ENCODE;


    static {
        try {
            METHOD_ENCODE = CLASS_ENCODER.getMethod("encode", Object.class);
        } catch (NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }


    /**
     * Class for {@code org.apache.commons.codec.EncoderException}.
     */
    private static final Class<? extends Throwable> CLASS_ENCODER_EXCEPTION;


    static {
        try {
            CLASS_ENCODER_EXCEPTION = Class.forName(
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

        return CLASS_ENCODER_EXCEPTION.newInstance();
    }


    private static Throwable newEncoderException(final String message)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return CLASS_ENCODER_EXCEPTION.getConstructor(String.class).
            newInstance(message);
    }


    private static Throwable newEncoderException(final String message,
                                                 final Throwable cause)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return CLASS_ENCODER_EXCEPTION.
            getConstructor(String.class, Throwable.class).
            newInstance(message, cause);
    }


    private static Throwable newEncoderException(final Throwable cause)
        throws NoSuchMethodException, InstantiationException,
               IllegalAccessException, InvocationTargetException {

        return CLASS_ENCODER_EXCEPTION.getConstructor(Throwable.class).
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
     * Returns a new proxy instance for
     * <code>org.apache.commons.codec.BinaryEncoder</code> with given
     * <code>hexEncoder</code>.
     *
     * @param idEncoder the HexEncoder to use
     *
     * @return a new proxy instance.
     */
    public static Object newInstance(final IdEncoder idEncoder) {

        if (idEncoder == null) {
            throw new NullPointerException("null idEncoder");
        }

        return Proxy.newProxyInstance(CLASS_ENCODER.getClassLoader(),
                                      new Class<?>[]{CLASS_ENCODER},
                                      new IdEncoderProxy(idEncoder));
    }


    /**
     * Creates a new instance.
     *
     * @param idEncoder the IdEncoder to use.
     */
    protected IdEncoderProxy(final IdEncoder idEncoder) {
        super();

        if (idEncoder == null) {
            throw new NullPointerException("null idEncoder");
        }

        this.idEncoder = idEncoder;
    }


    @Override
    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        if (METHOD_ENCODE.equals(method)) {
            try {
                return idEncoder.encode((Long) args[0]);
            } catch (ClassCastException cce) {
                throw newEncoderException(cce);
            }
        }

        throw new UnsupportedOperationException(
            "unsupported: [" + method + "]");
    }


    private final IdEncoder idEncoder;


}

