/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


package com.googlecode.jinahya.nica.ws.rs.container;


import com.googlecode.jinahya.nica.HeaderFields;
import com.googlecode.jinahya.nica.servlet.AbstractNicaKeyLocator;
import com.googlecode.jinahya.nica.servlet.NicaFilter;
import com.googlecode.jinahya.nica.util.Aes;
import com.googlecode.jinahya.nica.util.AesJCE;
import com.googlecode.jinahya.nica.util.HacJCE;
import com.googlecode.jinahya.nica.util.Hex;
import com.googlecode.jinahya.nica.util.Par;
import com.googlecode.jinahya.ws.core.response.BadRequest400;
import com.googlecode.jinahya.ws.core.response.InternalServerError500;
import com.googlecode.jinahya.ws.core.response.OrphanStatusType;
import com.googlecode.jinahya.ws.core.response.Unauthorized401;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public abstract class NicaContainerRequestFilter
    implements ContainerRequestFilter {


    private static boolean errorOccuredAndAborted(
        final ContainerRequestContext context) {

        if (context == null) {
            throw new IllegalArgumentException("context");
        }

        final Integer statusCode = (Integer) context.getProperty(
            NicaFilter.ATTRIBUTE_RESPONSE_ERROR_STATUS_CODE);
        if (statusCode == null) {
            return false;
        }

        String reasonPhrase = (String) context.getProperty(
            NicaFilter.ATTRIBUTE_RESPONSE_ERROR_REASON_PHRASE);

        if (reasonPhrase == null) {
            reasonPhrase = "";
        }

        context.abortWith(
            OrphanStatusType.newInstance(statusCode, reasonPhrase).built());

        return true;
    }


    @Override
    public void filter(final ContainerRequestContext context)
        throws IOException {

        final MultivaluedMap<String, String> headers = context.getHeaders();

        // ----------------------------------------------------------- Nica-Name
        final String nicaName = headers.getFirst(HeaderFields.NAME);
        if (nicaName == null) {
            final String message = "missing header: " + HeaderFields.NAME;
            throw new BadRequest400(message).throwable();
        }
        final Map<String, String> nicaNames;
        try {
            @SuppressWarnings("unchecked")
            final Map<String, String> nicaNames_ = Par.decode(nicaName);
            nicaNames = Collections.unmodifiableMap(nicaNames_);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            final String message = "missing header: " + HeaderFields.NAME;
            throw new WebApplicationException(Response.status(
                new BadRequest400(message)).build());
        }
        context.setProperty(NicaFilter.ATTRIBUTE_NICA_NAMES, nicaNames);
        if (errorOccuredAndAborted(context)) {
            return;
        }
        // Re-chekcing the key. Do not trust developers.
        byte[] key = (byte[]) context.getProperty(
            AbstractNicaKeyLocator.ATTRIBUTE_NICA_LOCATED_KEY);
        if (key == null) {
            context.abortWith(
                new InternalServerError500("no key located").built());
            return;
        }
        if (key.length != Aes.KEY_SIZE_IN_BYTES) {
            context.abortWith(
                new InternalServerError500("wrong key located").built());
            return;
        }
        key = Arrays.copyOf(key, key.length);

        // ----------------------------------------------------------- Nica-Init
        final String nicaInit = headers.getFirst(HeaderFields.INIT);
        if (nicaInit == null) {
            final String message = "missing header: " + HeaderFields.INIT;
            context.abortWith(new BadRequest400(message).built());
            return;
        }
        final byte[] iv;
        try {
            iv = Hex.decode(nicaInit);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            final String message = "wrong header: " + HeaderFields.INIT;
            context.abortWith(new BadRequest400(message).built());
            return;
        }
        if (iv.length != Aes.BLOCK_SIZE_IN_BYTES) {
            final String message = "wrong header: " + HeaderFields.INIT;
            context.abortWith(new BadRequest400(message).built());
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
            context.abortWith(new BadRequest400(message).built());
            return;
        }

        // ----------------------------------------------------------- Nica-Code
        final String nicaCode = headers.getFirst(HeaderFields.CODE);
        if (nicaCode == null) {
            final String message = "missing header: " + HeaderFields.CODE;
            context.abortWith(new BadRequest400(message).built());
            return;
        }
        final byte[] base;
        try {
            base = new AesJCE(key).decrypt(iv, Hex.decode(nicaCode));
        } catch (Exception e) {
            final String message = "wrong header: " + HeaderFields.CODE;
            context.abortWith(new BadRequest400(message).built());
            return;
        }

        // =====================================================================
        // DO NOT PROCEED WITH CODES YET!
        // WE MUST VERIFY THEM FIRST!
        // =====================================================================

        // ----------------------------------------------------------- Nica-Auth
        final String nicaAuth = headers.getFirst(HeaderFields.AUTH);
        if (nicaAuth == null) {
            final String message = "missing header: " + HeaderFields.AUTH;
            throw new BadRequest400(message).throwable();
        }
        final byte[] auth;
        try {
            auth = Hex.decode(nicaAuth);
        } catch (final Exception e) {
            final String message =
                "wrong header: " + HeaderFields.AUTH + ": " + e.getMessage();
            throw new BadRequest400(message).throwable();
        }
        if (!Arrays.equals(new HacJCE(key).authenticate(base), auth)) {
            final String message = "authentication failed";
            context.abortWith(new BadRequest400(message).built());
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
        } catch (final Exception e) {
            final String message =
                "wrong header: " + HeaderFields.CODE + ": " + e.getMessage();
            context.abortWith(new BadRequest400(message).built());
            return;
        }
        context.setProperty(NicaFilter.ATTRIBUTE_NICA_CODES,
                            Collections.unmodifiableMap(nicaCodes));
        if (errorOccuredAndAborted(context)) {
            return;
        }
        context.setProperty(NicaFilter.ATTRIBUTE_NICA_CODES_L2,
                            Collections.unmodifiableMap(nicaCodes));
        if (errorOccuredAndAborted(context)) {
            return;
        }
        context.setProperty(NicaFilter.ATTRIBUTE_NICA_CODES_L3,
                            Collections.unmodifiableMap(nicaCodes));
        if (errorOccuredAndAborted(context)) {
            return;
        }
    }


}
