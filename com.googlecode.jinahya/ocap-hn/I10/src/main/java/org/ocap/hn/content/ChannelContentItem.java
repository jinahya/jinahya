package org.ocap.hn.content;

import java.lang.UnsupportedOperationException;
import org.ocap.net.OcapLocator;
import org.ocap.storage.ExtendedFileAccessPermissions;
import javax.tv.locator.InvalidLocatorException;

/**
 * This interface represents a video or audio broadcast channel object.
 */
public interface ChannelContentItem extends ContentItem
{

    /**
     * Gets The channel type for this ChannelContentItem
     * 
     * @return the String channel type for this item, or null if unknown.
     */
    public String getChannelType();
    
    /**
     * Gets The channel number for this ChannelContentItem
     * 
     * @return The String channel number for this item, or null if unknown.
     */
    public String getChannelNumber();

    /**
     * Gets The channel name for this ChannelContentItem
     * 
     * @return the String channel name for this item, or null if unknown.
     */
    public String getChannelName();

    /**
     * Gets The title for this ChannelContentItem, or null if the title is unknown.
     * 
     * @return the String title for this item, or null if unknown.
     */
    public String getChannelTitle();

    /**
     * Gets the locator for this ChannelContentItem set in createChannelContentItem.
     * 
     * @return The locator for this ChannelContentItem, returns
     *      null if the isLocal method returns false.
     */
    public OcapLocator getChannelLocator();

    
    /**
     * Gets the extended file access permissions for this ChannelContentItem.
     * 
     * @return The extended file access permissions.
     */
    public ExtendedFileAccessPermissions getExtendedFileAccessPermissions();

    /**
     * Gets the frequency based tuning locator used for service resolution.
     * 
     * @return The frequency based tuning locator if previously resolved, 
     * null otherwise.
     */
    public OcapLocator getTuningLocator();

    /**
     * <p>Sets the tuning locator for this ChannelContentItem that the
     * implementation can use for tuning a broadcast channel.  Returns false 
     * if the #isLocal method returns false.
     * </p>
     * <p>
     * An application may call this method to update a channel's tuning parameters (for example,
     * when an SDV channel's program number or frequency changes). Upon a successful update of
     * the channel's tuning parameters the implementation SHALL be responsible for updating 
     * any active streaming sessions to the new tuning parameters. When a JavaTV Service represents
     * this ChannelContentItem the implementation SHALL modify the transport dependent locator 
     * of the Service to match the locator parameter.
     * </p>
     * <p>
     * Setting the channel tuning locator to a null locator makes the channel
     * item no longer tunable (for example, when the application ends an SDV
     * session).
     * Setting the tuning locator to null while streaming ends 
     * streaming, removes the locator, 
     * and {@link StreamingActivityListener#notifyStreamingEnded} 
     * is called with an activityID of <code>ACTIVITY_END_SERVICE_VANISHED
     * </code>.
     * </p>
     *
     * @param locator A frequency-based locator for the channel, or null to
     *      remove the current locator.
     *
     * @return true when the locator parameter is set correctly, otherwise,
     *      returns false.
     * 
     * @throws InvalidLocatorException if the locator parameter is not
     *      frequency based and is not null.
     *
     * @throws SecurityException if the calling application is not granted
     *      write permission by the permissions returned from the
     *      getExtendedFileAccessPermissions method.
     */
    public boolean setTuningLocator(OcapLocator locator) throws InvalidLocatorException;
}
