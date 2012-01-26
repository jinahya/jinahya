package org.ocap.dvr;

import org.ocap.shared.dvr.RecordingProperties;
import org.ocap.dvr.storage.MediaStorageVolume;
import org.ocap.storage.ExtendedFileAccessPermissions;

/**
 * Encapsulates the details about how a recording is to be made.  Used by the
 * implementation to create a parent or leaf recording request when the
 * <code>RecordingManager record</code> or <code>resolve</code>
 * methods are called.  The only attributes in this class that are used by a
 * <code>ParentRecordingRequest</code> are the access and organization
 * attributes.  All of the other attributes are not used by a parent recording request
 * <code>ParentRecordingRequest</code> for the life cycle of the request.
 * <p></p>
 * When the implementation creates a <code>ParentRecordingRequest</code> using
 * this class it SHALL set the <code>ExtendedFileAccessPermissions</code>
 * to read and write application access rights only.
 * </p><p>
 * For purposes of the <code>RecordingRequest.setRecordingProperties</code> method,
 * properties MAY be changed under the following state conditions:
 * <ul>
 * <li>bitRate - leaf recordings only, in the PENDING_NO_CONFLICT_STATE and 
 * PENDING_WITH_CONFLICT_STATE</li>
 * <li>priorityFlag - leaf recordings only, in the PENDING_NO_CONFLICT_STATE and
 * PENDING_WITH_CONFLICT_STATE</li>
 * <li>retentionPriority - leaf recordings only, any state except DELETED_STATE and CANCELLED_STATE</li>
 * <li>access - leaf or parent recordings in any state</li>
 * <li>organization - cannot be changed in any state</li>
 * <li>destination - leaf recordings only, in the PENDING_NO_CONFLICT_STATE and 
 * PENDING_WITH_CONFLICT_STATE</li>
 * <li>expirationPeriod - leaf recordings only, any state except DELETED_STATE and CANCELLED_STATE</li>
 * <li>resourcePriority - leaf recordings only, any state except DELETED_STATE, CANCELLED_STATE,
 * FAILED_STATE, COMPLETE_STATE, or INCOMPLETE_STATE</li>
 * </ul>
 */
public class OcapRecordingProperties extends RecordingProperties
{
    /**
     * Indicates an implementation specific value for high bit-rate.
     */
    public static final byte HIGH_BIT_RATE =1;

    /**
     * Indicates an implementation specific value for low bit-rate.
     */
    public static final byte LOW_BIT_RATE =2;

    /**
     * Indicates an implementation specific value for medium bit-rate.
     */
    public static final byte MEDIUM_BIT_RATE =3;

    /**
     * Indicates a recording SHALL be deleted by the implementation as soon as
     * its expiration date is reached.
     */
    public static final int DELETE_AT_EXPIRATION = 0;

    /**
     * Record only if there are no conflicts.
     */
    public static final byte RECORD_IF_NO_CONFLICTS =1;

    /**
     * Record even when resource conflicts exist. 
     */
    public static final byte RECORD_WITH_CONFLICTS =2;

    /**
     * Schedule only test recording requests corresponding to this spec.
     * Does not cause a recording to be started.
     * This value could be used as the priorityFlag parameter value to
     * the constructor for instances of this class.  When an
     * OcapRecordingProperties with this value used as a priority value
     * is used to schedule a recording request, any leaf recording
     * requests scheduled will be in the TEST_STATE.  If a test recording
     * request is unresolved, partially resolved or completely resolved,
     * the states would be UNRESOLVED_STATE, PARTIALLY_RESOLVED_STATE and
     * COMPLETELY_RESOLVED_STATE respectively. Test recording requests
     * maybe used by applications to detect potential conflicts before
     * scheduling a regular recording. Scheduling a test recording request
     * will not affect the states of any other recording requests. No
     * events will be generated corresponding to a test recording request.
     * Test recording requests will not change state to any other state.
     */
    public static final byte TEST_RECORDING =3;

