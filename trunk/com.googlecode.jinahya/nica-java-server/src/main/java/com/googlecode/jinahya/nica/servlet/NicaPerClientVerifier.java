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


/**
 * An abstract ServletRequestAttributeListener for Nica-Codes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class NicaPerClientVerifier extends NicaCodesListener {


    @Override
    protected void nicaCodesAdded(final ServletRequest request,
                                  final Map<String, String> codes) {

        final String platformId = codes.get(Code.PLATFORM_ID.key());

        String deviceId = codes.get(Code.DEVICE_ID.key());
        if (deviceId == null) {
            deviceId = "";
        }

        String systemId = codes.get(Code.SYSTEM_ID.key());
        if (systemId == null) {
            systemId = "";
        }

        verifyPerClient(request, codes, platformId, deviceId, systemId);
    }


    /**
     * Verifies nica codes per client.
     *
     * @param request request
     * @param codes nica codes
     * @param platformId platform id
     * @param deviceId device id
     * @param systemId system id
     */
    protected abstract void verifyPerClient(
        final ServletRequest request, final Map<String, String> codes,
        final String platformId, final String deviceId, final String systemId);


}

