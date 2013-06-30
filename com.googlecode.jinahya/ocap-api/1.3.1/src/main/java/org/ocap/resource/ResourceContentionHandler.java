// ResourceContentionHandler.java

package org.ocap.resource;


/**
 * A class implementing this interface decides which application shall be 
 * allowed to reserve a resource. 
 * <P>
 * An application which has a MonitorAppPermission("handler.resource") 
 * may have a class implementing this interface, and may set an instance of 
 * it in the ResourceContentionManager. 
 * The {@link ResourceContentionHandler#resolveResourceContention} method decides 
 * the how to resolve resource conflicts between the new request and existing resource
 * allocations. 
 * See the {@link ResourceContentionManager} for the details. 
 */
public interface ResourceContentionHandler
{

    /**
     * This method notifies the ResourceContentionHandler that one to many
     * resource contentions have occurred between one or more applications
     * and system modules, except the Emergency Alert System (EAS) module.
     * EAS system module resource requests SHALL be given the highest
     * priority by the implementation and resource requests by this module
     * SHALL not be reported to the ResourceContentionHandler.
     * In the case of one application, the same application is conflicting
     * with itself and a registered ResourceContentionHandler SHALL be
     * notified in this case.
     * <p>
     * This method notifies the ResourceContentionHandler that one to many
     * resource contentions have occurred between two or more applications.
     * Each entry in the <i>currentReservations</i> indicates a set of resources 
     * reserved by an application for a single activity such as a resource usage 
     * by a single service context.  There may be multiple 
     * entries in this list from a single application.  An entry may correspond 
     * to a current resource usage or resource reservations for a future activity.
     * <p>
     * A prioritized array of {@link ResourceUsage} instances is returned.  
     * The array is in priority order from highest to lowest indicating the 
     * priority order to be followed by the implementation while resolving the 
     * conflicts.  When this method returns the implementation will iterate 
     * through each entry in the array in the order of priority, awarding resources 
     * as required by the activity represented by the resourceUsage. The 
     * ResourceContentionHandler may use information such as Application Priority
     * to prioritize the array of ResourceUsages returned. When the value
     * returned is not null the ResourceContentionHandler MAY return an array
     * containing all of the <code>ResourceUsage</code> objects passed to it,
     * or it MAY return a subset of those objects.
     * </p>
     *
     * @param   newRequest The resource usage object containing the attributes of
     *     the resource request(s).
     *
     * @param   currentReservations The set of resource usage objects that describe
     *     current resource reservations which are in conflict with the newRequest.  
     *     A <code>ResourceUsage</code> associated
     *     with a current reservation MAY belong to an application that has been
     *     destroyed.  Use of the <code>AppID</code> contained within such a 
     *     <code>ResourceUsage</code>
     *     with any of the methods in <code>org.dvb.application.AppsDatabase</code>
     *     MAY cause a failure status to be returned.
     *
     * @return  A prioritized array of resource usage objects. The first entry has the highest 
     *     priority. This function returns null if the contention handler wants the 
     *     implementation to resolve the conflict.
     */
    public ResourceUsage [] resolveResourceContention(
            ResourceUsage newRequest,
            ResourceUsage [] currentReservations);

    /**
     * Warns the resource contention handler of an impending contention with
     * a presenting ServiceContext (e.g., scheduled recording as defined by
     * the OCAP DVR specification).  If a ResourceContentionHandler is
     * registered the implementation SHALL call this method as defined by
     * the {@link ResourceContentionManager#setWarningPeriod} method.
     *
     * @param	newRequest The resource usage object containing the attributes of
     *     the resource[s] request.
     *
     * @param	currentReservations The resource usage objects currently owned by applications
     *     which are in conflict with the newRequest.  A ResourceUsage associated
     *     with a current reservation may belong to an application that has been
     *     destroyed.  Use of the AppID contained within such a ResourceUsage
     *     with any of the methods in <code>org.dvb.application.AppsDatabase</code>
     *     MAY cause a failure status to be returned.
     */
    public void resourceContentionWarning(ResourceUsage newRequest,
                                          ResourceUsage [] currentReservations);
}
