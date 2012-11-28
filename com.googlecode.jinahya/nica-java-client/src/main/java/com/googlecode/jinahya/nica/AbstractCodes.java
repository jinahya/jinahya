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
import java.util.Locale;
import java.util.MissingResourceException;


/**
 * A class for holding codes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class AbstractCodes {


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
    protected static void putLanguageAndCountry(final Locale locale,
                                                final AbstractCodes codes) {

        if (codes == null) {
            throw new IllegalArgumentException("null entries");
        }

        try {
            codes.putVariableEntry(CodeKeys.USER_LANGUAGE3,
                                   locale.getISO3Language());
        } catch (MissingResourceException mre) {
        }
        codes.putVariableEntry(CodeKeys.USER_LANGUAGE2, locale.getLanguage());
        codes.putVariableEntry(CodeKeys.USER_LANGUAGE,
                               locale.getDisplayLanguage(Locale.ENGLISH));

        try {
            codes.putVariableEntry(CodeKeys.USER_COUNTRY3,
                                   locale.getISO3Country());
        } catch (MissingResourceException mre) {
        }
        codes.putVariableEntry(CodeKeys.USER_COUNTRY2, locale.getLanguage());
        codes.putVariableEntry(CodeKeys.USER_COUNTRY,
                               locale.getDisplayCountry(Locale.ENGLISH));
    }


    /**
     *
     * @param codes
     */
    protected static void putTimestampAndNonce(final AbstractCodes codes) {

        if (codes == null) {
            throw new IllegalArgumentException("null hashtable");
        }

        final long timestamp = System.currentTimeMillis();
        codes.putVolatileEntry(CodeKeys.REQUEST_TIMESTAMP,
                               Long.toString(timestamp));

        final long nonce = Nuo.generate(timestamp);
        codes.putVolatileEntry(CodeKeys.REQUEST_NONCE, Long.toString(nonce));
    }


    /**
     * Adds a constant code entry. An IllegalArgumentException will be thrown if
     * <code>key</code> is already occupied.
     *
     * @param key code key
     * @param value code value
     */
    public abstract void putConstantEntry(String key, String value);


    /**
     * Adds a variable code entry.
     *
     * @param key code key
     * @param value code value
     *
     * @return previous value mapped to the key.
     */
    public abstract String putVariableEntry(String key, String value);


    /**
     * Adds a volatile code entry. Note that volatile code entries are cleared
     * after they once used.
     *
     * @param key code key
     * @param value code value
     *
     * @return previous value mapped to the key
     */
    public abstract String putVolatileEntry(String key, String value);


    protected abstract String getBase();


}

