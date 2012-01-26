package org.ocap.dvr;

import java.util.EventListener;
import javax.tv.service.selection.ServiceContext;

/**
 * This interface represents a listener that can be added to listen for
 * recording playback start.  The implementation SHALL notify a listener
 * once when a recording playback starts.  For purposes of this listener
 * playback is considered ongoing while the presenting
 * <code>ServiceContext</code> is in the presenting state regardless of
 * trick mode.  This listener is specific to <code>ServiceContext</code>
 * recording playback and does not notify for discreet
 * <code>Player</code> based recording playback.
 */
public interface RecordingPlaybackListener extends EventListener
{
    /**
     * Notifies the listener a recording playback has started.  The
     * implementation SHALL create a new carousel Id for any
     * artificial carousel in each playback.  The carouselIDs parameter
     * SHALL reference broadcast carousels when stored with a recorded
     * service.  An artificial carousel ID shall not conflict with a
     * carousel ID of a signaled carousel that was also stored with the
     * recorded service and presented by the context parameter.
     * An artificial carousel ID MAY conflict with other carousel IDs.
     * 
     * @param context The <code>ServiceContext</code> presenting the 
     *      recorded service.
     * @param artificialCarouselID Carousel ID for an artificial carousel
     *      that MAY have been created for the recording being played back.
     *      A value of -1 indicates no artificial carousel was created.
     * @param carouselIDs Array of carousel IDs associated with broadcast
     *      carousels stored with the recording being played back.  If no
     *      carousels are contained a zero length array is passed in.
     */
    public void notifyRecordingPlayback(ServiceContext context,
                                        int artificialCarouselID,
                                        int [] carouselIDs);
}
