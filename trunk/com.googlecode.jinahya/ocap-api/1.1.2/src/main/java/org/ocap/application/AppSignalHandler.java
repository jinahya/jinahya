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
     * the version of the XAIT changes.
     *
     * @param newApps A list of instances of the OCAPAppAttributes class 
     *         associated with all the applications whose details 
     *         are listed in the new version of the XAIT.
     * @return the return value SHALL be ignored by the implementation.
     */
    public boolean notifyXAITUpdate(OcapAppAttributes[] newApps);
}