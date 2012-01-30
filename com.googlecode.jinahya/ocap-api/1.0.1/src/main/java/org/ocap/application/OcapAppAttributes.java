//
// OcapAppAttributes.java
//
package org.ocap.application;

/**
 * <P>
 * This interface represents various information about an application 
 * registered in the AppsDatabase. 
 * This interface extends the org.dvb.application.AppAttributes in the 
 * points of following: 
 * <BR>
 *  Defining the OCAP Application types.
 * <BR>
 *  Adding the getControlFlag method to get the application_control_code 
 *    flag as signaled in an AIT or an XAIT.
 * </P><P> 
 * For applications which are signaled in the AIT or the XAIT, the 
 * mapping between the values returned by methods 
 * in this interface and the fields and descriptors of the AIT or the 
 * XAIT shall be as specified in this specification. 
 * Instance of the class implementing this interface are immutable. 
 * </P><P>
 * org.dvb.application.AppsDatabase MUST return an instance of OcapAppAttributes by the 
 * getAppAttributes methods.
 * </P>
 */
public interface OcapAppAttributes
    extends org.dvb.application.AppAttributes
{
    /**
     * The OCAP registered value for all OCAP-J applications. 
     */
    static final int OCAP_J = 1;

    
    /**
     * This represents the application control code "AUTOSTART" 
     * defined for the application_control_code in an AIT or a XAIT.
     *
     */
    static final int AUTOSTART = 1;

    /**
     * This represents the application control code "PRESENT" 
     * defined for the application_control_code in an AIT or a XAIT.
     */
    static final int PRESENT = 2;

    /**
     * This represents the application control code "DESTROY" 
     * defined for the application_control_code in an AIT or a XAIT.
     */
    static final int DESTROY = 3;

    /**
     * This represents the application control code "KILL" 
     * defined for the application_control_code in an AIT or a XAIT.
     */
    static final int KILL = 4;

    /**
     * This represents the application control code "PREFETCH" 
     * defined for the application_control_code in an AIT.
     */
    static final int PREFETCH = 5;

    /**
     * This represents the application control code "REMOTE" 
     * defined for the application_control_code in an AIT.
     */
    static final int REMOTE = 6;


    /**
     * This method returns the application_control_code of the 
     * application represented by this interface. 
     *
     * @return int The application_control_code of the application 
     *             represented by this interface. 
     */
    public int getApplicationControlCode();
    
    /**
     * This method returns the currently set storage priority for the application.
     *
     * @return int  The storage priority for a currently stored application
     *              or zero if the application is not stored.
     */
    public int getStoragePriority();
    
    /**
     * Indicates that a new version of the application is stored and this
     * version will replace the currently launched version when a new lifecycle
     * for this application starts.
     *
     * @return  True, if the currently launched application will be replaced
     *          by a new version when the next application lifecycle starts.
     *          False, if the application is not currently launched, or if the 
     *          application is not currently stored, or if the stored version
     *          of the application matches the version that is currently 
     *          launched.
     */
    
    public boolean hasNewVersion();
}
