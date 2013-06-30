/*
 * ResourceUsage.java
 *
 * Created on September 18, 2004, 11:31 AM
 */

package org.ocap.resource;

import org.dvb.application.AppID;
import org.davic.resources.ResourceProxy;

/**
 * This interface represents a grouping of resources specific to an function
 * performed by an application.
 */
public interface ResourceUsage
{
    /**
     * Gets the {@link AppID} of the application associated with the set
     * of resources contained in the usage.
     *
     * @return AppID of the application.
     */
    public AppID getAppID();

    /**
     * Gets the array of resource names associated with the resource reservation. 
     *
     * @return The array of qualified java class names for the resources used (or required) 
     *    for this resource usage.
     */
    public java.lang.String [] getResourceNames();

    /**
     * Gets the instance of {@link ResourceProxy} corresponding to a resource name returned
     * by the getResourceNames method of this ResourceUsage. This method will return null if
     * the resource is not yet reserved.  This method provides information to distinguish 
     * which resources in the ResourceUsage have already been reserved. Since DAVIC resource 
     * API reserves resource in one by one manner, the ResourceUsage may include both 
     * reserved and unreserved resources.
     *
     * @param resourceName The fully qualified java name for a resource included in this
     *    ResourceUsage.
     *
     * @return The instance of {@link ResourceProxy} corresponding to the resourceName.
     *
     * @throws IllegalArgumentException if the resourceName is not in the list of fully 
     * qualified java class names returned by the method getResourceNames()
     */
    public ResourceProxy getResource(java.lang.String resourceName);
}
