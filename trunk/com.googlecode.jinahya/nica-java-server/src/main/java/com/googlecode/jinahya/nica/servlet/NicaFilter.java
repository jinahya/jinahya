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
import com.googlecode.jinahya.nica.Header;
import com.googlecode.jinahya.nica.util.Aes;
import com.googlecode.jinahya.nica.util.AesJCE;
import com.googlecode.jinahya.nica.util.HacJCE;
import com.googlecode.jinahya.nica.util.Hex;
import com.googlecode.jinahya.nica.util.Par;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class NicaFilter implements Filter {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(NicaFilter.class.getName());


    /**
     * The attribute name for decoded value of {@link Header#NAME}. The value is
     * an instance of unmodifiable
     * <code>Map&lt;String, String&gt;</code>.
     */
    protected static final String ATTRIBUTE_NICA_NAMES =
        NicaFilter.class.getName() + "#names";


    /**
     * The attribute name for located encryption key. The value must be an array
     * of bytes.
     */
    protected static final String ATTRIBUTE_NICA_KEY =
        NicaFilter.class.getName() + "#key";


    /**
     * The attribute name for decoded value of {@link Header#CODE}. The value is
     * an instance of unmodifiable
     * <code>Map&lt;String, String&gt;</code>.
     */
    protected static final String ATTRIBUTE_NICA_CODES =
        NicaFilter.class.getName() + "#codes";


    /**
     * The attribute name for response status code. The value must be an
     * Integer.
     */
    public static final String ATTRIBUTE_ERROR_RESPONSE_STATUS_CODE =
        NicaFilter.class.getName() + "#error_response_status_code";


    /**
     * The attribute name for response reason phrase.
     */
    public static final String ATTRIBUTE_ERROR_RESPONSE_REASON_PHRASE =
        NicaFilter.class.getName() + "#error_response_reason_phrase";


    /**
     * Checks error response attributes.
     *
     * @param request request
     * @param response response
     * @return true if ok; false if error has been sent
     * @throws IOException if an I/O error occurs
     */
    private static boolean checkErrorResponseAttributes(
        final HttpServletRequest request, final HttpServletResponse response)
        throws IOException {

        if (request == null) {
            throw new IllegalArgumentException("null request");
        }

        if (response == null) {
            throw new IllegalArgumentException("null response");
        }

        final Integer statusCode = (Integer) request.getAttribute(
            ATTRIBUTE_ERROR_RESPONSE_STATUS_CODE);
        if (statusCode == null) {
            return true;
        }

        final String reasonPhrase = (String) request.getAttribute(
            ATTRIBUTE_ERROR_RESPONSE_REASON_PHRASE);

        if (reasonPhrase == null) {
            response.sendError(statusCode.intValue());
        } else {
            response.sendError(statusCode.intValue(), reasonPhrase);
        }

        return false;
    }


    /**
     * Returns an unmodifiable map of {@link Header#NAME}.
     *
     * @param request servlet request
     *
     * @return an unmodifiable map of {@link Header#NAME}
     */
    @SuppressWarnings("unchecked")
    protected static Map<String, String> getNicaNames(
        final ServletRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("null request");
        }

        return (Map<String, String>) request.getAttribute(ATTRIBUTE_NICA_NAMES);
    }


    /**
     * Returns the value mapped to given
     * <code>key</code>.
     *
     * @param request HttpServletRequest instance
     * @param key key
     *
     * @return the value mapped to given <code>key</code> or <code>null</code>
     * if not found
     */
    protected static String getNicaName(final ServletRequest request,
                                        final String key) {

        if (request == null) {
            throw new IllegalArgumentException("null request");
        }

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        final Map<String, String> names = getNicaNames(request);

        if (names == null) {
            return null;
        }

        return names.get(key);
    }


    /**
     * Returns an unmodifiable map of {@link Header#CODE}.
     *
     * @param request servlet request
     *
     * @return an unmodifiable map of {@link Header#CODE}
     */
    @SuppressWarnings("unchecked")
    protected static Map<String, String> getNicaCodes(
        final ServletRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("null request");
        }

        return (Map<String, String>) request.getAttribute(ATTRIBUTE_NICA_CODES);
    }


    /**
     * Return the value mapped to given
     * <code>key</code>.
     *
     * @param request ServletRequest instance
     * @param key key
     *
     * @return the value mapped to give <code>key</code> or <code>nul</code> if
     * not found
     */
    protected static String getNicaCode(final ServletRequest request,
                                        final String key) {

        if (request == null) {
            throw new IllegalArgumentException("null request");
        }

        if (key == null) {
            throw new IllegalArgumentException("null key");
        }

        final Map<String, String> codes = getNicaCodes(request);

        if (codes == null) {
            return null;
        }

        return codes.get(key);
    }


    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain)
        throws IOException, ServletException {

        final HttpServletRequest hequest = (HttpServletRequest) request;
        final HttpServletResponse hesponse = (HttpServletResponse) response;


        // ----------------------------------------------------------- Nica-Name
        final String nicaName = hequest.getHeader(Header.NAME.fieldName());
        if (nicaName == null) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "missing header: " + Header.NAME.fieldName());
            return;
        }
        final Map<String, String> names;
        try {
            names = Par.decode(nicaName);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "wrong header: " + Header.NAME.fieldName() + ": "
                + e.getMessage());
            return;
        }
        hequest.setAttribute(ATTRIBUTE_NICA_NAMES,
                             Collections.unmodifiableMap(names));
        if (!checkErrorResponseAttributes(hequest, hesponse)) {
            return;
        }

        final byte[] key = (byte[]) request.getAttribute(ATTRIBUTE_NICA_KEY);
        if (key == null) {
            hesponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                               "no key found");
            return;
        }
        if (key.length != Aes.KEY_SIZE_IN_BYTES) {
            LOGGER.log(Level.SEVERE, "key.length({0}) != {1}",
                       new Object[]{key.length, Aes.KEY_SIZE_IN_BYTES});
            hesponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        // ----------------------------------------------------------- Nica-Init
        final String nicaInit = hequest.getHeader(Header.INIT.fieldName());
        if (nicaInit == null) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "missing header: " + Header.INIT.fieldName());
            return;
        }
        final byte[] iv;
        try {
            iv = Hex.decode(nicaInit);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "wrong header: " + Header.INIT.fieldName());
            return;
        }
        if (iv.length != Aes.BLOCK_SIZE_IN_BYTES) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "wrong header: " + Header.INIT.fieldName());
            return;
        }

        // ----------------------------------------------------------- Nica-Code
        final String nicaCode = hequest.getHeader(Header.CODE.fieldName());
        if (nicaCode == null) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "missing header: " + Header.CODE.fieldName());
            return;
        }
        final byte[] base;
        try {
            base = new AesJCE(key).decrypt(iv, Hex.decode(nicaCode));
        } catch (Exception e) {
            e.printStackTrace(System.err);
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "wrong header: " + Header.CODE.fieldName());
            return;
        }
        final Map<String, String> codes;
        try {
            codes = Par.decode(new String(base, "US-ASCII"));
        } catch (Exception e) {
            e.printStackTrace(System.err);
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "wrong header: " + Header.CODE.fieldName());
            return;
        }

        // ----------------------------------------------- Nica-Code/PLATFORM_ID
        final String platformId = codes.get(Code.PLATFORM_ID.key());
        if (platformId == null) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "missing code: " + Code.PLATFORM_ID.key());
            return;
        }
        if (platformId.trim().isEmpty()) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "empty (or blank) code: " + Code.PLATFORM_ID.key());
            return;
        }

        // ------------------------------------------------- Nica-Code/DEVICE_ID
        final String deviceId = codes.get(Code.DEVICE_ID.key());
        if (deviceId != null && deviceId.trim().isEmpty()) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "empty (or blank) code: " + Code.DEVICE_ID.key());
            return;
        }

        // ------------------------------------------------- Nica-Code/SYSTEM_ID
        final String systemId = codes.get(Code.SYSTEM_ID.key());
        if (systemId != null && systemId.trim().isEmpty()) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "empty (or blank) code: " + Code.SYSTEM_ID.key());
            return;
        }

        // --------------------------------------- Nica-Code/DEVICE_ID+SYSTEM_ID
        if (deviceId == null && systemId == null) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "no " + Code.DEVICE_ID.name() + " nor "
                + Code.SYSTEM_ID.name());
            return;
        }

        // ----------------------------------------------------------- Nica-Auth
        final String nicaAuth = hequest.getHeader(Header.AUTH.fieldName());
        if (nicaAuth == null) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "missing header: " + Header.AUTH.fieldName());
            return;
        }
        final byte[] auth;
        try {
            auth = Hex.decode(nicaAuth);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "wrong header: " + Header.AUTH.fieldName() + ": "
                + e.getMessage());
            return;
        }
        if (!Arrays.equals(new HacJCE(key).authenticate(base), auth)) {
            hesponse.sendError(
                HttpServletResponse.SC_UNAUTHORIZED, "authentication failed");
            return;
        }

        request.setAttribute(ATTRIBUTE_NICA_CODES,
                             Collections.unmodifiableMap(codes));
        if (!checkErrorResponseAttributes(hequest, hesponse)) {
            return;
        }

        chain.doFilter(request, response);
    }


}

