package org.ocap.shared.dvr;

/**
 * This exception is returned when applications call the
 * getFailedException() for a failed recording request or
 * an incomplete recording request.
 */

public class  RecordingFailedException extends java.lang.Exception
{
    /**
     * Constructs a RecordingFailedException with no detail message
     * The reason code instances created by this constructor shall be
     * REASON_NOT_KNOWN.
     */
    public RecordingFailedException()    {    }

    /**
     * Reason code : Recording failed due to the CA system refusing
     * to permit it.
     */
    public static final int CA_REFUSAL = 1;

    /**
     * Reason code : Recording failed because the requested content
     * could not be found in the network.
     */
    public static final int CONTENT_NOT_FOUND = 2;

    /**
     * Reason code : Recording failed due to problems with tuning.
     */
    public static final int TUNING_FAILURE = 3;

    /**
     * Reason code : Recording failed due to a lack of resources
         * required to present this service.
     */
    public static final int INSUFFICIENT_RESOURCES = 4;

    /**
     * Reason code : Recording did not complete successfully because
     * access to the service or some component of it were withdrawn
     * by the system before the scheduled completion of the recording.
     */
    public static final int ACCESS_WITHDRAWN = 5;

    /**
     * Reason code : Recording did not complete sucessfully because
     * Resources needed to present the service were removed before
     * the scheduled completion of the recording.
     */
    public static final int RESOURCES_REMOVED = 6;

    /**
     * Reason code : Recording did not complete sucessfully because
     * the service vanished from the network before the completion
     * of the recording.
     */
    public static final int SERVICE_VANISHED = 7;

    /**
     * Reason code : Recording did not complete successfully because
     * the application selected another service on the service context.
     * This is applicable only if the recording spec for the recording
     * request is an instance of ServiceContextRecordingSpec.
     */
    public static final int TUNED_AWAY = 8;

    /**
     * Reason code : The application terminated the recording using
     * LeafRecordingRequest.stop method or by calling the stop on
     * the service context (if the recording spec is an instance of
     * ServiceContextRecordingSpec).
     */
    public static final int USER_STOP = 9;

    /**
     * Reason code : Recording could not complete due to lack of storage space.
     */
    public static final int SPACE_FULL = 10;

    /**
     * Reason code : Recording failed due to lack of IO bandwidth to
     * record this program.
     */
    public static final int OUT_OF_BANDWIDTH = 11;

    /**
     * Reason code : The recording request failed due to errors in
     * request resolution.
     */
    public static final int RESOLUTION_ERROR = 12;

	/**
	 * Reason code : When the device is powered off
	 * and the power returns after the scheduled end time of a recording but
	 * before the expiration time of the recording request, the request shall
	 * be in the failed state with this reason code.
	 */

	public static final int POWER_INTERRUPTION = 13;

	/**
	 * Reason code: reason not known
	 */
	public int REASON_NOT_KNOWN = 14;

    /**
     * Constructs a RecordingFailedException with a detail message
     * @param reason the reason why the exception was thrown
     */
    public RecordingFailedException(int reason)
    {
    }

    /**
     * Reports the reason for which the recording request failed to
     * complete successfully.
     *
     * @return the reason code for which the recording request failed
     * to complete successfully.
     */
    public int getReason()
    {
        return 0;
    }

}


