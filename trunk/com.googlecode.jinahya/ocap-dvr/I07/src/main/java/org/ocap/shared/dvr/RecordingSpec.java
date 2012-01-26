package org.ocap.shared.dvr;

/**
 * Base class for specifying what to record and how to record it.
 */
public abstract class RecordingSpec
{
    /**
     * Constructor.
     *
     * @param properties The definition of how the recording is to be done.
     */
    public RecordingSpec(RecordingProperties properties)
    {
    }

    /**
     * Return the description of how the recording is to be done.
     *
     * @return The properties to use for the recording.
     */
    public RecordingProperties getProperties()
    {
        return null;
    }
}


