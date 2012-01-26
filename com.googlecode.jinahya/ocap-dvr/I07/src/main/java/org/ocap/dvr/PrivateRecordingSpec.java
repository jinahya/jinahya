package org.ocap.dvr;

import java.io.Serializable;
import org.ocap.shared.dvr.RecordingSpec;
import org.ocap.shared.dvr.RecordingProperties;

/**
 * Specifies a recording request that can be resolved only by an application
 * defined request resolution handler.
 */
public class PrivateRecordingSpec extends RecordingSpec
{
    /**
     * Constructor
     * @param requestData private data the format of which is known only
     *      to the application.
     * @param properties the definition of how the recording is to be done
     */
    public PrivateRecordingSpec(Serializable requestData,
                                RecordingProperties properties)
    {
        super(properties);
    }

    /**
     * Returns the private data stored in this recording spec
     * @return the private data passed into the constructor
     */
    public Serializable getPrivateData()
    {
        return null;
    }
}
