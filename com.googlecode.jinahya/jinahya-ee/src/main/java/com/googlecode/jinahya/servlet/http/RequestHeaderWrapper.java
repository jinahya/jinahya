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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class RequestHeaderWrapper extends HttpServletRequestWrapper {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(RequestHeaderWrapper.class);


    /**
     * Creates a new instance with preceding headers.
     *
     * @param request the actual request
     * @param headers the preceding headers.
     *
     * @return a new instance
     */
    public static HttpServletRequest newPrecedingInstance(
        final HttpServletRequest request,
        final Map<String, List<String>> headers) {

        if (headers == null) {
            throw new NullPointerException("headers");
        }

        final HttpServletRequest instance =
            new RequestHeaderWrapper(request, headers, null);

        return instance;
    }


    /**
     * Creates a new instance with a preceding header.
     *
     * @param request the actual request
     * @param name the preceding header name
     * @param values the preceding header values.
     *
     * @return a new instance.
     */
    public static HttpServletRequest newPrecedingInstance(
        final HttpServletRequest request,
        final String name, List<String> values) {

        if (name == null) {
            throw new NullPointerException("name");
        }

        if (values == null) {
            throw new NullPointerException("values");
        }

        final Map<String, List<String>> headers = new HashMap<>(1);
        headers.put(name, values);

        return newPrecedingInstance(request, headers);
    }


    /**
     * Creates a new instance with a preceding header.
     *
     * @param request the actual request
     * @param name the preceding header name
     * @param value the preceding header value.
     *
     * @return a new instance
     */
    public static HttpServletRequest newPrecedingInstance(
        final HttpServletRequest request,
        final String name, final String value) {

        if (name == null) {
            throw new NullPointerException("name");
        }

        if (value == null) {
            throw new NullPointerException("value");
        }

        return newPrecedingInstance(
            request, name, Arrays.asList(value));
    }


    /**
     * Creates a new instance with succeeding headers.
     *
     * @param request the actual request
     * @param headers the succeeding headers.
     *
     * @return a new instance
     */
    public static HttpServletRequest newSuccedingInstance(
        final HttpServletRequest request,
        final Map<String, List<String>> headers) {

        if (headers == null) {
            throw new NullPointerException("headers");
        }

        final HttpServletRequest instance =
            new RequestHeaderWrapper(request, headers, null);

        return instance;
    }


    /**
     * Creates a new instance with a succeeding header.
     *
     * @param request the actual request
     * @param name the succeeding header name
     * @param values the succeeding header values.
     *
     * @return a new instance.
     */
    public static HttpServletRequest newSucceedingInstance(
        final HttpServletRequest request,
        final String name, List<String> values) {

        if (name == null) {
            throw new NullPointerException("name");
        }

        if (values == null) {
            throw new NullPointerException("values");
        }

        final Map<String, List<String>> headers = new HashMap<>(1);
        headers.put(name, values);

        return newPrecedingInstance(request, headers);
    }


    /**
     * Creates a new instance with a succeeding header.
     *
     * @param request the actual request
     * @param name the succeeding header name
     * @param value the succeeding header value.
     *
     * @return a new instance
     */
    public static HttpServletRequest newSucceedingInstance(
        final HttpServletRequest request,
        final String name, final String value) {

        if (name == null) {
            throw new NullPointerException("name");
        }

        if (value == null) {
            throw new NullPointerException("value");
        }

        return newPrecedingInstance(
            request, name, Arrays.asList(value));
    }


    /**
     * Creates a new instance with {@code request} and additional headers.
     *
     * @param request the request
     * @param precedingHeaders additional headers precede requested headers;
     * {@code null} allowed.
     * @param succeedingHeaders additional header succeed requested headers;
     * {@code null} allowed.
     */
    public RequestHeaderWrapper(
        final HttpServletRequest request,
        final Map<String, List<String>> precedingHeaders,
        final Map<String, List<String>> succeedingHeaders) {

        super(request);

        headers = new HashMap<>();

        if (precedingHeaders != null) {
            for (final String name : precedingHeaders.keySet()) {
                List<String> values = headers.get(name);
                if (values == null) {
                    values = new ArrayList<>();
                    headers.put(name, values);
                }
                values.addAll(precedingHeaders.get(name));
            }
        }

        for (final Enumeration<String> names = request.getHeaderNames();
             names.hasMoreElements();) {
            final String name = names.nextElement();
            List<String> value = headers.get(name);
            if (value == null) {
                value = new ArrayList<>();
                headers.put(name, value);
            }
            value.addAll(Collections.list(request.getHeaders(name)));
        }

        if (succeedingHeaders != null) {
            for (final String name : succeedingHeaders.keySet()) {
                List<String> values = headers.get(name);
                if (values == null) {
                    values = new ArrayList<>();
                    headers.put(name, values);
                }
                values.addAll(succeedingHeaders.get(name));
            }
        }
    }


    /**
     * {@inheritDoc}
     *
     * @param name {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String getHeader(final String name) {

        LOGGER.debug("getHeader({})", name);

        final List<String> values = headers.get(name);
        if (values != null && !values.isEmpty()) {
            return values.get(0);
        }

        return null;
    }


    /**
     * {@inheritDoc}
     *
     * @param name {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Enumeration<String> getHeaders(final String name) {

        LOGGER.debug("getHeaders({})", name);

        List<String> values = headers.get(name);
        if (values == null) {
            return Collections.emptyEnumeration();
        }

        return Collections.enumeration(values);
    }


    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Enumeration<String> getHeaderNames() {

        LOGGER.debug("getHeaderNames()");

        return Collections.enumeration(headers.keySet());
    }


    /**
     * aggregated headers.
     */
    private final Map<String, List<String>> headers;


}
