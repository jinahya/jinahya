package org.ocap.hn.recording;

import org.ocap.hn.content.MetadataNode;

/**
 * This class represents a network recording specification. NetRecordingSpec
 * object may be used to request recordings be scheduled on remote devices.
 * Metadata contained within this object can be used to schedule or modify
 * recordings on the home network.
 */
public class NetRecordingSpec {

    /**
     * Default constructor for the NetRecordingSpec.
     * 
     * Upon creation, NetRecordingSpecs will contain a single empty metadata
     * node.
     */
    public NetRecordingSpec()
    {
        
    }
    
    /**
     * Metadata constructor for the NetRecordingSpec. Applications can use
     * this form of the constructor to specify the root metadata node
     * at time of construction. 
     *  
     * @param metadata root metadata node for the NetRecordingSpec
     */
    public NetRecordingSpec(MetadataNode metadata)
    {
        
    }
    
    /**
     * Retrieves the root metadata node for this recording spec. Metadata added
     * to this recording spec will be utilized to identify recording requests on
     * remote devices.
     * 
     * @return The root MetadataNode for this NetRecordingSpec
     */
    public MetadataNode getMetadata() {
        return null;
    }
}
