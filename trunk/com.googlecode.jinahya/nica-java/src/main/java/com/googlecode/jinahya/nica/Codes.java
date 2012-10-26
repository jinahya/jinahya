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
 * A RequestInterceptor which sets NICA headers.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Codes {


    private static class SynchronizedCodes extends Codes {


        public SynchronizedCodes(final Codes codes) {
            super();

            this.codes = codes;
        }


        @Override
        public Map<String, String> getCodes() {
            synchronized (codes) {
                return super.getCodes();
            }
        }


        @Override
        public void getCodes(final Map<String, String> codes) {
            synchronized (codes) {
                super.getCodes(codes);
            }
        }


        @Override
        public void putConstantCode(final String key, final String value) {
            synchronized (codes) {
                super.putConstantCode(key, value);
            }
        }


        @Override
        public String putVariableCode(final String key, final String value) {
            synchronized (codes) {
                return super.putVariableCode(key, value);
            }
        }


        private final Codes codes;


    }


    public static Codes synchronziedCodes(final Codes codes) {

        if (codes == null) {
            throw new IllegalArgumentException("null codes");
        }

        return new SynchronizedCodes(codes);
    }


    public Map<String, String> getCodes() {

        final long requestTimestamp = System.currentTimeMillis();
        variableCodes.put(Code.REQUEST_TIMESTAMP.name(),
                          Long.toString(requestTimestamp));

        final long requestNonce = Nuo.generate(requestTimestamp);
        variableCodes.put(Code.REQUEST_NONCE.name(),
                          Long.toString(requestNonce));

        final Map<String, String> codes = new HashMap<String, String>(
            constantCodes.size() + variableCodes.size());

        getCodes(codes);

        return codes;
    }


    public void getCodes(final Map<String, String> codes) {

        if (codes == null) {
            throw new IllegalArgumentException("null codes");
        }

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
     * used.
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

        return variableCodes.put(key, value);
    }


    /**
     * constant codes.
     */
    private final Map<String, String> constantCodes =
        new HashMap<String, String>();


    /**
     * variable codes.
     */
    private final transient Map<String, String> variableCodes =
        new HashMap<String, String>();


}

