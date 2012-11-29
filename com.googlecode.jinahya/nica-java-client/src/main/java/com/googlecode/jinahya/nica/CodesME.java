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


import com.googlecode.jinahya.nica.util.ParME;
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
    protected static void copy(final Hashtable source, final Hashtable target) {

        if (source == null) {
            throw new IllegalArgumentException("null source");
        }

        if (target == null) {
            throw new IllegalArgumentException("null target");
        }

        final Enumeration keys = source.keys();
        while (keys.hasMoreElements()) {
            final Object key = keys.nextElement();
            target.put(key, source.get(key));
        }
    }


//    /**
//     * Returns codes.
//     *
//     * @return codes
//     */
//    public final Hashtable getEntries() {
//
//        final Hashtable entries = new Hashtable(
//            constantEntries.size() + variableEntries.size()
//            + volatileEntries.size() + 2);
//
//        getEntries(entries);
//
//        return entries;
//    }
//
//
//    /**
//     * Put codes to given
//     * <code>codes</code>.
//     *
//     * @param entries the hashtable to be filled.
//     *
//     * @return given codes.
//     */
//    public final void getEntries(final Hashtable entries) {
//    }


    //@Override
    public final void putConstantEntry(final String key, final String value) {

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (value == null) {
            throw new IllegalArgumentException("null value");
        }

        if (constantEntries.containsKey(key)) {
            throw new IllegalArgumentException(
                "key(" + key + ") is already occupied");
        }

        constantEntries.put(key, value);
    }


    //@Override
    public final String putVariableEntry(final String key, final String value) {

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (value == null) {
            throw new IllegalArgumentException("null value");
        }

        return (String) variableEntries.put(key, value);
    }


    //@Override
    public final String putVolatileEntry(final String key, final String value) {

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (value == null) {
            throw new IllegalArgumentException("null value");
        }

        return (String) volatileEntries.put(key, value);
    }


    //@Override
    protected final String getBase() {

        putTimestampAndNonce(this);

        final Hashtable decoded = new Hashtable();

        copy(volatileEntries, decoded);
        volatileEntries.clear();

        copy(variableEntries, decoded);

        copy(constantEntries, decoded);

        return ParME.encode(decoded);
    }


    /**
     * constant codes.
     */
    private final Hashtable constantEntries = new Hashtable();


    /**
     * variable codes.
     */
    private final Hashtable variableEntries = new Hashtable();


    /**
     * volatile codes.
     */
    private final Hashtable volatileEntries = new Hashtable();


}

