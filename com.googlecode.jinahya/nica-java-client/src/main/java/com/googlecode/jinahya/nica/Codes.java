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


import com.googlecode.jinahya.nica.util.Par;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * A class for holding codes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Codes extends AbstractCodes {


//    /**
//     * synchronized class.
//     */
//    private static final class SynchronizedCodes extends Codes {
//
//
//        /**
//         * Creates a new instance.
//         *
//         * @param mutex the codes instance to be wrapped.
//         */
//        public SynchronizedCodes(final Codes mutex) {
//            super();
//
//            if (mutex == null) {
//                throw new IllegalArgumentException("null mutex");
//            }
//
//            this.mutex = mutex;
//        }
//
//
//        //@Override
//        public Map getCodes() {
//            synchronized (mutex) {
//                return super.getCodes();
//            }
//        }
//
//
//        //@Override
//        public Map getCodes(final Map codes) {
//            synchronized (mutex) {
//                return super.getCodes(codes);
//            }
//        }
//
//
//        //@Override
//        public java.util.Hashtable getCodes(final java.util.Hashtable codes) {
//            synchronized (mutex) {
//                return super.getCodes(codes);
//            }
//
//        }
//
//
//        //@Override
//        public void putConstantCode(final String key, final String value) {
//            synchronized (mutex) {
//                super.putConstantCode(key, value);
//            }
//        }
//
//
//        //@Override
//        public String putVariableCode(final String key, final String value) {
//            synchronized (mutex) {
//                return super.putVariableCode(key, value);
//            }
//        }
//
//
//        //@Override
//        public String putVolatileCode(final String key, final String value) {
//            synchronized (mutex) {
//                return super.putVolatileCode(key, value);
//            }
//        }
//
//
//        /**
//         * mutex.
//         */
//        private final Codes mutex;
//
//
//    }
//
//
//    /**
//     * Returns a synchronized (thread-safe) codes backed by the specified codes.
//     *
//     * @param codes the codes to be "wrapped" in a synchronized codes.
//     *
//     * @return a synchronized view of the specified codes.
//     */
//    public static Codes synchronziedCodes(final Codes codes) {
//
//        if (codes == null) {
//            throw new IllegalArgumentException("null codes");
//        }
//
//        return new SynchronizedCodes(codes);
//    }
    /**
     * Creates a new instance.
     */
    public Codes() {
        super();

        putLanguageAndCountry(Locale.getDefault(), this);
    }


//    /**
//     * Returns codes.
//     *
//     * @return codes
//     */
//    public final Map<String, String> getEntries() {
//
//        final Map<String, String> codes = new HashMap<String, String>(
//            constantCodes.size() + variableCodes.size() + volatileCodes.size()
//            + 2);
//
//        getEntries(codes);
//
//        return codes;
//    }
//
//
//    /**
//     * Put codes to given
//     * <code>codes</code>.
//     *
//     * @param entries the map to be filled.
//     */
//    public final void getEntries(final Map<String, String> entries) {
//
//        if (entries == null) {
//            throw new IllegalArgumentException("null entries");
//        }
//
//        putTimestampAndNonce(this);
//
//        entries.putAll(volatileCodes);
//        volatileCodes.clear();
//
//        entries.putAll(variableCodes);
//
//        entries.putAll(constantCodes);
//    }


    @Override
    public final void putConstantEntry(final String key, final String value) {

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


    @Override
    public final String putVariableEntry(final String key, final String value) {

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (value == null) {
            throw new IllegalArgumentException("null value");
        }

        return variableCodes.put(key, value);
    }


    @Override
    public final String putVolatileEntry(final String key, final String value) {

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (value == null) {
            throw new IllegalArgumentException("null value");
        }

        return volatileCodes.put(key, value);
    }


    @Override
    protected final String getBase() {

        putTimestampAndNonce(this);

        final Map<String, String> entries = new HashMap<String, String>();

        entries.putAll(volatileCodes);
        volatileCodes.clear();

        entries.putAll(variableCodes);

        entries.putAll(constantCodes);

        return Par.encode(entries);
    }


    /**
     * constant codes.
     */
    private final Map<String, String> constantCodes =
        new HashMap<String, String>();


    /**
     * variable codes.
     */
    private final Map<String, String> variableCodes =
        new HashMap<String, String>();


    /**
     * volatile codes.
     */
    private final Map<String, String> volatileCodes =
        new HashMap<String, String>();


}

