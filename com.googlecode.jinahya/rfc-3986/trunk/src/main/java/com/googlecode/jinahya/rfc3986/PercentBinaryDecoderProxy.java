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
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * Proxy for <code>org.apache.commons.codec.BinaryDecoder</code>.
 *
 * <link rel="stylesheet" type="text/css" href="{@docRoot}/resources/google-code-prettify/prettify.css"> 
 * <script src="{@docRoot}/resources/google-code-prettify/prettify.js" type="text/javascript"></script>
 * <script language="JavaScript">window.onload=function(){windowTitle();prettyPrint();}</script>
 * <pre class="prettyprint"><code class="language-java">
 * // create
 * final BinaryDecoder decoder = (BinaryDecoder)
 *     PercentBinaryDecoderProxy.newInstance();
 * 
 * // decode String
 * decoder.decode("");
 * 
 * // decode byte array
 * decoder.decode(new byte[0]);
 * </code></pre>
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @see <a href="http://goo.gl/l58d1"">BinaryDecoder (Apache Commons Codec)</a>
 */
public class PercentBinaryDecoderProxy implements InvocationHandler {


    private static final Class<?> BINARY_ENCODER_CLASS;


    static {
        try {
            BINARY_ENCODER_CLASS =
                Class.forName("org.apache.commons.codec.BinaryDecoder");
        } catch (ClassNotFoundException cnfe) {
            throw new InstantiationError(cnfe.getMessage());
        }
    }


    /**
     * Creates a new instance.
     *
     * @return a new proxy instance.
     */
    public static Object newInstance() {
        return Proxy.newProxyInstance(BINARY_ENCODER_CLASS.getClassLoader(),
                                      new Class<?>[]{BINARY_ENCODER_CLASS},
                                      new PercentBinaryDecoderProxy());
    }


    /**
     * Creates a new instance.
     */
    protected PercentBinaryDecoderProxy() {
        super();
    }


    @Override
    public Object invoke(final Object proxy, final Method method,
                         final Object[] args)
        throws Throwable {

        if (args[0] == null) {
            throw new NullPointerException("null source");
        }

        if (args[0] instanceof String) {
            try {
                return PercentDecoder.decode((String) args[0]);
            } catch (IOException ioe) {
                throw (Throwable) Class.forName(
                    "org.apache.commons.codec.DecoderException").
                    getConstructor(Throwable.class).newInstance(ioe);
            }
        }

        if (args[0] instanceof byte[]) {
            try {
                return PercentDecoder.decode((byte[]) args[0]);
            } catch (IOException ioe) {
                throw (Throwable) Class.forName(
                    "org.apache.commons.codec.DecoderException").
                    getConstructor(Throwable.class).newInstance(ioe);
            }
        }

        throw new IllegalArgumentException(
            "unacceptable source type: " + args[0].getClass());
    }
}

