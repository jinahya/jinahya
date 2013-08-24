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


import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;


/**
 * An abstract class for listening request attribute changes from
 * {@link NicaFilter}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class NicaRequestAttributeListener
    implements ServletRequestAttributeListener {


    /**
     * Checks if a response error has been set.
     *
     * @param event the event
     *
     * @return true if a response error has been set; false otherwise.
     */
    protected static boolean hasResponseError(
        final ServletRequestAttributeEvent event) {

        if (event == null) {
            throw new IllegalArgumentException("event");
        }

        final Integer statusCode =
            (Integer) event.getServletRequest().getAttribute(
            NicaFilter.ATTRIBUTE_RESPONSE_ERROR_STATUS_CODE);

        return statusCode != null;
    }


    /**
     * Sets an HTTP response error.
     *
     * @param event the servlet request attribute listener.
     * @param statusCode HTTP response status code
     * @param reasonPhrase HTTP response reason phrase
     */
    protected void setResponseError(final ServletRequestAttributeEvent event,
                                    final Integer statusCode,
                                    String reasonPhrase) {

        if (event == null) {
            throw new IllegalArgumentException("event");
        }

        if (statusCode == null) {
            reasonPhrase = null;
        }

        final ServletRequest request = event.getServletRequest();

        request.setAttribute(NicaFilter.ATTRIBUTE_RESPONSE_ERROR_STATUS_CODE,
                             statusCode);
        request.setAttribute(NicaFilter.ATTRIBUTE_RESPONSE_ERROR_REASON_PHRASE,
                             reasonPhrase);
    }


    /**
     * Overridden to do nothing.
     *
     * @param event {@inheritDoc }
     */
    @Override
    public void attributeRemoved(final ServletRequestAttributeEvent event) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }


    /**
     * Overridden to do nothing.
     *
     * @param event {@inheritDoc }
     */
    @Override
    public void attributeReplaced(final ServletRequestAttributeEvent event) {
        // throw new UnsupportedOperationException("Not supported yet.");
    }


}
