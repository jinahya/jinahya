/*
 * Copyright 2011 Jin Kwon.
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


package com.googlecode.jinahya.txc;


import java.io.IOException;


/**
 * Interface for HMAC-SHA1 authentication.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public interface Authenticator {


    /**
     * Generates HMAC-SAH1 signature with given <code>key</code> and
     * <code>input</code>.
     *
     * @param key key
     * @param input input
     * @return signature
     * @throws IOException if an I/O error occurs.
     * @throws TXCException if any error occurs.
     */
    byte[] authenticate(final byte[] key, final byte[] input)
        throws IOException, TXCException;
}
