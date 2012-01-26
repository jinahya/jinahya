package org.ocap.hn.recording;

import java.io.IOException;
import org.ocap.hn.NetActionHandler;
import org.ocap.hn.NetActionRequest;
import org.ocap.hn.content.ContentItem;
import java.util.Date;
import javax.media.Time;

/**
 * This ContentItem represents a recording that has been scheduled
 * on the home network.
 * 
 * This interface represents a DVR recording which can be published to
 * the home network. On devices which support both the OCAP Home Networking
 * API and the OCAP DVR API, objects implementing <code> org.ocap.dvr.OcapRecordingRequest </code>
 * will also implement this interface. When a RecordingRequest is deleted, implementations
 * SHALL call the RecordingContentItem.deleteEntry method in the same object.
 */
public interface RecordingContentItem extends ContentItem
{

	/**
	 * Key constant for retrieving the state of this recording item from this
	 * item's metadata. Values returned for this key will be represented as an 
	 * Integer.
	 */
	public static final String PROP_RECORDING_STATE = "ocap:taskState";

	/**
	 * Key constant for retrieving the start time of this recording item from 
	 * this item's metadata. Values returned for this key will be represented  
	 * as a java.util.Date.
	 */
	public static final String PROP_START_TIME = "ocap:scheduledStartDateTime";

	/**
	 * Key constant for retrieving the duration in milliseconds of this recording  
	 * item from this item's metadata. Values returned for this key will be represented  
	 * as an Integer.
	 */
	public static final String PROP_DURATION = "ocap:scheduledDuration";

	/**
	 * Key constant for retrieving the source ID of this recording item   
	 * from this item's metadata. Values returned for this key will be represented  
	 * as a String.
	 */
	public static final String PROP_SOURCE_ID = "ocap:scheduledChannelID";

	/**
	 * Key constant for retrieving the source ID type of this recording item   
	 * from this item's metadata. Values returned for this key will be represented  
	 * as a String.
	 */
	public static final String PROP_SOURCE_ID_TYPE = "ocap:scheduledChannelIDType";

	/**
	 * Key constant for retrieving the destination of this recording item   
	 * from this item's metadata. Values returned for this key will be represented  
	 * as a String.
	 */
	public static final String PROP_DESTINATION = "ocap:destination";

	/**
	 * Key constant for retrieving the priority flag of this recording item   
	 * from this item's metadata. Values returned for this key will be represented  
	 * as an Integer.
	 */
	public static final String PROP_PRIORITY_FLAG = "ocap:priorityFlag";

	/**
	 * Key constant for retrieving the retention priority of this recording item   
	 * from this item's metadata. Values returned for this key will be represented  
	 * as an Integer.
	 */
	public static final String PROP_RETENTION_PRIORITY = "ocap:retentionPriority";

	/**
	 * Key constant for retrieving the file access permissions of this recording item   
	 * from this item's metadata. Values returned for this key will be represented  
	 * as an org.ocap.storage.ExtendedFileAccessPermissions.
	 */
	public static final String PROP_ACCESS_PERMISSIONS = "ocap:accessPermissions";

	/**
	 * Key constant for retrieving the organization of this recording item   
	 * from this item's metadata. Values returned for this key will be represented  
	 * as a String.
	 */
	public static final String PROP_ORGANIZATION = "ocap:organization";

	/**
	 * Key constant for retrieving the application ID of this recording item   
	 * from this item's metadata. Values returned for this key will be represented  
	 * as an org.dvb.application.AppID.
	 */
	public static final String PROP_APP_ID = "ocap:appID";

	/**
	 * Key constant for retrieving the estimated space required for this recording item   
	 * from this item's metadata. Values returned for this key will be represented  
	 * as a Long.
	 */
	public static final String PROP_SPACE_REQUIRED = "ocap:spaceRequired";

	/**
	 * Key constant for retrieving the location of content associated with this 
	 * recording item from this item's metadata. Values returned for this key 
	 * will be represented as a String.
	 */
	public static final String PROP_CONTENT_URI = "ocap:contentURI";

