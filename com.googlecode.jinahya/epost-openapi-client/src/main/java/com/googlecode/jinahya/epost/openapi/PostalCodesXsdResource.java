/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.epost.openapi;


import java.io.IOException;
import java.io.OutputStream;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Path("/postalCodes.xsd")
public class PostalCodesXsdResource {


    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response reads() throws Exception {

        return Response.ok(new StreamingOutput() {
            @Override
            public void write(final OutputStream output) throws IOException {
                try {
                    final JAXBContext context = JAXBContext.newInstance(
                        "com.googlecode.jinahya.epost.openapi");
                    context.generateSchema(new SchemaOutputResolver() {
                        @Override
                        public Result createOutput(
                            final String namespaceUri,
                            final String suggestedFileName) throws IOException {
                            return new StreamResult(output) {
                                @Override
                                public String getSystemId() {
                                    return "noid";
                                }
                            };
                        }
                    });
                } catch (JAXBException jaxbe) {
                    throw new WebApplicationException(jaxbe);
                }
            }
        }).build();
    }
}

