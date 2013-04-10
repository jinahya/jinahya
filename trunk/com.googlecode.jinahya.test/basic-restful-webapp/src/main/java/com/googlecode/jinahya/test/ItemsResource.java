

package com.googlecode.jinahya.test;


import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
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
@Path("/items")
public class ItemsResource {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(ItemsResource.class.getName());


    /**
     * id sequencer.
     */
    private static final AtomicLong IDS = new AtomicLong();


    /**
     * items map.
     */
    private static final Map<Long, Item> ITEMS =
        Collections.synchronizedSortedMap(new TreeMap<Long, Item>());


    /**
     * Reads all items.
     *
     * @param empty an optional flag for empty list.
     *
     * @return a list of items.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> read(@QueryParam("empty") final boolean empty) {

        LOGGER.log(Level.INFO, "read({0})", empty);

        return new ArrayList<>(ITEMS.values());
    }


    /**
     * Replaces all items.
     *
     * @param items new items
     */
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @PUT
    public void update(final List<Item> items) {

        LOGGER.log(Level.INFO, "update({0})", items);

        synchronized (ITEMS) {
            ITEMS.clear();
            for (Item item : items) {
                item.createdAt = new Date();
                item.id = IDS.incrementAndGet();
                ITEMS.put(item.getId(), item);
            }
        }
    }


    /**
     * Deletes all items.
     */
    @DELETE
    public void delete() {

        LOGGER.info("delete()");

        ITEMS.clear();
    }


    /**
     * Creates a new item.
     *
     * @param item the new item.
     *
     * @return 201 Created with {@code Location} response header.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createItem(final Item item) {

        LOGGER.log(Level.INFO, "createItem({0})", item);

        item.createdAt = new Date();
        item.id = IDS.incrementAndGet();

        ITEMS.put(item.getId(), item); // synchronzied

        final URI uri = uriInfo.getAbsolutePathBuilder()
            .path(Long.toString(item.getId()))
            .build();

        return Response.created(uri).build();
    }


    /**
     * Reads the item mapped to {@code /{id: \\d+}}.
     *
     * @param id item id
     *
     * @return the item; or 404 Not Found
     */
    @Path("/{id: \\d+}")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Item readItem(@PathParam("id") final long id) {
        
        LOGGER.log(Level.INFO, "readItem({0})", id);

        final Item item = ITEMS.get(id); // synchronized

        if (item == null) {
            throw new WebApplicationException(Status.NOT_FOUND);
        }

        return item;
    }


    /**
     * Updates the item mapped to {@code /{id: \\d+}} with the content of given
     * {@code newItem}.
     *
     * @param id item id
     * @param newItem new item
     */
    @Path("/{id: \\d+}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateItem(@PathParam("id") final long id,
                           final Item newItem) {

        LOGGER.log(Level.INFO, "updateItem({0}, {1})",
                   new Object[]{id, newItem});

        synchronized (ITEMS) {
            final Item oldItem = ITEMS.get(id);
            if (oldItem == null) {
                throw new WebApplicationException(Status.NOT_FOUND);
            }
            oldItem.claimedAt = newItem.claimedAt;
            oldItem.updatedAt = new Date();
            oldItem.name = newItem.name;
            oldItem.stock = newItem.stock;
        }
    }


    /**
     * Deletes the item mapped to specified {@code /{id: \\d+}}.
     *
     * @param id item id.
     */
    @Path("/{id: \\d+}")
    @DELETE
    public void deleteItem(@PathParam("id") final long id) {

        final Item item = ITEMS.remove(id);
    }


    @Context
    private HttpHeaders httpHeaders;


    @Context
    private UriInfo uriInfo;


}

