/*
 * ForcedDisconnectedEvent.java
 *
 * Created on September 18, 2004, 1:30 PM
 */

package org.ocap.media;

import org.davic.resources.*;

/**
 * <p>
 * This event indicates a VBIFilterGroup is detached from a ServiceContext 
 * for any reason. A {@link ResourceClient#notifyRelease} is also called 
 * to inform all filters held by the VBIFilterGroup have been forcibly 
 * released. 
 * </p>
 *
 * @author  Shigeaki Watanabe (Panasonic)
 */
public class ForcedDisconnectedEvent extends ResourceStatusEvent {
    /**
     * A constructor of this class. 
     *
     * @param f  Instance of a VBIFilterGroup that is the source of this event. 
     */
    public ForcedDisconnectedEvent(VBIFilterGroup f) {
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
