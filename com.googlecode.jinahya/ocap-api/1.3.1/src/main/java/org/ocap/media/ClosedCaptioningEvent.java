/*
 * ClosedCaptioningEvent.java
 *
 * Created on November 26, 2001, 2:34 PM
 */

package org.ocap.media;

/**
 * This class is an event to notify a change of a closed-captioning state. 
 *
 * @author  Mark S. Millard (Vidiom Systems)
 * @author  Shigeaki Watanabe (Panasonic): modify
 */
public class ClosedCaptioningEvent extends java.util.EventObject
{
    /**
     * This event indicates a current closed-captioning state is turning on. 
     */
    public static final int EVENTID_CLOSED_CAPTIONING_ON = 0;

    /**
     * This event indicates a current closed-captioning state is turning off. 
     */
    public static final int EVENTID_CLOSED_CAPTIONING_OFF = 1;

    /**
     * This event indicates a current closed-captioning state is "on mute". 
     */
    public static final int EVENTID_CLOSED_CAPTIONING_ON_MUTE = 2;

    /**
     * This event indicates a new closed-captioning service (C1 to C4, T1 to 
     * T4) is selected. 
     */
    public static final int EVENTID_CLOSED_CAPTIONING_SELECT_NEW_SERVICE = 3;


    /**
     * Construct a new ClosedCaptioningEvent with the specified event ID.
     *
     * @param source The object where the event originated. 
     * 
     * @param id The event ID. One of the following values: 
     * EVENTID_CLOSED_CAPTIONING_ON, EVENTID_CLOSED_CAPTIONING_OFF, 
     * EVENTID_CLOSED_CAPTIONING_ON_MUTE 
     * and EVENTID_CLOSED_CAPTIONING_SELECT_NEW_SERVICE. 
     */
    public ClosedCaptioningEvent(Object source, int id) {
        super(source);
    }


    /**
     * Get the event ID associated with this ClosedCaptioningEvent.
     *
     * @return  An integer representation of the event ID. 
     */
    public int getEventID() {
        return 0 ;
    }
}
