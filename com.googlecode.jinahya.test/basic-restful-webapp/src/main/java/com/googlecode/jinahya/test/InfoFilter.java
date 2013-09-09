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


package com.googlecode.jinahya.test;


import com.googlecode.jinahya.servlet.http.HttpFilter;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@WebFilter(urlPatterns = {"/*"})
public class InfoFilter extends HttpFilter {


    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

        super.init(filterConfig);

        final ServletContext servletContext = filterConfig.getServletContext();

        final String contextPath = servletContext.getContextPath();
        System.out.println("contextPath: " + contextPath);
    }


    @Override
    protected void doFilter(final HttpServletRequest request,
                            final HttpServletResponse response,
                            final FilterChain chain)
        throws IOException, ServletException {

        final String pathInfo = request.getPathInfo();
        System.out.println("pathInfo: " + pathInfo);

        final String pathTranslated = request.getPathTranslated();
        System.out.println("pathTranslated: " + pathTranslated);

        final String servletPath = request.getServletPath();
        System.out.println("servletPath: " + servletPath);

        final String requestUri = request.getRequestURI();
        System.out.println("requestUri: " + requestUri);

        final String requestUrl = request.getRequestURL().toString();
        System.out.println("requestUrl: " + requestUrl);

        chain.doFilter(request, response);
    }


}
