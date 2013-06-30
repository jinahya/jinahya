package org.ocap.shared.media;

import javax.media.Control;

/**
 * This interface provides single frame move control for a Player
 * presenting video, for example an MPEG video stream.
 * This control SHALL be added to any JMF Player presenting a video
 * stream where trick modes can be applied.
 */
public interface FrameControl extends Control
{
    /**
     * Indicates a frame move in a forward direction in the stream.
     */
    public static final boolean FORWARD = true;

    /**
     * Indicates a frame move in a reverse direction in the stream.
     */
    public static final boolean REVERSE = false;

    /**
     * Moves one frame in a video stream.<p>If the parameter is FORWARD
     * then the Player SHALL move forward in the stream to the next suitable frame,
     * assuming there is one following the current location (i.e.
     * there is a suitable frame between the current location and the
     * end of the content).
     * If the stream is an MPEG video stream, the next suitable frame
     * is the next I or P frame, whichever is closest.
     * <p>If the parameter is REVERSE then the Player SHALL move in 
     * reverse to the previous suitable frame, assuming there is one
     * before the current location. If the stream
     * is an MPEG video stream the previous suitable frame is the
     * previous I frame.
     * 
     * @param direction The direction to move.
     * 
     * @return True if the move occurred, false if the move could not
     *      occur for any reason, e.g. there is no suitable frame
     *      between the current location and either the start or
     *      end of the content.
     */
    public boolean move(boolean direction);
}

