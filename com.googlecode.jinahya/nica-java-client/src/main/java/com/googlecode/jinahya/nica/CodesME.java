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


package com.googlecode.jinahya.nica;


import java.util.Enumeration;
import java.util.Hashtable;


/**
 * A class for holding codes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class CodesME extends AbstractCodes {


    /**
     * Copies all entries from
     * <code>source</code> to
     * <code>target</code>.
     *
     * @param source source
     * @param target target
     */
    private static void copy(final Hashtable source, final Hashtable target) {

        if (source == null) {
            throw new IllegalArgumentException("null source");
        }

        if (target == null) {
            throw new IllegalArgumentException("null target");
        }

        final Enumeration keys = source.elements();
        while (keys.hasMoreElements()) {
            final Object key = keys.nextElement();
            target.put(key, source.get(key));
        }
    }


    /**
     * Returns codes.
     *
     * @return codes
     */
    public final Hashtable getCodes() {

        final Hashtable codes = new Hashtable(
            constantCodes.size() + variableCodes.size() + volatileCodes.size()
            + 2);

        getCodes(codes);

        return codes;
    }


    /**
     * Put codes to given
     * <code>codes</code>.
     *
     * @param codes the hashtable to be filled.
     *
     * @return given codes.
     */
    public final void getCodes(final Hashtable codes) {

        if (codes == null) {
            throw new IllegalArgumentException("null codes");
        }

        putTimestampAndNonce(volatileCodes);

        copy(volatileCodes, codes);
        volatileCodes.clear();

        copy(variableCodes, codes);

        copy(constantCodes, codes);
    }


    //@Override
    public final void putConstantCode(final String key, final String value) {

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (value == null) {
            throw new IllegalArgumentException("null value");
        }

        if (constantCodes.containsKey(key)) {
            throw new IllegalArgumentException(
                "key(" + key + ") is already occupied");
        }

        constantCodes.put(key, value);
    }


    //@Override
    public final String putVariableCode(final String key, final String value) {

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (value == null) {
            throw new IllegalArgumentException("null value");
        }

        return (String) variableCodes.put(key, value);
    }


    //@Override
    public final String putVolatileCode(final String key, final String value) {

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (value == null) {
            throw new IllegalArgumentException("null value");
        }

        return (String) volatileCodes.put(key, value);
    }


    /**
     * constant codes.
     */
    private final Hashtable constantCodes = new Hashtable();


    /**
     * variable codes.
     */
    private final Hashtable variableCodes = new Hashtable();


    /**
     * volatile codes.
     */
    private final Hashtable volatileCodes = new Hashtable();


}

