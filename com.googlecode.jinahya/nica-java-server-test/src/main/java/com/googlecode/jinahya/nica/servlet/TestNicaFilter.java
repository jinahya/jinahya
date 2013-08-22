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


package com.googlecode.jinahya.nica.servlet;


import static com.googlecode.jinahya.nica.servlet.NicaFilter.ATTRIBUTE_NICA_NAMES;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@WebFilter(urlPatterns = {"/servlet/*", "/resource/*"})
public class TestNicaFilter extends NicaFilter {


    private static final Logger LOGGER =
        Logger.getLogger(TestNicaFilter.class.getName());


    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain)
        throws IOException, ServletException {

        LOGGER.log(Level.INFO, "doFilter({0}, {1}, {2})",
                   new Object[]{request, response, chain});

        super.doFilter(request, response, chain);
    }


    @Override
    public void doFilter(final HttpServletRequest request,
                         final HttpServletResponse response,
                         final FilterChain chain)
        throws IOException, ServletException {

        System.out.println("----------------------------------------"
                           + "----------------------------------------");
        System.out.println("doFilter(" + request + ", " + response + ", "
                           + chain + ")");

        final String requestUri = request.getRequestURI();
        System.out.println("requestUri: " + requestUri);

        final String pathInfo = request.getPathInfo();
        System.out.println("pathInfo: " + pathInfo);

        final String servletPath = request.getServletPath();
        System.out.println("servletPath: " + servletPath);

        super.doFilter(request, response, chain);

        @SuppressWarnings("unchecked")
        final Map<String, String> nicaNames =
            (Map<String, String>) request.getAttribute(ATTRIBUTE_NICA_NAMES);
        if (nicaNames != null) {
            for (final Entry<String, String> entry : nicaNames.entrySet()) {
                System.out.println("nicaName: " + entry.getKey() + ": "
                                   + entry.getValue());
            }
        }

        @SuppressWarnings("unchecked")
        final Map<String, String> nicaCodes =
            (Map<String, String>) request.getAttribute(ATTRIBUTE_NICA_CODES);
        if (nicaCodes != null) {
            for (final Entry<String, String> entry : nicaCodes.entrySet()) {
                System.out.println("nicaCode: " + entry.getKey() + ": "
                                   + entry.getValue());
            }
        }
    }


}
