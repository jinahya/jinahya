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


package com.googlecode.jinahya.util;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class Genealogy {


    /**
     * Casts or wraps given
     * <code>value</code> as a
     * <code>Collection</code>.
     *
     * @param <T> type parameter
     * @param type type
     * @param value the value
     *
     * @return an instance of value collection
     */
    @SuppressWarnings({"unchecked"})
    private static <T> Collection<T> asCollection(final Class<T> type,
                                                  final Object value) {

        if (value == null) {
            return Collections.<T>emptyList();
        }

        if (value instanceof Collection<?>) {
            return Collections.checkedCollection((Collection<T>) value, type);
        }

        if (value instanceof Map<?, ?>) {
            return Collections.checkedCollection(
                (Collection<T>) ((Map) value).values(), type);
        }

        return Collections.singleton(type.cast(value)); // ClassCastException
    }


    /**
     * Returns the value of a property named
     * <code>name</code> of given
     * <code>of</code>.
     *
     * @param <T> type parameter
     * @param type type
     * @param of current object
     * @param name property name
     *
     * @return the property value or null
     *
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static <T> T getPropertyValue(final Class<T> type, final T of,
                                          final String name)
        throws IntrospectionException, IllegalAccessException,
               InvocationTargetException {

        final PropertyDescriptor descriptor =
            new PropertyDescriptor(name, type);
        final Method read = descriptor.getReadMethod();
        if (read == null) {
            // property can't be read
            return null;
        }

        return type.cast(read.invoke(of));
    }


    /**
     *
     * @param <T> type parameter
     * @param type type
     * @param of the base object
     * @param name name of the field
     *
     * @return the field value
     *
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static <T> T getFieldValue(final Class<T> type, final T of,
                                       final String name)
        throws NoSuchFieldException, IllegalAccessException {

        final Field field = of.getClass().getDeclaredField(name);
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }

        return type.cast(field.get(of));
    }


    /**
     *
     * @param <T> type parameter
     * @param type type
     * @param of of
     * @param name field or property name
     *
     * @return
     */
    private static <T> T getValue(final Class<T> type, final T of,
                                  final String name) {

        try {
            final Field field = of.getClass().getDeclaredField(name);
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            try {
                return type.cast(field.get(of));
            } catch (IllegalAccessException iae) {
            }
        } catch (NoSuchFieldException nfe) {
        }

        try {
            final PropertyDescriptor propetyDescriptor =
                new PropertyDescriptor(name, type);
            final Method readMethod = propetyDescriptor.getReadMethod();
            if (readMethod != null) {
                try {
                    return type.cast(readMethod.invoke(of));
                } catch (IllegalAccessException iae) {
                } catch (InvocationTargetException ite) {
                }
            }
        } catch (IntrospectionException ie) {
        }

        return null;
    }


    /**
     *
     * @param <T>
     * @param type
     * @param of
     * @param name
     *
     * @return
     */
    private static <T> Collection<T> getValueAsCollection(
        final Class<T> type, final T of, final String name) {

        return asCollection(type, getValue(type, of, name));
    }


    /**
     *
     * @param <T>
     * @param type
     * @param is
     * @param of
     * @param names
     *
     * @return
     */
    public static <T> boolean isAncestor(final Class<T> type, final T is,
                                         final T of, final String... names) {

        if (type == null) {
            throw new NullPointerException("null type");
        }

        if (of == null) {
            throw new NullPointerException("null of");
        }

        if (is == null) {
            return false;
        }

        if (is == of) {
            throw new IllegalArgumentException("is == of");
        }

        if (names == null) {
            throw new NullPointerException("null names");
        }

        if (names.length == 0) {
            throw new IllegalArgumentException("empty names");
        }

        for (String name : names) {
            try {
                for (final T parent : getValueAsCollection(type, of, name)) {
                    if (is == parent) {
                        return true;
                    }
                    if (isAncestor(type, is, parent, name)) {
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(System.err);
                return false;
            }
        }

        return false;
    }


    public static <T> boolean isDescendant(final Class<T> type, final T is,
                                           final T of, final String... names) {

        if (type == null) {
            throw new NullPointerException("null type");
        }

        if (is == null) {
            return false;
        }

        if (of == null) {
            throw new NullPointerException("null of");
        }

        if (is == of) {
            throw new IllegalArgumentException("is == of");
        }

        if (names == null) {
            throw new NullPointerException("null names");
        }

        if (names.length == 0) {
            throw new IllegalArgumentException("empty names");
        }

        for (String name : names) {
            try {
                for (T child : getValueAsCollection(type, of, name)) {
                    if (is == child) {
                        return true;
                    }
                    if (isDescendant(type, is, child, name)) {
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(System.err);
                return false;
            }
        }

        return false;
    }


    /**
     * PRIVATE.
     */
    private Genealogy() {
        super();
    }


}