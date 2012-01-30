// AppSignalHandler.java

package org.ocap.application;

/**
 * <P>
 * This interface represents a handler that can be registered in order to
 * receive a notification whenever the XAIT version changes.
 * An application which has a MonitorAppPermission("registrar") 
 * may have a class implementing this interface, and may set it as
 * the AppSignalHandler using the AppManagerProxy. 
 * </P>
 */
public interface AppSignalHandler
{
    /**
     * Notifies the registered handler that the version of the
     * signaled XAIT has changed.  When an AppSignalHandler is
     * registered the implementation SHALL call this method whenever
     * the version of the XAIT changes or when an XAIT is received and a
     * previous call to this method for the same version of the XAIT returned
     * false.
     * <p>
     * When the return value is true the implementation SHALL process the
     * corresponding XAIT as required by the OCAP specification.  When the 
     * return value is false the implementation SHALL ignore the corresponding 
     * XAIT and treat it as if it were never received. Subsequent XAIT receipt with
     * the same version as the ignored table is treated as a new version and 
     * notified using this method.
     *
     * @param newApps A list of instances of the OCAPAppAttributes class 
     *       associated with all the applications whose details 
     *       are listed in the new version of the XAIT.
     * @return When true the XAIT is processed, otherwise the XAIT is ignored by 
     *       the implementation.
     */
    public boolean notifyXAITUpdate(OcapAppAttributes[] newApps);
}
