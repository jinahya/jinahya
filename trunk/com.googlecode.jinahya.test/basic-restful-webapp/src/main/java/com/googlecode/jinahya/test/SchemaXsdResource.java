

package com.googlecode.jinahya.test;


import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;
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
@Path("/schema.xsd")
public class SchemaXsdResource {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(SchemaXsdResource.class.getName());


    @GET
    @Produces({MediaType.APPLICATION_XML})
    public Response read() throws JAXBException {

        LOGGER.info("read()");

        final JAXBContext context =
            JAXBContext.newInstance("com.googlecode.jinahya.test");

        return Response.ok(new StreamingOutput() {


            @Override
            public void write(final OutputStream output)
                throws IOException, WebApplicationException {

                context.generateSchema(new SchemaOutputResolver() {


                    @Override
                    public Result createOutput(final String namespaceUri,
                                               final String suggestedFileName)
                        throws IOException {

                        return new StreamResult(output) {


                            @Override
                            public String getSystemId() {
                                return suggestedFileName;
                            }


                        };
                    }


                });
            }


        }).build();
    }


}