    /**
     * Constructs an immutable instance of <code>OcapRecordingProperties</code>
     * with the specified attributes.
     * <p>
     *
     * @param bitRate An application may specify LOW_BIT_RATE,
     *      MEDIUM_BIT_RATE, or HIGH_BIT_RATE. For analog recordings the
     *      corresponding bit-rate values are implementation specific. For
     *      digital recordings these values request optional transrating.
     *      When transrating is supported, HIGH_BIT_RATE indicates no
     *      transrating, and MEDIUM_BIT_RATE to LOW_BIT_RATE indicates
     *      increasing compression with a potential decrease in video quality.
     * @param expirationPeriod The period in seconds after the initiation
     *      of recording when leaf recording requests with this recording
     *      property are deemed as expired.  The implementation will delete
     *      recorded services based on the expirationPeriod and
     *      retentionPriority parameters.  This is done without application
     *      intervention and transitions those recording requests to the
     *      deleted state.
     * @param retentionPriority Indicates when the recording shall be deleted.
     *      An application MAY pass in DELETE_AT_EXPIRATION or a higher value
     *      indicating a retention priority.  If the value is not
     *      DELETE_AT_EXPIRATION the recording will be kept after the
     *      expirationPeriod has passed if the implementation does not need
     *      the storage space for any other reason.  If the space is needed
     *      expired recordings will be deleted based on retention priority,
     *      i.e. higher value equals higher priority, until the needed space is
     *      achieved.
     * @param priorityFlag Indication whether the recording should be made
     *      regardless of resource conflict or not. This parameter can
     *      contain the values RECORD_IF_NO_CONFLICTS, TEST_RECORDING or
     *      RECORD_WITH_CONFLICTS.
     * @param access File access permission for the recording request. If a
     *      null value is passed in the implementation SHALL create an
     *      <code>ExtendedFileAccessPermissions</code> object with read and
     *      write application access rights only and contain it in the
     *      object instantiated from this class.
     * @param organization Name of the organization this recording will be
     *      tied to. This <code>String</code> will be compared against the
     *      <code>organization_id</code> as would be found in the 
     *      organization name field of an application's leaf certificate 
     *      to authenticate a playback request. A value of null disables 
     *      such playback authentication for this recording.
     * @param destination The volume that represents the Storage location of
     *      the recording. When an instance of this class is
     *      used with a ServiceRecordingSpec a LocatorRecordingSpec,
     *      or a ServiceContextRecordingSpec where the specified service
     *      context is not attached to a time-shift buffer,
     *      with the value of this parameter set to null, the
     *      implementation shall use the default recording volume (see
     *      org.ocap.storage.MediaStorageOption ) in one of the storage
     *      devices connected.  If the value is null when used
     *      with a ServiceContextRecordingSpec, when the service context
     *      specified in the ServiceContextRecordingSpec is
     *      attached to a time-shift buffer, the default recording volume
     *      from the storage device where the time-shift buffer is located
     *      shall be used. When an instance of this class is used with a
     *      ServiceContextRecordingSpec,
     *      the record(..) method will throw an IllegalArgumentException
     *      if the destination is not in same storage device where an attached
     *      time-shift buffer is located.
     *
     * @throws java.lang.IllegalArgumentException if bitRate does not equal
     *      one of LOW_BIT_RATE, MEDIUM_BIT_RATE, or HIGH_BIT_RATE; or if
     *      priorityFlag does not contain the value RECORD_IF_NO_CONFLICTS,
     *      TEST_RECORDING or RECORD_WITH_CONFLICTS; or if organization
     *      is not found in the application's certificate file.
     */
    public OcapRecordingProperties(byte bitRate,
                                   long expirationPeriod,
                                   int retentionPriority,
                                   byte priorityFlag,
                                   ExtendedFileAccessPermissions access,
                                   String organization,
                                   MediaStorageVolume destination)
        {
        super(expirationPeriod);
    }

