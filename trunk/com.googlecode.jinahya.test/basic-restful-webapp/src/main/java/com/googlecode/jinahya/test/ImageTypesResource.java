

package com.googlecode.jinahya.test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
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


    public static final Map<String, ImageType> TYPES;


    static {

        final SortedMap<String, ImageType> types = new TreeMap<>();
        final String[] readerMIMETypes = ImageIO.getReaderMIMETypes();
        for (String readerMIMEType : readerMIMETypes) {
            ImageType imageType = types.get(readerMIMEType);
            if (imageType == null) {
                imageType = new ImageType();
                imageType.value = readerMIMEType;
                types.put(readerMIMEType, imageType);
            }
            imageType.canRead = true;
        }

        final String[] writerMIMETypes = ImageIO.getWriterMIMETypes();
        for (String writerMIMEType : writerMIMETypes) {
            ImageType imageType = types.get(writerMIMEType);
            if (imageType == null) {
                imageType = new ImageType();
                imageType.value = writerMIMEType;
                types.put(writerMIMEType, imageType);
            }
            imageType.canWrite = true;
        }

        TYPES = Collections.unmodifiableSortedMap(types);
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
