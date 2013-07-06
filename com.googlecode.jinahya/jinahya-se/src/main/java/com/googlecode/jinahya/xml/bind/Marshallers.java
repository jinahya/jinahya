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
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Marshallers {


    public static void marshal(final Marshaller marshaller,
                               final Object element, final Object target)
        throws MarshalException {

        if (marshaller == null) {
            throw new NullPointerException("marshaller");
        }

        if (element == null) {
            throw new NullPointerException("element");
        }

        if (target == null) {
            throw new NullPointerException("target");
        }

        Method marshal = null;

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

            if ("marshal".equals(method.getName())) {
                continue;
            }

            final Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 2) {
                continue;
            }

            if (parameterTypes[0].isAssignableFrom(target.getClass())) {
                marshal = method;
                break;
            }
        }

        if (marshal == null) {
            throw new IllegalArgumentException(
                "no method for " + target.getClass());
        }

        try {
            final Object result = marshal.invoke(marshaller, target);
        } catch (IllegalAccessException iae) {
            throw new MarshalException(iae);
        } catch (InvocationTargetException ite) {
            final Throwable cause = ite.getCause();
            if (cause instanceof MarshalException) {
                throw ((MarshalException) cause);
            }
            throw new MarshalException(cause);
        }
    }


    public static void marshal(final JAXBContext context, final Object element,
                               final Object target)
        throws JAXBException {

        if (context == null) {
            throw new NullPointerException("context");
        }

        marshal(context.createMarshaller(), element, target);
    }


    protected Marshallers() {

        super();
    }


    boolean useMethodHandle = true;
}

