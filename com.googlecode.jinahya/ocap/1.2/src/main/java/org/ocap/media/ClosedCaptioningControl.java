// ClosedCaptioningControl.java
//
// Created on November 26, 2001, 2:01 PM

package org.ocap.media;

import javax.media.Control;

/**
 * <p>
 * This interface is used to turn closed-captioning in a running JMF player 
 * on and off and to select a captioning service (C1 to C4 and T1 to T4) to 
 * be represented. Instance of the ClosedCaptioningControl interface shall 
 * be obtained via a {@link javax.media.Player#getControl} and a 
 * {@link javax.media.Player#getControls} method by all applications. But 
 * MonitorAppPermission(“handler.closedCaptioning”) is necessary to call 
 * methods in this interface. 
 * </p><p>
 * The captioning text is represented according to preferred attribute values 
 * set by a {@link ClosedCaptioningAttribute} class.  
 *
 * @author  Mark S. Millard (Vidiom Systems)
 * @author  Shigeaki Watanabe (Panasonic): modify
 * @version 1.0
 */
public interface ClosedCaptioningControl extends javax.media.Control
{
    /**
     * Indicates an analog closed-captioning service CC1. 
     */
    public final static int CC_ANALOG_SERVICE_CC1 = 1000;

    /**
     * Indicates an analog closed-captioning service CC2. 
     */
    public final static int CC_ANALOG_SERVICE_CC2 = 1001;

    /**
     * Indicates an analog closed-captioning service CC3. 
     */
    public final static int CC_ANALOG_SERVICE_CC3 = 1002;

    /**
     * Indicates an analog closed-captioning service CC4. 
     */
    public final static int CC_ANALOG_SERVICE_CC4 = 1003;

    /**
     * Indicates an analog closed-captioning service T1. 
     */
    public final static int CC_ANALOG_SERVICE_T1 = 1004;

    /**
     * Indicates an analog closed-captioning service T2. 
     */
    public final static int CC_ANALOG_SERVICE_T2 = 1005;

    /**
     * Indicates an analog closed-captioning service T3. 
     */
    public final static int CC_ANALOG_SERVICE_T3 = 1006;

    /**
     * Indicates an analog closed-captioning service T4. 
     */
    public final static int CC_ANALOG_SERVICE_T4 = 1007;

    /**
     * Indicates no closed-captioning service.
     */
    public final static int CC_NO_SERVICE = -1;

    /**
     * Indicates turn digital/analog closed-captioning on. 
     */
    public final static int CC_TURN_OFF = 0;

    /**
     * Indicates turn digital/analog closed-captioning off. 
     */
    public final static int CC_TURN_ON = 1;

    /**
     * Indicates turn digital/analog closed-captioning on only when muting 
     * an audio. 
     */
    public final static int CC_TURN_ON_MUTE = 2;



    /**
     * Turn closed-captioning of a JMF Player that is controlled by a 
     * ClosedCaptioningControl instance on or off. 
     * Note that only one closed-captioning decoding may be supported on the 
     * OCAP implementation at once. This method may turn off closed-captioning 
     * of another JMF Player automatically. Such a automatic turn off is 
     * notified by a {@link ClosedCaptioningEvent} event. 
     *
     * @param turnOn An integer value specifying whether to turn 
     *               closed-captioning on, off or "on mute". 
     *               CC_TURN_ON, to turn closed-captioning represented by a 
     *               {@link javax.media.Player} instance that is controlled 
     *               by the ClosedCaptioningControl instance on. 
     *               CC_TURN_OFF, to turn it off. 
     *               CC_TURN_ON_MUTE, to turn it on only when muting an audio. 
     *
     * @throws SecurityException  if the caller doesn't have 
     *               MonitorAppPermission("handler.closedCaptioning"). 
     */
    void setClosedCaptioningState(int turnOn);


    /**
     * Get the current state of closed-captioning of a JMF Player that is 
     * controlled by a ClosedCaptioningControl instance. Note that this method 
     * returns a current status set by the setClosedCaptioningState() method 
     * or automatic turning off brought by the method call. This method 
     * doesn't care if an actual caption channel packet in the MPEG video 
     * header or line 21 data exist, or if an Caption Service Descriptor has 
     * changed. 
     *
     * @return An integer value representing a closed captioning state.
     *               One of CC TURN ON, CC TURN OFF, and CC TURN ON MUTE.
     *
     * @throws SecurityException  if the caller doesn't have 
     *               MonitorAppPermission("handler.closedCaptioning"). 
     */
    int getClosedCaptioningState();


