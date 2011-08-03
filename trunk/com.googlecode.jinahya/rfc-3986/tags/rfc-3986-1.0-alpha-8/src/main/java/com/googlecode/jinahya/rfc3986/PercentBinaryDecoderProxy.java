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


import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * Proxy for <code>org.apache.commons.codec.BinaryDecoder</code>.
 *
 * <blockquote><pre>
 * // create
 * final BinaryDecoder decoder = (BinaryDecoder)
 *     PercentBinaryDecoderProxy.newInstance();
 * 
 * // decode
 * decoder.decode(byte[]);
 * </code></pre>
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @see <a href="http://goo.gl/l58d1"">BinaryDecoder (Apache Commons Codec)</a>
 */
public class PercentBinaryDecoderProxy implements InvocationHandler {


    /** BinaryDecoder Class. */
    private static final Class CLAZZ;


    static {
        try {
            CLAZZ = Class.forName("org.apache.commons.codec.BinaryDecoder");
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    /** DecoderException Constructor. */
    private static final Constructor CONSTRUCTOR;


    static {
        try {
            CONSTRUCTOR = Class.forName(
                "org.apache.commons.codec.DecoderException").getConstructor(
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
            CLAZZ.getClassLoader(), new Class[]{CLAZZ},
            new PercentBinaryDecoderProxy());
    }


    /**
     * Creates a new instance.
     */
    protected PercentBinaryDecoderProxy() {
        super();
    }


    //@Override
    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        if (args[0] == null) {
            throw new NullPointerException("null source");
        }

        try {
            return PercentDecoder.decode((byte[]) args[0]);
        } catch (IOException ioe) {
            throw (Throwable) CONSTRUCTOR.newInstance(new Object[]{ioe});
        }
    }
}

