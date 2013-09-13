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
 * Abstract implementation of {@link Filter}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class AbstractFilter implements Filter {


    /**
     * {@inheritDoc}
     * <p/>
     * Overridden to store given {@code filterConfig}.
     *
     * @param filterConfig {@inheritDoc}
     *
     * @throws ServletException {@inheritDoc}
     */
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;
    }


    /**
     * {@inheritDoc}
     * <p/>
     * Overridden to remove the stored {@code filterConfig} instance.
     */
    @Override
    public void destroy() {

        filterConfig = null;
    }


    /**
     * Returns config.
     *
     * @return filerConfig
     *
     * @throws IllegalStateException if this method called after
     * {@link #destroy()} invoked.
     */
    protected FilterConfig getFilterConfig() {

        if (filterConfig == null) {
            throw new IllegalStateException("no filterConfig");
        }

        return filterConfig;
    }


    /**
     * Returns servlet context.
     *
     * @return the servlet context
     *
     * @see #getServletConfig()
     * @see FilterConfig#getServletContext()
     */
    protected ServletContext getServletContext() {

        return getFilterConfig().getServletContext();
    }


    /**
     * Logs given {@code message}.
     *
     * @param message message
     *
     * @see #getServletContext()
     * @see ServletContext#log(java.lang.String)
     */
    protected void log(final String message) {

        getServletContext().log(message);
    }


    /**
     * Logs given {@code message} with specified {@code cause}.
     *
     * @param message message
     * @param throwable throwable
     *
     * @see #getServletContext()
     * @see ServletContext#log(java.lang.String, java.lang.Throwable)
     */
    protected void log(final String message, final Throwable throwable) {

        getServletContext().log(message, throwable);
    }


    /**
     * filter config.
     */
    private FilterConfig filterConfig;


}
