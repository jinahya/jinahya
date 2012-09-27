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


package com.googlecode.jinahya.inca.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class SHAJCA extends SHA {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(SHAJCA.class.getName());


    static {
//        LOGGER.setLevel(Level.OFF);
    }


    @Override
    public byte[] hash(final byte[] unhashed) {

        LOGGER.log(Level.INFO, "hash({0})", unhashed);

        if (unhashed == null) {
            throw new IllegalArgumentException("null unhashed");
        }

        try {
            return MessageDigest.getInstance(NAME).digest(unhashed);
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException("\"" + NAME + "\" not available?");
        }
    }


}

