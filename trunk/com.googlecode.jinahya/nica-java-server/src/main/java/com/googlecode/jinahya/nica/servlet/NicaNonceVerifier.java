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
public abstract class NicaNonceVerifier extends NicaPerClientVerifier {


    @Override
    protected void verifyPerClient(final ServletRequest request,
                                 final Map<String, String> codes,
                                 final String platformId, final String deviceId,
                                 final String systemId) {

        final String requestNonce_ = codes.get(Code.REQUEST_NONCE.key());

        if (requestNonce_ == null) {
            setResponseError(request, HttpServletResponse.SC_BAD_REQUEST,
                             "missing code: " + Code.REQUEST_NONCE.key());
            return;
        }

        final long requestNonce;
        try {
            requestNonce = Long.parseLong(requestNonce_);
        } catch (NumberFormatException nfe) {
            setResponseError(request, HttpServletResponse.SC_BAD_REQUEST,
                             "wrong code: " + Code.REQUEST_NONCE.key());
            return;
        }

        final boolean nonceVerified = verifyNonce(
            platformId, deviceId, systemId, requestNonce);
        if (!nonceVerified) {
            setResponseError(request, HttpServletResponse.SC_BAD_REQUEST,
                             "unacceptable nonce");
            return;
        }
    }


    /**
     *
     * @param platformId
     * @param deviceId
     * @param systemId
     * @param requestNonce
     * @return true if given nonce is ok for given ids; false if duplicated
     */
    protected abstract boolean verifyNonce(final String platformId,
                                           final String deviceId,
                                           final String systemId,
                                           final long requestNonce);


}

