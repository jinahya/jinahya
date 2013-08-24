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


import com.googlecode.jinahya.nica.HeaderFields;
import com.googlecode.jinahya.nica.util.Aes;
import com.googlecode.jinahya.nica.util.AesJCE;
import com.googlecode.jinahya.nica.util.HacJCE;
import com.googlecode.jinahya.nica.util.Hex;
import com.googlecode.jinahya.nica.util.Par;
import com.googlecode.jinahya.servlet.http.HttpFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class NicaFilter extends HttpFilter {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(NicaFilter.class.getName());


    static {
        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);
        LOGGER.addHandler(new ConsoleHandler());
    }


    protected static final String ATTRIBUTE_NAMESPACE =
        NicaFilter.class.getName();


    /**
     * The attribute name for decoded value of {@link HeaderFields#NAME}. The
     * value is an instance of unmodifiable {@code Map&lt;String, String&gt;}.
     */
    public static final String ATTRIBUTE_NICA_NAMES =
        ATTRIBUTE_NAMESPACE + "#nica_names";


    /**
     * The attribute name for decoded value of {@link HeaderFields#NAME}. The
     * value is an instance of unmodifiable {@code Map&lt;String, String&gt;}.
     */
    public static final String ATTRIBUTE_NICA_NAMES_L2 =
        ATTRIBUTE_NICA_NAMES + "/l2";


    /**
     * The attribute name for decoded value of {@link HeaderFields#NAME}. The
     * value is an instance of unmodifiable {@code Map&lt;String, String&gt;}.
     */
    public static final String ATTRIBUTE_NICA_NAMES_L3 =
        ATTRIBUTE_NICA_NAMES + "/l3";


    /**
     * The attribute name for decoded value of {@link HeaderFields#CODE}. The
     * value is an instance of unmodifiable {@code Map&lt;String, String&gt;}.
     */
    public static final String ATTRIBUTE_NICA_CODES =
        ATTRIBUTE_NAMESPACE + "#nica_codes";


    /**
     * The attribute name for decoded value of {@link HeaderFields#CODE}. The
     * value is an instance of unmodifiable {@code Map&lt;String, String&gt;}.
     */
    public static final String ATTRIBUTE_NICA_CODES_L2 =
        ATTRIBUTE_NICA_CODES + "/l2";


    /**
     * The attribute name for decoded value of {@link HeaderFields#CODE}. The
     * value is an instance of unmodifiable {@code Map&lt;String, String&gt;}.
     */
    public static final String ATTRIBUTE_NICA_CODES_L3 =
        ATTRIBUTE_NICA_CODES + "/l3";


