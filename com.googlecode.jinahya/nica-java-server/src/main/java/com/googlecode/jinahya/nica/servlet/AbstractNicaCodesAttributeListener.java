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


import com.googlecode.jinahya.nica.CodeKeys;
import java.util.Map;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.http.HttpServletResponse;


abstract class AbstractNicaCodesAttributeListener
    extends NicaRequestAttributeListener {


    protected AbstractNicaCodesAttributeListener(final String attributeName) {

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
            nicaCodesAdded(event);
        } catch (final Exception e) {
            e.printStackTrace(System.err);
            setResponseError(
                event, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                e.getMessage());
            return; // unnecessary?
        }
    }


    /**
     * Notifies nica codes added.
     *
     * @param event the servlet request attribute event
     *
     * @throws Exception if any error occurs.
     */
    protected abstract void nicaCodesAdded(ServletRequestAttributeEvent event)
        throws Exception;


    protected Map<String, String> getNicaCodes(
        final ServletRequestAttributeEvent event) {

        if (event == null) {
            throw new NullPointerException("event");
        }

        @SuppressWarnings("unchecked")
        final Map<String, String> nicaCodes =
            (Map<String, String>) event.getServletRequest().getAttribute(
            attributeName);

        if (nicaCodes == null) {
            final String message = "no nica codes in the context";
            setResponseError(
                event, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message);
            throw new RuntimeException(message);
        }

        return nicaCodes;
    }


    protected long getRequestTimestamp(
        final ServletRequestAttributeEvent event) {

        if (event == null) {
            throw new NullPointerException("event");
        }

        final Map<String, String> codes = getNicaCodes(event);

        final String timesamp_ = codes.get(CodeKeys.REQUEST_TIMESTAMP);
        if (timesamp_ == null) {
            final String message =
                "no code value for " + CodeKeys.REQUEST_TIMESTAMP;
            setResponseError(event, HttpServletResponse.SC_BAD_REQUEST,
                             message);
            throw new RuntimeException(message);
        }

        final long timestamp;
        try {
            timestamp = Long.parseLong(timesamp_);
        } catch (final NumberFormatException nfe) {
            final String message =
                "wrong code value for " + CodeKeys.REQUEST_TIMESTAMP;
            setResponseError(event, HttpServletResponse.SC_BAD_REQUEST,
                             message);
            throw new RuntimeException(message);
        }

        return timestamp;
    }


    protected String getRequestNonce(final ServletRequestAttributeEvent event) {

        if (event == null) {
            throw new NullPointerException("event");
        }

        final Map<String, String> codes = getNicaCodes(event);

        final String nonce = codes.get(CodeKeys.REQUEST_NONCE);
        if (nonce == null) {
            final String message =
                "no code value for " + CodeKeys.REQUEST_NONCE;
            setResponseError(event, HttpServletResponse.SC_BAD_REQUEST,
                             message);
            throw new RuntimeException(message);
        }

        return nonce;
    }


    /**
     * request attribute name.
     */
    private final String attributeName;


}
