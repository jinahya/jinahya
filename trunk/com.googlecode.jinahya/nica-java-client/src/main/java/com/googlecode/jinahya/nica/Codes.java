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


import com.googlecode.jinahya.nica.util.Nuo;
import java.util.Hashtable;


/**
 * A class for holding codes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class Codes {


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
     *
     * @param entries
     */
    protected static void putLanguageAndCountry(final java.util.Map entries) {

        if (entries == null) {
            throw new IllegalArgumentException("null entries");
        }

        final java.util.Locale locale = java.util.Locale.getDefault();

        try {
            entries.put(CodeKeys.USER_LANGUAGE3, locale.getISO3Language());
        } catch (java.util.MissingResourceException mre) {
        }
        entries.put(CodeKeys.USER_LANGUAGE2, locale.getLanguage());
        entries.put(CodeKeys.USER_LANGUAGE,
                    locale.getDisplayLanguage(java.util.Locale.ENGLISH));

        try {
            entries.put(CodeKeys.USER_COUNTRY3, locale.getISO3Country());
        } catch (java.util.MissingResourceException mre) {
        }
        entries.put(CodeKeys.USER_COUNTRY2, locale.getLanguage());
        entries.put(CodeKeys.USER_COUNTRY,
                    locale.getDisplayCountry(java.util.Locale.ENGLISH));
    }


    /**
     *
     * @param entries
     * @deprecated Use {@link #putLanguageAndCountry(java.util.Map)}.
     */
    protected static void putLanguageAndCountry(final Hashtable entries) {

        if (entries == null) {
            throw new IllegalArgumentException("null entries");
        }

        final java.util.Locale locale = java.util.Locale.getDefault();

        try {
            entries.put(CodeKeys.USER_LANGUAGE3, locale.getISO3Language());
        } catch (java.util.MissingResourceException mre) {
        }
        entries.put(CodeKeys.USER_LANGUAGE2, locale.getLanguage());
        entries.put(CodeKeys.USER_LANGUAGE,
                    locale.getDisplayLanguage(java.util.Locale.ENGLISH));

        try {
            entries.put(CodeKeys.USER_COUNTRY3, locale.getISO3Country());
        } catch (java.util.MissingResourceException mre) {
        }
        entries.put(CodeKeys.USER_COUNTRY2, locale.getLanguage());
        entries.put(CodeKeys.USER_COUNTRY,
                    locale.getDisplayCountry(java.util.Locale.ENGLISH));
    }


    /**
     *
     * @param entries
     */
    protected static void putTimestampAndNonce(final java.util.Map entries) {

        if (entries == null) {
            throw new IllegalArgumentException("null entries");
        }

        final long requestTimestamp = System.currentTimeMillis();
        entries.put(CodeKeys.REQUEST_TIMESTAMP, Long.toString(requestTimestamp));

        final long requestNonce = Nuo.generate(requestTimestamp);
        entries.put(CodeKeys.REQUEST_NONCE, Long.toString(requestNonce));
    }


    /**
     *
     * @param entries
     * @deprecated Use {@link #putTimestampAndNonce(java.util.Map)}.
     */
    protected static void putTimestampAndNonce(final Hashtable entries) {

        if (entries == null) {
            throw new IllegalArgumentException("null hashtable");
        }

        final long requestTimestamp = System.currentTimeMillis();
        entries.put(CodeKeys.REQUEST_TIMESTAMP,
                    Long.toString(requestTimestamp));

        final long requestNonce = Nuo.generate(requestTimestamp);
        entries.put(CodeKeys.REQUEST_NONCE, Long.toString(requestNonce));
    }


//    /**
//     * Creates a new instance.
//     */
//    //@SuppressWarnings("unchecked")
//    public Codes() {
//        super();
//    }
    /**
     * Adds a constant code entry. An IllegalArgumentException will be thrown if
     * <code>key</code> is already occupied.
     *
     * @param key code key
     * @param value code value
     */
    public abstract void putConstantEntry(final String key, final String value);


    /**
     * Adds a variable code entry.
     *
     * @param key code key
     * @param value code value
     *
     * @return previous value mapped to the key.
     */
    public abstract String putVariableEntry(final String key,
                                            final String value);


    /**
     * Adds a volatile code entry. Note that volatile code entries are cleared
     * after they once used.
     *
     * @param key code key
     * @param value code value
     *
     * @return previous value mapped to the key
     */
    public abstract String putVolatileEntry(final String key,
                                            final String value);


}

