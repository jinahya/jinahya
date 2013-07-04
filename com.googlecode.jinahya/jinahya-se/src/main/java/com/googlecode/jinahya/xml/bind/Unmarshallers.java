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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Unmarshallers {


    protected static final List<Class<?>> UNMARSHAL_SOURCE_TYPES;


    protected static final Map<Class<?>, Method> UNMARSHAL_METHODS;


    static {

        final List<Class<?>> unmarshalSourceTypes = new ArrayList<Class<?>>();
        final Map<Class<?>, Method> unmarshalMethods =
            new HashMap<Class<?>, Method>();

        for (Method method : Marshaller.class.getMethods()) {

            if (method.isBridge()) {
                continue;
            }

            if (method.isSynthetic()) {
                continue;
            }

            final int modifiers = method.getModifiers();

            if (!Modifier.isPublic(modifiers)) {
                continue;
            }

            if (!"unmarshal".equals(method.getName())) {
                continue;
            }

            final Class<?>[] parameterTypes = method.getParameterTypes();

            if (parameterTypes.length != 1) {
                continue;
            }

            final Class<?> returnType = method.getReturnType();

            if (!Object.class.equals(returnType)) {
                continue;
            }

            unmarshalSourceTypes.add(parameterTypes[1]);
            unmarshalMethods.put(parameterTypes[1], method);
        }

        UNMARSHAL_SOURCE_TYPES =
            Collections.unmodifiableList(unmarshalSourceTypes);
        UNMARSHAL_METHODS = Collections.unmodifiableMap(unmarshalMethods);
    }


    protected static Class<?> getUnmarshalSourceType(
        final Class<?> sourceClass) {

        if (sourceClass == null) {
            throw new NullPointerException("sourceClass");
        }

        for (Class<?> unmarshalSourceType : UNMARSHAL_SOURCE_TYPES) {
            if (unmarshalSourceType.isAssignableFrom(sourceClass)) {
                return unmarshalSourceType;
            }
        }

        return null;
    }


    protected static Class<?> getUnmarshalSourceType(final Object source) {

        if (source == null) {
            throw new NullPointerException("source");
        }

        return getUnmarshalSourceType(source.getClass());
    }


    public static <T> Object unmarshal(final Unmarshaller unmarshaller,
                                       final Class<? super T> sourceType,
                                       final T source)
        throws IllegalAccessException, InvocationTargetException {

        if (unmarshaller == null) {
            throw new NullPointerException("unmarshaller");
        }

        if (sourceType == null) {
            throw new NullPointerException("sourceType");
        }

        if (source == null) {
            throw new NullPointerException("source");
        }

        final Method method = UNMARSHAL_METHODS.get(sourceType);

        if (method == null) {
            throw new IllegalArgumentException(
                "no method for sourceType(" + sourceType + ")");
        }

        return method.invoke(unmarshaller, source);
    }


    public static Object unmarshal(final Unmarshaller unmarshaller,
                                   final Object source)
        throws IllegalAccessException, InvocationTargetException {

        if (unmarshaller == null) {
            throw new NullPointerException("unmarshaller");
        }

        if (source == null) {
            throw new NullPointerException("source");
        }

        final Class<?> unmarshalSourceType = getUnmarshalSourceType(source);

        if (unmarshalSourceType == null) {
            throw new IllegalArgumentException(
                "no sourceType for target(" + source.getClass() + ")");
        }

        final Method method = UNMARSHAL_METHODS.get(unmarshalSourceType);

        return method.invoke(unmarshaller, source);
    }


    public static <S> Object unmarshal(final JAXBContext context,
                                       final Class<? super S> sourceType,
                                       final S source)
        throws JAXBException, IllegalAccessException,
               InvocationTargetException {

        if (context == null) {
            throw new NullPointerException("context");
        }

        if (sourceType == null) {
            throw new NullPointerException("sourceType");
        }

        if (source == null) {
            throw new NullPointerException("source");
        }

        return unmarshal(context.createUnmarshaller(), sourceType, source);
    }


    public static Object unmarshal(final JAXBContext context,
                                   final Object source)
        throws JAXBException, IllegalAccessException,
               InvocationTargetException {

        if (context == null) {
            throw new NullPointerException("context");
        }

        if (source == null) {
            throw new NullPointerException("source");
        }

        return unmarshal(context.createUnmarshaller(), source);
    }


    protected Unmarshallers() {

        super();
    }


}

