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


package com.googlecode.jinahya.jdbc.realm.service.xsd.persistence;


import com.googlecode.jinahya.jdbc.realm.persistence.Service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@WebServlet(urlPatterns = {"/xsd/persistence/service.xsd"})
public class ServiceSchemaServlet extends HttpServlet {


    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
        throws ServletException, IOException {

        try {
            final JAXBContext context =
                JAXBContext.newInstance(Service.class);

            final Result output = new StreamResult(resp.getOutputStream());
            output.setSystemId(req.getRequestURL().toString());


            final SchemaOutputResolver resolver = new SchemaOutputResolver() {


                @Override
                public Result createOutput(final String namespaceUri,
                                           final String suggestedFileName)
                    throws IOException {

                    return output;
                }


            };

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/xml");
            resp.setCharacterEncoding("UTF-8");

            context.generateSchema(resolver);
            resp.flushBuffer();
            return;

        } catch (JAXBException jaxbe) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                           jaxbe.getMessage());
            return;
        }
    }


}

