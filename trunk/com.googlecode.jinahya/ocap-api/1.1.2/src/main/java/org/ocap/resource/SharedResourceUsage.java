package org.ocap.resource;

import org.ocap.resource.ResourceUsage;
import org.davic.resources.ResourceProxy;

/**
 * This interface represents a group of resources where one or more 
 * resources are shared between multiple resource usages. 
 * If there is a contention over a resource that is considered shared between
 * multiple uses, the shared usage SHALL be represented by an instance of
 * <code>SharedResourceUsage</code> and the {@link #getResourceUsages getResourceUsages()}
 * method would return the individual <code>ResourceUsage</code> instances that share 
 * the resource.
 * <p>
 * Because a <code>SharedResourceUsage</code> can contain multiple
 * <code>ResourceUsage</code> instances where different applications reserved 
 * the resources, the value returned by the <code>SharedResourceUsage.getAppID</code>
 * method is meaningless and SHALL be null.  To determine AppID instances an
 * application can peruse the <code>ResourceUsage</code> instances returned by the
 * <code>getResourceUsages</code> method.
 * 
 */
public interface SharedResourceUsage extends ResourceUsage {
 
	/**
	 * Gets the list of <code>ResourceUsage</code> instances that share the resources 
	 * represented by this resource usage
	 *
	 * @return An array of <code>ResourceUsage</code> instances that share one 
	 * or more resources.
	 */
	public ResourceUsage[] getResourceUsages();
	 
	/**
	 * Gets the list of <code>ResourceUsage</code> instances that share a particular
	 * resource.
	 *
	 * @param resource The shared resource for which <code>ResourceUsage</code>
	 * instances should be returned.
	 * @return An array of <code>ResourceUsage</code> instances that share the 
	 * specified resource.
	 */
	public ResourceUsage[] getResourceUsages(ResourceProxy resource);
} 
