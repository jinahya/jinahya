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
 * An abstract ServletRequestAttributeListener.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class NicaAttributeListener
    implements ServletRequestAttributeListener {


    /**
     * Sets HTTP response error.
     *
     * @param srae event
     * @param statusCode HTTP response status code
     * @param reasonPhrase HTTP response reason phrase
     */
    protected void setResponseError(final ServletRequestAttributeEvent srae,
                                    final int statusCode,
                                    final String reasonPhrase) {

        if (srae == null) {
            throw new IllegalArgumentException("null event");
        }

        setResponseError(srae.getServletRequest(), statusCode, reasonPhrase);
    }


    /**
     * Sets HTTP response error.
     *
     * @param request request
     * @param statusCode HTTP response status code
     * @param reasonPhrase HTTP response reason phrase
     */
    protected void setResponseError(final ServletRequest request,
                                    final int statusCode,
                                    final String reasonPhrase) {
        if (request == null) {
            throw new IllegalArgumentException("null sr");
        }

        request.setAttribute(NicaFilter.ATTRIBUTE_ERROR_RESPONSE_STATUS_CODE,
                             statusCode);
        request.setAttribute(NicaFilter.ATTRIBUTE_ERROR_RESPONSE_REASON_PHRASE,
                             reasonPhrase);
    }


    protected boolean hasResponseError(
        final ServletRequestAttributeEvent srae) {
        return hasResponseError(srae.getServletRequest());
    }


    protected boolean hasResponseError(final ServletRequest request) {
        return request.getAttribute(
            NicaFilter.ATTRIBUTE_ERROR_RESPONSE_STATUS_CODE) != null;
    }


    /**
     * Overridden to do nothing.
     *
     * @param srae {@inheritDoc }
     */
    @Override
    public void attributeRemoved(final ServletRequestAttributeEvent srae) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }


    /**
     * Overridden to do nothing.
     *
     * @param srae {@inheritDoc }
     */
    @Override
    public void attributeReplaced(final ServletRequestAttributeEvent srae) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }


    private boolean responseErrorHasBeenSet = false;


}

