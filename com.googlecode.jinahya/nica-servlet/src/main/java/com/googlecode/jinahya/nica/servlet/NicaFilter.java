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
import com.googlecode.jinahya.nica.util.AES;
import com.googlecode.jinahya.nica.util.HEX;
import com.googlecode.jinahya.nica.util.KVP;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//import java.util.logging.Filter;
/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class NicaFilter implements Filter {


    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain)
        throws IOException, ServletException {

        final HttpServletRequest hequest = (HttpServletRequest) request;
        final HttpServletResponse hesponse = (HttpServletResponse) response;

        // ----------------------------------------------------------- Nica-Name
        final String name = hequest.getHeader(Header.NAME.fieldName());
        if (name == null) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "missing required header: " + Header.NAME.fieldName());
            return;
        }
        final Map<String, String> names;
        try {
            names = KVP.decode(name);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "failed to decode header value: " + Header.NAME.fieldName()
                + ": " + name);
            return;
        }

        // ----------------------------------------------------------- Nica-Init
        final String init = hequest.getHeader(Header.INIT.fieldName());
        if (init == null) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "missing required header: " + Header.INIT.fieldName());
            return;
        }
        final byte[] iv;
        try {
            iv = HEX.decode(init);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "failed to decode header value: " + Header.INIT.fieldName()
                + ": " + init);
            return;
        }
        if (iv.length != AES.KEY_SIZE_IN_BYTES) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "wrong header value: " + Header.INIT.fieldName()
                + ": " + init);
            return;
        }

        // ----------------------------------------------------------- Nica-Code
        final String code = hequest.getHeader(Header.CODE.fieldName());
        if (code == null) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "missing required header: " + Header.CODE.fieldName());
            return;
        }
        final Map<String, String> codes;
        try {
            codes = KVP.decode(code);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "failed to decode header value: " + Header.CODE.fieldName()
                + ": " + code);
            return;
        }

        // ----------------------------------------------------------- Nica-Auth
        final String auth = hequest.getHeader(Header.AUTH.fieldName());
        if (auth == null) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "missing required header: " + Header.AUTH.fieldName());
            return;
        }
        final byte[] au;
        try {
            au = HEX.decode(auth);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "failed to decode header value: " + Header.AUTH.fieldName()
                + ": " + auth);
            return;
        }


        final byte[] key = getKey(Collections.unmodifiableMap(names));
        if (key == null) {
            hesponse.sendError(
                HttpServletResponse.SC_BAD_REQUEST, "key not found");
            return;
        }
        if (key.length != AES.KEY_SIZE_IN_BYTES) {
            hesponse.sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "bad implementation: key.length(" + key.length + ") != "
                + AES.KEY_SIZE_IN_BYTES);
            return;
        }


        chain.doFilter(request, response);
    }


    /**
     * Finds encryption key for given
     * <code>names</code>.
     *
     * @param names an unmodifiable map of names for locating key
     * @return located key or <code>null</code> if not found
     */
    protected abstract byte[] getKey(Map<String, String> names);


}

