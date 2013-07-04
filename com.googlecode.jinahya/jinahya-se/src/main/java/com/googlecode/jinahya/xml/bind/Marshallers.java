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


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Marshallers {


    protected static final List<Class<?>> MARSHAL_TARGET_TYPES;


    protected static final Map<Class<?>, Method> MARSHAL_METHODS;


    static {

        final List<Class<?>> marshalTargetTypes = new ArrayList<Class<?>>();
        final Map<Class<?>, Method> marshalMethods =
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

            marshalTargetTypes.add(parameterTypes[1]);
            marshalMethods.put(parameterTypes[1], method);
        }

        MARSHAL_TARGET_TYPES = Collections.unmodifiableList(marshalTargetTypes);
        MARSHAL_METHODS = Collections.unmodifiableMap(marshalMethods);
    }


    protected static Class<?> getMarshalTargetType(final Class<?> targetClass) {

        if (targetClass == null) {
            throw new NullPointerException("targetClass");
        }

        for (Class<?> marshalTargetType : MARSHAL_TARGET_TYPES) {
            if (marshalTargetType.isAssignableFrom(targetClass)) {
                return marshalTargetType;
            }
        }

        return null;
    }


    protected static Class<?> getMarshalTargetType(final Object target) {

        if (target == null) {
            throw new NullPointerException("target");
        }

        return getMarshalTargetType(target.getClass());
    }


    public static <T> void marshal(final Marshaller marshaller,
                                   final Object element,
                                   final Class<? super T> targetType,
                                   final T target)
        throws IllegalAccessException, InvocationTargetException {

        if (marshaller == null) {
            throw new NullPointerException("marshaller");
        }

        if (element == null) {
            throw new NullPointerException("element");
        }

        if (targetType == null) {
            throw new NullPointerException("targetType");
        }

        if (target == null) {
            throw new NullPointerException("target");
        }

        final Method method = MARSHAL_METHODS.get(targetType);

        if (method == null) {
            throw new IllegalArgumentException(
                "no method for targetType(" + targetType + ")");
        }

        method.invoke(marshaller, element, target);
    }


    public static void marshal(final Marshaller marshaller,
                               final Object element,
                               final Object target)
        throws IllegalAccessException, InvocationTargetException {

        if (marshaller == null) {
            throw new NullPointerException("marshaller");
        }

        if (element == null) {
            throw new NullPointerException("element");
        }

        if (target == null) {
            throw new NullPointerException("target");
        }

        final Class<?> marshalTargetType = getMarshalTargetType(target);

        if (marshalTargetType == null) {
            throw new IllegalArgumentException(
                "no targetType for target(" + target.getClass() + ")");
        }

        final Method method = MARSHAL_METHODS.get(marshalTargetType);

        method.invoke(marshaller, element, target);
    }


    public static <T> void marshal(final JAXBContext context,
                                   final Object element,
                                   final Class<? super T> targetType,
                                   final T target)
        throws JAXBException, IllegalAccessException,
               InvocationTargetException {

        if (context == null) {
            throw new NullPointerException("context");
        }

        if (element == null) {
            throw new NullPointerException("element");
        }

        if (targetType == null) {
            throw new NullPointerException("targetType");
        }

        if (target == null) {
            throw new NullPointerException("target");
        }

        marshal(context.createMarshaller(), element, targetType, target);
    }


    public static void marshal(final JAXBContext context, final Object element,
                               final Object target)
        throws JAXBException, IllegalAccessException,
               InvocationTargetException {

        if (context == null) {
            throw new NullPointerException("context");
        }

        if (element == null) {
            throw new NullPointerException("element");
        }

        if (target == null) {
            throw new NullPointerException("target");
        }

        marshal(context.createMarshaller(), element, target);
    }


    protected Marshallers() {

        super();
    }


}

