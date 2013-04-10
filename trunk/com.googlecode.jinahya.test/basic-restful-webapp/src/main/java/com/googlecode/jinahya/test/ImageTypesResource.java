

package com.googlecode.jinahya.test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
@Path("/imageTypes")
public class ImageTypesResource {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(ImageTypesResource.class.getName());


    private static final Map<String, ImageType> TYPES = new HashMap<>();


    static {

        final String[] readerMIMETypes = ImageIO.getReaderMIMETypes();
        for (String readerMIMEType : readerMIMETypes) {
            ImageType imageType = TYPES.get(readerMIMEType);
            if (imageType == null) {
                imageType = new ImageType();
                imageType.value = readerMIMEType;
                TYPES.put(readerMIMEType, imageType);
            }
            imageType.canRead = true;
        }

        final String[] writerMIMENames = ImageIO.getWriterMIMETypes();
        for (String writerMIMEType : writerMIMENames) {
            ImageType imageType = TYPES.get(writerMIMEType);
            if (imageType == null) {
                imageType = new ImageType();
                imageType.value = writerMIMEType;
                TYPES.put(writerMIMEType, imageType);
            }
            imageType.canWrite = true;
        }
    }


    protected static String[] getImageTypes() {
        final Set<String> imageTypes = TYPES.keySet();
        return imageTypes.toArray(new String[imageTypes.size()]);
    }


    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ImageType> read() {

        LOGGER.info("read()");

        return new ArrayList<>(TYPES.values());
    }


    @GET
    @Path("/{name: .+}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ImageType readImageType(@PathParam("name") final String name) {

        LOGGER.log(Level.INFO, "readImageType({0})", name);

        final ImageType imageType = TYPES.get(name);

        if (imageType == null) {
            throw new WebApplicationException(Status.BAD_REQUEST);
        }

        return imageType;
    }


}

