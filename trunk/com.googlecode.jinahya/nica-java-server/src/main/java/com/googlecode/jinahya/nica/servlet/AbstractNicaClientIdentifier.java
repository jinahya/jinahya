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
import com.googlecode.jinahya.nica.util.ShaJCA;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.http.HttpServletResponse;


/**
 *
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class AbstractNicaClientIdentifier
    extends NicaCodesAttributeListenerL2 {


    private static final Logger LOGGER =
        Logger.getLogger(AbstractNicaClientIdentifier.class.getName());


    static {
        LOGGER.setLevel(Level.INFO);
        LOGGER.setUseParentHandlers(false);
        LOGGER.addHandler(new ConsoleHandler());
    }


    public static final String ATTRIBUTE_NICA_CLIENT_IDENTITY =
        AbstractNicaClientIdentifier.class.getName() + "#client_identity";


    @Override
    protected void nicaCodesAdded(final ServletRequestAttributeEvent event)
        throws Exception {

        final String clientIdentifier;
        try {
            clientIdentifier = identifyNicaClient(event);
        } catch (final Exception e) {
            e.printStackTrace(System.err);
            setResponseError(
                event, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                e.getMessage());
            return;
        }

        if (clientIdentifier == null) {
            setResponseError(
                event, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "no client identity genreated");
            return;
        }

        event.getServletRequest().setAttribute(
            ATTRIBUTE_NICA_CLIENT_IDENTITY, clientIdentifier);
        LOGGER.fine("client identified and attributed");
    }


    /**
     * Identifies the nica client and returns an identifier.
     *
     * @param event the servlet request attribute event
     *
     * @return a client identifier.
     *
     * @throws Exception if any error occurs.
     */
    protected String identifyNicaClient(
        final ServletRequestAttributeEvent event)
        throws Exception {

        final Map<String, String> codes = getNicaCodes(event);

        // --------------------------------------------------------- PLATFORM_ID
        final String platformId = codes.get(CodeKeys.PLATFORM_ID);
        if (platformId == null || platformId.trim().isEmpty()) {
            final String message =
                "missing, empty, or blank code: " + CodeKeys.PLATFORM_ID;
            setResponseError(event, HttpServletResponse.SC_BAD_REQUEST,
                             message);
            throw new RuntimeException(message);
        }

        // ----------------------------------------------------------- DEVICE_ID
        final String deviceId = codes.get(CodeKeys.DEVICE_ID);
        if (deviceId != null && deviceId.trim().isEmpty()) {
            final String message =
                "missing, empty, or blank code: " + CodeKeys.DEVICE_ID;
            setResponseError(event, HttpServletResponse.SC_BAD_REQUEST,
                             message);
            throw new RuntimeException(message);
        }

        // ----------------------------------------------------------- SYSTEM_ID
        final String systemId = codes.get(CodeKeys.SYSTEM_ID);
        if (systemId != null && systemId.trim().isEmpty()) {
            final String message =
                "missing, empty, or blank code: " + CodeKeys.SYSTEM_ID;
            setResponseError(event, HttpServletResponse.SC_BAD_REQUEST,
                             message);
            throw new RuntimeException(message);
        }

        // ------------------------------------------------------------- USER_ID
        final String userId = codes.get(CodeKeys.USER_ID);
        if (userId != null && userId.trim().isEmpty()) {
            final String message =
                "missing, empty, or blank code: " + CodeKeys.USER_ID;
            setResponseError(event, HttpServletResponse.SC_BAD_REQUEST,
                             message);
            throw new RuntimeException(message);
        }

        return new ShaJCA().hashToString(
            platformId + deviceId + systemId + userId);
    }


}
