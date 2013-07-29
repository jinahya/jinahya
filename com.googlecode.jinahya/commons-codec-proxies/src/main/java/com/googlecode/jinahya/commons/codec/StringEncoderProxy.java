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


package com.googlecode.jinahya.commons.codec;


import java.lang.reflect.Method;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <E> encoder (delegate) type parameter
 */
public abstract class StringEncoderProxy<E> extends EncoderProxy<E> {


    /**
     * Class for {@code org.apache.commons.codec.StringEncoder}.
     */
    private static final Class<?> ENCODER;


    static {
        try {
            ENCODER = Class.forName("org.apache.commons.codec.StringEncoder");
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    /**
     * method for {@code encode(Ljava/lang/String;)Ljava/lang/String;}.
     */
    private static final Method ENCODE;


    static {
        try {
            ENCODE = ENCODER.getMethod("encode", String.class);
        } catch (NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }


    protected static <P extends AbstractEncoderProxy<E>, E> Object newInstance(
        final Class<P> proxyType, final Class<E> encoderType, final E encoder) {

        if (proxyType != null
            && !StringEncoderProxy.class.isAssignableFrom(proxyType)) {
            throw new IllegalArgumentException(
                "proxyType(" + proxyType + ") is not assignable to "
                + StringEncoderProxy.class);
        }

        return newInstance(ENCODER.getClassLoader(),
                           new Class<?>[]{ENCODER},
                           proxyType, encoderType, encoder);
    }


    /**
     * Creates a new instance.
     *
     * @param encoder encoding delegate
     */
    protected StringEncoderProxy(final E encoder) {

        super(encoder);
    }


    @Override
    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        if (ENCODE.equals(method)) {
            return encode((String) args[0]);
        }

        return super.invoke(proxy, method, args);
    }


    @Override
    protected Object encode(final Object source) throws Throwable {

        if (source == null) {
            throw newEncoderException("null source"); // documented
        }

        try {
            return encode((String) source);
        } catch (ClassCastException cce) {
            throw newEncoderException(cce);
        }
    }


    protected abstract String encode(final String source) throws Throwable;


}

