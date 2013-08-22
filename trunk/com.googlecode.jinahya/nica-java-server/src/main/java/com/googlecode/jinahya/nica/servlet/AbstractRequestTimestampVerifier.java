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


import javax.servlet.ServletRequestAttributeEvent;


/**
 * An abstract ServletRequestAttributeListener for Nica-Codes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class AbstractRequestTimestampVerifier
    extends NicaCodesAttributeListenerL3 {


    @Override
    protected void nicaCodesAdded(final ServletRequestAttributeEvent event)
        throws Exception {

        final long requestTimestamp = getRequestTimestamp(event);

        verifyTimestamp(event, requestTimestamp);
    }


    /**
     * Verifies specified request timestamp. Subclasses must set the response
     * error via
     * {@link #setResponseError(ServletRequestAttributeEvent, int, String)} if
     * specified request timestamp is not acceptable.
     * <p><pre>
     * final long threshold = System.currentTimestamp() - 10000L; // 10 secs
     * if (requestTimestamp > threshold) {
     *     // acceptable
     *     return;
     * }
     * setResponseError(event, HttpServletResponse.SC_BAD_REQUEST,
     *                  "too old request timestamp");
     * return;
     * </pre></p>
     *
     * @param event the servlet request attribute event
     * @param requestTimestamp the request timestamp
     *
     * @throws Exception if any error occurs.
     *
     * @see #setResponseError(ServletRequestAttributeEvent, int, String)
     */
    protected abstract void verifyTimestamp(
        ServletRequestAttributeEvent event, long requestTimestamp)
        throws Exception;


}
