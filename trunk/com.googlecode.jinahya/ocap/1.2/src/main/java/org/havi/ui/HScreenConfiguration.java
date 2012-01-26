package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */


import java.awt.Point;
import java.awt.Dimension;

/**
   The {@link org.havi.ui.HScreenConfiguration HScreenConfiguration}
   class describes the characteristics (settings) of an {@link
   org.havi.ui.HScreenDevice HScreenDevice}. There can be many {@link
   org.havi.ui.HScreenConfiguration HScreenConfiguration} objects
   associated with a single {@link org.havi.ui.HScreenDevice
   HScreenDevice}.

 @see  org.havi.ui.HScreenDevice

*/

public abstract class HScreenConfiguration
    extends Object
{
    
    /**
     * package scope constructor to stop javadoc generating one 
     */
    HScreenConfiguration()
    {
    }

    /**
     * Convert a pixel position from one coordinate system to another
     * without including any rounding errors from passing through
     * normalized coordinates. This returns null if this
     * transformation isn't possible for various reasons. These
     * reasons are:     
     * <ul>     
     * <li> at least one of the two {@link HScreenConfiguration
     * HScreenConfigurations} isn't pixel based or doesn't yet have a
     * fixed location on the HScreen.     
     * <li> a non-linear transformation is in use between the two.
     * <li> the information needed to calculate this isn't available.
     * <li> the transformation is changing with time (e.g. due to pan
     * & scan).     
     * </ul>
     * <p>     
     * The source position is interpreted in the coordinate system of
     * the HScreenConfiguration object on which this method is
     * called.
     *
     * @param destination the destination {@link HScreenConfiguration
     * HScreenConfiguration}.
     * @param source the pixel position in this {@link
     * HScreenConfiguration HScreenConfiguration}.
     * @return the position of the specified pixel position measured
     * in the destination coordinate system, or null if this isn't
     * possible.  
     */
    public Point convertTo(HScreenConfiguration destination,
			   Point source)
    {
        return (null);
    }

    /**
     * Return whether this configuration includes filtering to reduce
     * interlace flicker.
     *
     * @return true if filtering is included, false otherwise.  
     */
    public boolean getFlickerFilter()
    {
        return (false);
    }

    /**
     * Return whether this configuration is interlaced
     *
     * @return true if this configuration is interlaced, false
     * otherwise.  
     */
    public boolean getInterlaced()
    {
        return (false);
    }

    /**
     * Return the pixel aspect ratio of this configuration.
     * Some examples are {16:15}, {64:45}, {1:1}.
     *
     * @return the aspect ratio of the pixels in this configuration.
     */
    public Dimension getPixelAspectRatio()
    {
        return (null);
    }

    /**
     * Return the resolution of this configuration in pixels.  The
     * pixel coordinate system used is that of the device concerned.
     *
     * @return the resolution of this configuration in pixels.  
     */
    public Dimension getPixelResolution()
    {
        return null;
    }

    /**
     * Return the position and size of this configuration on the
     * screen in screen coordinates.
     *
     * @return the area on the screen of this configuration in
     * screen coordinates.  
     */
    public HScreenRectangle getScreenArea()
    {
        return (null);
    }

    /**
     * Returns the offset between the origin of the pixel coordinate
     * space of the specified {@link org.havi.ui.HScreenConfiguration
     * HScreenConfiguration}, and the origin of the current pixel
     * coordinate space of this {@link
     * org.havi.ui.HScreenConfiguration HScreenConfiguration}.  The
     * offset is returned in the pixel coordinate space of this {@link
     * org.havi.ui.HScreenConfiguration HScreenConfiguration}.
     *
     * @param hsc the {@link org.havi.ui.HScreenConfiguration
     * HScreenConfiguration} to which the offset between pixel origins
     * should be recovered.
     * @return the offset between the pixel coordinate space of the
     * specified {@link org.havi.ui.HScreenConfiguration
     * HScreenConfiguration} and the current pixel coordinate space of
     * this {@link org.havi.ui.HScreenConfiguration
     * HScreenConfiguration}. A null object will be returned if there
     * is insufficient information to recover the pixel offset.
     */
    public java.awt.Dimension getOffset(HScreenConfiguration hsc)
    {
        return (null);
    }
}

