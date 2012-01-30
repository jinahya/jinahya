/*
 * VBIFilter.java
 *
 * Created on September 18, 2004, 1:31 PM
 */

package org.ocap.media;

import org.davic.mpeg.*;

/**
 * <p>
 * This class represents a VBI filter. 
 * VBIFilter instances are created by a VBIFilterGroup based on the OCAP 
 * resource management. 
 * Line numbers and a data format to be filtered are specified when the filter 
 * is created. 
 * </p><p>
 * The startFiltering() method starts filtering of the specified data format 
 * in the specified VBI line and stores data units in an internal buffer. 
 * When the first single data unit is filtered, a VBIFilterEvent with 
 * EVENT_CODE_FIRST_VBI_DATA_AVAILABLE is issued only once. The VBIFilter 
 * continues filtering. 
 * </p><p>
 * VBI filtering stops in the following cases: <br>
 * <ul>
 * <li> If a stopFiltering() method is called, a VBIFilterEvent with 
 * EVENT_CODE_FORCIBLE_TERMINATED notifies it. <br>
 * <li> If an internal buffer is full, a VBIFilterEvent with 
 * EVENT_CODE_BUFFER_FULL notifies it. <br>
 * <li> If a time out (specified by a timeout value in a 
 * {@link VBIFilter#setTimeOut}) occurs, a VBIFilterEvent with 
 * EVENT_CODE_TIMEOUT notifies it. <br>
 * </ul></p><p>
 * VBI filtering continues in the following cases: <br>
 * <ul>
 * <li> Timer notification by a VBIFilterEvent with 
 * EVENT_CODE_TIME_NOTIFICATION. <br>
 * <li> Cyclic notification for every specified number of data units by a 
 * VBIFilterEvent with EVENT_CODE_UNITS_NOTIFICATION. <br>
 * </ul></p><p>
 * See also the {@link VBIFilterGroup}. 
 * </p>
 *
 * @author  Shigeaki Watanabe (Panasonic)
 */
public interface VBIFilter {
    /**
     * Initiate filtering of VBI data for the specified line and the specified 
     * data format by a VBIFilterGroup. Filtering starts only after the 
     * {@link VBIFilterGroup#attach} method is called. 
     * 
     * @param appData  application specific data. This data is notified 
     *              to the application with a SectionFilterEvent. Null is 
     *              possible. 
     *
     */
    void startFiltering(Object appData);


    /**
     * Initiate filtering of VBI data for the specified line and the specified 
     * data format by a VBIFilterGroup. Only data unit(s) matching with a 
     * specified filter parameters are retrieved. Filtering starts only after 
     * the {@link VBIFilterGroup#attach} method is called. 
     *
     * @param appData  application specific data. This data is notified 
     *              to the application with a SectionFilterEvent. Null is 
     *              possible. 
     *
     * @param offset  defines a number of offset bytes that the specified 
     *              matching bits and masking bits are applied. Value 0 
     *              means no offset. Value 1 means that the matching/masking 
     *              bit is applied from the second byte. 
     *
     * @param posFilterDef  defines values to match for bits in a single data 
     *              unit. Only data unit that has matching bytes with this 
     *              posFilterDef are retrieved. 
     *              Maximum length is 36 bytes. 
     *
     * @param posFilterMask  defines which bits in the data unit are to be 
     *              compared against the posFilterDef bytes. 
     *              Matching calculation of negFilterDef and negFilterMask 
     *              obeys E.8.1 of DAVIC 1.4.1 Part 9. 
     *              Maximum length is 36 bytes. 
     *
     * @param negFilterDef  defines values to match for bits in a single data 
     *              unit. Only data unit that has matching bytes with this 
     *              negFilterDef are retrieved. 
     *              Maximum length is 36 bytes. 
     *
     * @param negFilterMask  defines which bits in the data unit are to be 
     *              compared against the negFilterDef bytes. 
     *              Matching calculation of negFilterDef and negFilterMask 
     *              obeys E.8.1 of DAVIC 1.4.1 Part 9. 
     *              Maximum length is 36 bytes. 
     */
    void startFiltering(Object appData, int offset, 
            byte posFilterDef[], byte posFilterMask[], 
            byte negFilterDef[], byte negFilterMask[]);


