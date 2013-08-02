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
 * An abstract class for {@code org.apache.commons.codec.BinaryEncoder} proxies.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <T> encoder(delegate) type parameter
 */
public abstract class BinaryEncoderProxy<T> extends EncoderProxy<T> {


    /**
     * {@code org.apache.commons.codec.BinaryEncoder}.
     */
    private static final Class<?> ENCODER;


    static {
        try {
            ENCODER = Class.forName("org.apache.commons.codec.BinaryEncoder");
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    /**
     * {@code encode([B)[B}.
     */
    private static final Method ENCODE;


    static {
        try {
            ENCODE = ENCODER.getMethod("encode", byte[].class);
        } catch (NoSuchMethodException nsme) {
            throw new InstantiationError(nsme.getMessage());
        }
    }


    protected static <P extends AbstractEncoderProxy<T>, T> Object newInstance(
        final Class<P> proxyType, final Class<T> encoderType, final T encoder) {

        if (!BinaryEncoderProxy.class.isAssignableFrom(proxyType)) {
            throw new IllegalArgumentException(
                "proxyType(" + proxyType + ") is not assignable to "
                + BinaryEncoderProxy.class);
        }

        return newInstance(ENCODER.getClassLoader(), new Class<?>[]{ENCODER},
                           proxyType, encoderType, encoder);
    }


    /**
     * Creates a new instance.
     *
     * @param encoder delegate
     */
    protected BinaryEncoderProxy(final T encoder) {

        super(encoder);
    }


    @Override
    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        if (ENCODE.equals(method)) {
            return encode((byte[]) args[0]);
        }

        return super.invoke(proxy, method, args);
    }


    @Override
    protected Object encode(final Object source) throws Throwable {

        try {
            return encode((byte[]) source);
        } catch (ClassCastException cce) {
            throw newEncoderException(cce);
        }
    }


    /**
     * Encodes given {@code source}.
     *
     * @param source source to encode.
     *
     * @return encoded output.
     *
     * @throws Throwable if an error occurs.
     */
    protected abstract byte[] encode(final byte[] source) throws Throwable;


}
