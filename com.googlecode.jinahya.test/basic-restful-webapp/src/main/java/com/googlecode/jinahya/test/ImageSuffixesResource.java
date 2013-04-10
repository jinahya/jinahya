

package com.googlecode.jinahya.test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
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
    public List<ImageSuffix> readAsList(@QueryParam("empty") final boolean empty) {

        LOGGER.log(Level.INFO, "read({0})", empty);

        if (empty) {
            return Collections.emptyList();
        }

        return new ArrayList<>(SUFFIXES.values());
    }


//    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ImageSuffix[] readAsArray(@QueryParam("empty") final boolean empty) {

        LOGGER.log(Level.INFO, "read({0})", empty);

        if (empty) {
            return new ImageSuffix[0];
        }

        return SUFFIXES.values().toArray(new ImageSuffix[SUFFIXES.size()]);
    }


    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @PUT
    public void updateWithList(final List<ImageSuffix> imageSuffixes) {

        LOGGER.log(Level.INFO, "update({0})", imageSuffixes);

        for (ImageSuffix imageSuffix : imageSuffixes) {
            System.out.println(imageSuffix);
        }
    }


    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    @PUT
    public void updateWithArray(final ImageSuffix[] imageSuffixes) {

        LOGGER.log(Level.INFO, "update({0})", imageSuffixes);

        for (ImageSuffix imageSuffix : imageSuffixes) {
            System.out.println(imageSuffix);
        }
    }


    @GET
    @Path("/{name: .+}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ImageSuffix readImageSuffix(@PathParam("name") final String name) {

        LOGGER.log(Level.INFO, "readImageSuffix({0})", name);

        final ImageSuffix imageSuffix = SUFFIXES.get(name);

        if (imageSuffix == null) {
            throw new WebApplicationException(Status.NOT_FOUND);
        }

        return imageSuffix;
    }


    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @PUT
    @Path("/{name: .+}")
    public void updateImageSuffix(@PathParam("name") final String name,
                                  final ImageSuffix imageSuffix) {

        LOGGER.log(Level.INFO, "updateImageSuffix({0}, {1})",
                   new Object[]{name, imageSuffix});
    }


}

