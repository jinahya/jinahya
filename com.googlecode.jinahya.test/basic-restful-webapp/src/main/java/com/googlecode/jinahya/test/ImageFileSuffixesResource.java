

package com.googlecode.jinahya.test;


import com.googlecode.jinahya.imageio.ImageFileSuffix;
import com.googlecode.jinahya.imageio.ImageFileSuffixes;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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
@Path("/imageFileSuffixes")
public class ImageFileSuffixesResource {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(ImageFileSuffixesResource.class.getName());


    public static final ImageFileSuffixes IMAGE_FILE_SUFFIXES =
        ImageFileSuffixes.newInstance();


    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ImageFileSuffix> read() {

        return IMAGE_FILE_SUFFIXES.getImageFileSuffixList();
    }


    @GET
    @Path("/{key: .+}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ImageFileSuffix readImageFileSuffix(
        @PathParam("key") final String key) {

        LOGGER.log(Level.INFO, "readImageFileSuffix({0})", key);

        final ImageFileSuffix iamgeFileSuffix =
            IMAGE_FILE_SUFFIXES.getImageDescriptors().get(key);

        if (iamgeFileSuffix == null) {
            throw new WebApplicationException(Status.NOT_FOUND);
        }

        return iamgeFileSuffix;
    }


    @HeaderParam("Content-Type")
    private String contentType;


    @HeaderParam("Accept")
    private String accept;


}
