

package com.googlecode.jinahya.test;


import com.googlecode.jinahya.imageio.ImageMediaType;
import com.googlecode.jinahya.imageio.ImageMediaTypes;
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
@Path("/imageMediaTypes")
public class ImageMediaTypesResource {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(ImageMediaTypesResource.class.getName());


    private static final ImageMediaTypes IMAGE_MEDIA_TYPES =
        ImageMediaTypes.newInstance();


    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ImageMediaType> read() {

        LOGGER.info("read()");

        return IMAGE_MEDIA_TYPES.getImageMediaTypeList();
    }


    @GET
    @Path("/{key: .+}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ImageMediaType readImageMediaType(
        @PathParam("key") final String key) {

        LOGGER.log(Level.INFO, "readImageMediaType({0})", key);

        final ImageMediaType imageMediaType =
            IMAGE_MEDIA_TYPES.getImageDescriptors().get(key);

        if (imageMediaType == null) {
            throw new WebApplicationException(Status.BAD_REQUEST);
        }

        return imageMediaType;
    }


}
