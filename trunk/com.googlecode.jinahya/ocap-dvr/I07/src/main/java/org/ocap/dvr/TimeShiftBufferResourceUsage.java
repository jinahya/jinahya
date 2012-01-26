package org.ocap.dvr;

import org.ocap.resource.ResourceUsage;
import javax.tv.service.Service;

/**
 * This interface represents a grouping of resources specific to a time-shift
 * buffering performed by an application.
 */
public interface TimeShiftBufferResourceUsage extends ResourceUsage
{
    /**
     * Gets the {@link Service} associated with the set of resources
     * contained in the usage where the last service selection was initiated 
     * by the application returned by the base ResourceUsage.getAppID method.
     *
     * @return The Service associated with the resource usage.
     */
    public Service getService();
}
