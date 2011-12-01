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
import java.io.InputStreamReader;

import java.net.URL;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;


/**
 * XSLT Filter.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class XSLTFilter extends AbstractFilter {


    /**
     * Preferred character encoding.
     */
    protected static final String PREFERRED_CHARACTER_ENCODING = "UTF-8";


    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain)
        throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            throw new IllegalArgumentException(
                request + " is not an instance of" + HttpServletRequest.class);
        }

        if (request instanceof HttpServletResponse) {
            throw new IllegalArgumentException(
                response + " is not an instance of"
                + HttpServletResponse.class);
        }

        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;

        final BufferedHttpServletResponseWrapper responseWrapper =
            new BufferedHttpServletResponseWrapper(httpResponse);

        chain.doFilter(request, responseWrapper); // ------------- doFilter(...)

        final byte[] outputBytes = responseWrapper.getBytes();

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

        final URL resource;
        try {
            resource = getStylesheetResource();
            if (resource == null) {
                ((HttpServletResponse) response).sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "failed to get stylesheet resource: null returned");
                return;
            }
        } catch (IOException e) {
            ((HttpServletResponse) response).sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "failed to get stylesheet resource: " + e.getMessage());
            return;
        }

        final Source source;
        try {
            source = new StreamSource(resource.openStream());
        } catch (IOException ioe) {
            ((HttpServletResponse) response).sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "failed to open stream from stylesheet resource("
                + resource + "): " + ioe.getMessage());
            return;
        }

        final Transformer transformer;
        try {
            transformer = getTransformerFactory().newTransformer(source);
        } catch (TransformerConfigurationException tce) {
            ((HttpServletResponse) response).sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "failed to create transformer: " + tce.getMessage());
            return;
        }

        final Properties properties = new Properties();
        getTransformerOutputProperties(properties);
        transformer.setOutputProperties(properties);

        final Map<String, Object> parameters = new HashMap<String, Object>();
        getTransformerParameters(parameters);
        for (Entry<String, Object> parameter : parameters.entrySet()) {
            transformer.setParameter(parameter.getKey(), parameter.getValue());
        }

        httpResponse.setStatus(HttpServletResponse.SC_OK);
        httpResponse.setContentType(getOutputContentType());
        httpResponse.setCharacterEncoding(getOutputCharacterEncoding());

        try {
            transformer.transform(
                new StreamSource(new InputStreamReader(
                new ByteArrayInputStream(outputBytes),
                responseWrapper.getCharacterEncoding())),
                new StreamResult(response.getWriter()));

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
     * Fills output properties for the Transformer.
     *
     * @param properties output properties to be filled
     */
    protected void getTransformerOutputProperties(final Properties properties) {
        properties.put(OutputKeys.ENCODING, getOutputCharacterEncoding());
    }


    /**
     * Fills parameters for the Transformer.
     *
     * @param parameters parameters to be filled
     */
    protected abstract void getTransformerParameters(
        Map<String, Object> parameters);


    /**
     * Returns stylesheet resource.
     *
     * @return stylesheet resource.
     * @throws IOException if an I/O error occurs.
     */
    protected abstract URL getStylesheetResource() throws IOException;


    /**
     * Returns output content type.
     *
     * @return output content type
     */
    protected abstract String getOutputContentType();


    /**
     * Returns output character encoding.
     *
     * @return output character encoding.
     */
    protected abstract String getOutputCharacterEncoding();


    /**
     * Returns a <code>TransformerFactory</code> instance. The default
     * implementation returns {@link TransformerFactory#newInstance()}. Override
     * this method if you want to use a custom factory.
     *
     * @return a new instance of TransformerFactory.
     */
    protected TransformerFactory getTransformerFactory() {

        if (transformerFactory == null) {
            transformerFactory = TransformerFactory.newInstance();
        }

        return transformerFactory;
    }


    /** transformerFactory. */
    private TransformerFactory transformerFactory;


}

