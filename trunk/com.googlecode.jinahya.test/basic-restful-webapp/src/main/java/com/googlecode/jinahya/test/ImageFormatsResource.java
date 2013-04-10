

package com.googlecode.jinahya.test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@Path("/imageFormats")
public class ImageFormatsResource {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(ImageFormatsResource.class.getName());


    /**
     * image formats.
     */
    private static final Map<String, ImageFormat> FORMATS = new HashMap<>();


    static {

        final String[] readerFormatNames = ImageIO.getReaderFormatNames();
        for (String readerFormatName : readerFormatNames) {
            ImageFormat imageFormat = FORMATS.get(readerFormatName);
            if (imageFormat == null) {
                imageFormat = new ImageFormat();
                imageFormat.value = readerFormatName;
                FORMATS.put(readerFormatName, imageFormat);
            }
            imageFormat.canRead = true;
        }

        final String[] writerFormatNames = ImageIO.getWriterFormatNames();
        for (String writerFormatName : writerFormatNames) {
            ImageFormat imageFormat = FORMATS.get(writerFormatName);
            if (imageFormat == null) {
                imageFormat = new ImageFormat();
                imageFormat.value = writerFormatName;
                FORMATS.put(writerFormatName, imageFormat);
            }
            imageFormat.canWrite = true;
        }
    }


    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ImageFormat> read() {

        LOGGER.info("read()");

        return new ArrayList<>(FORMATS.values());
    }


    @GET
    @Path("/{name: .+}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ImageFormat readImageFormat(@PathParam("name") final String name) {

        LOGGER.log(Level.INFO, "readImageFormat({0})", name);

        final ImageFormat imageFormat = FORMATS.get(name);

        if (imageFormat == null) {
            throw new WebApplicationException(Status.NOT_FOUND);
        }

        return imageFormat;
    }


}

