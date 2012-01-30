package org.ocap.media;

import org.dvb.media.VideoFormatEvent;

/**
 * This class represents an event that will be reported to an application with an
 * <code>org.ocap.media.VideoFormatControl</code>.  For a presenting
 * <code>Player</code> the implementation SHALL monitor the signaling of
 * 3D formatting data, as defined by [OCCEP], and generate this event when:
 * <ul>
 *  <li>3D formatting data is signaled after presentation starts,</li>
 *  <li>3D formatting data changes,</li>
 *  <li>3D formatting data is no longer signaled.</li>
 * </ul>
 *
 * @see org.ocap.media.VideoFormatControl
 */
public class S3DSignalingChangedEvent extends VideoFormatEvent
{

    /**
     * 3D formatting data in content transitioned from no 3D
     * formatting data present (i.e., 2D content), to 3D formatting data present
     * in content.
     */
    public static final int TRANSITION_FROM_2D_TO_3D = 1;

    /**
     * 3D formatting data in content transitioned from 3D formatting data present
     * in content to no 3D formatting data present in content (i.e., 2D content).
     */
    public static final int TRANSITION_FROM_3D_TO_2D = TRANSITION_FROM_2D_TO_3D + 1;

    /**
     * 3D formatting data in content transitioned from one format to another;
     * e.g., Side by Side to Top and Bottom, Top and Bottom to Side by Side.
     */
    public static final int TRANSITION_OF_3D_FORMAT = TRANSITION_FROM_3D_TO_2D + 1;


    /**
     * Constructs an event.
     *
     * @param source The source of the event.
     *
     * @param transitionType Indicates the type of content format change.
     *      When the content type transitions
     *      from 2D to 3D this parameter is set to
     *      <code>TRANSITION_FROM_2D_TO_3D</code>.  When the content type
     *      transitions from 3D to 2D this parameter is set to
     *      <code>TRANSITION_FROM_3D_TO_2D</code>.  When the content type
     *      transitions between 3D formats this parameter is set to
     *      <code>TRANSITION_OF_3D_FORMAT</code>.
     *
     * @param config The 3D configuration that was signaled.
     *               The value SHALL be <code>null</code> when 2D content
     *               is currently signaled.
     */
    public S3DSignalingChangedEvent(javax.media.Player source,
                                            int transitionType,
                                            S3DConfiguration config)
    {
        super(source);
    }
    

    /**
     * Gets the <code>transitionType</code> value passed to the constructor.
     *
     * @return The 3D signaling transition type.
     */
    public int getTransitionType()
    {
        return 0;
    }


    /**
     * Gets the <code>config</code> value passed to the constructor.
     *
     * @return The signaled 3D configuration, or <code>null</code> if 2D
     *         content is currently signaled.  Note: Rapid changes in
     * 		   3D signaling may cause the returned S3DConfiguration object
     * 		   to be stale as soon as this method completes.
     */
    public S3DConfiguration getConfig()
    {
        return null;
    }
}
