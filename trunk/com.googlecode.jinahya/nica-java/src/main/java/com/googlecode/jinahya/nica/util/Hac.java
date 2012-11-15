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


package com.googlecode.jinahya.nica.util;


import java.io.UnsupportedEncodingException;


/**
 * An abstract class for HMAC-SHA-512.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class Hac {


    /**
     * synchronized class.
     */
    private static class SynchronizedHac extends Hac {


        /**
         * Creates a new instance.
         *
         * @param mutex the hac to be wrapped.
         */
        public SynchronizedHac(final Hac mutex) {
            super();

            if (mutex == null) {
                throw new IllegalArgumentException("null mutex");
            }

            this.mutex = mutex;
        }


        //@Override
        public byte[] authenticate(final byte[] message) {
            synchronized (mutex) {
                return mutex.authenticate(message);
            }
        }


        /**
         * hac.
         */
        private final Hac mutex;


    }


    /**
     * Returns a synchronized (thread-safe) hac backed by the specified hac.
     *
     * @param hac the hac to be "wrapped" in a synchronized hac.
     *
     * @return a synchronized view of the specified hac.
     */
    public static Hac synchronizedHac(final Hac hac) {

        if (hac == null) {
            throw new IllegalArgumentException("null hac");
        }

        return new SynchronizedHac(hac);
    }


    /**
     * Authenticates given
     * <code>message</code>.
     *
     * @param message the message to authenticate
     *
     * @return authentication output
     */
    public abstract byte[] authenticate(final byte[] message);


    /**
     * Authenticates given
     * <code>message</code>.
     *
     * @param message the message to authenticate
     *
     * @return authentication output
     */
    public byte[] authenticate(final String message) {

        if (message == null) {
            throw new IllegalArgumentException("null message");
        }

        try {
            return authenticate(message.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF-8\" is not supported?");
        }
    }


    /**
     * Authenticates given
     * <code>message</code> and returns output as a HEX string.
     *
     * @param message the message to authenticate
     *
     * @return the output as a HEX string
     */
    public String authenticateToString(final byte[] message) {
        return Hex.encodeToString(authenticate(message));
    }


    /**
     * Authenticates given
     * <code>message</code> and returns output as a HEX string.
     *
     * @param message the message to authenticate
     *
     * @return the output as a HEX string
     */
    public String authenticateToString(final String message) {
        return Hex.encodeToString(authenticate(message));
    }


}

