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


package com.googlecode.jinahya.xml.bind;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Marshallers {


    private static final Map<Class<?>, Method> MARSHAL_METHODS;


    static {
        final Map<Class<?>, Method> methods = new HashMap<Class<?>, Method>();
        for (Method method : Marshaller.class.getMethods()) {
            final int modifiers = method.getModifiers();
            if (Modifier.isStatic(modifiers)) {
                continue;
            }
            final Class<?> returnType = method.getReturnType();
            if (!Void.TYPE.equals(returnType)) {
                continue;
            }
            if (!"marshal".equals(method.getName())) {
                continue;
            }
            final Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 2) {
                continue;
            }
            if (!Object.class.equals(parameterTypes[0])) {
                continue;
            }
            methods.put(parameterTypes[1], method);
        }
        MARSHAL_METHODS = Collections.unmodifiableMap(methods);
    }


    /**
     * Marshals given {@code value} to {@code output} using specified
     * {@code marshaller}.
     *
     * @param <T> output type parameter
     * @param marshaller marshaller
     * @param value the value to marshal
     * @param outputType output type
     * @param output the output to which {@code value} is marshalled.
     *
     * @throws JAXBException if a JAXB error occurs.
     */
    public static <T> void marshal(final Marshaller marshaller,
                                   final Object value,
                                   final Class<? super T> outputType,
                                   final T output)
        throws JAXBException {

        if (marshaller == null) {
            throw new NullPointerException("marshaller");
        }

        if (value == null) {
            throw new NullPointerException("value");
        }

        if (outputType == null) {
            throw new NullPointerException("outputType");
        }

        if (output == null) {
            throw new NullPointerException("output");
        }

        final Method method = MARSHAL_METHODS.get(outputType);
        if (method == null) {
            throw new IllegalArgumentException(
                "can't marshal to " + outputType);
        }

        try {
            method.invoke(marshaller, value, output);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (InvocationTargetException ite) {
            final Throwable cause = ite.getCause();
            if (JAXBException.class.isInstance(cause)) {
                throw (JAXBException) cause;
            }
            throw new RuntimeException(cause);
        }
    }


    /**
     * Marshals given {@code value} to {@code output} using specified
     * {@code context}.
     *
     * @param <T> output type parameter
     * @param context context
     * @param value value to marshal
     * @param outputType output type
     * @param output the output to which {@code value} is marshalled.
     *
     * @throws JAXBException if a JAXB error occurs.
     */
    public static <T> void marshal(final JAXBContext context,
                                   final Object value,
                                   final Class<? super T> outputType,
                                   final T output)
        throws JAXBException {

        if (context == null) {
            throw new NullPointerException("context");
        }

        marshal(context.createMarshaller(), value, outputType, output);
    }


    /**
     * Marshals {@code value} to {@code output}.
     *
     * @param <T> output type parameter
     * @param value value to marshal
     * @param outputType output type
     * @param output the output to which {@code value} is marshalled.
     *
     * @throws JAXBException if a JAXB error occurs.
     */
    public static <T> void marshal(final Object value,
                                   final Class<? super T> outputType,
                                   final T output)
        throws JAXBException {

        if (value == null) {
            throw new NullPointerException("value");
        }

        final JAXBContext context = JAXBContext.newInstance(value.getClass());

        marshal(context, value, outputType, output);
    }


    /**
     * Marshals {@code value} to {@code output}.
     *
     * @param marshaller marshaller
     * @param value the value to marshal
     * @param output the output to which {@code value} is marshalled.
     *
     * @throws JAXBException if a JAXB error occurs.
     */
    public static void marshal(final Marshaller marshaller,
                               final Object value, final Object output)
        throws JAXBException {

        if (marshaller == null) {
            throw new NullPointerException("marshaller");
        }

        if (value == null) {
            throw new NullPointerException("value");
        }

        if (output == null) {
            throw new NullPointerException("output");
        }

        Method method = null;
        for (Entry<Class<?>, Method> entry : MARSHAL_METHODS.entrySet()) {
            if (entry.getKey().isAssignableFrom(output.getClass())) {
                method = entry.getValue();
                break;
            }
        }

        if (method == null) {
            throw new IllegalArgumentException(
                "can't marshal to " + output.getClass());
        }

        try {
            method.invoke(marshaller, value, output);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (InvocationTargetException ite) {
            final Throwable cause = ite.getCause();
            if (JAXBException.class.isInstance(cause)) {
                throw (JAXBException) cause;
            }
            throw new RuntimeException(cause);
        }
    }


    /**
     * Marshals given {@code value} to specified {@code output}.
     *
     * @param context context
     * @param value the value to marshal
     * @param output the output to which {@code value} is marshalled.
     *
     * @throws JAXBException if a JAXB error occurs.
     */
    public static void marshal(final JAXBContext context, final Object value,
                               final Object output)
        throws JAXBException {

        if (context == null) {
            throw new NullPointerException("context");
        }

        marshal(context.createMarshaller(), value, output);
    }


    /**
     * Marshals given {@code value} to specified {@code output}.
     *
     * @param value the value to marshal
     * @param output the output to which {@code value} is marshalled.
     *
     * @throws JAXBException if a JAXB error occurs.
     */
    public static void marshal(final Object value, final Object output)
        throws JAXBException {

        if (value == null) {
            throw new NullPointerException("value");
        }

        final JAXBContext context = JAXBContext.newInstance(value.getClass());

        marshal(context, value, output);
    }


    /**
     * Creates a new instance.
     */
    protected Marshallers() {

        super();
    }


}