    /**
     * Constructs an immutable instance of <code>OcapRecordingProperties</code>
     * with the specified attributes.
     *
     * @param bitRate An application may specify LOW_BIT_RATE,
     *      MEDIUM_BIT_RATE, or HIGH_BIT_RATE. For analog recordings the
     *      corresponding bit-rate values are implementation specific. For
     *      digital recordings these values request optional transrating.
     *      When transrating is supported, HIGH_BIT_RATE indicates no
     *      transrating, and MEDIUM_BIT_RATE to LOW_BIT_RATE indicates
     *      increasing compression with a potential decrease in video quality.
     * @param expirationPeriod The period in seconds after the initiation
     *      of recording when leaf recording requests with this recording
     *      property are deemed as expired.  The implementation will delete
     *      recorded services based on the expirationPeriod and
     *      retentionPriority parameters.  This is done without application
     *      intervention and transitions those recording requests to the
     *      deleted state.
     * @param retentionPriority Indicates when the recording shall be deleted.
     *      An application MAY pass in DELETE_AT_EXPIRATION or a higher value
     *      indicating a retention priority.  If the value is not
     *      DELETE_AT_EXPIRATION the recording will be kept after the
     *      expirationPeriod has passed if the implementation does not need
     *      the storage space for any other reason.  If the space is needed
     *      expired recordings will be deleted based on retention priority,
     *      i.e. higher value equals higher priority, until the needed space is
     *      achieved.
     * @param priorityFlag Indication whether the recording should be made
     *      regardless of resource conflict or not. This parameter can
     *      contain the values RECORD_IF_NO_CONFLICTS, TEST_RECORDING or
     *      RECORD_WITH_CONFLICTS.
     * @param access File access permission for the recording request. If a
     *      null value is passed in the implementation SHALL create an
     *      <code>ExtendedFileAccessPermissions</code> object with read and
     *      write application access rights only and contain it in the
     *      object instantiated from this class.
     * @param organization Name of the organization this recording will be
     *      tied to. This <code>String</code> will be compared against the
     *      <code>organization_id</code> as would be found in the 
     *      organization name field of an application's leaf certificate 
     *      to authenticate a playback request. A value of null disables 
     *      such playback authentication for this recording.
     * @param destination The volume that represents the Storage location of
     *      the recording. When an instance of this class is
     *      used with a ServiceRecordingSpec a LocatorRecordingSpec,
     *      or a ServiceContextRecordingSpec where the specified service
     *      context is not attached to a time-shift buffer,
     *      with the value of this parameter set to null, the
     *      implementation shall use the default recording volume (see
     *      org.ocap.storage.MediaStorageOption ) in one of the storage
     *      devices connected.  If the value is null when used
     *      with a ServiceContextRecordingSpec, when the service context
     *      specified in the ServiceContextRecordingSpec is
     *      attached to a time-shift buffer, the default recording volume
     *      from the storage device where the time-shift buffer is located
     *      shall be used. When an instance of this class is used with a
     *      ServiceContextRecordingSpec,
     *      the record(..) method will throw an IllegalArgumentException
     *      if the destination is not in same storage device where an attached
     *      time-shift buffer is located.
     * @param resourcePriority Indicates the application-specified resource
     *      priority.  This value MAY be used by a resource contention handler
     *      application.
     *
     * @throws java.lang.IllegalArgumentException if bitRate does not equal
     *      one of LOW_BIT_RATE, MEDIUM_BIT_RATE, or HIGH_BIT_RATE; or if
     *      priorityFlag does not contain the value RECORD_IF_NO_CONFLICTS,
     *      TEST_RECORDING or RECORD_WITH_CONFLICTS; or if organization
     *      is not found in the application's certificate file.
     */
    public OcapRecordingProperties(byte bitRate,
                                   long expirationPeriod,
                                   int retentionPriority,
                                   byte priorityFlag,
                                   ExtendedFileAccessPermissions access,
                                   String organization,
                                   MediaStorageVolume destination,
                                   int resourcePriority)
       {
        super(expirationPeriod);
    }

    /**
     * Return the bitRate to use for the recording
     * @return the bitRate as passed into the constructor
     */
    public byte getBitRate()
    {
        return 0;
    }

    /**
     * Gets the period in seconds the recording expires after being scheduled.
     *
     * @return the expiration period as passed into the constructor
     */
    public long getExpirationPeriod()
    {
        return 0;
    }

    /**
     * Gets the priority determining how the recording is deleted.
     *
     * @return the retention priority as passed into the constructor
     */
    public int getRetentionPriority()
    {
        return 0;
    }

    /**
     * Return whether or not the recording should be made if there are
     * resource conflicts
     * @return the priority flag passed into the constructor
     */
    public byte getPriorityFlag()
    {
        return 0;
    }

    /**
     * Return the file access permission to use for the recording
     * @return the file access permission passed into the constructor
     */
    public ExtendedFileAccessPermissions getAccessPermissions()
    {
        return null;
    }

    /**
     * Return the name of the organization that this recording will be tied to
     * @return the organization passed into the constructor
     */
    public String getOrganization()
    {
        return null;
    }

    /**
     * Return the volume that represents the storage location of the recording
     * @return the volume passed into the constructor
     */
    public MediaStorageVolume getDestination()
    {
        return null;
    }

    /**
     * Return the application-specified resource priority that may be considered
     * at resource contention resolution time.
     * 
     * @return the resource priority
     */
    public int getResourcePriority()
    {
        return 0;
    }
}