	/**
	 * Key constant for retrieving the media first time for this recording item   
	 * from this item's metadata. Values returned for this key will be represented  
	 * as a Long.
	 */
	public static final String PROP_MEDIA_FIRST_TIME = "ocap:mediaFirstTime";

	/**
	 * Key constant for retrieving the presentation point for this recording item   
	 * from this item's metadata. Values returned for this key will be represented  
	 * as a Long.
	 */
	public static final String PROP_PRESENTATION_POINT = "ocap:mediaPresentationPoint";

	/**
	 * Key constant for retrieving the expiration period for this recording item   
	 * from this item's metadata. Values returned for this key will be represented  
	 * as an Long.
	 */
	public static final String PROP_EXPIRATION_PERIOD = "ocap:expirationPeriod";

	/**
	 * Key constant for retrieving the MSO content indicator for this recording item   
	 * from this item's metadata. Values returned for this key will be represented  
	 * as an Boolean.
	 */
	public static final String PROP_MSO_CONTENT = "ocap:msoContentIndicator";

	/**
	 * Key constant for retrieving the ID of any NetRecordingEntry
	 * containing this RecordingContentItem.
	 * Values returned for this key will be represented as a String.
	 */
	public static final String PROP_NET_RECORDING_ENTRY = "ocap:netRecordingEntry";

       /**
         * Deletes this RecordingContentItem, but does not remove the physical recording.  
         * Deletes a local RecordingContentItem only. If the #isLocal method returns false 
         * an exception is thrown.
         * <p>
         * Note: this overrides the definition of {@link ContentItem#deleteEntry}.  If an
         * application calls the ContentEntry.deleteEntry method on an object that
         * is an instance of RecordingContentItem, the implementation SHALL delete the
         * RecordingContentItem as defined by this method.
         * </p>
         * 
         * @return True if this RecordingContentItem was deleted, otherwise returns
         *      false.
         *
         * @throws java.lang.SecurityException
         *             if the application does not have write
         *             ExtendedFileAccessPermission.
         * @throws IOException
         *             if the RecordingContentItem is not local.
         */
        public boolean deleteEntry() throws IOException;	

	/**
	 * Requests that the presentation point of this recording be updated. 
	 * 
	 * @param time The presentation point of this recording.
	 *        
	 * @param handler The NetActionHandler which gets informed once this request
	 *        completes.
	 *        
	 * @return NetActionRequest which can be used to monitor asynchronous action progress
	 */
	NetActionRequest requestSetMediaTime(Time time, NetActionHandler handler);

	/**
	 * Requests a list of recordings whose usage of resources conflict with 
	 * this recording content item. The resulting list of recordings SHALL be returned
	 * as an array of RecordingContentItem objects from the NetActionEvent.getResponse()
	 * method of the resulting NetActionEvent. 
	 * 
	 * @param handler The NetActionHandler implementation to receive the asynchronous
	 *                response to this request
	 * 
	 * @return NetActionRequest which can be used to monitor asynchronous action progress
	 */
	NetActionRequest requestConflictingRecordings(NetActionHandler handler);

	/**
	 * Returns the NetRecordingEntry which contains this recording content item
	 * if the NetRecordingEntry is available.
	 * 
	 * @return null if this RecordingContentItem is not added to any NetRecordingEntry
	 *  or if the NetRecordingEntry containing this RecordingContentItem is not available.
	 * Otherwise the NetRecordingEntry containing this RecordingContentItem 
	 */
	NetRecordingEntry getRecordingEntry();

	/**
	 * Returns the ObjectID of the NetRecordingEntry which contains this recording content item.
	 * The ObjectID can be obtained from ocap:netRecordingEntry property of this recording content
	 *  item.
	 *
	 * @return null if this RecordingContentItem does not contain ocap:netRecordingEntry property. 
	 * Otherwise, the value contained in ocap:netRecordingEntry property of this 
	 * RecordingContentItem. 
	 */
	String getRecordingEntryID();
}
