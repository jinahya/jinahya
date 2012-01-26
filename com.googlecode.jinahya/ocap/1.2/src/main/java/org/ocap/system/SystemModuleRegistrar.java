package org.ocap.system;

/**
 * <p>
 * This class is used by an OCAP-J application to access a system 
 * module.  
 * </p><p>
 * <b>Private Host Application</b><br>
 *  
 * An OCAP-J application MAY register a SystemModuleHandler to act as   
 * a Private Host Application. 
 * If the SystemModuleHandler is registered successfully, the current 
 * Private Host Application that has a matching Private Host Application 
 * ID is terminated. 
 * </p><p>
 * <b>Man Machine Interface (MMI) Resource and Application Information 
 * Resource</b><br>
 * An OCAP-J application MAY register a SystemModuleHandler to access the 
 * Host’s MMI and Application Information resources. If the 
 * SystemModuleHandler is registered successfully, the Host's MMI Resource and
 * Application Information Resource MAY not terminate, but the implementation
 * SHALL pass all APDUs to the registered Handler, and the resident MMI dialog
 * SHALL be hidden. 
 * </p><p>
 * See also Section 20 <I>Baseline Functionality</I> for details. 
 * </p>
 *
 * @author Patrick Ladd
 * @author Brent Foust
 * @author Shigeaki Watanabe (modified by ECN 03.0531-4)
 *
 * @since 1.0
 */
public class SystemModuleRegistrar {
    /**
     * A constructor of this class.  An application must use the 
     * {@link SystemModuleRegistrar#getInstance} method to create an 
     * instance.
     */
    protected SystemModuleRegistrar () {
    }


    /**
     * This method returns a sole instance of the 
     * SystemModuleRegistrar class. 
     * The SystemModuleRegistrar instance is either a singleton for 
     * each OCAP application or a singleton for an entire OCAP 
     * implementation. 
     *
     * @return   a singleton SystemModuleRegistrar instance.
     *
     * @throws SecurityException if the caller does not have 
     *             MonitorAppPermission("podApplication").
     *
     */
    public static SystemModuleRegistrar getInstance() {
        return null;
    }


    /**
     * <p>
     * This method registers the specified SystemModuleHandler 
     * instance for the specified privateHostAppID. 
     * The Private Host Application is a logical entity defined in 
     * the CableCARD Interface 2.0 Specification. 
     * </p><p>
     * If there is a current Private Host Application that has a 
     * matching Private Host Application ID as the privateHostAppID 
     * parameter, it shall be unregistered first, i.e., corresponding 
     * SystemModule and SystemModuleHandler shall be unregistered. 
     * The {@link SystemModuleHandler#notifyUnregister} method of 
     * the SystemModuleHandler to be unregistered shall be called 
     * to notify its unregistration and give a chance to do a 
     * termination procedure. 
     * Note that the OCAP implementation shall call the 
     * notifyUnregister() method in a new thread to avoid blocking. 
     * </p><p>
     * After the SystemModuleHandler.notifyUnregister() method returns, 
     * the OCAP implementation selects an appropriate session number 
     * for sending and receiving APDU. Then the OCAP implementation shall
     * send the sas_connect_rqst APDU with the session automatically. 
     * After establishing the SAS connection, the OCAP implementation 
     * shall call the SystemModuleHandler.ready() method with a new 
     * SystemModule instance. 
     * <p></p>
     * After ready() method is called, all APDUs shall be handled 
     * by the registered OCAP-J application instead of the OCAP 
     * implementation. 
     * <p></p>
     * If a native resident Private Host Application is implemented on 
     * the Host, it shall has Java interface and be registered in a same 
     * manner as the OCAP-J application. Only when no SystemModuleHandler 
     * that has a matching Private Host Application ID is registered by 
     * the Monitor Application, the OCAP implementation shall register 
     * such a native resident Private Host Application. 
     * </p>
     *
     * @param handler  a SystemModuleHandler instance to receive an 
     *             APDU from the CableCARD device. 
     *             If the handler has already been registered to 
     *             the SystemModuleRegistrar, the method does nothing 
     *             and throws IllegalArgumentException. 
     *             Multiple call of this method with different 
     *             SystemModuleHandler instance registers all of them. 
     *
     * @param privateHostAppID  a Private Host Application ID for the 
     *             specified handler. This value is defined as an 
     *             unsigned 64-bit value in the OpenCable CableCARD 
     *             Interface specification. The specified byte array 
     *             shall be big endian. This value is specified as 
     *             the private_host_application_id field in the 
     *             sas_connect_rqst APDU. 
     *             If a SystemModuleHandler instance that has a matching 
     *             privateHostAppID has already been registered, it 
     *             shall be unregistered even if it is registered by 
     *             another OCAP-J application. 
     *
     * @throws SecurityException if the caller does not have 
     *             MonitorAppPermission("podApplication").
     *
     * @throws IllegalStateException if the CableCARD device is not ready. 
     *
     * @throws IllegalArgumentException if the specified handler 
     *             already exists, 
     *             or the specified parameter is out of range. 
     *
     */
    public void registerSASHandler(SystemModuleHandler handler, 
                                            byte[] privateHostAppID) {
    }



