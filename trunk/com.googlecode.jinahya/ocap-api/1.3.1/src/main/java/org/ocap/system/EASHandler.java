/*
 * EASHandler.java
 *
 * Created on September 18, 2004, 10:31 AM
 */

package org.ocap.system;

/**
 * An OCAP-J application can register an EASHandler to the 
 * EASModuleRegistrar via the 
 * {@link EASModuleRegistrar#registerEASHandler} method. The 
 * {@link #notifyPrivateDescriptor} of this class is called to notify a 
 * location of an alternative audio for EAS representation. The OCAP-J 
 * application can play an audio specified by a private descriptor. 
 *
 * @see EASModuleRegistrar
 *
 * @author Shigeaki Watanabe
 */
public interface EASHandler {
    /**
     * <p>
     * This is a call back method to notify a private descriptor in the 
     * cable_emergency_alert message defined in [SCTE 18]. If the 
     * alert_priority=15 but no audio specified by [SCTE 18] is 
     * available, the OCAP implementation shall call this method. The OCAP-J 
     * application can get a location of an alternative audio specified 
     * in the private descriptor and play it according to [SCTE 18]. 
     * If the OCAP-J application doesn't support the private descriptor, the 
     * EAShandler.notifyPrivateDescriptor() method shall return false and 
     * the OCAP implementation can play detailed channel or proprietary audio. 
     * This method shall return immediately. The audio shall be played in 
     * a unique thread not to prevent an alert text representation. 
     * <p>
     *
     * @return true if the OCAP-J application can sound an audio of the 
     *         location of the specified descriptor. 
     *
     * @param descriptor  an array of bytes of a private descriptor in the 
     *         cable_emergency_alert message defined in [SCTE 18]. 
     *
     *
     */
    public boolean notifyPrivateDescriptor(byte[] descriptor);



    /**
     * This is a call back method to notify that the alert duration has 
     * finished. The OCAP-J application stops an audio specified by a 
     * private descriptor. The OCAP-J application shall not unregister 
     * the EASHandler until terminating an audio by this method. 
     */
    public void stopAudio();
}

