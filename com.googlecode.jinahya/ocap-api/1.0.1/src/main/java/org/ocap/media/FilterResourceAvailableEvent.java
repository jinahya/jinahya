/*
 * FilterResourceAvailableEvent.java
 *
 * Created on September 18, 2004, 1:28 PM
 */

package org.ocap.media;

import org.davic.resources.*;

/**
 * <p>
 * This event notifies an application that a VBIFilterGroup released 
 * VBIFilters, i.e.,  another application may have an opportunity to reserve 
 * new VBIFilters. This event is just a hint of resource status, so that 
 * the filters may not be available when an application calls a 
 * {@link VBIFilterGroup#attach} method actually. 
 * </p>
 *
 * @author  Shigeaki Watanabe (Panasonic)
 */
public class FilterResourceAvailableEvent extends ResourceStatusEvent {
    /**
     * A constructor of this class. 
     *
     * @param f  Instance of a VBIFilterGroup that is the source of this event. 
     */
    public FilterResourceAvailableEvent(VBIFilterGroup f) {
        super(f);
    }


    /**
     * This method returns an instance of a class implementing VBIFilterGroup 
     * that is the source of the event. 
     *
     * @return  instance of a class implementing VBIFilterGroup that is the
     *              source of the event
     */
    public Object getSource() {
        return null;
    }
}
