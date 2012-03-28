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


package com.googlecode.jinahya.persistence;


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
     * interface for finding parents or children.
     *
     * @param <T> type parameter
     */
    static interface Finder<T> {


        /**
         * Returns parents or children of
         * <code>of</code>.
         *
         * @param of current object
         * @return parents or children
         */
        Collection<T> find(T of);


    }


    /**
     * Creates a new finder of given
     * <code>type</code> for
     * <code>name</code>.
     *
     * @param <T> type parameter
     * @param type type
     * @param name field or property name
     * @return new instance
     */
    static <T> Finder<T> newFinder(final Class<T> type, final String name) {

        return new Finder<T>() {


            @Override
            public Collection<T> find(final T of) {
                try {
                    return getValueAsCollection(type, of, name);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                    return Collections.EMPTY_LIST;
                }
            }


        };
    }


    /**
     * Casts or wraps given
     * <code>value</code> as a
     * <code>Collection</code>.
     *
     * @param <T> type parameter
     * @param type type
     * @param value the value
     * @return an instance of value collection
     */
    private static <T> Collection<T> asCollection(final Class<T> type,
                                                  final Object value) {

        if (value == null) {
            return Collections.EMPTY_LIST;
        }

        if (value instanceof Collection<?>) {
            return Collections.checkedCollection((Collection) value, type);
        }

        if (value instanceof Map<?, ?>) {
            return Collections.checkedCollection(((Map) value).values(), type);
        }

        return Collections.singleton((T) value);
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
     * @return the property value or null
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

        return (T) read.invoke(of);
    }


    /**
     *
     * @param <T> type parameter
     * @param type type
     * @param of the base object
     * @param name name of the field
     * @return the field value
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

        return (T) field.get(of);
    }


    /**
     *
     * @param <T> type parameter
     * @param type type
     * @param of the base object
     * @param name the field or property name
     * @return the value of given field or property
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IntrospectionException
     * @throws InvocationTargetException
     */
    private static <T> T getValue(final Class<T> type, final T of,
                                  final String name)
        throws NoSuchFieldException, IllegalAccessException,
               IntrospectionException, InvocationTargetException {

        T value = getFieldValue(type, of, name);

        if (value == null) {
            value = getPropertyValue(type, of, name);
        }

        return value;
    }


    /**
     *
     * @param <T>
     * @param type
     * @param of
     * @param name
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IntrospectionException
     * @throws InvocationTargetException
     */
    private static <T> Collection<T> getValueAsCollection(
        final Class<T> type, final T of, final String name)
        throws NoSuchFieldException, IllegalAccessException,
               IntrospectionException, InvocationTargetException {

        return asCollection(type, getValue(type, of, name));
    }


    /**
     *
     * @param <T>
     * @param is
     * @param of
     * @param name
     * @return
     */
    public static <T> boolean isAncestor(final T is, final T of,
                                         final String name) {

        return isAncestor((Class<T>) is.getClass(), is, of, name);
    }


    /**
     *
     * @param <T>
     * @param type
     * @param is
     * @param of
     * @param name
     * @return
     */
    public static <T> boolean isAncestor(final Class<T> type, final T is,
                                         final T of, final String name) {

        if (of == null) {
            throw new NullPointerException("null of");
        }

        if (is == null) {
            return false;
        }

        if (is.equals(of)) {
            throw new IllegalArgumentException("is == of");
        }

        if (name == null) {
            throw new NullPointerException("null name");
        }

        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("empty name");
        }

        try {
            for (final T parent : getValueAsCollection(type, of, name)) {
                System.out.println(" ------->>>>>>> " + is + " / " + parent);
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

        return false;
    }


    /**
     * Check if
     * <code>is</code> is a descendant of
     * <code>of</code>.
     *
     * @param <T> type parameter
     * @param is the object to be checked
     * @param of the base object
     * @param name descendant field/property name
     * @return true if
     * <code>is</code> is a descendant of
     * <code>of</code>; false otherwise
     */
    public static <T> boolean isDescendant(final T is, final T of,
                                           final String name) {

        return isDescendant((Class<T>) is.getClass(), is, of, name);
    }


    public static <T> boolean isDescendant(final Class<T> type, final T is,
                                           final T of, final String name) {

        if (of == null) {
            throw new NullPointerException("null of");
        }

        if (is == null) {
            return false;
        }

        if (is.equals(of)) {
            throw new IllegalArgumentException("is == of");
        }

        if (name == null) {
            throw new NullPointerException("null name");
        }

        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("empty name");
        }

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

        return false;
    }


    /**
     * PRIVATE.
     */
    private Genealogy() {
        super();
    }


}