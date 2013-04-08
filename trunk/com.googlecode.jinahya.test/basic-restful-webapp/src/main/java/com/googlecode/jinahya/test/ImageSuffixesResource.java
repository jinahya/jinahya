

package com.googlecode.jinahya.test;


import java.util.HashMap;
import java.util.Map;
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
@Path("/imageSuffixes")
public class ImageSuffixesResource {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(ImageSuffixesResource.class.getName());


    private static final Map<String, ImageSuffix> SUFFIXES = new HashMap<>();


    static {

        final String[] readerFileSuffixes = ImageIO.getReaderFileSuffixes();
        for (String readerFileSuffix : readerFileSuffixes) {
            ImageSuffix imageSuffix = SUFFIXES.get(readerFileSuffix);
            if (imageSuffix == null) {
                imageSuffix = new ImageSuffix();
                imageSuffix.value = readerFileSuffix;
                SUFFIXES.put(readerFileSuffix, imageSuffix);
            }
            imageSuffix.canRead = true;
        }

        final String[] writerFileSuffixes = ImageIO.getWriterFileSuffixes();
        for (String writerFileSuffix : writerFileSuffixes) {
            ImageSuffix imageSuffix = SUFFIXES.get(writerFileSuffix);
            if (imageSuffix == null) {
                imageSuffix = new ImageSuffix();
                imageSuffix.value = writerFileSuffix;
                SUFFIXES.put(writerFileSuffix, imageSuffix);
            }
            imageSuffix.canWrite = true;
        }
    }


    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ImageSuffixes read() {

        LOGGER.info("read()");

        final ImageSuffixes imageSuffixes = new ImageSuffixes();

        imageSuffixes.getSingulars().addAll(SUFFIXES.values());

        return imageSuffixes;
    }


    @GET
    @Path("/{name: .+}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response readImageSuffix(@PathParam("name") final String name) {

        LOGGER.log(Level.INFO, "readImageSuffix({0})", name);

        final ImageSuffix imageSuffix = SUFFIXES.get(name);

        if (imageSuffix == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(imageSuffix).build();
    }


}

