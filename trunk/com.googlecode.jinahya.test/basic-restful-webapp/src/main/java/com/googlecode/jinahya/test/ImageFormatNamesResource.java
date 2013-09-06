

package com.googlecode.jinahya.test;


import com.googlecode.jinahya.imageio.ImageFormatName;
import com.googlecode.jinahya.imageio.ImageFormatNames;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Path("/imageFormats")
public class ImageFormatNamesResource {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(ImageFormatNamesResource.class.getName());


    private static final ImageFormatNames IMAGE_FORMAT_NAMES =
        ImageFormatNames.newInstance();


    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ImageFormatName> read() {

        LOGGER.info("read()");

        return IMAGE_FORMAT_NAMES.getImageFormatNameList();
    }


    @GET
    @Path("/{key: .+}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ImageFormatName readImageFormatName(
        @PathParam("key") final String key) {

        LOGGER.log(Level.INFO, "readImageFormatName({0})", key);

        final ImageFormatName iamgeFormatName =
            IMAGE_FORMAT_NAMES.getImageFormatNames().get(key);

        if (iamgeFormatName == null) {
            throw new WebApplicationException(Status.NOT_FOUND);
        }

        return iamgeFormatName;
    }


}
