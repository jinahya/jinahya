/*
 * VBIFilterEvent.java
 *
 * Created on September 18, 2004, 1:33 PM
 */

package org.ocap.media;

/**
 * <p>
 * This class represents a VBI filter event. When a specific event happens, 
 * the {@link VBIFilterListener#filterUpdate} method is called with an event 
 * that has a proper event code to indicate the event. 
 * </p>
 *
 * @author  Shigeaki Watanabe (Panasonic)
 */
public class VBIFilterEvent {
    /**
     * Indicates that the first VBI data unit is available. This event code 
     * is issued only once after calling {@link VBIFilter#startFiltering} 
     * method even if multiple lines/fields is specified to the filter. 
     * Filtering continues. 
     */
    public static final int EVENT_CODE_FIRST_VBI_DATA_AVAILABLE = 1;

    /**
     * Indicates current filtering is terminated forcibly for any reason 
     * except other EVENT_CODE_ constants. 
     * E.g., a {@link VBIFilter#stopFiltering} is called. 
     */
    public static final int EVENT_CODE_FORCIBLE_TERMINATED = 2;

    /**
     * Indicates the current video for VBI data unit filtering has changed. 
     * Note that current filtering doesn't stop even if this event happens. 
     * An application may stop filtering and then restart to retrieve valid 
     * data units. 
     */
    public static final int EVENT_CODE_VIDEO_SOURCE_CHANGED = 3;

    /**
     * Indicates descrambling is unavailable for current video. 
     * Note that current filtering doesn't stop even if this event happens. 
     * Continues filtering until timeout. 
     */
    public static final int EVENT_CODE_FAILED_TO_DESCRAMBLE = 4;

    /**
     * Indicates a timeout (specified by {@link VBIFilter#setTimeOut}) 
     * occurred, i.e., this event code indicates no data unit is available. 
     */
    public static final int EVENT_CODE_TIMEOUT = 5;

    /**
     * Indicates an internal buffer is full. Filtering stops automatically. 
     */
    public static final int EVENT_CODE_BUFFER_FULL = 6;

    /**
     * Indicates that a specified time-period elapsed after receiving the 
     * first byte of a data unit. 
     */
    public static final int EVENT_CODE_TIME_NOTIFICATION = 7;

    /**
     * Indicates that the specified number of new data units are filtered 
     * and stored in a buffer cyclically. 
     */
    public static final int EVENT_CODE_UNITS_NOTIFICATION= 8;

    /**
     * Constructor of this class.
     */
    public VBIFilterEvent() {
    }

    
    /**
     * This method returns an instance of a class implementing VBIFilter 
     * that is the source of the event. 
     *
     * @return  instance of a class implementing VBIFilter that is the
     *              source of the event.
     */
    public Object getSource() {
        return null;
    }


    /**
     * This method returns application specific data that was specified 
     *              by VBIFilter.startFiltering() methods. 
     *
     * @return  an application specific data that was specified by 
     *              VBIFilter.startFiltering() methods. 
     */
    public Object getAppData() {
        return null;
    }


    /**
     * This method returns the specific event code that caused this event. 
     *
     * @return  an event code. One of the constants that has EVENT_CODE_ 
     *              prefix. 
     */
    public int getEventCode() {
        return -1;
    }
}

