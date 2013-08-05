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
 * An abstract class for HMAC-SHA-1.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class Hac {


    /**
     * Authenticates given {@code message}.
     *
     * @param message the message to authenticate
     *
     * @return authenticated output
     */
    public abstract byte[] authenticate(byte[] message);


    /**
     * Authenticates given {@code message} which is treated as a {@code UTF-8}
     * encoded string.
     *
     * @param message the message to authenticate
     *
     * @return authenticated output
     */
    public byte[] authenticate(final String message) {

        if (message == null) {
            throw new NullPointerException("message");
        }

        try {
            return authenticate(message.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF-8\" is not supported?");
        }
    }


    /**
     * Authenticates given {@code message} and returns output as a {@code hex}
     * encoded string.
     *
     * @param message the message to authenticate
     *
     * @return the output as a {@code hex} encoded string
     */
    public String authenticateToString(final byte[] message) {

        return Hex.encodeToString(authenticate(message));
    }


    /**
     * Authenticates given {@code message} which is treated as a {@code UTF-8}
     * encoded string and returns output as a {@code hex} encoded string.
     *
     * @param message the message to authenticate
     *
     * @return the output as a {@code hex} encoded string
     */
    public String authenticateToString(final String message) {

        try {
            return authenticateToString(message.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF-8\" is not supported?");
        }
    }


}
