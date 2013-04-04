

package com.googlecode.jinahya.test;


import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ItemFacade {


    private static final long serialVersionUID = 5775071328874654134L;


    private static class InstanceHolder {


        private static final ItemFacade INSTANCE = new ItemFacade();


        private InstanceHolder() {
            super();
        }


    }


    public static ItemFacade getInstance() {
        return InstanceHolder.INSTANCE;
    }


    private ItemFacade() {
        super();
    }


    public void insert(final Item... items) {

        for (Item item : items) {
            Item.putCreatedAtAndId(item);
            tuples.put(item.getId(), item);
        }
    }


    public Item select(final long id) {

        return tuples.get(id);
    }


    public Collection<Item> selectAll() {
        return tuples.values();
    }


    public boolean update(final long id, final Item newItem) {

        if (!tuples.containsKey(id)) {
            return false;
        }

        final Item oldItem = tuples.get(id);
        if (oldItem == null) {
            return false;
        }

        oldItem.updatedAt = new Date();
        oldItem.name = newItem.name;
        oldItem.stock = newItem.stock;

        return true;
    }


    public Item delete(final long id) {
        return tuples.remove(id);
    }


    public void deleteAll() {
        tuples.clear();
    }


    private final Map<Long, Item> tuples = new HashMap<>();


}

