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
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class MAC {


    /**
     *
     * @param unauthenciated
     *
     * @return
     */
    public abstract byte[] authenticate(final byte[] unauthenciated);


    /**
     *
     * @param unauthenticated
     *
     * @return
     */
    public String authenticateToString(final byte[] unauthenticated) {
        return HEX.encodeToString(unauthenticated);
    }


    /**
     *
     * @param unauthenticated
     *
     * @return
     */
    public byte[] authenticate(final String unauthenticated) {

        if (unauthenticated == null) {
            throw new IllegalArgumentException("null unauthenticated");
        }

        try {
            return authenticate(unauthenticated.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF-8\" is not supported?");
        }
    }


    public String authenticateToString(final String unauthenticated) {
        return HEX.encodeToString(authenticate(unauthenticated));
    }


}

