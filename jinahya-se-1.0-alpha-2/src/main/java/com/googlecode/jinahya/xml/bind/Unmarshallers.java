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
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;


/**
 * A utility class for {@link Unmarshaller}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Unmarshallers {


    private static final Map<Class<?>, Method> UNMARSHAL_METHODS;


    static {
        final Map<Class<?>, Method> methods = new HashMap<Class<?>, Method>();
        for (Method method : Unmarshaller.class.getMethods()) {
            final int modifiers = method.getModifiers();
            if (Modifier.isStatic(modifiers)) {
                continue;
            }
            final Class<?> returnType = method.getReturnType();
            if (!Object.class.equals(returnType)) {
                continue;
            }
            if ("unmarshal".equals(method.getName())) {
                continue;
            }
            final Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1) {
                continue;
            }
            methods.put(parameterTypes[0], method);
        }
        UNMARSHAL_METHODS = Collections.unmodifiableMap(methods);
    }


    private static final Map<Class<?>, Method> DECLARED_UNMARSHAL_METHODS;


    static {
        final Map<Class<?>, Method> methods = new HashMap<Class<?>, Method>();
        for (Method method : Unmarshaller.class.getMethods()) {
            final int modifiers = method.getModifiers();
            if (Modifier.isStatic(modifiers)) {
                continue;
            }
            final Class<?> returnType = method.getReturnType();
            if (!JAXBElement.class.equals(returnType)) {
                continue;
            }
            if (!"unmarshal".equals(method.getName())) {
                continue;
            }
            final Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 2) {
                continue;
            }
            if (!Class.class.equals(parameterTypes[1])) {
                continue;
            }
            if (!returnType.getTypeParameters()[0].getName().equals(
                parameterTypes[1].getTypeParameters()[0].getName())) { // T==T ?
                continue;
            }
            methods.put(parameterTypes[0], method);
        }
        DECLARED_UNMARSHAL_METHODS = Collections.unmodifiableMap(methods);
    }


    public static <T> Object unmarshal(final Unmarshaller unmarshaller,
                                       final Class<? super T> inputType,
                                       final T input)
        throws UnmarshalException {

        if (unmarshaller == null) {
            throw new NullPointerException("unmarshaller");
        }

        if (inputType == null) {
            throw new NullPointerException("inputType");
        }

        if (input == null) {
            throw new NullPointerException("input");
        }

        final Method method = UNMARSHAL_METHODS.get(inputType);
        if (method == null) {
            throw new IllegalArgumentException(
                "can't unmarshal from " + inputType);
        }

        try {
            return method.invoke(unmarshaller, input);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (InvocationTargetException ite) {
            final Throwable cause = ite.getCause();
            if (UnmarshalException.class.isInstance(cause)) {
                throw (UnmarshalException) cause;
            }
            throw new RuntimeException(ite);
        }
    }


    public static <T> Object unmarshal(final JAXBContext context,
                                       final Class<? super T> inputType,
                                       final T input)
        throws JAXBException {

        if (context == null) {
            throw new NullPointerException("context");
        }

        return unmarshal(context.createUnmarshaller(), inputType, input);
    }


    public static Object unmarshal(final Unmarshaller unmarshaller,
                                   final Object input)
        throws UnmarshalException {

        if (unmarshaller == null) {
            throw new NullPointerException("unmarshaller");
        }

        if (input == null) {
            throw new NullPointerException("input");
        }

        final Class<?> inputType = input.getClass();

        Method method = null;
        for (Entry<Class<?>, Method> entry : UNMARSHAL_METHODS.entrySet()) {
            if (entry.getKey().isAssignableFrom(inputType)) {
                method = entry.getValue();
                break;
            }
        }

        if (method == null) {
            throw new IllegalArgumentException(
                "can't unmarshal from " + inputType);
        }

        try {
            return method.invoke(unmarshaller, input);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (InvocationTargetException ite) {
            final Throwable cause = ite.getCause();
            if (UnmarshalException.class.isInstance(cause)) {
                throw (UnmarshalException) cause;
            }
            throw new RuntimeException(ite);
        }
    }


    public static Object unmarshal(final JAXBContext context,
                                   final Object input)
        throws JAXBException {

        if (context == null) {
            throw new NullPointerException("context");
        }

        return unmarshal(context.createUnmarshaller(), input);
    }


    public static <T, V> JAXBElement<V> unmarshal(
        final Unmarshaller unmarshaller, final Class<? super T> inputType,
        final T input, final Class<V> valueType)
        throws UnmarshalException {

        if (unmarshaller == null) {
            throw new NullPointerException("unmarshaller");
        }

        if (inputType == null) {
            throw new NullPointerException("inputType");
        }

        if (input == null) {
            throw new NullPointerException("input");
        }

        if (valueType == null) {
            throw new NullPointerException("valueType");
        }

        final Method method = DECLARED_UNMARSHAL_METHODS.get(inputType);
        if (method == null) {
            throw new IllegalArgumentException(
                "can't unmarshal from " + input.getClass());
        }

        try {
            @SuppressWarnings("unchecked")
            final JAXBElement<V> result = (JAXBElement<V>) method.invoke(
                unmarshaller, input, valueType);
            return result;
//            final JAXBElement<?> result = (JAXBElement<?>) method.invoke(
//                unmarshaller, input, valueType);
//            return new JAXBElement<V>(result.getName(), valueType,
//                                      result.getScope(),
//                                      valueType.cast(result.getValue()));
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (InvocationTargetException ite) {
            final Throwable cause = ite.getCause();
            if (UnmarshalException.class.isInstance(cause)) {
                throw (UnmarshalException) cause;
            }
            throw new RuntimeException(ite);
        }
    }


    public static <T, V> JAXBElement<V> unmarshal(
        final JAXBContext context, final Class<? super T> inputType,
        final T input, final Class<V> valueType)
        throws JAXBException {

        if (context == null) {
            throw new NullPointerException("context");
        }

        return unmarshal(context.createUnmarshaller(), input, valueType);
    }


    public static <T, V> JAXBElement<V> unmarshal(
        final Class<? super T> inputType, final T input,
        final Class<V> valueType)
        throws JAXBException {

        final JAXBContext context = JAXBContext.newInstance(valueType);

        return unmarshal(context, input, valueType);
    }


    public static <V> JAXBElement<V> unmarshal(final Unmarshaller unmarshaller,
                                               final Object input,
                                               final Class<V> valueType)
        throws UnmarshalException {

        if (unmarshaller == null) {
            throw new NullPointerException("unmarshaller");
        }

        if (input == null) {
            throw new NullPointerException("input");
        }

        if (valueType == null) {
            throw new NullPointerException("valueType");
        }

        final Class<?> inputType = input.getClass();

        Method method = null;
        for (Entry<Class<?>, Method> entry
             : DECLARED_UNMARSHAL_METHODS.entrySet()) {
            if (entry.getKey().isAssignableFrom(inputType)) {
                method = entry.getValue();
                break;
            }
        }

        if (method == null) {
            throw new IllegalArgumentException(
                "can't unmarshal from " + inputType);
        }

        try {
            @SuppressWarnings("unchecked")
            final JAXBElement<V> result = (JAXBElement<V>) method.invoke(
                unmarshaller, input, valueType);
            return result;
        } catch (IllegalAccessException iae) {
            throw new UnmarshalException(iae);
        } catch (InvocationTargetException ite) {
            final Throwable cause = ite.getCause();
            if (cause instanceof UnmarshalException) {
                throw ((UnmarshalException) cause);
            }
            throw new UnmarshalException(cause);
        }
    }


    public static <V> JAXBElement<V> unmarshal(final JAXBContext context,
                                               final Object input,
                                               final Class<V> valueType)
        throws JAXBException {

        if (context == null) {
            throw new NullPointerException("context");
        }

        return unmarshal(context.createUnmarshaller(), input, valueType);
    }


    public static <V> JAXBElement<V> unmarshal(final Object input,
                                               final Class<V> valueType)
        throws JAXBException {

        final JAXBContext context = JAXBContext.newInstance(valueType);

        return unmarshal(context, input, valueType);
    }


    protected Unmarshallers() {

        super();
    }


}
