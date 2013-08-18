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


package com.googlecode.jinahya.lang.reflect;


import java.lang.reflect.Array;
import java.util.Objects;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class GenericArray {


    @SuppressWarnings("unchecked")
    public static <T> T[] newInstance(final Class<T> componentType,
                                      final int length) {

        Objects.requireNonNull(componentType, "null componentType");

        if (length < 0) {
            throw new IllegalArgumentException("length(" + length + ") < 0");
        }

        return (T[]) Array.newInstance(componentType, length);
    }


    protected GenericArray() {

        super();
    }


}

