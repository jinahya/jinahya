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


import com.googlecode.jinahya.nica.NicaCode;
import com.googlecode.jinahya.nica.NicaHeader;
import com.googlecode.jinahya.nica.util.Aes;
import com.googlecode.jinahya.nica.util.AesJCE;
import com.googlecode.jinahya.nica.util.HacJCE;
import com.googlecode.jinahya.nica.util.Hex;
import com.googlecode.jinahya.nica.util.Par;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
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
    protected static final String ATTRIBUTE_NICA_NAMES_KEY =
        NicaFilter.class.getName() + "#names#key";


    /**
     * The attribute name for decoded value of {@link Header#CODE}. The value is
     * an instance of unmodifiable
     * <code>Map&lt;String, String&gt;</code>.
     */
    protected static final String ATTRIBUTE_NICA_CODES =
        NicaFilter.class.getName() + "#codes";


    /**
     * The attribute name for validating {@link Header#CODE}. The value must be
     * a Boolean.
     */
    protected static final String ATTRIBUTE_NICA_CODES_VALID =
        NicaFilter.class.getName() + "#codes#valid";


    /**
     * Returns an unmodifiable map of {@link Header#NAME}.
     *
     * @param request servlet request
     *
     * @return an unmodifiable map of {@link Header#NAME);
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
     * Returns an unmodifiable map of {@link Header#CODE}.
     *
     * @param request servlet request
     *
     * @return an unmodifiable map of {@link Header#CODE);
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
        final String nicaName = hequest.getHeader(NicaHeader.NAME.fieldName());
        if (nicaName == null) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "missing header: " + NicaHeader.NAME.fieldName());
            return;
        }
        final Map<String, String> names;
        try {
            names = Par.decode(nicaName);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "wrong header: " + NicaHeader.NAME.fieldName() + ": "
                + e.getMessage());
            return;
        }
        if (names.isEmpty()) {
            // ok
        }
        hequest.setAttribute(ATTRIBUTE_NICA_NAMES,
                             Collections.unmodifiableMap(names));

        try {
            locateKey(request, response, Collections.unmodifiableMap(names));
        } catch (IOException ioe) {
            throw ioe;
        } catch (ServletException se) {
            throw se;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            hesponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        if (response.isCommitted()) {
            return;
        }
        final Object nicaKey = request.getAttribute(ATTRIBUTE_NICA_NAMES_KEY);
        if (nicaKey == null) {
            hesponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               "no key located");
            return;
        }
        if (!(nicaKey instanceof byte[])) {
            hesponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               "wrong key value");
            return;
        }
        final byte[] key = ((byte[]) nicaKey).clone();
        if (key.length != Aes.KEY_SIZE_IN_BYTES) {
            hesponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               "wrong key value");
            return;
        }

        // ----------------------------------------------------------- Nica-Init
        final String nicaInit = hequest.getHeader(NicaHeader.INIT.fieldName());
        if (nicaInit == null) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "missing header: " + NicaHeader.INIT.fieldName());
            return;
        }
        final byte[] iv;
        try {
            iv = Hex.decode(nicaInit);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "wrong header: " + NicaHeader.INIT.fieldName() + ": "
                + e.getMessage());
            return;
        }
        if (iv.length != Aes.BLOCK_SIZE_IN_BYTES) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "wrong header: " + NicaHeader.INIT.fieldName() + ": size");
            return;
        }

        // ----------------------------------------------------------- Nica-Code
        final String nicaCode = hequest.getHeader(NicaHeader.CODE.fieldName());
        if (nicaCode == null) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "missing header: " + NicaHeader.CODE.fieldName());
            return;
        }
        final byte[] base;
        try {
            base = new AesJCE(key).decrypt(iv, Hex.decode(nicaCode));
        } catch (Exception e) {
            e.printStackTrace(System.err);
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "wrong header: " + NicaHeader.CODE.fieldName() + ": "
                + e.getMessage());
            return;
        }
        final Map<String, String> codes;
        try {
            codes = Par.decode(new String(base, "US-ASCII"));
        } catch (Exception e) {
            e.printStackTrace(System.err);
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "wrong header: " + NicaHeader.CODE.fieldName() + ": "
                + e.getMessage());
            return;
        }
        // ----------------------------------------------- Nica-Code/PLATFORM_ID
        final String platformId = codes.get(NicaCode.PLATFORM_ID.name());
        if (platformId == null) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "missing code: " + NicaCode.PLATFORM_ID.name());
            return;
        }
        if (platformId.trim().isEmpty()) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "empty code: " + NicaCode.PLATFORM_ID.name());
            return;
        }

        // ------------------------------------------------- Nica-Code/DEVICE_ID
        final String deviceId = codes.get(NicaCode.DEVICE_ID.name());
        if (deviceId != null && deviceId.trim().isEmpty()) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "empty code: " + NicaCode.DEVICE_ID.name());
            return;
        }
        // ------------------------------------------------- Nica-Code/SYSTEM_ID
        final String systemId = codes.get(NicaCode.SYSTEM_ID.name());
        if (systemId != null && systemId.trim().isEmpty()) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "empty code: " + NicaCode.SYSTEM_ID.name());
            return;
        }
        // --------------------------------------- Nica-Code/DEVICE_ID+SYSTEM_ID
        if (deviceId == null && systemId == null) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "no " + NicaCode.DEVICE_ID.name() + " nor "
                + NicaCode.SYSTEM_ID.name());
            return;
        }
        try {
            checkCodes(request, response, Collections.unmodifiableMap(codes));
        } catch (IOException ioe) {
            throw ioe;
        } catch (ServletException se) {
            throw se;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            hesponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        if (response.isCommitted()) {
            return;
        }


        // ----------------------------------------------------------- Nica-Auth
        final String nicaAuth = hequest.getHeader(NicaHeader.AUTH.fieldName());
        if (nicaAuth == null) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "missing header: " + NicaHeader.AUTH.fieldName());
            return;
        }
        final byte[] auth;
        try {
            auth = Hex.decode(nicaAuth);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "wrong header: " + NicaHeader.AUTH.fieldName() + ": "
                + e.getMessage());
            return;
        }
        final byte[] aut_;
        try {
            aut_ = new HacJCE(key).authenticate(base);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "authentication failed: " + e.getMessage());
            return;
        }
        if (!Arrays.equals(auth, aut_)) {
            hesponse.sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "authentication failed: not equal");
            return;
        }


        hequest.setAttribute(ATTRIBUTE_NICA_CODES,
                             Collections.unmodifiableMap(codes));

        chain.doFilter(request, response);
    }


    /**
     * Finds encryption key for given
     * <code>names</code>.
     *
     * @param request
     * @param response
     * @param names an unmodifiable map of names for locating key.
     *
     * @throws IOException
     * @throws ServletException
     */
    protected abstract void locateKey(ServletRequest request,
                                      ServletResponse response,
                                      Map<String, String> names)
        throws IOException, ServletException;


    /**
     *
     * @param request
     * @param response
     * @param codes an unmodifiable map of codes.
     *
     * @throws IOException
     * @throws ServletException
     */
    protected abstract void checkCodes(ServletRequest request,
                                       ServletResponse response,
                                       Map<String, String> codes)
        throws IOException, ServletException;


}

