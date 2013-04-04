

package com.googlecode.jinahya.test;


import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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


    private static final ImageTypes IMAGE_TYPES = new ImageTypes();


    protected static String[] getImageTypes() {
        final Set<String> imageTypes = IMAGE_TYPES.getImageType().keySet();
        return imageTypes.toArray(new String[imageTypes.size()]);
    }


    static {

        final String[] readerMIMETypes = ImageIO.getReaderMIMETypes();
        for (String readerMIMEType : readerMIMETypes) {
            ImageType imageType =
                IMAGE_TYPES.getImageType().get(readerMIMEType);
            if (imageType == null) {
                imageType = new ImageType();
                imageType.name = readerMIMEType;
                IMAGE_TYPES.getImageType().put(readerMIMEType, imageType);
            }
            imageType.canRead = true;
        }

        final String[] writerMIMENames = ImageIO.getWriterMIMETypes();
        for (String writerMIMEType : writerMIMENames) {
            ImageType imageType =
                IMAGE_TYPES.getImageType().get(writerMIMEType);
            if (imageType == null) {
                imageType = new ImageType();
                imageType.name = writerMIMEType;
                IMAGE_TYPES.getImageType().put(writerMIMEType, imageType);
            }
            imageType.canWrite = true;
        }
    }


    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ImageTypes read() {

        LOGGER.info("read()");

        return IMAGE_TYPES;
    }


    @GET
    @Path("/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response readImageType(@PathParam("name") final String name) {

        LOGGER.log(Level.INFO, "readImageType({0})", name);

        final ImageType imageType = IMAGE_TYPES.getImageType().get(name);

        if (imageType == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(imageType).build();
    }


}

