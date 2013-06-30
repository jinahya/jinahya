package org.ocap.hn.recording;

import java.io.IOException;

/**
 * This interface represents a local RecordingNetModule. An instance of
 * RecordingNetModule for which the isLocal() method returns true will
 * also be an instance of NetRecordingRequestManager.
 */
public interface NetRecordingRequestManager extends RecordingNetModule 
{
    
    /**
     * This method creates a local entry which represents a network visible
     * collection of recording items.
     * 
     * @throws IOException
     *             if the isLocal() method of this object does not return true
     *             
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("recordinghandler")
     */
    NetRecordingEntry createNetRecordingEntry() throws IOException;
    
    /**
     * This method sets the specified NetRecordingRequestHandler that processes
     * requests to schedule recordings from remote devices. Only one instance of
     * NetRecordingRequestHandler can be set on a given RecordingNetModule at a
     * time.
     * 
     * A NetRecordingRequestHandler can only be set on an instance of
     * RecordingNetModule that is local to the device.
     * 
     * 
     * @param handler
     *            the handler to be set for this RecordingNetModule. If null is
     *            specified, the currently set handler will be removed, and no
     *            application notification will occur for recording requests.

     *             
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("recordinghandler")
     */
    void setNetRecordingRequestHandler(NetRecordingRequestHandler handler);    
}
