package org.ocap.shared.dvr;

/**
 * This interface a represents a recorded service that has been
 * segmented.  A recorded service may be segmented for various
 * reasons, e.g. power cycle, loss and reacquisition of resources,
 * change of elementary stream information. A segmented recording
 * is not created until actual recording begins and will always
 * contain at least one segment.
 */
public interface SegmentedRecordedService extends RecordedService
{
    /**
     * Gets the segments the recording was split up into.
     * 
     * @return An array ordered by ascending time the segments
     *      were recorded.
     */
    public RecordedService [] getSegments();

    /**
     * Gets the start media times for the segments in the media time
     * line created by the implementation for playing across all
     * segments.  This array is parallel to the array returned by
     * the getSegments method.  For instance, the media time in the
     * second location [1] of the array returned by this method is the
     * start media time for the RecordedService in the second 
     * location [1] in the array returned by the getSegments method.
     * 
     * @return Array of start media times for segments.  The length
     *      is the same as the array returned by the getSegments
     *      method.
     * 
     */
    public javax.media.Time [] getSegmentMediaTimes();
}


