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


import java.util.Map;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.http.HttpServletResponse;


/**
 * An abstract ServletRequestAttributeListener for
 * {@link NicaFilter#ATTRIBUTE_NICA_NAMES}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
abstract class AbstractNicaNamesAttributeListener
    extends NicaRequestAttributeListener {


    protected AbstractNicaNamesAttributeListener(final String attributeName) {

        super();

        if (attributeName == null) {
            throw new NullPointerException("attributeName");
        }

        this.attributeName = attributeName;
    }


    @Override
    public void attributeAdded(final ServletRequestAttributeEvent event) {

        if (!attributeName.equals(event.getName())) {
            return;
        }

        try {
            nicaNamesAdded(event);
        } catch (final Exception e) {
            e.printStackTrace(System.err);
            setResponseError(
                event, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                e.getMessage());
            return; // unnecessary?
        }
    }


    /**
     * Notifies nica names added.
     *
     * @param event the servlet request attribute event
     *
     * @throws Exception if any error occurs.
     */
    protected abstract void nicaNamesAdded(ServletRequestAttributeEvent event)
        throws Exception;


    protected Map<String, String> getNicaNames(
        final ServletRequestAttributeEvent event) {

        if (event == null) {
            throw new NullPointerException("event");
        }

        @SuppressWarnings("unchecked")
        final Map<String, String> nicaNames =
            (Map<String, String>) event.getServletRequest().getAttribute(
            attributeName);

        if (nicaNames == null) {
            final String message = "no nica names in the context";
            setResponseError(
                event, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message);
            throw new RuntimeException(message);
        }

        return nicaNames;
    }


    /**
     * request attribute name.
     */
    private final String attributeName;


}
