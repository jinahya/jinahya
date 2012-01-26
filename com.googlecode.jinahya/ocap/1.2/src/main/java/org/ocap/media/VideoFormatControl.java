package org.ocap.media;

import javax.media.Controller;


/**
 * This interface extends org.dvb.media.VideoFormatControl to provide access
 * to OCAP-specific info signaled in presented video, such as 3D formatting data.
 * <p>
 * All instances of {@link org.dvb.media.VideoFormatControl} returned from calls to
 * {@link javax.media.Controller#getControl} or {@link javax.media.Controller#getControls}
 * within an OCAP implementation SHALL also be instances of
 * <code>org.ocap.media.VideoFormatControl</code>.
 */
public interface VideoFormatControl extends org.dvb.media.VideoFormatControl
{

    /**
     * Returns the 3D configuration info of the video.
     * See [OCCEP] for the 3D formatting data definition.
     * Returns <code>null</code> if no 3D formatting data is present
     * (e.g., in the case of 2D video).  Note: Rapid changes in
     * 3D signaling may cause the returned S3DConfiguration object
     * to be stale as soon as this method completes.
     * 
     *
     * @return The signaled 3D formatting data, or <code>null</code> if no
     * 3D formatting data is present.
     */
    public S3DConfiguration getS3DConfiguration();
}
