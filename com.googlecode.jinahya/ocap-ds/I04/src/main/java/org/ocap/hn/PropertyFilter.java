package org.ocap.hn;

import java.util.Properties;

/**
 * The filter for (key,value) pair filtering mechanism. If a device or a 
 * NetModule has same value on all of the specified keys, it is 
 * regarded as a match.
 */
public class PropertyFilter
{

    /**
     * Constructs a PropertyFilter object.
     * 
     * @param prop	Initial properties for this Property filter
     */
    public PropertyFilter(Properties prop) 
    {
    }

    /**
     * Adds a (key,value) pair to the filter. If the key is already in the list,
     * no action is taken.
     * 
     * @param key New key which will be used for filtering.
     * @param value Value for the new key.
     */
    public void addProperty(String key, String value)
    {
    }

    /**
     * Checks whether a key is in the list.
     * 
     * @param key Key to be checked against.
     * 
     * @return True if key is in the list; otherwise returns false.
     */
    public boolean contains(String key)
    {
        return false;
    }

    /**
     * Checks whether an element is accepted by this filter, the element
     * must be either <code>NetModule</code> or <code>Device</code>.
     * If a NetModule/Device's properties share the same value as all 
     * properties from this filter, it is accepted and true is returned;
     * otherwise, false is returned. 
     * 
     * @param element Element to be checked against.
     * 
     * @return True if the element is accepted by the PropertyFilter,
     *      otherwise returns false.
     */
    public boolean accept(Object element)
    {
        return false;
    }

    /**
     * Remove a key from the filter, if the key is not in the property 
     * list, no action is taken.
     * 
     * @param key Key to be removed from list.
     */
    public void removeKey(String key)
    {
    }

    /**
     * Remove keys from the filter, if a key is not in the property list, 
     * it is disregarded; while others are processed as normal.
     * 
     * @param keys Keys to be removed from the list.
     */
    public void removeKeys(String[] keys)
    {
    }
}