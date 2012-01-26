package org.ocap.shared.dvr;

/**
 * Base class for specifying properties defining how a recording is to be made.
 */

public abstract class RecordingProperties
{
    /**
     * Constructor
     *
     * @param expirationPeriod The period in seconds after the initiation of
     *      recording when leaf recording requests with this recording
     *      property are deemed as expired. 
     */
    public RecordingProperties(long expirationPeriod)
    {
    }

    /**
     * Return the value of the expiration period.
     *
     * @return The expiration period as passed into the constructor.
     */
    public long getExpirationPeriod()
    {
        return 0;
    }
}


