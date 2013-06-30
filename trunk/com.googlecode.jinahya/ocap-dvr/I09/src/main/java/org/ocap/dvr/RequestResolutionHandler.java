package org.ocap.dvr;

import org.ocap.shared.dvr.RecordingRequest;

/**
 * This interface will be implemented by the application that registers the
 * RequestResolutionHandler. The RequestResolutionHandler will be invoked
 * whenever a new unresolved recording request is added to the
 * RecordingManager database.  The RecordingResolutionHandler may call
 * the resolve(..) method of the OcapRecordingManager multiple times to
 * schedule one or more recording requests corresponding to the recording
 * request.
 *
 */
public interface RequestResolutionHandler
{
    /**
     * This method would be invoked by the implementation when an
     * unresolved recording request is scheduled in response to an
     * application calling the record(..) method of the RecordingManager.
     */
    public void requestResolution(RecordingRequest request);

}

