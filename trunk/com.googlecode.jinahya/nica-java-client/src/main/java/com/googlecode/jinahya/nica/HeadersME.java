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


import com.googlecode.jinahya.nica.util.Aes;
import com.googlecode.jinahya.nica.util.AesBC;
import com.googlecode.jinahya.nica.util.Hac;
import com.googlecode.jinahya.nica.util.HacBC;
import com.googlecode.jinahya.nica.util.ParME;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;


/**
 * A RequestInterceptor which sets NICA headers.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HeadersME extends Headers {


    /**
     *
     * @param names
     * @param key
     *
     * @return
     */
    public static Headers newInstance(final Hashtable names, final byte[] key) {

        return new HeadersME(ParME.encode(names), new CodesME(), new AesBC(key),
                             new HacBC(key));
    }


    /**
     *
     * @param names
     * @param codes
     * @param key
     *
     * @return
     */
    public static Headers newInstance(final Hashtable names,
                                      final CodesME codes, final byte[] key) {

        return new HeadersME(ParME.encode(names), codes, new AesBC(key),
                             new HacBC(key));
    }


    /**
     *
     * @param name
     * @param codes
     * @param aes
     * @param hac
     */
    public HeadersME(final String name, final CodesME codes, final Aes aes,
                     final Hac hac) {

        super(name, codes, aes, hac);
    }


    /**
     * Generates request headers.
     *
     * @return a map of request headers
     */
    public final Hashtable getEntries() {

        final Hashtable headers = new Hashtable(4);

        getEntries(headers);

        return headers;
    }


    //@Override
    protected byte[] getBase(final Codes codes) {
        try {
            return ParME.encode(((CodesME) codes).getEntries()).
                getBytes("US-ASCII");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"US-ASCII\" is not supported?");
        }
    }


}

