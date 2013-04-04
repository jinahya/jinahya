

package com.googlecode.jinahya.test;


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
@Path("/imageFormats")
public class ImageFormatsResource {


    private static final Logger LOGGER =
        Logger.getLogger(ImageFormatsResource.class.getName());


    private static final ImageFormats IMAGE_FORMATS = new ImageFormats();


    static {

        final String[] readerFormatNames = ImageIO.getReaderFormatNames();
        for (String readerFormatName : readerFormatNames) {
            ImageFormat imageFormat =
                IMAGE_FORMATS.getImageFormat().get(readerFormatName);
            if (imageFormat == null) {
                imageFormat = new ImageFormat();
                imageFormat.name = readerFormatName;
                IMAGE_FORMATS.getImageFormat().put(
                    readerFormatName, imageFormat);
            }
            imageFormat.canRead = true;
        }

        final String[] writerFormatNames = ImageIO.getWriterFormatNames();
        for (String writerFormatName : writerFormatNames) {
            ImageFormat imageFormat =
                IMAGE_FORMATS.getImageFormat().get(writerFormatName);
            if (imageFormat == null) {
                imageFormat = new ImageFormat();
                imageFormat.name = writerFormatName;
                IMAGE_FORMATS.getImageFormat().put(
                    writerFormatName, imageFormat);
            }
            imageFormat.canWrite = true;
        }
    }


    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ImageFormats read() {

        LOGGER.info("read()");

        return IMAGE_FORMATS;
    }


    @GET
//    @Path("/{name: .+}")
    @Path("/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response readImageFormat(@PathParam("name") final String name) {

        LOGGER.log(Level.INFO, "readImageFormat({0})", name);

        final ImageFormat imageFormat =
            IMAGE_FORMATS.getImageFormat().get(name);

        if (imageFormat == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(imageFormat).build();
    }


}

