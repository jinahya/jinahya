/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.servlet;


import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;


/**
 * Abstract implementation of Filter.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class AbstractFilter implements Filter {


    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }


    @Override
    public void destroy() {
        filterConfig = null;
    }


    /**
     * Logs given {@code message}.
     *
     * @param message message
     */
    protected void log(final String message) {
        getServletContext().log(message);
    }


    /**
     * Logs given {@code message} with specified {@code cause}.
     *
     * @param message message
     * @param cause cause
     */
    protected void log(final String message, final Throwable cause) {
        getServletContext().log(message, cause);
    }


    /**
     * Returns filterConfig.
     *
     * @return filterConfig
     */
    protected FilterConfig getFilterConfig() {

        if (filterConfig == null) {
            throw new IllegalStateException("no filterConfig");
        }

        return filterConfig;
    }


    /**
     * Returns servletContext.
     *
     * @return servletContext
     */
    protected ServletContext getServletContext() {

        return getFilterConfig().getServletContext();
    }


    /**
     * filterConfig.
     */
    private FilterConfig filterConfig;


}

