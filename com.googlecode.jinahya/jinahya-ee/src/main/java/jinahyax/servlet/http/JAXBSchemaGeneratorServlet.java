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


package jinahyax.servlet.http;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;

import jinahyax.servlet.ServletResponseResult;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class JAXBSchemaGeneratorServlet extends HttpServlet {


    /** GENERATED. */
    private static final long serialVersionUID = -4515187927939394335L;


    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
        throws ServletException, IOException {

        try {
            final JAXBContext context = getJAXBContext();

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/xml");
            resp.setCharacterEncoding("UTF-8");

            final ServletResponseResult result =
                new ServletResponseResult(resp);
            result.setSystemId(req);

            final SchemaOutputResolver resolver =
                result.toSchemaOutputResolver();

            context.generateSchema(resolver);

            resp.flushBuffer();
            return;

        } catch (JAXBException jaxbe) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                           jaxbe.getErrorCode() + ":" + jaxbe.getMessage());
            return;
        }
    }


    /**
     * Creates the JAXBContex for generating schema.
     *
     * @return the JAXBContext instance.
     * @throws JAXBException if a JAXB error occurs.
     */
    protected abstract JAXBContext getJAXBContext() throws JAXBException;


}

