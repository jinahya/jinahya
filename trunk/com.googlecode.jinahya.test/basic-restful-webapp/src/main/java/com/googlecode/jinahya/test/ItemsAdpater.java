

package com.googlecode.jinahya.test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ItemsAdpater extends XmlAdapter<List<Item>, Map<Long, Item>> {


    @Override
    public Map<Long, Item> unmarshal(final List<Item> value) throws Exception {

        final Map<Long, Item> bound = new HashMap<>();

        for (Item item : value) {
            bound.put(item.getId(), item);
        }

        return bound;
    }


    @Override
    public List<Item> marshal(final Map<Long, Item> bound) throws Exception {
        return new ArrayList<>(bound.values());
    }


}

