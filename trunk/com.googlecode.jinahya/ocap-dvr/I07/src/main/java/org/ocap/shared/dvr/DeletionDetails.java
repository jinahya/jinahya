package org.ocap.shared.dvr;

/**
 * This class contains details about the deletion of
 * a recorded service. Instances of this class are constructed by
 * the implementation and returned to applications from the method
 * {@link org.ocap.shared.dvr.LeafRecordingRequest#getDeletionDetails 
 * getDeletionDetails} method.
 */

public class  DeletionDetails 
{
    /**
     * Reason code :  The recorded service was deleted by the 
	 * implementation because the recording request has expired. 
     */
    public static final int EXPIRED = 1;

    /**
     * Reason code : The recorded service was explicitly deleted
	 * by the application.
     */
    public static final int USER_DELETED = 2;


    /**
     * Constructs a DeletionDetails 
     * @param reason the reason why the recorded service was deleted
     * @param date the date and time the recorded service was deleted
     */
    public DeletionDetails(int reason, java.util.Date date )
    {
    }

    /**
     * Reports the reason for why the recorded service was deleted. 
     * This is the value as passed in to the constructor.
     * @return the reason code for which the recorded service was deleted.
     */
    public int getReason()
    {
        return 0;
    }

    /**
     * Gets the date and time when the recorded service was deleted. 
     * This is the value as passed in to the constructor.
     *
     * @return the deletion date and time. 
     */
    public java.util.Date getDeletionTime()
    {
        return null;
    }	

}


