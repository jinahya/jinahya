/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class BossEncoderProxy implements InvocationHandler {


    private static final Class<?> BINARY_ENCODER_CLASS;


    static {
        try {
            BINARY_ENCODER_CLASS = Class.forName(
                "org.apache.commons.codec.BinaryEncoder");
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    public static Object newInstance() {

        return Proxy.newProxyInstance(BINARY_ENCODER_CLASS.getClassLoader(),
                                      new Class<?>[]{BINARY_ENCODER_CLASS},
                                      new BossEncoderProxy());
    }


    protected BossEncoderProxy() {
        super();

        this.bossEncoder = new BossEncoder();
    }


    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        if ("encode".equals(method.getName())) {
            return bossEncoder.encode((byte[]) args[0]);
        }

        throw new UnsupportedOperationException("unsupported: " + method);
    }


    private final BossEncoder bossEncoder;


}