     /**
     * 
     * <p>
     * This method sets a new closed-captioning service number to be 
     * represented by a JMF Player that is controlled by a 
     * ClosedCaptioningControl instance. 
     * </p><p>
     * Captioning text will be rendered when captioning is turned on and 
     * corresponding captioning text data is transmitted. 
     * When an analog video is played on the JMF player, captioning service 
     * of an analogServiceNumber in VBI signal defined by EIA-608-B analog 
     * closed captioning will be rendered. 
     * When a digital video is played on the JMF player, captioning service 
     * of a digitalServiceNumber in MPEG picture header defined by EIA-708-B 
     * digital closed captioning will be rendered. If MPEG picture header 
     * doesn’t contain a captioning service of the digitalServiceNumber, a
     * captioning service of analogServiceNumber in the MPEG picture header
     * may be used instead. 
     * </p><p>
     * The previously represented caption service shall be disappeared, 
     * when a new service is set. 
     * This method doesn't check if the specified closed-captioning service 
     * is transmitted with the current video actually. 
     * </p><p>
     *
     * @param analogServiceNumber An integer representing an analog 
     * closed-captioning service number. 
     * The serviceNumber value shall be a return value of 
     * the {@link #getSupportedClosedCaptioningServiceNumber} 
     * method and shall be an analog captioning service, 
     * i.e., have a CC_ANALOG_SERVICE prefix. 
     * A value of CC_NO_SERVICE if no decoding of analog captioning is necessary. 
     *
     * @param digitalServiceNumber An integer representing a digital 
     * closed-captioning service number. 
     * The serviceNumber value shall be a return value of 
     * the {@link #getSupportedClosedCaptioningServiceNumber} 
     * method and shall be an digital captioning service. 
     * A value of CC_NO_SERVICE if no decoding of digital captioning is necessary. 
     *
     * @throws IllegalArgumentException if the serviceNumber is not a return 
     * value of the getSupportedClosedCaptioningServiceNumber() 
     * method. 
     *
     * @throws SecurityException if the caller doesn't have 
     * MonitorAppPermission("handler.closedCaptioning"). 
     */

    void setClosedCaptioningServiceNumber(int analogServiceNumber, 
                                          int digitalServiceNumber);


    /**
    * 
    * This method returns a current closed-captioning service for a JMF Player 
    * that is controlled by a ClosedCaptioningControl instance. This method 
    * doesn't care if the specified closed-captioning service is transmitted 
    * in the current video. 
    *
    * @return An array of integers representing a closed-captioning service. 
    *              The first item shall be an analog captioning service number 
    *              and the second item shall be a digital captioning service 
    *              number. 
    *              The array length shall be 2. 
    *              A value of CC_NO_SERVICE indicates no captioning service 
    *              number is specified for the captioning type. 
    *
    * @throws SecurityException if the caller doesn't have 
    *              MonitorAppPermission("handler.closedCaptioning"). 
    */
    int[] getClosedCaptioningServiceNumber();


    /**
     * This method returns closed-captioning service numbers that are 
     * supported by a JMF Player that is controlled by a ClosedCaptioningControl 
     * instance. Only service numbers returned by this method can be specified 
     * to the {@link #setClosedCaptioningServiceNumber} method. 
     * Note that this method doesn't check if the returned closed-captioning 
     * service is transmitted with the current video actually, i.e., this 
     * method returns just a capability of the host device. 
     *
     * @return  An array of closed-captioning service numbers that are 
     *               supported by a JMF Player that is controlled by a 
     *               ClosedCaptioningControl instance. 
     *               If the service is analog captioning of EIA-608-B, 
     *               the returned service number value shall be one of 
     *               constants that has a prefix of CC_ANALOG_SERVICE_. 
     *               If the service is digital captioning of EIA-708-B, 
     *               the returned service number value shall be an actual 
     *               service number (1 to 63). 
     *
     * @throws SecurityException  if the caller doesn't have 
     *               MonitorAppPermission("handler.closedCaptioning"). 
     */
    int[] getSupportedClosedCaptioningServiceNumber();


    /**
     * Add a listener to notify a closed-captioning state change. Multiple 
     * calls with same ccListener instance is simply ignored with throwing 
     * no exception. 
     *
     * @param ccListener a {@link ClosedCaptioningListener} instance to notify 
     *               a change of the closed-captioning state. 
     *
     * @throws SecurityException  if the caller doesn't have 
     *               MonitorAppPermission("handler.closedCaptioning"). 
     */
    void addClosedCaptioningListener(ClosedCaptioningListener ccListener);


    /**
     * Remove the given ClosedCaptioningListener. This method does nothing 
     * if the specified ccListener is null, not previously added or already 
     * removed. 
     *
     * @param ccListener a {@link ClosedCaptioningListener} instance to be 
     *               removed. 
     *
     * @throws SecurityException  if the caller doesn't have 
     *               MonitorAppPermission("handler.closedCaptioning"). 
     */
    void removeClosedCaptioningListener(ClosedCaptioningListener ccListener);
}
