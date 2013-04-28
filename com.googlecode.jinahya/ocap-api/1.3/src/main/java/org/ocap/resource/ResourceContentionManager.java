// ResourceContentionManager.java

package org.ocap.resource;

/**
 * <P>
 * This class manages a means of resolving a resource contention. 
 * </P><P>
 * An application which has a MonitorAppPermission 
 * ("handler.resource") may have a subclass of the AppsDatabaseFilter 
 * class or a class implementing the ResourceContentionHandler interface, 
 * and may set an instance of them in the ResourceContentionManager. 
 * The concrete class of the AppsDatabaseFilter class identifies an 
 * application that is not allowed absolutely to reserve the resource. 
 * The class implementing the ResourceContentionHandler interface resolves 
 * a resource contention after a resource negotiation. 
 * </P><P>
 * See the section 19 Resource Management in this specification for details. 
 *</P>
 */
public class ResourceContentionManager
{
    /**
     * A constructor of this class. An application must use the 
     * {@link ResourceContentionManager#getInstance} 
     * method to create an instance. 
     */
    protected ResourceContentionManager() {
    }

    /**
     * This method returns an instance of the ResourceContentionManager class. 
     * It is not required to be a singleton manner. 
     * 
     * @return	The ResourceContentionManager instance. 
     */
    public static ResourceContentionManager getInstance() {
        return null;
    }

    /**
     * This method sets an instance of a concrete class that extends 
     * AppsDatabaseFilter. 
     * The AppsDatabaseFilter.accept(AppID) method returns true if an 
     * application specified by the AppID is allowed to reserve the 
     * resource, and returns false if the application is not allowed to 
     * reserve it. 
     * At most, only one AppsDatabaseFilter is set for each type of resource. 
     * Multiple calls of this method replace the previous instance 
     * by a new one. 
     * If an AppsDatabaseFilter has not been associated with the 
     * resource, then any application is allowed to reserve the resource.
     * By default, no AppsDatabaseFilter is set, i.e., all 
     * applications are allowed to reserve the resource. 
     * 
     * @param	filter the AppsDatabaseFilter to deny the application reserving 
     *         the specified resource. If null is set, the AppsDatabaseFilter 
     *         for the specified resource will be removed. 
     *
     * @param	resourceProxy A full path class name of a concrete class 
     *         of the org.davic.resources.ResourceProxy interface. It 
     *         specifies a resource type that the specified AppsDatabaseFilter 
     *         filters. 
     *         For example, "org.davic.net.tuning.NetworkInterfaceController".
     * 
     * @throws	SecurityException if the caller does not have 
     *         MonitorAppPermission("handler.resource"). 
     */
    public void setResourceFilter(
            org.dvb.application.AppsDatabaseFilter filter, 
            java.lang.String resourceProxy)  {
    }

    /**
     * This method sets the specified ResourceContentionHandler that decides 
     * which application shall be denied reserving a scarce resource. At most 
     * only one instance of ResourceContentionHandler can be set. Multiple 
     * calls of this method replace the previous instance 
     * by a new one. By default, no ResourceContentionHandler is set, i.e. the 
     * {@link ResourceContentionHandler#resolveResourceContention} method is 
     * not called. 
     * 
     * @param	handler the ResourceContentionHandler to be set. 
     *        If null is set, the ResourceContentionHandler instance will be removed 
     *        and the {@link ResourceContentionHandler#resolveResourceContention} 
     *        method will not be called. 
     * 
     * @throws	SecurityException if the caller does not have 
     *        MonitorAppPermission("handler.resource"). 
     */
    public void setResourceContentionHandler(ResourceContentionHandler handler) {
    }
    /**
     * Gets the warning period set by the setWarningPeriod method.
     *
     * @return The warning period in milli-seconds.
     */
    public int getWarningPeriod()
    {
        return 0;
    }

    /**
     * Sets the warning period used by the implementation to determine when to
     * call the resourceContentionWarning method in a registered
     * {@link ResourceContentionHandler}.  If the parameter is zero
     * the implementation SHALL NOT call the resourceContentionWarning method.
     * If the parameter is non-zero the implementation SHALL call the
     * resourceContentionWarning method if it has enough information to do so.
     * Setting the warningPeriod to non-zero MAY NOT cause the
     * resourceContentionWarning method to be called for two reasons, 1) the
     * implementation cannot determine when contention is going to happen,
     * and 2) the warning period is longer than the duration to the contention.
     *
     * @param warningPeriod New warning period in milli-seconds.  If the value
     *      is smaller than the minimum clock resolution supported by the
     *      implementation, the implementation MAY round it up to the minimum.
     *
     * @throws IllegalArgumentException if the parameter is negative.
     * @throws SecurityException if the caller does not have
     *      MonitorAppPermission("handler.resource").
     */
    public void setWarningPeriod(int warningPeriod)
    {
    }

}
