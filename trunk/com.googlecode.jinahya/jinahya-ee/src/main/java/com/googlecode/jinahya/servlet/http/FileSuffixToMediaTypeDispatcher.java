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
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public abstract class FileSuffixToMediaTypeDispatcher extends HttpFilter {


    public static final String FILE_SUFFIX_EXPRESSION =
        "file\\.suffix\\.([^\\.].+)";


    public static final Pattern FILE_SUFFIX_PATTERN =
        Pattern.compile(FILE_SUFFIX_EXPRESSION);


    public static final String MEDIA_TYPE_EXPRESSION = "media/type/(.+)";


    public static final Pattern MEDIA_TYPE_PATTERN =
        Pattern.compile(MEDIA_TYPE_EXPRESSION);


    private static final Pattern FILE_NAME_PATTERN =
        Pattern.compile("(.+)\\.([^\\.].+)");


//    private static final Logger LOGGER =
//        Logger.getLogger(FileSuffixToMediaTypeDispatcher.class.getName());
    private static final Logger LOGGER =
        LoggerFactory.getLogger(FileSuffixToMediaTypeDispatcher.class);


    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

        super.init(filterConfig);

        contextPath = getServletContext().getContextPath();
        contextPathLength = contextPath.length();

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
            LOGGER.debug("{} -> {}", fileSuffix, mediaType);
            map.put(fileSuffix, mediaType);
        }
    }


    @Override
    protected void doFilter(final HttpServletRequest request,
                            final HttpServletResponse response,
                            final FilterChain chain)
        throws IOException, ServletException {

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
        final Matcher fileNameMatcher = FILE_NAME_PATTERN.matcher(fileName);
        if (!fileNameMatcher.matches()) {
            LOGGER.debug("fileName not matches");
            chain.doFilter(request, response);
            return;
        }

        final String fileSuffix = fileNameMatcher.group(2);
        final String mediaType = map.get(fileSuffix);
        if (mediaType == null) {
            LOGGER.debug("no mapped media type.");
            chain.doFilter(request, response);
            return;
        }

        final String path = resourcePath.substring(
            0, resourcePath.length() - fileSuffix.length() - 1);
        LOGGER.debug("path: {}", path);

        final ServletRequest wrapper =
            RequestHeaderWrapper.newPrecedingInstance(
            request, "Accept", mediaType);
        final RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(wrapper, response);
        LOGGER.debug("fowarded");
        return;
    }


    private transient String contextPath;


    private transient int contextPathLength;


    private Map<String, String> map;


}
