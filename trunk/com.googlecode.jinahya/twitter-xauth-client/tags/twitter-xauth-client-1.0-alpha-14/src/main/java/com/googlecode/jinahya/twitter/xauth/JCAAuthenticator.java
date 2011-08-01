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


package com.googlecode.jinahya.twitter.xauth;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class JCAAuthenticator implements Authenticator {


    /**
     * Standard algorithm name for HMAC-SHA1.
     */
    protected static final String ALGORITHM = "HmacSHA1";


    //@Override
    public byte[] authenticate(final byte[] key, final byte[] input)
        throws Exception {

        final Mac mac = getInstance();
        mac.init(new SecretKeySpec(key, ALGORITHM));

        return mac.doFinal(input);
    }


    /**
     * Returns a Mac instance for {@value #ALGORITHM}.
     *
     * @return a Mac instance.
     * @throws Exception if any error occurs.
     */
    protected Mac getInstance() throws Exception {
        return Mac.getInstance(ALGORITHM);
    }
}
