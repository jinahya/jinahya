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
     * The attribute name for decoded value of {@link Header#CODE}. The value is
     * an instance of unmodifiable
     * <code>Map&lt;String, String&gt;</code>.
     */
    protected static final String ATTRIBUTE_NICA_CODES =
        NicaFilter.class.getName() + "#codes";


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
        if (names.isEmpty()) {
            // ok
        }
        final byte[] key_;
        try {
            key_ = locateKey(Collections.unmodifiableMap(names));
        } catch (Exception e) {
            System.err.println("error while locating key: " + e.getMessage());
            e.printStackTrace(System.err);
            hesponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        if (key_ == null) {
            hesponse.sendError(HttpServletResponse.SC_BAD_REQUEST,
                               "no key found");
            return;
        }
        if (key_.length != Aes.KEY_SIZE_IN_BYTES) {
            System.err.println("wrong key size: " + key_.length);
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
        final byte[] init;
        try {
            init = Hex.decode(nicaInit);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "wrong header: " + Header.INIT.fieldName() + ": "
                + e.getMessage());
            return;
        }
        if (init.length != Aes.KEY_SIZE_IN_BYTES) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "wrong header: " + Header.INIT.fieldName() + ": size");
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
            base = new AesJCE(key_).decrypt(init, Hex.decode(nicaCode));
        } catch (Exception e) {
            e.printStackTrace(System.err);
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "wrong header: " + Header.CODE.fieldName() + ": "
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
                "wrong header: " + Header.CODE.fieldName() + ": "
                + e.getMessage());
            return;
        }
        if (codes.isEmpty()) {
            // ok
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
        final byte[] aut_;
        try {
            aut_ = new HacJCE(key_).authenticate(base);
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

        hequest.setAttribute(ATTRIBUTE_NICA_NAMES,
                             Collections.unmodifiableMap(names));
        hequest.setAttribute(ATTRIBUTE_NICA_CODES,
                             Collections.unmodifiableMap(codes));

        chain.doFilter(request, response);
    }


    /**
     * Finds encryption key for given
     * <code>names</code>.
     *
     * @param names an unmodifiable map of names for locating key
     *
     * @return located key or <code>null</code> if not found
     */
    protected abstract byte[] locateKey(Map<String, String> names);


}

