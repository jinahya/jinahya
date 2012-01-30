package org.ocap.shared.dvr;

import javax.tv.service.selection.InvalidServiceComponentException;
import javax.tv.locator.Locator;
import java.util.Date;

/**
 * Specifies a recording request in terms of one or more Locators.<p>
 * If multiple locators are contained within the source, all of
 * them MUST be part of the same service. <p>
 * When instances of this class are passed to RecordingManager.record(..),
 * the following additional failure mode shall apply - if the end time 
 * (computed as the start time + the duration) is in the past when the 
 * record method is called, the record method shall throw an IllegalArgumentException.<p>
 * When an instance of this recording spec is passed in as a parameter to the 
 * RecordingRequest.reschedule(..) method, an IllegalArgumentException 
 * shall be thrown if either of the following apply;<ul>
 * <li>if the source is different from the source 
 * specified in the current recording spec for the recording request 
 * and if the recording request is in progress state.
 * <li>if the properties parameter of the instance is an instance of an application defined class
 * </ul><p>
 * When instances of this class are passed to RecordingManager.record(..),
 * if the start time is in the past and either<ul>
 * <li>none of the content concerned is already recorded
 * <li>some of the content concerned is already recorded but the implementation 
 * does not support including already recorded content in a scheduled recording
 * </ul>
 * then the current time shall be used as the start time and the duration
 * reduced accordingly. The present document does not require implementations
 * to include already recorded content in scheduled recordings however GEM
 * recording specifications may require this.
 */

public class LocatorRecordingSpec extends RecordingSpec
{
    /**
     * Constructor
     * @param source Source of streams to be recorded. Implementations shall
     * make a copy of this array before the constructor returns.
     * @param startTime Start time of the recording. Changes to this parameter
     * after the constructor returns shall have no effect on the actual
     * start time of the recording.
     * @param duration Length of time to record in milli-seconds
     * @param properties the definition of how the recording is to be done
     * @throws InvalidServiceComponentException if all of the locators in the
     * source parameter are not all in the same service.
     * @throws IllegalArgumentException if duration is negative.
     */
    public LocatorRecordingSpec(Locator[] source, Date startTime, long duration,
                                RecordingProperties properties)
    throws InvalidServiceComponentException
    {
        super(properties);
    }

    /**
     * Returns the source of the recording
     * @return a copy of the source passed into the constructor
     */
    public Locator[] getSource()
    {
        return null;
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

