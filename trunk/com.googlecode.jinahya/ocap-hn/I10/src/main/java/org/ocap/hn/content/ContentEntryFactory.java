package org.ocap.hn.content;

import org.ocap.storage.ExtendedFileAccessPermissions;
import org.ocap.net.OcapLocator;
import javax.tv.service.Service;

/**
 * This factory can be used to create <code>ContentEntry</code> instances.
 * There are specialty methods for application convenience when creating
 * channel content items.
 */
public abstract class ContentEntryFactory
{

    /**
     * Singleton behavior.
     */
    protected ContentEntryFactory()
    {
    }

    /**
     * Gets an instance of the factory.
     *
     * @return A content entry factory instance.
     */
    public static ContentEntryFactory getInstance()
    {
        return null;
    }

    /**
     * Creates a new <code>ChannelContentItem</code> representing a broadcast
     * channel.  A <code>ChannelContentItem</code> can only be added to a
     * container created by <code>createChannelGroupContainer</code>.
     * </p>
     * This content item is not automatically added to a parent container.
     * Applications can publish multiple channels in a single method call by
     * creating an array of <code>ChannelContentItem</code> instances and
     * passing it to the <code>addContentEntries</code> of a container
     * created by <code>createChannelGroupContainer</code> in CDS.
     * </p>
     * At the point that the created ChannelContentItem is requested by a DMC, 
     * the implementation SHALL determine if the channelLocator is 
     * transport-dependent (e.g. a frequency-based Locator) and use the Locator 
     * to acquire the Service for streaming. If the channelLocator is a 
     * transport-independent (e.g. a sourceID-based) Locator and can be resolved 
     * via JavaTV, the implementation SHALL use JavaTV (e.g. SIManager.getService) 
     * to acquire the Service for streaming. If the transport-independent Locator 
     * cannot be resolved via JavaTV, and the Locator returned from 
     * getTuningLocator() is null, the implementation SHALL invoke the 
     * ServiceResolutionHandler.resolveChannelItem method.  If resolveChannelItem 
     * returns true, the 
     * implementation SHALL use this Locator returned from getTuningLocator() 
     * to acquire the Service for streaming.     
     * </p>
     *
     * @param channelType The type of broadcast channel, can be one of
     *      <code>VIDEO_ITEM_BROADCAST</code> or <code>AUDIO_ITEM_BROADCAST</code>
     *      defined in { @link ContentItem }.
     * @param channelTitle The title of the new ChannelContentItem.
     * @param channelName The name of the new ChannelContentItem.
     * @param channelNumber String representing type and channel number, where
     *      type must be "Analog" or "Digital", number is (major channel number)
     *      for analog channels, and number is (major channel and minor channel)
     *      for digital channels, in the format "type, number [, number]". For
     *      example: "Analog,12", or "Digital,15,2". May also be null, when
     *      application is not providing this information.
     * @param channelLocator An <code>OcapLocator</code> for the channel     
     * @param permissions Access permissions of the new
     *      <code>ChannelContentItem</code> for local server applications only.
     * 
     * @return true if a new ChannelContentItem has been created, otherwise
     *      return false.
     *
     * @throws IllegalArgumentException if channelType is not
     *      AUDIO_ITEM_BROADCAST or VIDEO_ITEM_BROADCAST,
     *      or if channelNumber format is invalid.
     * @throws NullPointerException if channelName, channelTitle,
     *      or channelLocator arguments passed to this method are null.
     * @throws SecurityException if the caller does not have
     *      HomeNetPermission("contentmanagement").
     */
    public abstract ChannelContentItem createChannelContentItem(
            String channelType,
            String channelTitle,
            String channelName,
            String channelNumber,
            OcapLocator channelLocator,
            ExtendedFileAccessPermissions permissions);
}