/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.xmlpull.xs;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <V> value type parameter
 */
public interface XSTypeAdapter<V> {


    /**
     * Parses given <code>string</code> to the target value. A
     * <code>NullPointerException</code> will be thrown if <code>string</code>
     * is null. An <code>IllegalArgumentException</code> will be thrown if
     * failed to parse.
     *
     * @param string string to parse
     * @return parsed value
     */
    V parse(String string);


    /**
     * Prints given <code>value</code> to a string value. A
     * <code>NullPointerException</code> will be thrown if <code>string</code>
     * is null. An <code>IllegalArgumentException</code> will be thrown if
     * failed to print.
     * 
     * @param value value to print
     * @return printed string
     */
    String print(V value);


}

