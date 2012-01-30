// AppSignalHandler.java

package org.ocap.application;

/**
 * <P>
 * A class implementing this interface can interrupt updating 
 * application information in the AppsDatabase caused by XAIT acquisition. 
 * </P><P>
 * An application which has a MonitorAppPermission("registrar") 
 * may have a class implementing this interface, and may set an instance of 
 * it in the AppManagerProxy. 
 * </P><P>
 * Whenever a new version of the XAIT is received by the OCAP 
 * implementation, the {@link AppSignalHandler#notifyXAITUpdate} 
 * method shall be called before updating application information in the 
 * AppsDatabase. 
 * If the method returns true or if no AppSignalHandler is set, the 
 * OCAP implementation shall update application information using the new
 * version of the XAIT and update the stored applications as defined by 
 * the storage priorities in new XAIT. Otherwise the OCAP implementation  
 * shall ignore the new XAIT. 
 * </P>
 */
public interface AppSignalHandler
{
    /**
     * This is a callback method to inquire of an application which has 
     * MonitorAppPermission("registrar") whether the AppDatabase 
     * shall update current application information using a new version 
     * of the XAIT or not. 
     *
     * @param newApps A list of instances of the OCAPAppAttributes class 
     *         associated with all the applications whose details 
     *         are listed in the new version of the XAIT
     *         that is received by the OCAP implementation. 
     *
     * @return true if the AppsDatabase shall update entire application 
     *         information using the new version of XAIT immediately. 
     *         false if the AppDatabase shall keep application information 
     *         as it is. 
     */
    public boolean notifyXAITUpdate(OcapAppAttributes[] newApps);
}