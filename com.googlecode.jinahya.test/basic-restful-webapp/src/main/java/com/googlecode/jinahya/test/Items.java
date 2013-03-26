

package com.googlecode.jinahya.test;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Path("/items")
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(namespace = "http://jinahya.googlecode.com/test")
public class Items implements Serializable {


    private static final long serialVersionUID = 5775071328874654134L;


    private static class InstanceHolder {


        private static final Items INSTANCE = new Items();


        private InstanceHolder() {
            super();
        }


    }


    public static Items getInstance() {
        return InstanceHolder.INSTANCE;
    }


    private Items() {
        super();
    }


    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> readAll() {
        return new ArrayList<>(items.values());
    }


    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateAll(final List<Item> items) {

        synchronized (this.items) {
            this.items.clear();
            for (Item item : items) {
                this.items.put(item.getId(), item);
            }
        }
    }


    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(final Item item) {

        Item.putCreatedAtAndId(item);

        items.put(item.getId(), item);

        return Response.created(null).build();
    }


    // ------------------------------------------------------------- /items/{id}
    @Path("/item/{id: \\d+}")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Item read(@PathParam("id") final long id) {

        return items.get(id);
    }


    @Path("/item/{id: \\d+}")
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") final long id, final Item newItem) {

        synchronized (items) {
            final Item oldItem = items.get(id);
            if (oldItem == null) {
                return Response.status(Status.NOT_FOUND).build();
            }
            oldItem.name = newItem.name;
            oldItem.stock = newItem.stock;
            return Response.status(Status.NO_CONTENT).build();
        }
    }


    @Path("/item/{id: \\d+}")
    @DELETE
    public Response delete(@PathParam("id") final long id) {

        items.remove(id);

        return Response.status(Status.NO_CONTENT).build();
    }


    public void putItem(final Item item) {

        synchronized (items) {
            items.put(item.getId(), item);
        }
    }


    public void removeItem(final Long id) {

        synchronized (items) {
            items.remove(id);
        }
    }


    public Item getItem(final long id) {
        return items.get(id);
    }


    public Item setItem(final Item item) {
        return items.put(item.getId(), item);
    }


    @XmlElement(name = "item")
    public List<Item> getItemList() {
        return new ArrayList<>(getItems().values());
    }


    public Map<Long, Item> getItems() {

        if (items == null) {
            items = new TreeMap<>();
        }

        return items;
    }


    @XmlElement(name = "item")
    @XmlJavaTypeAdapter(ItemsAdpater.class)
    private Map<Long, Item> items;


}

