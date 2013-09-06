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


import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class SuffixedToTypedFilter extends HttpFilter {


    public static final String FILE_SUFFIX_EXPRESSION = "file\\.suffix\\.(.+)";


    public static final Pattern FILE_SUFFIX_PATTERN =
            Pattern.compile(FILE_SUFFIX_EXPRESSION);


    public static final String MEDIA_TYPE_EXPRESSION = "media/type/(.+)";


    public static final Pattern MEDIA_TYPE_PATTERN =
            Pattern.compile(MEDIA_TYPE_EXPRESSION);


    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

        super.init(filterConfig);

        for (final Enumeration<String> initParametrNames =
                filterConfig.getInitParameterNames();
             initParametrNames.hasMoreElements();) {
            final String name = initParametrNames.nextElement();
            final Matcher nameMatcher = FILE_SUFFIX_PATTERN.matcher(name);
            if (!nameMatcher.matches()) {
                continue;
            }
            final String value = filterConfig.getInitParameter(name);
            final Matcher valueMatcher = MEDIA_TYPE_PATTERN.matcher(value);
            if (!valueMatcher.matches()) {
                continue;
            }
            final String fileSuffix = nameMatcher.group(1);
            final String mediaType = valueMatcher.group(1);
            if (map == null) {
                map = new HashMap<>();
            }
            map.put(fileSuffix, mediaType);
        }
    }


    @Override
    protected void doFilter(final HttpServletRequest request,
                            final HttpServletResponse response,
                            final FilterChain chain)
            throws IOException, ServletException {

        final String pathInfo = request.getPathInfo();
        final String pathTranslated = request.getPathTranslated();
        final String servletPath = request.getServletPath();
        final String requestUri = request.getRequestURI();
        final StringBuffer requestUrl = request.getRequestURL();

        System.out.println("-------------------------------------------------");
        System.out.println("pathInfo: " + pathInfo);
        System.out.println("pathTranslated: " + pathTranslated);
        System.out.println("servletPath: " + servletPath);
        System.out.println("requestUri: " + requestUri);
        System.out.println("requestUrl: " + requestUrl);
        System.out.println("-------------------------------------------------");

        if (map == null) {
            System.out.println("null suffixes");
            chain.doFilter(request, response);
            return;
        }

        if (pathInfo == null) {
            System.out.println("null pathInfo");
            chain.doFilter(request, response);
            return;
        }

        final int lastSlashIndex = pathInfo.lastIndexOf('/');
        if (lastSlashIndex == -1) {
            chain.doFilter(request, response);
            return;
        }

        final String fileName = pathInfo.substring(lastSlashIndex);

        final int lastPeriodIndex = fileName.lastIndexOf('.');
        if (lastPeriodIndex == -1 || lastPeriodIndex == 0) {
            chain.doFilter(request, response);
            return;
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
            if (!fileName.endsWith(entry.getKey())) {
                continue;
            }
            System.out.println("pathInfo endsWith " + entry.getKey());
            final String accept = request.getHeader("Accept");
            if (accept != null) {
                System.out.println("Accept: " + accept);
                continue;
            }
            final HttpServletRequest wequest =
                    HeadersRequestWrapper.newInstanceForPrecedingHeaders(
                    request, "Accept", entry.getValue());
            final String path = pathInfo.substring(
                    0, pathInfo.length() - entry.getKey().length() - 1);
            System.out.println("dispatcher.path: " + path);
            final RequestDispatcher dispatcher =
                    wequest.getRequestDispatcher(path);
            dispatcher.forward(wequest, response);
            System.out.println("fowareded");
            return;
        }

        System.out.println("normal chain");
        chain.doFilter(request, response);
        return;
    }


    private Map<String, String> map;


}

