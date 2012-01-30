/*
 * ServiceContextResourceUsage.java
 *
 * Created on September 18, 2004, 11:32 AM
 */

package org.ocap.service;

import javax.tv.service.Service;
import javax.tv.service.selection.ServiceContext;
import org.ocap.resource.ResourceUsage;

/**
 * This interface represents a ResourceUsage corresponding to a group of resources 
 * implicitly reserved by the implementation for the successful completion of the 
 * ServiceContext.select() method. An object implementing this interface should be used
 * by the implementation to represent ResourceUsages corresponding to ServiceContext 
 * when the ResourceContentionHandler.resolveResourceContention() method is invoked.
 */
public interface ServiceContextResourceUsage extends ResourceUsage
{
    /**
     * Gets the {@link ServiceContext} for which the resources have been reserved. 
     *
     * @return the ServiceContext for which the resources have been reserved. 
     */
    public ServiceContext getServiceContext();
    
    /**
     * Gets the {@link Service} that was requested when the resource contention
     * was incurred.
     *
     * @return Service requested.
     */
    public Service getRequestedService();

}