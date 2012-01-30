package org.ocap.dvr;

import org.ocap.resource.ResourceUsage;
import org.davic.resources.ResourceProxy;

/**
 * This interface represents a group of resources where one or more
 * resources are shared between multiple resource usages. For example,
 * when a tuner is used for an ongoing recording and also for presenting 
 * a broadcast service in a service context, and if the tuner is in a 
 * resource contention, the tuner is considered shared between a
 * <code>RecordingResourceUsage</code> and a <code>ServiceContextResourceUsage</code>.
 * If there is a resource contention for a tuner, the shared usage of tuner is
 * represented by a <code>SharedResourceUsage</code> where the getResourceUsages()
 * method would return both <code>ResourceUsage</code> instances that share the tuner.
 * </p>
 * Because a <code>SharedResourceUsage</code> can contain multiple
 * <code>ResourceUsage</code> instances where different applications reserved 
 * the resources, the value returned by the <code>SharedResourceUsage.getAppID</code>
 * method is meaningless and SHALL be null.  To determine AppID instances an
 * application can peruse the <code>ResourceUsage</code> instances returned by the
 * <code>getResourceUsages</code> method.
 * 
 */
public interface SharedResourceUsage extends org.ocap.resource.SharedResourceUsage {
 
	/**
	 * Gets the list of <code>ResourceUsage</code> instances that share the resources 
	 * represented by this resource usage.
	 *
	 * @return An array of <code>ResourceUsage</code> instances that share one 
	 * or more resources.
	 */
	public ResourceUsage[] getResourceUsages();
	 
	/**
	 * Gets the list of <code>ResourceUsage</code> instances that share a particular resource.
	 *
	 * @param resource The shared resource for which <code>ResourceUsage</code>
	 * instances should be returned.
	 * @return An array of <code>ResourceUsage</code> instances that share the 
	 * specified resource.
	 */
	public ResourceUsage[] getResourceUsages(ResourceProxy resource);
} 

