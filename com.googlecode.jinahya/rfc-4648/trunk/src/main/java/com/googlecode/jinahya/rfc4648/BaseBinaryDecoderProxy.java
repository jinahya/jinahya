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


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * Proxy for BinaryDecoder.
 * 
 * <blockquote><pre>
 * // create
 * final BinaryDecoder decoder = (BinaryDecoder)
 *     BaseBinaryDecoderProxy.newInstance();
 *
 * // decode
 * decoder.decode(byte[]);
 * </pre></blockquote>
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BaseBinaryDecoderProxy implements InvocationHandler {


    private static final Class CLAZZ;


    static {
        try {
            CLAZZ = Class.forName("org.apache.commons.codec.BinaryDecoder");
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


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
     * Returns a new instance of BinaryEncoder proxy.
     *
     * @param base base instance to use
     * @return a new instance of BinaryEncoder proxy.
     */
    public static Object newInstance(final Base base) {

        if (base == null) {
            throw new NullPointerException("null base");
        }

        return Proxy.newProxyInstance(CLAZZ.getClassLoader(),
                                      new Class[]{CLAZZ},
                                      new BaseBinaryDecoderProxy(base));
    }


    /**
     * Creates a new instance.
     *
     * @param base base
     */
    private BaseBinaryDecoderProxy(final Base base) {
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

        final ByteArrayInputStream input =
            new ByteArrayInputStream((byte[]) args[0]);
        final ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            base.decode(input, output); // IOException
            output.flush(); // IOException
            return output.toByteArray();
        } catch (IOException ioe) {
            throw (Throwable) CONSTRUCTOR.newInstance(new Object[]{ioe});
        }
    }


    /** base. */
    private final Base base;
}

