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
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A {@link javax.servlet.Filter} implementation which dispatches suffixed path
 * to a typed path.
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class SuffixToTypeDispatcher extends HttpFilter {


    /**
     * A regular expression for file suffix.
     */
    public static final String SUFFIX_EXPRESSION =
        "file\\.suffix\\.([^\\.]+)";


    /**
     * A precompiled pattern of {@link #SUFFIX_EXPRESSION}.
     */
    protected static final Pattern SUFFIX_PATTERN =
        Pattern.compile(SUFFIX_EXPRESSION);


    /**
     * A regular expression for media type.
     */
    public static final String TYPE_EXPRESSION = "media/type/(.+)";


    /**
     * A precompiled pattern of {@link #TYPE_EXPRESSION}.
     */
    protected static final Pattern TYPE_PATTERN =
        Pattern.compile(TYPE_EXPRESSION);


    private static final Pattern NAME_PATTERN =
        Pattern.compile("([^\\.]+)\\.([^\\.]+)");


    /**
     * logger.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(SuffixToTypeDispatcher.class);


    @Override
    public void init(final FilterConfig config) throws ServletException {

        LOGGER.debug("init({}", config);

        super.init(config);

        contextPath = getServletContext().getContextPath();
        contextPathLength = contextPath.length();

        for (final Enumeration<String> e = config.getInitParameterNames();
             e.hasMoreElements();) {
            final String name = e.nextElement();
            final Matcher suffixMatcher = SUFFIX_PATTERN.matcher(name);
            if (!suffixMatcher.matches()) {
                continue;
            }
            final String value = config.getInitParameter(name);
            final Matcher typeMatcher = TYPE_PATTERN.matcher(value);
            if (!typeMatcher.matches()) {
                continue;
            }
            final String suffix = suffixMatcher.group(1);
            final String type = typeMatcher.group(1);
            if (map == null) {
                map = new HashMap<>();
            }
            LOGGER.debug("{} -> {}", suffix, type);
            map.put(suffix, type);
        }
    }


    @Override
    protected void doFilter(final HttpServletRequest request,
                            final HttpServletResponse response,
                            final FilterChain chain)
        throws IOException, ServletException {

        LOGGER.debug("doFilter({}, {}, {})", request, response, chain);

        if (map == null) {
            LOGGER.debug("null map");
            chain.doFilter(request, response);
            return;
        }

        final String requestUri = request.getRequestURI();
        if (requestUri == null) {
            LOGGER.debug("null requestUri");
            chain.doFilter(request, response);
            return;
        }

        final String resourcePath = requestUri.substring(contextPathLength);
        LOGGER.debug("resourcePath: {}", resourcePath);

        final int lastSlashIndex = resourcePath.lastIndexOf('/');
        if (lastSlashIndex == -1) {
            LOGGER.debug("no last slash");
            chain.doFilter(request, response);
            return;
        }

        final String fileName = resourcePath.substring(lastSlashIndex + 1);
        final Matcher fileNameMatcher = NAME_PATTERN.matcher(fileName);
        if (!fileNameMatcher.matches()) {
            LOGGER.debug("fileName doesn't match");
            chain.doFilter(request, response);
            return;
        }

        final String fileSuffix = fileNameMatcher.group(2);
        LOGGER.debug("fileSuffix: {}", fileSuffix);
        final String mediaType = map.get(fileSuffix);
        if (mediaType == null) {
            LOGGER.debug("no mapped media type");
            chain.doFilter(request, response);
            return;
        }

        final String path = resourcePath.substring(
            0, resourcePath.length() - fileSuffix.length() - 1);
        LOGGER.debug("path: {}", path);

        final ServletRequest wrapper =
            HeadersRequestWrapper.newPrecedingInstance(
            request, "Accept", mediaType);
        final RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(wrapper, response);
        LOGGER.debug("fowarded");

        return;
    }


    private transient String contextPath;


    private transient int contextPathLength;


    private Map<String, String> map = null;


}
