

package com.googlecode.jinahya.test;


import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Path("/images.zip")
public class ImagesZipResource {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(ImagesZipResource.class.getName());


    @GET
    @Produces("application/zip")
    public Response read() {

        LOGGER.info("read()");

        synchronized (ImagesResource.IMAGES) {

            if (ImagesResource.IMAGES.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(new StreamingOutput() {


                @Override
                public void write(final OutputStream output)
                    throws IOException, WebApplicationException {
                }


            }).build();
        }
    }


    @Context
    private HttpHeaders httpHeaders;


    @Context
    private UriInfo uriInfo;


}

