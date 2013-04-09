

package com.googlecode.jinahya.test;


import com.googlecode.jinahya.ws.rs.core.SchemaStreamingOutput;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Path("/imageFormats.xsd")
public class ImageFormatsXsdResource {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(ImageFormatsXsdResource.class.getName());


    @GET
    @Produces({MediaType.APPLICATION_XML})
    public Response read() throws JAXBException {

        LOGGER.info("read()");

        final JAXBContext context =
            JAXBContext.newInstance(ImageFormats.class, ImageFormat.class);

        return Response.ok(new SchemaStreamingOutput(context)).build();
    }


}

