

package com.googlecode.jinahya.test;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ImageFacade {


    private static class InstanceHolder {


        private static final ImageFacade INSTANCE = new ImageFacade();


        private InstanceHolder() {
            super();
        }


    }


    public static ImageFacade getInstance() {
        return InstanceHolder.INSTANCE;
    }


    private ImageFacade() {
        super();
    }


    public void insert(final String name, final byte[] image) {

        tuples.put(name, image);
    }


    public byte[] select(final String name) {

        return tuples.get(name);
    }


    public Collection<byte[]> selectAll() {
        
        return tuples.values();
    }


    public byte[] update(final String name, final byte[] image) {

        return tuples.put(name, image);
    }


    public byte[] delete(final String name) {

        return tuples.remove(name);
    }


    public void deleteAll() {

        tuples.clear();
    }


    private final Map<String, byte[]> tuples = new HashMap<>();


}

