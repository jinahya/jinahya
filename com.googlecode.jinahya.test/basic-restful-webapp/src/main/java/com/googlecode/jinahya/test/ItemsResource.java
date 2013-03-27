

package com.googlecode.jinahya.test;


import java.net.URI;
import javax.annotation.PostConstruct;
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


    @PostConstruct
    protected void _PostConstruct() {


        ItemFacade.getInstance().insert(Item.newInstance("item1", 30));
        ItemFacade.getInstance().insert(Item.newInstance("item2", 40));
    }


    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Items read() {

        final Items items = new Items();

        items.getItems().addAll(ItemFacade.getInstance().selectAll());

        return items;
    }


    @DELETE
    public void delete() {

        ItemFacade.getInstance().deleteAll();
    }


    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createItem(@Context final UriInfo info,
                               final Item item) {

        ItemFacade.getInstance().insert(item);

        UriBuilder builder = info.getAbsolutePathBuilder();
        builder = builder.path(Long.toString(item.getId()));

        final URI uri = builder.build();

        return Response.created(uri).build();
    }


    @Path("/{id: \\d+}")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Item readItem(@PathParam("id") final long id) {

        return ItemFacade.getInstance().select(id);
    }


    @Path("/{id: \\d+}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateItem(@PathParam("id") final long id,
                               final Item newItem) {

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


}

