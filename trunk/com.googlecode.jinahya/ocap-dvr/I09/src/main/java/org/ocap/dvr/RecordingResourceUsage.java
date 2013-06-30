package org.ocap.dvr;

import org.dvb.application.AppID;
import org.davic.resources.ResourceProxy;
import org.ocap.shared.dvr.RecordingRequest;
import org.ocap.resource.ResourceUsage;

/**
 * This interface represents a grouping of resources specific to a recording
 * function performed by an application.
 */
public interface RecordingResourceUsage extends ResourceUsage
{
    /**
     * Gets the {@link RecordingRequest} associated with the set of resources
     * contained in the usage and initiated by the application returned by the
     * base ResourceUsage.getAppID method.
     *
     * @return The recording request associated with the resource usage.
     */
    public RecordingRequest getRecordingRequest();
}

