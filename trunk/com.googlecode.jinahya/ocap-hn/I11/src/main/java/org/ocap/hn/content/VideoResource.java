package org.ocap.hn.content;

import java.awt.Dimension;

/**
 * ContentResource to identify that a content item contains video/still image
 * material.
 */
public interface VideoResource extends ContentResource
{

    /**
     * Returns the resolution of the video/still image.
     * 
     * @return the resolution of the video/still image or null if the resolution is not known.
     */
    public Dimension getResolution();

    /**
     * Returns the color depth (in bits) of the video/still image.
     * 
     * @return the color depth (in bits) of the video/still image or -1 if the color depth is not known.
     */
    public int getColorDepth();

}
