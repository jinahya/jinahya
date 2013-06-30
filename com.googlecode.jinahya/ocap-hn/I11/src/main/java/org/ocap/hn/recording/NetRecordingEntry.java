package org.ocap.hn.recording;

import java.io.IOException;
import org.ocap.hn.NetActionHandler;
import org.ocap.hn.NetActionRequest;
import org.ocap.hn.content.ContentEntry;


/**
 * This ContentEntry represents a series recording that has been scheduled on
 * the home network.
 */
public interface NetRecordingEntry extends ContentEntry {

    /**
     * Key constant for retrieving the CDS reference of this recording entry   
     * from this entry's metadata. Values returned for this key will be represented  
     * as a String.
     */
    public static final String PROP_CDS_REFERENCE = "ocap:cdsReference";

    /**
     * Key constant for retrieving the RCI list of this recording entry   
     * from this entry's metadata. Values returned for this key will be represented  
     * as a String.
     */
    public static final String PROP_RCI_LIST = "ocap:RCIList";

    /**
     * Key constant for retrieving the scheduled CDS entry ID of this recording entry   
     * from this entry's metadata. Values returned for this key will be represented  
     * as a String.
     */
    public static final String PROP_SCHEDULED_CDS_ENTRY_ID = "ocap:scheduledCDSEntryID";

    /**
     * Deletes this NetRecordingEntry if and only if it contains no RecordingContentItems.
     * Deletes a local NetRecordingEntry only. If the #isLocal method returns false 
     * an exception is thrown.
     *
     * @return True if this NetRecordingEntry was deleted, otherwise returns
     *      false.
     *
     * @throws java.lang.SecurityException
     *             if the application does not have write
     *             ExtendedFileAccessPermission.
     * @throws IOException
     *             if the NetRecordingEntry is not local.
     */
    public boolean deleteEntry() throws IOException;   


    /**
     * Retrieves the local individual RecordingContentItems that make up this 
     * series recording. 
     * 
     * @throws IOException
     *             if this isLocal() method of this object does not return true
     *             
     * @return the RecordingContentItems in this series
     * 
     */
    RecordingContentItem[] getRecordingContentItems() throws IOException;

    /**
     * Adds a local RecordingContentItem to this recording object
     * 
     * @param item
     *            The recording content item to add to this series
     * 
     * @throws IOException
     *             if this isLocal() method of this object does not return true
     *             
     * @throws IllegalStateException if this recording object is not associated 
     *       with a UPnP AV Scheduled Recording Service Object (RerordSchedule)
     *       
     * @throws IllegalArgumentException if the RecordingContentItem paramter has 
     *       the associated UPnP AV Scheduled Recording Service Object (RecordTask)
     *             
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("recordinghandler")
     *             
     */
    void addRecordingContentItem(RecordingContentItem item) throws IOException;

    /**
     * Removes a local RecordingContentItem from this recording object. If the
     * RecordingContentItem passed into this method is not contained in this
     * NetRecordingObject, this method has no effect.
     * 
     * @param item
     *            The recording content item to remove from this series
     * 
     * @throws IOException
     *             if this isLocal() method of this object does not return true
     *             
     * @throws IllegalArgumentException if the RecordingContentItem paramter has 
     *             the associated UPnP AV Scheduled Recording Service Object (RecordTask)
     *             
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("recordinghandler")
     */
    void removeRecordingContentItem(RecordingContentItem item) throws IOException;

    /**
     * Retrieves ObjectIDs of the individual RecordingContentItems that 
     * make up this series recording.
     * 
     * @return the ObjectIDs of the RecordingContentItems in this series
     * 
     */
    String[] getRecordingContentItemIDs();
}
