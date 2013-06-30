package org.ocap.dvr;

import org.ocap.resource.ResourceUsage;
import org.davic.resources.ResourceProxy;

/**
 * This interface represents a group of resources where one or more
 * resources are shared between multiple resource usages. For example,
 * when a tuner is used for an ongoing recording and also for presenting 
 * a broadcast service in a service context, and if the tuner is in a 
 * resource contention, the tuner is considered shared between a
 * <code>RecordingResourceUsage</code> and a
 * <code>ServiceContextResourceUsage</code>.
 * If there is a resource contention for a tuner, the shared usage of tuner is
 * represented by a <code>SharedResourceUsage</code> where the
 * getResourceUsages() method would return both <code>ResourceUsage</code>
 * instances that share the tuner.
 * </p>
 * Because a <code>SharedResourceUsage</code> can contain multiple
 * <code>ResourceUsage</code> instances where different entities reserved 
 * the resources, the value returned by the
 * <code>SharedResourceUsage.getAppID</code> method
 * SHALL be the AppID of the highest-priority ResourceUsage contained in the
 * SharedResourceUsage or {@code null} if none of the contained ResourceUsages
 * have AppIDs.
 */
public interface SharedResourceUsage extends org.ocap.resource.SharedResourceUsage {
} 

