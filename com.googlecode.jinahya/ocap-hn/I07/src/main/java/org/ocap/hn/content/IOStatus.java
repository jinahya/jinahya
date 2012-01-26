package org.ocap.hn.content;

/**
 * This interface represents the ability to detect whether any 
 * asset represented by an object or its children is in use on
 * the home network and hence the object should not be deleted. 
 * <p> 
 * ContentResource and ContentEntry objects representing local 
 * assets on a server SHALL implement IOStatus. 
 */
public interface IOStatus 
{
    /**
     * Returns an indication of whether any asset within this object
     * is in use on the home network. "In Use" is indicated if there
     * is an active network transport protocol session (for example 
     * HTTP, RTSP) to the asset. 
     * <p> 
     * For objects which logically contain other objects, 
     * recursively iterates through all logical children of this 
     * object. For ContentContainer objects, recurses through all 
     * ContentEntry objects they contain. For NetRecordingEntry 
     * objects, iterates through all RecordingContentItem objects 
     * they contain. 
     * 
     * @return True if there is an active network transport protocol
     *         session to any asset that this ContentResource,
     *         ContentEntry, or any children of the ContentEntry
     *         contain, otherwise false.
     */
    public boolean isInUse();

}
