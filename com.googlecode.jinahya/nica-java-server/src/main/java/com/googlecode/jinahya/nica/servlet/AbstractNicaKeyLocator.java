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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.http.HttpServletResponse;


/**
 * An abstract ServletRequestAttributeListener for Nica-Names. One of primary
 * goal of the implementing class is locating a secret key and add it to
 * specified ServletRequest as an attribute named
 * {@link NicaFilter#ATTRIBUTE_NICA_KEY}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class AbstractNicaKeyLocator
    extends NicaNamesAttributeListenerL2 {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(AbstractNicaKeyLocator.class.getName());


    /**
     * The attribute name for located key. The value must be an array of bytes.
     */
    public static final String ATTRIBUTE_NICA_LOCATED_KEY =
        AbstractNicaKeyLocator.class.getName() + "#located_key";


    @Override
    protected void nicaNamesAdded(final ServletRequestAttributeEvent event)
        throws Exception {

        final byte[] locatedKey = locateNicaKey(event);

        if (locatedKey == null) {
            setResponseError(event, HttpServletResponse.SC_BAD_REQUEST,
                             "no key located");
            return;
        }

        if (locatedKey.length != Aes.KEY_SIZE_IN_BYTES) {
            LOGGER.log(Level.WARNING, "located.key.length({0}) != {1}",
                       new Object[]{locatedKey.length, Aes.KEY_SIZE_IN_BYTES});
            setResponseError(
                event, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "wrong key located");
            return;
        }

        event.getServletRequest().setAttribute(
            ATTRIBUTE_NICA_LOCATED_KEY,
            Arrays.copyOf(locatedKey, locatedKey.length));

        LOGGER.fine("key located and attributed");
    }


    /**
     * Locates the key for given nica names.
     *
     * @param event the servlet request attribute event
     *
     * @return the located key or {@code null} if no key mapped to given names.
     *
     * @throws if any error occurs.
     */
    protected abstract byte[] locateNicaKey(
        final ServletRequestAttributeEvent event)
        throws Exception;


}
