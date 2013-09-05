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


package com.googlecode.jinahya.servlet.http;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class HeadersRequestWrapper extends HttpServletRequestWrapper {


    private static final Logger LOGGER =
        Logger.getLogger(HeadersRequestWrapper.class.getName());


    static {
        LOGGER.setLevel(Level.ALL);
    }


    public static HttpServletRequest newInstanceForPrecedingHeaders(
        final HttpServletRequest request,
        final Map<String, List<String>> headers) {

        final HttpServletRequest instance =
            new HeadersRequestWrapper(request, headers, null);

        return instance;
    }


    public static HttpServletRequest newInstanceForPrecedingHeaders(
        final HttpServletRequest request,
        final String name, List<String> values) {

        final Map<String, List<String>> headers = new HashMap<>();
        headers.put(name, values);

        return newInstanceForPrecedingHeaders(request, headers);
    }


    public static HttpServletRequest newInstanceForPrecedingHeaders(
        final HttpServletRequest request,
        final String name, final String value) {

        return newInstanceForPrecedingHeaders(
            request, name, Arrays.asList(value));
    }


    /**
     *
     * @param request
     * @param precedingHeaders
     * @param succeedingHeaders
     */
    public HeadersRequestWrapper(
        final HttpServletRequest request,
        final Map<String, List<String>> precedingHeaders,
        final Map<String, List<String>> succeedingHeaders) {

        super(request);

        headers = new HashMap<>();

        if (precedingHeaders != null) {
            for (Entry<String, List<String>> e : precedingHeaders.entrySet()) {
                final String name = e.getKey();
                List<String> values = headers.get(name);
                if (values == null) {
                    values = new ArrayList<>();
                    headers.put(name, values);
                }
                values.addAll(e.getValue());
            }
        }

        for (final Enumeration<String> names = request.getHeaderNames();
             names.hasMoreElements();) {
            final String fieldName = names.nextElement();
            List<String> fieldValues = headers.get(fieldName);
            if (fieldValues == null) {
                fieldValues = new ArrayList<>();
                headers.put(fieldName, fieldValues);
            }
            fieldValues.addAll(Collections.list(request.getHeaders(fieldName)));
        }


        if (succeedingHeaders != null) {
            for (Entry<String, List<String>> e : succeedingHeaders.entrySet()) {
                final String name = e.getKey();
                List<String> values = headers.get(name);
                if (values == null) {
                    values = new ArrayList<>();
                    headers.put(name, values);
                }
                values.addAll(e.getValue());
            }
        }
    }


    @Override
    public String getHeader(final String name) {

        final List<String> values = headers.get(name);
        if (values != null && !values.isEmpty()) {
            return values.get(0);
        }

        return null;
    }


    @Override
    public Enumeration<String> getHeaders(final String name) {

        List<String> values = headers.get(name);
        if (values == null) {
            values = Collections.emptyList();
        }

        return Collections.enumeration(values);
    }


    @Override
    public Enumeration<String> getHeaderNames() {

        LOGGER.info("getHeaderNames()");

        return Collections.enumeration(headers.keySet());
    }


    private final Map<String, List<String>> headers;


}

