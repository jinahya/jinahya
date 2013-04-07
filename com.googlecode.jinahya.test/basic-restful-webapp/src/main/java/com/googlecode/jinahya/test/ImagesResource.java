

package com.googlecode.jinahya.test;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Path("/images")
public class ImagesResource {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(ImagesResource.class.getName());


    /**
     * The map of names and images.
     */
    protected static final Map<String, byte[]> IMAGES =
        Collections.synchronizedMap(new HashMap<String, byte[]>());


    /**
     * Deletes all images.
     */
    @DELETE
    public void delete() {

        LOGGER.info("delete()");

        IMAGES.clear();
    }


    /**
     * Reads the image mapped to {@code /{name}}.
     *
     * @param name the name of the image
     *
     * @return a Response
     */
    @Path("/{name: .+}")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response readImage(@PathParam("name") final String name) {

        LOGGER.log(Level.INFO, "readImage({0})", name);

        final byte[] image = IMAGES.get(name);

        if (image == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(image).build();
    }


    /**
     * Updates the image mapped to {@code /{name}}.
     *
     * @param name the name of the image
     * @param image image bytes
     *
     * @return a Response
     */
    @Path("/{name: .+}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateImage(@PathParam("name") final String name,
                                final byte[] image) {

        LOGGER.log(Level.INFO, "updateImage({0}, {1})",
                   new Object[]{name, image});

        final byte[] oldImage = IMAGES.put(name, image);

        if (oldImage == null) {
            return Response.created(uriInfo.getAbsolutePath()).build();
        }

        return Response.status(Status.NO_CONTENT).build();
    }


    /**
     * Deletes the image mapped by {@code /{name}}.
     *
     * @param name the name of the image.
     */
    @Path("/{name: .+}")
    @DELETE
    public void deleteImage(@PathParam("name") final String name) {

        LOGGER.log(Level.INFO, "deleteImage({0})", name);

        final byte[] image = IMAGES.remove(name);

        // void -> 204 Not Found
    }


    @Context
    private HttpHeaders httpHeaders;


    @Context
    private UriInfo uriInfo;


}

