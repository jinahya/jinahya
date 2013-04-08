

package com.googlecode.jinahya.test;


import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Path("/items")
public class ItemsResource {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(ItemsResource.class.getName());


    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response read() {

        final Items items = new Items();

        items.getItem().addAll(ItemFacade.getInstance().selectAll());

//        if (items.getItems().isEmpty()) {
//            final List<MediaType> acceptableMediaTypes =
//                httpHeaders.getAcceptableMediaTypes();
//            if (acceptableMediaTypes != null && !acceptableMediaTypes.isEmpty()
//                && MediaType.APPLICATION_JSON_TYPE.equals(
//                acceptableMediaTypes.get(0))) {
//                // Accept: application/json
//                return Response.ok("{}").build();
//            }
//        }

        return Response.ok(items).build();
    }


    @DELETE
    public void delete() {

        ItemFacade.getInstance().deleteAll();
    }


    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createItem(final Item item) {

        LOGGER.log(Level.INFO, "createItem({0})", item);

        ItemFacade.getInstance().insert(item);

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder = builder.path(Long.toString(item.getId()));

        final URI uri = builder.build();

        return Response.created(uri).build();
    }


    @Path("/{id: \\d+}")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response readItem(@PathParam("id") final long id) {

        final Item item = ItemFacade.getInstance().select(id);

        if (item == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(item).build();
    }


    @Path("/{id: \\d+}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateItem(@PathParam("id") final long id,
                               final Item newItem) {

        LOGGER.log(Level.INFO, "updateItem({0}, {1})",
                   new Object[]{id, newItem});

        final boolean updated = ItemFacade.getInstance().update(id, newItem);

        if (!updated) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.status(Status.NO_CONTENT).build();
    }


    @Path("/{id: \\d+}")
    @DELETE
    public void deleteItem(@PathParam("id") final long id) {

        ItemFacade.getInstance().delete(id);
    }


    @Context
    private HttpHeaders httpHeaders;


    @Context
    private UriInfo uriInfo;


}

