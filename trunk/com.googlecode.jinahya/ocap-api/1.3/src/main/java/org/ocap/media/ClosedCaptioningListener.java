/*
 * ClosedCaptioningListener.java
 *
 * Created on November 26, 2001, 3:26 PM
 */

package org.ocap.media;

/**
 * <p>
 * This is a listener interface to receive a notification when the state
 * of closed-captioning has changed or a new closed-captioning service (C1 to 
 * C4, T1 to T4) is selected. 
 * </p><p>
 * A listener instance is added to a ClosedCaptioningControl instance via the 
 * {@link org.ocap.media.ClosedCaptioningControl#addClosedCaptioningListener}
 * method. 
 * </p><p>
 *
 * @author  Mark S. Millard (Vidiom Systems)
 * @author  Shigeaki Watanabe (Panasonic): modify
 * @version 1.0
 */
public interface ClosedCaptioningListener extends java.util.EventListener
{
    /**
     * This method shall be called when a closed-captioning state or a 
     * captioning service of the JMF Player that is controlled by the 
     * ClosedCaptioningControl instance has changed. 
     * Note that this method is not called when an existence of an actual 
     * caption channel packet in the MPEG video header or line 21 data 
     * has changed. This method is not called when an Caption Service 
     * Descriptor has changed. 
     *
     * @param event The closed-captioning status event. 
     *
     * @see ClosedCaptioningEvent 
     */
    void ccStatusChanged(ClosedCaptioningEvent event); 
}
