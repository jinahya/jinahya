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


package com.googlecode.jinahya.lang;


import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;


/**
 * A helper class for {@link FieldEnum}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class FieldEnumHelper {


    /**
     * Verifies all enum constants are mapped to unique field values including
     * {@code null}.
     *
     * @param <E> enum type parameter
     * @param <F> field type parameter
     * @param enumType enum type
     *
     * @return true if verified; false otherwise.
     */
    public static <E extends Enum<E> & FieldEnum<E, F>, F> boolean verify(
        final Class<E> enumType) {

        if (enumType == null) {
            throw new NullPointerException("enumtype");
        }

        if (!enumType.isEnum()) {
            // is this required?
            throw new IllegalArgumentException(
                "enumType(" + enumType + ") is not Enum");
        }

        final Set<F> fieldValues = new HashSet<F>();
        for (final E enumConstant : enumType.getEnumConstants()) {
            if (!fieldValues.add(enumConstant.getFieldValue())) {
                return false;
            }
        }

        return true;
    }


    /**
     * Returns all field values.
     *
     * @param <E> enum type parameter
     * @param <F> field type parameter
     * @param enumType enum type
     * @param fieldType field type
     *
     * @return all field values.
     */
    public static <E extends Enum<E> & FieldEnum<E, F>, F> F[] fieldValues(
        final Class<E> enumType, final Class<F> fieldType) {

        if (enumType == null) {
            throw new NullPointerException("enumtype");
        }

        if (!enumType.isEnum()) {
            // is this required?
            throw new IllegalArgumentException(
                "enumType(" + enumType + ") is not Enum");
        }

        if (fieldType == null) {
            throw new NullPointerException("fieldType");
        }

        final E[] enumConstants = enumType.getEnumConstants();

        @SuppressWarnings("unchecked")
        final F[] fieldValues =
            (F[]) Array.newInstance(fieldType, enumConstants.length);

        for (int i = 0; i < fieldValues.length; i++) {
            fieldValues[i] = enumConstants[i].getFieldValue();
        }

        return fieldValues;
    }


    /**
     * Finds enum constant mapped to given {@code fieldValue}. An
     * {@code IllegalArgumentException} will be thrown if no enum constant
     * found.
     *
     * @param <E> enum type parameter
     * @param <F> field type parameter
     * @param enumType enum type
     * @param fieldValue field value
     *
     * @return the mapped enum constant.
     */
    public static <E extends Enum<E> & FieldEnum<E, F>, F> E fromFieldValue(
        final Class<E> enumType, final F fieldValue) {

        if (enumType == null) {
            throw new NullPointerException("enumtype");
        }

        if (!enumType.isEnum()) {
            // is this required?
            throw new IllegalArgumentException(
                "enumType(" + enumType + ") is not Enum");
        }

        if (fieldValue == null) {
            // ok
        }

        for (E enumConstant : enumType.getEnumConstants()) {
            if (fieldValue == enumConstant.getFieldValue()
                || (fieldValue != null
                    && fieldValue.equals(enumConstant.getFieldValue()))) {
                return enumConstant;
            }
        }

        throw new IllegalArgumentException("unknwon fieldValue: " + fieldValue);
    }


    /**
     * Creates a new instance.
     */
    protected FieldEnumHelper() {

        super();
    }


}

