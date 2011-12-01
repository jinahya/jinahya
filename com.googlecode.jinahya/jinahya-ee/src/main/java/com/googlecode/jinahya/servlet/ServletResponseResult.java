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


import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.bind.SchemaOutputResolver;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ServletResponseResult extends StreamResult {


    /**
     * Creates a new instance.
     *
     * @param response servlet response
     * @throws IOException if an I/O error occurs.
     */
    public ServletResponseResult(final HttpServletResponse response)
        throws IOException {

        super(response.getOutputStream());
    }


    /**
     * Sets systemId with given <code>request</code>'s
     * <code>getRequestURL()</code> value.
     *
     * @param request servlet request
     */
    public void setSystemId(final HttpServletRequest request) {

        if (request == null) {
            throw new NullPointerException("null request");
        }

        setSystemId(request.getRequestURL().toString());
    }


    /**
     * Returns self as a SchemaOutputResolver.
     *
     * @return self as a SchemaOutputResolver.
     */
    public SchemaOutputResolver toSchemaOutputResolver() {

        return new SchemaOutputResolver() {


            @Override
            public Result createOutput(final String namespaceUri,
                                       final String suggestedFileName)
                throws IOException {

                return ServletResponseResult.this;
            }


        };
    }


}

