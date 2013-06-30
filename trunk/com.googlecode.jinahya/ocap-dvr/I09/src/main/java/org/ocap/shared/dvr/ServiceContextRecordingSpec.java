package org.ocap.shared.dvr;

import java.util.Date;
import javax.tv.service.selection.ServiceContext;

/**
 * Specifies a recording request in terms of what is being presented on a
 * ServiceContext.
 * The streams that are being presented in the indicated ServiceContext
 * parameter are recorded. If the Service being recorded from is tuned
 * away, recording SHALL be terminated. If the startTime is in
 * the past and the source javax.tv.service.selection.ServiceContext
 * is associated with a time shift buffer, the
 * contents of the time-shift buffer may be immediately stored
 * to the destination beginning at the startTime, if possible, up to the
 * live broadcast point. If the time-shift buffer does not contain the
 * source from the startTime, as much of the source may be
 * recorded as possible. If the startTime is in the past, but a time-shift
 * buffer cannot be associated with the recording, the recording begins
 * from the live broadcast point. From there the contents of the
 * live broadcast are recorded until the remaining duration is satisified.
 * <p>
 * When instances of this class are passed to RecordingManager.record(..),
 * the following additional failure modes shall apply;<ul>
 * <li>IllegalArgumentException SHALL be thrown if serviceContext is not
 * in the presenting state or if it is not
 * presenting a broadcast service or if the startTime is in the future or
 * if the properties parameter to the instance is an instance of an application defined class
 * <li>SecurityException SHALL be thrown if the application does not have
 * permission to access the service context.
 * <li>AccessDeniedException shall be thrown where the calling application 
 * is not permitted to perform this operation by RecordingRequest specific 
 * security attributes.
 * </ul>
 * <p>
 * When an instance of this recording spec is passed in as a parameter 
 * to the RecordingRequest.reschedule(..) method, an IllegalArgumentException 
 * is thrown if the service context parameter is different from the service 
 * context specified in the current recording spec for the recording request.
 * 
 */

public class ServiceContextRecordingSpec extends RecordingSpec
{
    /**
     * Constructor
     * @param serviceContext The ServiceContext to record from.
     * @param startTime Start time of the recording.  If the start time is in the
     * future when the RecordingManager.record(..) method is called with this 
     * ServiceContextRecordingSpec as an argument  the record method will
     * throw an IllegalArgumentException. Changes to this parameter
     * after the constructor returns shall have no effect on the actual
     * start time of the recording.
     * @param duration Length of time to record in milli-seconds.
     * @param properties the definition of how the recording is to be done
     * @throws IllegalArgumentException if duration is negative
     */
    public ServiceContextRecordingSpec(ServiceContext serviceContext,
                                       Date startTime,
                                       long duration,
                                       RecordingProperties properties)
    throws IllegalArgumentException
    {
        super(properties);
    }

    /**
     * Returns the ServiceContext to record from
     * @return the ServiceContext instance passed into the constructor
     */
    public ServiceContext getServiceContext()
    {
        return null ;
    }

    /**
     * Returns the start time passed as an argument to the constructor. 
     * @return a copy of the start time passed into the constructor
     */
    public Date getStartTime()
    {
        return null ;
    }

    /**
     * Returns the duration passed as an argument to the constructor. 
     * @return the duration passed into the constructor
     */
    public long getDuration()
    {
        return 0 ;
    }

}