    /**
     * <p>
     * This method unregisters the specified SystemModuleHandler and 
     * the corresponding SystemModule instance, 
     * and revives an original resident Private Host Application. 
     * </p><p>
     * In this method call, 
     * the {@link SystemModuleHandler#notifyUnregister} method of the 
     * specified SystemModuleHandler shall be called to notify its 
     * unregistration and give a chance to do a termination procedure. 
     * The SystemModuleHandler and the corresponding SystemModule shall 
     * be removed from the SystemModuleRegistrar after returning of 
     * the notifyUnregister() method. 
     * Note that the OCAP implementation shall call the 
     * notifyUnregister() method in a new thread to avoid blocking. 
     * </p><p>
     * The OCAP implementation shall re-register a native resident 
     * Private Host Application automatically (i.e., revive it), when no 
     * SystemModuleHandler that has a matching Private Host Application 
     * ID is registered. 
     * </p>
     *
     * @param  handler  a SystemModuleHandler instance (the Private 
     *             Host Application) to be unregistered. 
     *             If the specified handler has not been registered to 
     *             the SystemModuleRegistrar, the method call does 
     *             nothing and throws IllegalArgumentException. 
     *
     * @throws  java.lang.SecurityException if the caller does not have 
     *             MonitorAppPermission("podApplication")
     *
     * @throws IllegalArgumentException if the specified handler has not 
     *             been registered. 
     *
     */
    public void unregisterSASHandler(SystemModuleHandler handler) {
    }



    /**
     * <p>
     * This method unregisters the SystemModuleHandler and 
     * the SystemModule instance corresponding to the specified 
     * privateHostAppID, 
     * and revives an original resident Private Host Application. 
     * </p><p>
     * In this method call, 
     * the {@link SystemModuleHandler#notifyUnregister} method 
     * corresponding to the specified privateHostAppID shall be 
     * called to notify its unregistration and give a chance to do 
     * a termination procedure. 
     * The SystemModuleHandler and the corresponding SystemModule shall 
     * be removed from the SystemModuleRegistrar after returning of 
     * the notifyUnregister() method. 
     * Note that the OCAP implementation shall call the 
     * notifyUnregister() method in a new thread to avoid blocking. 
     * </p><p>
     * The OCAP implementation shall re-register a native resident 
     * Private Host Application automatically (i.e., revive it), when no 
     * SystemModuleHandler that has a matching Private Host Application 
     * ID is registered. 
     * </p>
     *
     * @param privateHostAppID  a Private Host Application ID of the 
     *             Private Host Application (i.e., SystemModuleHandler) 
     *             to be unregistered. 
     *             This value is defined as an unsigned 64-bit value in 
     *             the OpenCable Host-POD Interface specification. 
     *             The specified byte array shall be a big endian. 
     *             If the specified privateHostAppID has not been 
     *             registered to the SystemModuleRegistrar, this method 
     *             does nothing and throws IllegalArgumentException. 
     *
     * @throws SecurityException if the caller does not have 
     *             MonitorAppPermission("podApplication").
     *
     * @throws IllegalArgumentException if the specified privateHostAppID 
     *             has not been registered. 
     *
     */
    public void unregisterSASHandler(byte[] privateHostAppID) {
    }


