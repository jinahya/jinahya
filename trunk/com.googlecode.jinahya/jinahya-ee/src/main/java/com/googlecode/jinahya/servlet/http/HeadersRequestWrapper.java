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
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class HeadersRequestWrapper extends HttpServletRequestWrapper {


    public HeadersRequestWrapper(
        final HttpServletRequest request,
        final Map<String, List<String>> headers) {

        super(request);

        if (headers == null) {
            throw new NullPointerException("headers");
        }

        this.headers = headers;
    }


    @Override
    public String getHeader(final String name) {

        final List<String> wrappedValues = headers.get(name);
        if (wrappedValues != null && !wrappedValues.isEmpty()) {
            return wrappedValues.get(0);
        }

        return super.getHeader(name);
    }


    @Override
    public Enumeration<String> getHeaders(final String name) {

        final List<String> values = new ArrayList<>();

        final List<String> wrappedValues = headers.get(name);
        if (wrappedValues != null) {
            values.addAll(wrappedValues);
        }

        for (final Enumeration<String> requestedValues = super.getHeaders(name);
             requestedValues.hasMoreElements();) {
            values.add(requestedValues.nextElement());
        }

        return Collections.enumeration(values);
    }


    @Override
    public Enumeration<String> getHeaderNames() {

        final Set<String> names = new HashSet<>();

        final Set<String> wrappedNames = headers.keySet();
        names.addAll(wrappedNames);

        final Enumeration<String> requestedNames = super.getHeaderNames();
        if (requestedNames != null) {
            while (requestedNames.hasMoreElements()) {
                final String requestedName = requestedNames.nextElement();
                names.add(requestedName);
            }
        }

        return Collections.enumeration(names);
    }


    private final Map<String, List<String>> headers;


}
