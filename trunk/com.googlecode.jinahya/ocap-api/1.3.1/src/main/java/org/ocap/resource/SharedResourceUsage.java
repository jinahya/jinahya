package org.ocap.resource;

import org.dvb.application.AppID;
import org.ocap.resource.ResourceUsage;
import org.davic.resources.ResourceProxy;

/**
 * This interface represents a group of resources where one or more 
 * resources are shared between multiple resource usages. 
 * If there is a contention over a resource that is considered shared between
 * multiple uses, the shared usage SHALL be represented by an instance of
 * <code>SharedResourceUsage</code> and the
 * {@link #getResourceUsages getResourceUsages()}
 * method would return the individual <code>ResourceUsage</code> instances that
 * share the resource.
 * <p>
 * Because a <code>SharedResourceUsage</code> can contain multiple
 * <code>ResourceUsage</code> instances where different entities reserved 
 * the resources, the value returned by the
 * <code>SharedResourceUsage.getAppID</code> method
 * SHALL be the AppID of the highest-priority ResourceUsage contained in the
 * SharedResourceUsage or {@code null} if none of the contained ResourceUsages
 * have AppIDs.
 */
public interface SharedResourceUsage extends ResourceUsage {
 
	/**
	 * Gets the list of <code>ResourceUsage</code> instances that share
	 * the resources represented by this resource usage.
	 *
	 * @return An array of <code>ResourceUsage</code> instances that
	 * share one or more resources.
	 */
	public ResourceUsage[] getResourceUsages();
	 
	/**
	 * Gets the list of <code>ResourceUsage</code> instances that share
	 * a particular resource.
	 *
	 * @param resource The shared resource for which
	 * <code>ResourceUsage</code> instances should be returned.
	 *
	 * @return An array of <code>ResourceUsage</code> instances that
	 * share the specified resource.
	 */
	public ResourceUsage[] getResourceUsages(ResourceProxy resource);
	
	
	//////// Methods inherited from ResourceUsage /////////
	
	/**
	 * Reports the {@link AppID} of the highest-priority
	 * {@code ResourceUsage} reported by {@link #getResourceUsages()},
	 * or {@code null} if none have {@code AppID}s.
	 *
	 * @return The {@link AppID} of the highest-priority
	 * {@code ResourceUsage} included in this {@code SharedResourceUsage}.
	 */
	public AppID getAppID();
} 