    /**
     * Stop current filtering of this VBI filter. Note that the VBIFilterGroup 
     * holding this VBI filter doesn't detach. 
     */
    void stopFiltering();


    /**
     * Set a timeout value. If no VBI data unit is retrieved after calling 
     * the startFiltering() method within the timeout value, the filtering 
     * stops automatically and SectionFilterEvent with EVENT_CODE_TIMEOUT 
     * notifies a timeout occurred. 
     * 
     * @param milliseconds  a timeout value in milli seconds. A default 
     *              value is -1 that indicates infinite. 
     */
    void setTimeOut(long milliseconds);


    /**
     * Set a notification time. By setting a notification time, the 
     * OCAP implementation notifies a VBIFilterEvent with 
     * EVENT_CODE_TIME_NOTIFICATION when the specified time-period has 
     * elapsed after receiving the first byte of the data unit. The event 
     * shall be sent even if the data received does not form a complete 
     * data unit. The event is sent only once. 
     * The filter continues filtering after sending the event. 
     * 
     * @param milliseconds  a time-period value in milli seconds. A default 
     *              value is -1 that indicates infinite. 
     */
    void setNotificationByTime(long milliseconds);


    /**
     * Set the number of data units to receive a cyclic notification. 
     * By setting the number of data units, the OCAP implementation notifies 
     * a VBIFilterEvent with EVENT_CODE_UNITS_NOTIFICATION cyclically 
     * every time when the specified number of new data units are filtered 
     * and stored in a buffer. 
     * The filter continues filtering after sending the event. 
     * 
     * @param numberOfDataUnits  the number of data units to be notified. 
     *              A default value is 0 that indicates no notification. 
     *              Note that if a small number of data units is specified, 
     *              the notification may be delayed and affect the host 
     *              performance. For example, if 1 is specified for UNKNOWN 
     *              data unit that comes every field (i.e., 1/60 seconds), 
     *              the host has to notify every 1/60 seconds and makes an 
     *              over load. 
     *
     * @throws IllegalArgumentException  if the numberOfDataUnit is larger 
     *              than the bufferSize specified by a 
     *              {@link VBIFilterGroup#newVBIFilter} method. 
     */
    void setNotificationByDataUnits(int numberOfDataUnits);


    /**
     * Add a new VBIFilterListener instance to this VBI filter. 
     * If the same instance that exists currently is specified, this method 
     * does nothing and no exception is thrown. 
     *
     * @param listener  a VBIFilterListener instance to be notified 
     *              a VBI filtering events. 
     */
    void addVBIFilterListener(VBIFilterListener listener);


    /**
     * Remove an existing VBIFilterListener instance from this VBI filter. 
     * If the specified instance has not been added, this method does nothing 
     * and no exception is thrown. 
     */
    void removeVBIFilterListener(VBIFilterListener listener);


    /**
     * This method returns multiple VBI data unit bytes. The data unit 
     * format is defined in a description of a {@link VBIFilter} interface. 
     * The returned bytes is a simple concatenated VBI data at the moment. 
     * Note that the return value is not aligned by a complete VBI data unit, 
     * i.e., an incomplete data unit may return. 
     * When this method is called, the internal buffer is cleared once, i.e., 
     * the next call returns the next byte of retrieved VBI data. 
     *
     * @return  a concatenated VBI data of the form of specified by a 
     *             {@link VBIFilterGroup#newVBIFilter} method. 
     */
    public byte[] getVBIData();


    /**
     * Clear an internal buffer to store retrieved VBI data. An application 
     * shall call this method before the data buffer is full. 
     */
     public void clearBuffer();
}

