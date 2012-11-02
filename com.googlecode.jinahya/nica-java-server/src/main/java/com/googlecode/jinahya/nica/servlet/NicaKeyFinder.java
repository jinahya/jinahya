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


package com.googlecode.jinahya.nica.servlet;


import com.googlecode.jinahya.nica.util.Aes;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * An abstract ServletRequestAttributeListener for Nica-Names. One of primary
 * goal of the implementing class is locating a secret key and add it to
 * specified ServletRequest as an attribute named
 * {@link NicaFilter#ATTRIBUTE_NICA_SECRET}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class NicaKeyFinder extends NicaNamesListener {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(NicaKeyFinder.class.getName());


    @Override
    protected void nicaNamesAdded(final ServletRequest request,
                                  final Map<String, String> names) {

        final byte[] key = findKey(names);

        if (key == null) {
            setResponseError(request, HttpServletResponse.SC_UNAUTHORIZED,
                             "no key found");
        } else {
            if (key.length != Aes.KEY_SIZE_IN_BYTES) {
                LOGGER.log(Level.WARNING, "found key.length({0}) != {1}",
                           new Object[]{key.length, Aes.KEY_SIZE_IN_BYTES});
            }
            request.setAttribute(NicaFilter.ATTRIBUTE_NICA_KEY, key);
        }
    }


    /**
     * Finds the encryption key for given
     * <code>names</code>.
     *
     * @param names nica names
     * @return the encryption key or null if unavailable.
     */
    protected abstract byte[] findKey(Map<String, String> names);


}

