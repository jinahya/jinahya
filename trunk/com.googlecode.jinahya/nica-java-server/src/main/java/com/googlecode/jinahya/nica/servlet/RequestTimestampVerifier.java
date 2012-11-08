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


import com.googlecode.jinahya.nica.Code;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * An abstract ServletRequestAttributeListener for Nica-Codes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class RequestTimestampVerifier extends ClientIdListener {


    @Override
    protected void nicaClientIdsAdded(final ServletRequest request,
                                      final String platformId,
                                      final String deviceId,
                                      final String systemId) {

        final Map<String, String> codes =
            (Map<String, String>) request.getAttribute(
            NicaFilter.ATTRIBUTE_NICA_CODES);

        final String requestTimestampValue =
            codes.get(Code.REQUEST_TIMESTAMP.key());
        if (requestTimestampValue == null) {
            setResponseError(request, HttpServletResponse.SC_BAD_REQUEST,
                             "missing code: " + Code.REQUEST_NONCE.key());
            return;
        }

        final long requestTimestamp;
        try {
            requestTimestamp = Long.parseLong(requestTimestampValue);
        } catch (NumberFormatException nfe) {
            setResponseError(request, HttpServletResponse.SC_BAD_REQUEST,
                             "wrong code: " + Code.REQUEST_NONCE.key());
            return;
        }

        final boolean requestTimestampVerified = verifyRequestTimestamp(
            platformId, deviceId, systemId, requestTimestamp);
        if (!requestTimestampVerified) {
            setResponseError(request, HttpServletResponse.SC_BAD_REQUEST,
                             "unaccpetable timestamp");
            return;
        }
    }


    /**
     * Verifies given
     * <code>requestTimestamp</code> per client.
     *
     * @param platformId platform id
     * @param deviceId device id
     * @param systemId system id
     * @param requestTimestamp request timestamp
     * @return true if given timestamp is latest for given ids; false if not
     */
    protected abstract boolean verifyRequestTimestamp(
        final String platformId, final String deviceId, final String systemId,
        final long requestTimestamp);


}

