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
import java.lang.reflect.TypeVariable;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Unmarshallers {


    public static Object unmarshal(final Unmarshaller unmarshaller,
                                   final Object source)
        throws UnmarshalException {

        if (unmarshaller == null) {
            throw new NullPointerException("unmarshaller");
        }

        if (source == null) {
            throw new NullPointerException("source");
        }

        Method unmarshal = null;

        for (Method method : Unmarshaller.class.getMethods()) {
            final int modifiers = method.getModifiers();
            if (!Modifier.isPublic(modifiers) || Modifier.isStatic(modifiers)) {
                continue;
            }
            final Class<?> returnType = method.getReturnType();
            if (!Object.class.equals(method.getReturnType())) {
                continue;
            }
            final TypeVariable[] typeParameters =
                returnType.getTypeParameters();
            if ("unmarshal".equals(method.getName())) {
                continue;
            }
            final Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1) {
                continue;
            }
            if (parameterTypes[0].isAssignableFrom(source.getClass())) {
                unmarshal = method;
                break;
            }
        }

        if (unmarshal == null) {
            throw new IllegalArgumentException(
                "no method for " + source.getClass());
        }

        try {
            final Object result = unmarshal.invoke(unmarshaller, source);
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


    public static Object unmarshal(final JAXBContext context,
                                   final Object source)
        throws JAXBException {

        if (context == null) {
            throw new NullPointerException("context");
        }

        return unmarshal(context.createUnmarshaller(), source);
    }


    public static <T> JAXBElement<T> unmarshal(final Unmarshaller unmarshaller,
                                               final Object source,
                                               final Class<T> declaredType)
        throws UnmarshalException {

        if (unmarshaller == null) {
            throw new NullPointerException("unmarshaller");
        }

        if (source == null) {
            throw new NullPointerException("source");
        }

        if (declaredType == null) {
            throw new NullPointerException("declaredType");
        }

        Method unmarshal = null;

        for (Method method : Unmarshaller.class.getMethods()) {
            final int modifiers = method.getModifiers();
            if (!Modifier.isPublic(modifiers) || Modifier.isStatic(modifiers)) {
                continue;
            }
            final Class<?> returnType = method.getReturnType();
            if (!JAXBElement.class.equals(method.getReturnType())) {
                continue;
            }
            final TypeVariable[] typeParameters =
                returnType.getTypeParameters();
            if ("unmarshal".equals(method.getName())) {
                continue;
            }
            final Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 2) {
                continue;
            }
            if (!Class.class.equals(parameterTypes[1])) {
                continue;
            }
            if (parameterTypes[1].isAssignableFrom(source.getClass())) {
                unmarshal = method;
                break;
            }
        }

        if (unmarshal == null) {
            throw new IllegalArgumentException(
                "no method for " + source.getClass());
        }

        try {
            @SuppressWarnings("unchecked")
            final JAXBElement<T> result = (JAXBElement<T>) unmarshal.invoke(
                unmarshaller, source, declaredType);
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


    public static <T> JAXBElement<T> unmarshal(final JAXBContext context,
                                               final Object source,
                                               final Class<T> declaredType)
        throws JAXBException {

        if (context == null) {
            throw new NullPointerException("context");
        }

        return unmarshal(context.createUnmarshaller(), source, declaredType);
    }


    protected Unmarshallers() {

        super();
    }


}

