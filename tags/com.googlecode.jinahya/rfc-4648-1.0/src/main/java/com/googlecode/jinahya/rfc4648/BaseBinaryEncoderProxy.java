/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
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


package com.googlecode.jinahya.rfc4648;


import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * Proxy for BinaryEncoder.
 *
 * <blockquote><pre>
 * // create
 * final BinaryEncoder encoder = (BinaryEncoder)
 *     BaseBinaryEncoderProxy.newInstance();
 *
 * // encode
 * encoder.encode(byte[]);
 * </pre></blockquote>
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public final class BaseBinaryEncoderProxy implements InvocationHandler {


    /**
     * BinaryEncoder class.
     */
    private static final Class BINARY_ENCODER_CLAZZ;


    static {
        try {
            BINARY_ENCODER_CLAZZ = Class.forName(
                "org.apache.commons.codec.BinaryEncoder");
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    /**
     * BinaryEncoder constructor.
     */
    private static final Constructor ENCODER_EXCEPTION_CONSTRUCTOR;


    static {
        try {
            ENCODER_EXCEPTION_CONSTRUCTOR = Class.forName(
                "org.apache.commons.codec.EncoderException").getConstructor(
                new Class[]{Throwable.class});
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        } catch (NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }


    /**
     * Returns a new instance of BinaryEncoder proxy.
     *
     * @param base base instance to use
     *
     * @return a new instance of BinaryEncoder proxy.
     */
    public static Object newInstance(final Base base) {

        if (base == null) {
            throw new NullPointerException("null base");
        }

        return Proxy.newProxyInstance(BINARY_ENCODER_CLAZZ.getClassLoader(),
                                      new Class[]{BINARY_ENCODER_CLAZZ},
                                      new BaseBinaryEncoderProxy(base));
    }


    /**
     * Creates a new instance.
     *
     * @param base the base to proxy
     */
    protected BaseBinaryEncoderProxy(final Base base) {
        super();

        if (base == null) {
            throw new NullPointerException("null base");
        }

        this.base = base;
    }


    //@Override
    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        try {
            return base.encode((byte[]) args[0]);
        } catch (IOException ioe) {
            throw (Throwable) ENCODER_EXCEPTION_CONSTRUCTOR.newInstance(
                new Object[]{ioe});
        }
    }


    /**
     * base.
     */
    private final Base base;


}

