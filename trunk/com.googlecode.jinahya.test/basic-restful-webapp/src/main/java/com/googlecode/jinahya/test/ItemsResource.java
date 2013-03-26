

package com.googlecode.jinahya.test;


import java.net.URI;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
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


    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Items read() {
        return Items.getInstance();
    }


    @DELETE
    public void delete() {

        Items.getInstance().getItems().clear();
    }


    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createItem(@Context final UriInfo info,
                               final Item item) {

        Item.putCreatedAtAndId(item);

        Items.getInstance().getItems().put(item.getId(), item);

        UriBuilder builder = info.getAbsolutePathBuilder();
        builder = builder.path(Long.toString(item.getId()));

        final URI uri = builder.build();

        return Response.created(uri).build();
    }


    @Path("/item/{id: \\d+}")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Item readItem(@PathParam("id") final long id) {

        return Items.getInstance().getItems().get(id);
    }


    @Path("/item/{id: \\d+}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateItem(@PathParam("id") final long id,
                               final Item newItem) {

        final Item oldItem = readItem(id);

        if (oldItem == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        oldItem.updatedAt = new Date();

        oldItem.name = newItem.name;
        oldItem.stock = newItem.stock;

        return Response.status(Status.NO_CONTENT).build();
    }


    @Path("/item/{id: \\d+}")
    @DELETE
    public void deleteItem(@PathParam("id") final long id) {

        Items.getInstance().getItems().remove(id);
    }


}