    /**
     * <p>
     * This method registers a SystemModuleHandler instance for 
     * accessing MMI and Application Information Resource. 
     * The OCAP implementation shall call the 
     * {@link org.ocap.system.SystemModuleHandler#ready} method 
     * with a new SystemModule instance to send an APDU to the 
     * CableCARD device. 
     * </p><p>
     * The resident MMI and Application Information Resources don't 
     * terminate but shall pass APDU to the SystemModuleHandler. 
     * The OCAP-J application can send and receive APDUs via the 
     * {@link org.ocap.system.SystemModule#sendAPDU} and the 
     * {@link org.ocap.system.SystemModuleHandler#receiveAPDU} method 
     * instead of the resident Resources. 
     * The sessions established by the resident MMI and Application 
     * Information Resource is used to send and receive the APDU. 
     * See also the description of the 
     * {@link org.ocap.system.SystemModule} 
     * and the {@link org.ocap.system.SystemModuleHandler}. 
     * </p><p>
     * After successful registration, the resident MMI Resource shall 
     * not represent the MMI dialog on the screen. 
     * The Host shall close all resident MMI dialog and finalize all 
     * transaction related to the MMI dialog. 
     * The Host shall send the close_mmi_cnf APDU to the CableCARD device to 
     * notify MMI dialog closing. 
     * If the unregisterMMIHandler() is called or the OCAP-J 
     * application that called this method changes its state to 
     * Destroyed, the resident MMI Resource can represent the MMI 
     * dialog again. 
     * </p>
     *
     * @param handler  a SystemModuleHandler instance to receive an 
     *             APDU from CableCARD device. Only one SystemModuleHandler 
     *             can be registered. 
     *             If the second SystemModuleHandler is to be 
     *             registered, this method throws 
     *             IllegalArgumentException. 
     *
     * @throws SecurityException if the caller does not have 
     *             MonitorAppPermission("podApplication").
     *
     * @throws IllegalStateException if the CableCARD device is not ready. 
     *
     * @throws IllegalArgumentException if the second 
     *             SystemModuleHandler is to be registered. 
     *
     */
    public void registerMMIHandler(SystemModuleHandler handler) {
    }


    /**
     * <p>
     * This method unregisters the SystemModuleHandler and SystemModule 
     * instance of the registered application accessing MMI and Application 
     * Information Resource and revives the resident MMI and Application 
     * Information Resource. 
     * </p><p>
     * In this method call, 
     * the {@link SystemModuleHandler#notifyUnregister} method of the 
     * SystemModuleHandler registered by the registerMMIHandler() method 
     * shall be called to notify its unregistration and give a chance 
     * to do a termination procedure. 
     * At least, all MMI dialog shall be closed and all of the 
     * transaction related to the MMI and Application Information 
     * Resource shall be terminated. The OCAP-J application shall send 
     * the close_mmi_cnf APDU to the CableCARD device to notify MMI dialog 
     * closing. 
     * The SystemModuleHandler and the corresponding SystemModule shall 
     * be removed from the SystemModuleRegistrar after returning of 
     * the notifyUnregister() method. I.e., after returning of the 
     * notifyUnregister() method, no APDU can be sent. 
     * Note that the OCAP implementation shall call the 
     * notifyUnregister() method in a new thread to avoid blocking. 
     * </p><p>
     * After this method is called, the resident MMI and Application 
     * Information Resource handles all APDUs and the resident MMI can 
     * represent the MMI dialog again. 
     * </p>
     *
     * @throws  java.lang.SecurityException if the caller does not have 
     *             MonitorAppPermission("podApplication")
     *
     */
    public void unregisterMMIHandler() {
    }
}
