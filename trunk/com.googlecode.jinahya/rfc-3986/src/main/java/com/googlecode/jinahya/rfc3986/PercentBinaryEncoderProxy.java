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


package com.googlecode.jinahya.rfc3986;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * Proxy for
 * <code>org.apache.commons.codec.BinaryEncoder</code>.
 *
 * <blockquote><pre>
 * // create
 * final BinaryEncoder encoder = (BinaryEncoder)
 *     PercentBinaryEncoderProxy.newInstance();
 *
 * // encode
 * encoder.encode(byte[]);
 * </blockquote></pre>
 *
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @see <a href="http://goo.gl/SId47">BinaryEncoder (Apache Commons Codec)</a>
 */
public class PercentBinaryEncoderProxy implements InvocationHandler {


    /**
     * BinaryEncoder Class.
     */
    private static final Class CLASS_BINARY_ENCODER;


    static {
        try {
            CLASS_BINARY_ENCODER = Class.forName(
                "org.apache.commons.codec.BinaryEncoder");
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    /**
     * EncoderException Constructor.
     */
    private static final Constructor CONSTRUCTOR_ENCODER_EXCEPTION_CONSTRUCTOR;


    static {
        try {
            CONSTRUCTOR_ENCODER_EXCEPTION_CONSTRUCTOR = Class.forName(
                "org.apache.commons.codec.EncoderException").getConstructor(
                new Class[]{Throwable.class});
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        } catch (NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }


    /**
     * Creates a new instance.
     *
     * @return a new proxy instance.
     */
    public static Object newInstance() {
        return Proxy.newProxyInstance(
            CLASS_BINARY_ENCODER.getClassLoader(),
            new Class[]{CLASS_BINARY_ENCODER},
            new PercentBinaryEncoderProxy());
    }


    /**
     * Creates a new instance.
     */
    protected PercentBinaryEncoderProxy() {
        super();
    }


    //@Override
    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        if (args[0] == null) {
            throw new NullPointerException("null source");
        }

        return null;
//        return PercentEncoder.encodeMultiple((byte[]) args[0]);
    }


}

