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
import java.util.Arrays;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * An abstract ServletRequestAttributeListener for Nica-Codes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class ClientIdsVerifier extends NicaCodesListener {


    @Override
    protected void nicaCodesAdded(final ServletRequest request,
                                  final Map<String, String> codes) {

        final String platformId = codes.get(Code.PLATFORM_ID.key());

        if (platformId == null) {
            setResponseError(request, HttpServletResponse.SC_BAD_REQUEST,
                             "missing code: " + Code.PLATFORM_ID.key());
            return;
        }

        if (platformId.trim().isEmpty()) {
            setResponseError(request, HttpServletResponse.SC_BAD_REQUEST,
                             "wrong code: " + Code.PLATFORM_ID.key());
            return;
        }

        String deviceId = codes.get(Code.DEVICE_ID.key());
        if (deviceId != null && deviceId.trim().isEmpty()) {
            setResponseError(request, HttpServletResponse.SC_BAD_REQUEST,
                             "wrong code: " + Code.DEVICE_ID.key());
            return;
        }

        String systemId = codes.get(Code.SYSTEM_ID.key());
        if (systemId != null && systemId.trim().isEmpty()) {
            setResponseError(request, HttpServletResponse.SC_BAD_REQUEST,
                             "wrong code: " + Code.SYSTEM_ID.key());
            return;
        }

        if (systemId == null && systemId == null) {
            setResponseError(request, HttpServletResponse.SC_BAD_REQUEST,
                             "missing both code: " + Code.DEVICE_ID.key()
                             + " and " + Code.SYSTEM_ID.key());
            return;
        }

        if (deviceId == null) {
            deviceId = "";
        }

        if (systemId == null) {
            systemId = "";
        }

        request.setAttribute(NicaFilter.ATTRIBUTE_NICA_CLIENT_IDS,
                             Arrays.asList(platformId, deviceId, systemId));
    }


}

