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


package com.googlecode.jinahya.servlet.http;


import com.googlecode.jinahya.servlet.AbstractFilter;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.net.URL;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class XSLTHttpFilter extends AbstractFilter {


    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain)
        throws IOException, ServletException {

        final BufferedHttpServletOutputStream outputStream =
            new BufferedHttpServletOutputStream();

        final HttpServletResponseWrapper responseWrapper =
            new BufferedHttpServletResponseWrapper(
            (HttpServletResponse) response, outputStream);

        chain.doFilter(request, responseWrapper);

        responseWrapper.flushBuffer();
        outputStream.flush();

        final byte[] outputBytes = outputStream.getBytes();

        final int status = responseWrapper.getStatus();
        if (status != HttpServletResponse.SC_OK) {
            ((HttpServletResponse) response).setStatus(status);
            response.setContentType(responseWrapper.getContentType());
            response.setCharacterEncoding(
                responseWrapper.getCharacterEncoding());
            response.getOutputStream().write(outputBytes);
            response.flushBuffer();
            return;
        }

        final URL stylesheetResource;
        try {
            stylesheetResource = getStylesheetResource();
        } catch (IOException e) {
            ((HttpServletResponse) response).sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "failed to get stylesheet resource: " + e.getMessage());
            return;
        }

        final Source stylesheetSource;
        try {
            stylesheetSource = new StreamSource(
                stylesheetResource.openStream());
        } catch (IOException ioe) {
            ((HttpServletResponse) response).sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "failed to open stream from stylesheet resource("
                + stylesheetResource + "): " + ioe.getMessage());
            return;
        }

        final Transformer transformer;
        try {
            transformer = newTransformer(stylesheetSource);
        } catch (TransformerConfigurationException tce) {
            ((HttpServletResponse) response).sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "failed to create transformer: " + tce.getMessage());
            return;
        }

        ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_OK);
        ((HttpServletResponse) response).setContentType(getContentType());
        ((HttpServletResponse) response).setCharacterEncoding(
            getCharacterEncoding());

        try {
            transformer.transform(
                new StreamSource(new ByteArrayInputStream(outputBytes)),
                new StreamResult(response.getOutputStream()));
            response.flushBuffer();
            return;
        } catch (TransformerException te) {
            ((HttpServletResponse) response).sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "failed to transform: " + te.getMessage());
            return;
        }

    }


    /**
     * 
     * @return
     * @throws IOException 
     */
    protected abstract URL getStylesheetResource() throws IOException;


    /**
     * 
     * @return 
     */
    protected abstract String getContentType();


    /**
     * 
     * @return 
     */
    protected String getCharacterEncoding() {
        return "UTF-8";
    }


    /**
     * Creates a transformer for <code>source</code>.
     *
     * @param source source
     * @return a new Transformer
     * @throws TransformerConfigurationException if an error occurs.
     */
    protected Transformer newTransformer(final Source source)
        throws TransformerConfigurationException {

        if (transformerFactory == null) {
            transformerFactory = newTransformerFactory();
        }

        return transformerFactory.newTransformer(source);
    }


    /**
     * 
     * @return 
     */
    protected TransformerFactory newTransformerFactory() {
        return TransformerFactory.newInstance();
    }


    /** transformerFactory. */
    private TransformerFactory transformerFactory;


}