//    /**
//     * The attribute name for client id consists
//     * {@link CodeKey#PLATFORM_ID}, {@link CodeKey#DEVICE_ID}, and
//     * {@link CodeKey#SYSTEM_ID}. The value must be an unmodifiable
//     * {@link java.util.List} of {@link java.lang.String}.
//     */
//    protected static final String ATTRIBUTE_NICA_CLIENT_ID =
//        NicaFilter.class.getName() + "#client_id";
    /**
     * The attribute name for response status code. The value must be an int.
     */
    public static final String ATTRIBUTE_RESPONSE_ERROR_STATUS_CODE =
        ATTRIBUTE_NAMESPACE + "#error_response_status_code";


    /**
     * The attribute name for response reason phrase. The value must be a
     * String.
     */
    public static final String ATTRIBUTE_RESPONSE_ERROR_REASON_PHRASE =
        ATTRIBUTE_NICA_NAMES + "#error_response_reason_phrase";


    /**
     * Checks any error occurred and send if exist.
     *
     * @param request request
     * @param response response
     *
     * @return true if error exists and sent; false otherwise.
     *
     * @throws IOException if an I/O error occurs
     */
    private static boolean errorOccuredAndSent(
        final HttpServletRequest request, final HttpServletResponse response)
        throws IOException {

        if (request == null) {
            throw new IllegalArgumentException("request");
        }

        if (response == null) {
            throw new IllegalArgumentException("response");
        }

        /*
         if (response.isCommitted()) {
         return true;
         }
         */

        final Integer statusCode = (Integer) request.getAttribute(
            ATTRIBUTE_RESPONSE_ERROR_STATUS_CODE);
        if (statusCode == null) {
            return false;
        }

        final String reasonPhrase = (String) request.getAttribute(
            ATTRIBUTE_RESPONSE_ERROR_REASON_PHRASE);

        if (reasonPhrase == null) {
            response.sendError(statusCode.intValue());
        } else {
            response.sendError(statusCode.intValue(), reasonPhrase);
        }

        return true;
    }


    /**
     * Returns an unmodifiable map of nica names.
     *
     * @param request the servlet request
     *
     * @return an unmodifiable map of nica names.
     */
    @SuppressWarnings("unchecked")
    protected static Map<String, String> getNicaNames(
        final ServletRequest request) {

        if (request == null) {
            throw new NullPointerException("request");
        }

        return (Map<String, String>) request.getAttribute(ATTRIBUTE_NICA_NAMES);
    }


    /**
     * Returns the nica name value mapped to given nica name key.
     *
     * @param request HttpServletRequest instance
     * @param key the nica name key
     *
     * @return the nica name value mapped to given nica name key or {@code null}
     * if no entries found.
     */
    protected static String getNicaName(final ServletRequest request,
                                        final String key) {

        if (request == null) {
            throw new NullPointerException("request");
        }

        if (key == null) {
            throw new NullPointerException("key");
        }

        final Map<String, String> names = getNicaNames(request);

        if (names == null) {
            return null;
        }

        return names.get(key);
    }


    /**
     * Returns an unmodifiable map of nica codes.
     *
     * @param request the servlet request
     *
     * @return an unmodifiable map of nica codes.
     */
    @SuppressWarnings("unchecked")
    protected static Map<String, String> getNicaCodes(
        final ServletRequest request) {

        if (request == null) {
            throw new NullPointerException("request");
        }

        return (Map<String, String>) request.getAttribute(ATTRIBUTE_NICA_CODES);
    }


    /**
     * Return the nica code value mapped to given nica code key.
     *
     * @param request the servlet request
     * @param key the nica code key
     *
     * @return the nica code value mapped to give nica code key or {@code null}
     * if no entries found.
     */
    protected static String getNicaCode(final ServletRequest request,
                                        final String key) {

        if (request == null) {
            throw new NullPointerException("request");
        }

        if (key == null) {
            throw new NullPointerException("key");
        }

        final Map<String, String> codes = getNicaCodes(request);

        if (codes == null) {
            return null;
        }

        return codes.get(key);
    }


    @Override
    public void doFilter(final HttpServletRequest request,
                         final HttpServletResponse response,
                         final FilterChain chain)
        throws IOException, ServletException {

        // ----------------------------------------------------------- Nica-Name
        final String nicaName = request.getHeader(HeaderFields.NAME);
        if (nicaName == null) {
            final String message = "missing header: " + HeaderFields.NAME;
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
            return;
        }
        final Map<String, String> nicaNames;
        try {
            @SuppressWarnings("unchecked")
            final Map<String, String> nicaNames_ = Par.decode(nicaName);
            nicaNames = Collections.unmodifiableMap(nicaNames_);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            final String message =
                "wrong header: " + HeaderFields.NAME + ": " + e.getMessage();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
            return;
        }
        request.setAttribute(ATTRIBUTE_NICA_NAMES, nicaNames);
        if (errorOccuredAndSent(request, response)) {
            return;
        }
        request.setAttribute(ATTRIBUTE_NICA_NAMES_L2, nicaNames);
        if (errorOccuredAndSent(request, response)) {
            return;
        }
        request.setAttribute(ATTRIBUTE_NICA_NAMES_L3, nicaNames);
        if (errorOccuredAndSent(request, response)) {
            return;
        }
        // Re-chekcing the key. Do not trust developers.
        byte[] key = (byte[]) request.getAttribute(
            AbstractNicaKeyLocator.ATTRIBUTE_NICA_LOCATED_KEY);
        if (key == null) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               "no key located");
            return;
        }
        if (key.length != Aes.KEY_SIZE_IN_BYTES) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               "wrong key located");
            return;
        }
        key = Arrays.copyOf(key, key.length);

        // ----------------------------------------------------------- Nica-Init
        final String nicaInit = request.getHeader(HeaderFields.INIT);
        if (nicaInit == null) {
            final String message = "missing header: " + HeaderFields.INIT;
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
            return;
        }
        final byte[] iv;
        try {
            iv = Hex.decode(nicaInit);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            final String message =
                "wrong header: " + HeaderFields.INIT + ": " + e.getMessage();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
            return;
        }
        if (iv.length != Aes.BLOCK_SIZE_IN_BYTES) {
            final String message = "wrong header: " + HeaderFields.INIT;
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
            return;
        }
        boolean zeros = true;
        for (byte b : iv) {
            if (b != 0) {
                zeros = false;
                break;
            }
        }
        if (zeros) {
            final String message =
                "wrong header: " + HeaderFields.INIT + " all zeros";
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
            return;
        }

        // ----------------------------------------------------------- Nica-Code
        final String nicaCode = request.getHeader(HeaderFields.CODE);
        if (nicaCode == null) {
            final String message = "missing header: " + HeaderFields.CODE;
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
            return;
        }
        final byte[] base;
        try {
            base = new AesJCE(key).decrypt(iv, Hex.decode(nicaCode));
        } catch (Exception e) {
            e.printStackTrace(System.err);
            final String message =
                "wrong header: " + HeaderFields.CODE + ": " + e.getMessage();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
            return;
        }

        // =====================================================================
        // DO NOT PROCEED WITH CODES YET!
        // WE MUST VERIFY THEM FIRST!
        // =====================================================================

        // ----------------------------------------------------------- Nica-Auth
        final String nicaAuth = request.getHeader(HeaderFields.AUTH);
        if (nicaAuth == null) {
            final String message = "missing header: " + HeaderFields.AUTH;
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
            return;
        }
        final byte[] auth;
        try {
            auth = Hex.decode(nicaAuth);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            final String message =
                "wrong header: " + HeaderFields.AUTH + ": " + e.getMessage();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
            return;
        }
        if (!Arrays.equals(new HacJCE(key).authenticate(base), auth)) {
            final String message = "authentication failed";
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
            return;
        }

        // =====================================================================
        // AUTHENTICATED. NOW WE CAN PROCEED WIDH CODES.
        // =====================================================================

        final Map<String, String> nicaCodes;
        try {
            @SuppressWarnings("unchecked")
            final Map<String, String> nicaCodes_ =
                (Map<String, String>) Par.decode(new String(base, "US-ASCII"));
            nicaCodes = Collections.unmodifiableMap(nicaCodes_);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            final String message =
                "wrong header: " + HeaderFields.CODE + ": " + e.getMessage();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
            return;
        }
        request.setAttribute(ATTRIBUTE_NICA_CODES, nicaCodes);
        if (errorOccuredAndSent(request, response)) {
            return;
        }
        request.setAttribute(ATTRIBUTE_NICA_CODES_L2, nicaCodes);
        if (errorOccuredAndSent(request, response)) {
            return;
        }
        request.setAttribute(ATTRIBUTE_NICA_CODES_L3, nicaCodes);
        if (errorOccuredAndSent(request, response)) {
            return;
        }

        chain.doFilter(request, response); // ------------------------- doFilter
    }


}
