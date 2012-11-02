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
import java.util.HashMap;
import java.util.Map;


/**
 * A class for holding codes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Codes {


    /**
     * synchronized class.
     */
    private static class SynchronizedCodes extends Codes {


        /**
         * Creates a new instance.
         *
         * @param codes the codes to be wrapped
         */
        public SynchronizedCodes(final Codes codes) {
            super();

            if (codes == null) {
                throw new IllegalArgumentException("null codes");
            }

            this.codes = codes;
        }


        //@Override
        public Map getCodes() {
            synchronized (codes) {
                return super.getCodes();
            }
        }


        //@Override
        public void getCodes(final Map codes) {
            synchronized (codes) {
                super.getCodes(codes);
            }
        }


        //@Override
        public void putConstantCode(final String key, final String value) {
            synchronized (codes) {
                super.putConstantCode(key, value);
            }
        }


        //@Override
        public String putVariableCode(final String key, final String value) {
            synchronized (codes) {
                return super.putVariableCode(key, value);
            }
        }


        /**
         * codes.
         */
        private final Codes codes;


    }


    /**
     * Returns a synchronized (thread-safe) map backed by the specified codes.
     *
     * @param codes the codes to be "wrapped" in a synchronized codes.
     * @return a synchronized view of the specified codes.
     */
    public static Codes synchronziedCodes(final Codes codes) {

        if (codes == null) {
            throw new IllegalArgumentException("null codes");
        }

        return new SynchronizedCodes(codes);
    }


    /**
     * Returns codes.
     *
     * @return codes
     */
    public Map getCodes() {

        final Map codes = new HashMap(
            constantCodes.size() + variableCodes.size() + 2);

        getCodes(codes);

        return codes;
    }


    /**
     * Put codes to given map.
     *
     * @param codes the map to which codes are added
     */
    public void getCodes(final Map codes) {

        if (codes == null) {
            throw new IllegalArgumentException("null codes");
        }

        final long requestTimestamp = System.currentTimeMillis();
        putVariableCode(CodeKeys.REQUEST_TIMESTAMP,
                        Long.toString(requestTimestamp));

        final long requestNonce = Nuo.generate(requestTimestamp);
        putVariableCode(CodeKeys.REQUEST_NONCE, Long.toString(requestNonce));

        codes.putAll(variableCodes);
        variableCodes.clear();

        codes.putAll(constantCodes);
    }


    /**
     * Puts a constant code.
     *
     * @param key key
     * @param value value
     */
    public void putConstantCode(final String key, final String value) {

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


    /**
     * Puts a variable code. Note that variable codes are cleared after they
     * once used.
     *
     * @param key key
     * @param value value
     *
     * @return previous value
     */
    public String putVariableCode(final String key, final String value) {

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        if (value == null) {
            throw new IllegalArgumentException("null value");
        }

        return (String) variableCodes.put(key, value);
    }


    /**
     * constant codes.
     */
    private final Map constantCodes = new HashMap();


    /**
     * variable codes.
     */
    private final transient Map variableCodes = new HashMap();


}

