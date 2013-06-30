package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
   The {@link org.havi.ui.HBackgroundConfiguration}
   class describes the characteristics
   (settings) of an {@link org.havi.ui.HBackgroundDevice}.
   There can be many {@link
   org.havi.ui.HBackgroundConfiguration}
   objects associated with a single {@link
   org.havi.ui.HBackgroundDevice} . <p> The basic
   background configuration supports backgrounds of a single
   color. More sophisticated backgrounds can be supported by defining
   new classes inheriting from this class. Where a device has a single
   non- changeable background color, this class will provide
   applications the ability to read that color however all attempts
   to reserve control of the background will fail.

  <hr>
  The parameters to the constructors are as follows, in cases where
  parameters are not used, then the constructor should use the default
  values.
  <p>
  <h3>Default parameter values exposed in the constructors</h3>
  <table border>
  <tr><th>Parameter</th><th>Description</th><th>Default value</th> 
  <th>Set method</th><th>Get method</th></tr>
  <tr><td colspan=5>None.</td></tr>
  </table>
  <h3>Default parameter values not exposed in the constructors</h3>
  <table border>
  <tr><th>Description</th><th>Default value</th><th>Set method</th>
  <th>Get method</th></tr>
  <tr><td colspan=4>None.</td></tr>
  </table>

  @see HBackgroundDevice
*/

public class HBackgroundConfiguration
    extends HScreenConfiguration
{
    /**
     * It is not intended that applications should directly construct
     * {@link org.havi.ui.HBackgroundConfiguration} objects.
     * <p>
     * Creates an {@link org.havi.ui.HBackgroundConfiguration}
     * object. See the class description for
     * details of constructor parameters and default values.
     */
    protected HBackgroundConfiguration()
    {
    }

    /**
     * Returns the {@link org.havi.ui.HBackgroundDevice}
     * associated with this {@link
     * org.havi.ui.HBackgroundConfiguration}.
     *
     * @return the {@link org.havi.ui.HBackgroundDevice
     * HBackgroundDevice} object that is associated with this {@link
     * org.havi.ui.HBackgroundConfiguration}.
     */
    public HBackgroundDevice getDevice()
    {
        return (null);
    }

    /**
     * Returns an {@link org.havi.ui.HBackgroundConfigTemplate}
     * object that describes and uniquely
     * identifies this {@link org.havi.ui.HBackgroundConfiguration}.
     * Hence, the following sequence
     * should return the original {@link
     * org.havi.ui.HBackgroundConfiguration}
     * <pre>
     * HBackgroundDevice.getBestMatch(HBackgroundConfiguration.getConfigTemplate())
     * </pre>
     * <p> 
     * Features that are implemented in the {@link
     * org.havi.ui.HBackgroundConfiguration}
     * will return {@link HScreenConfigTemplate#REQUIRED}
     * priority. Features that are not implemented in the {@link
     * org.havi.ui.HBackgroundConfiguration}
     * will return {@link HScreenConfigTemplate#REQUIRED_NOT}
     * priority. Preferences that are not filled in by
     * the platform will return {@link
     * HScreenConfigTemplate#DONT_CARE} priority.
     *
     * @return an {@link org.havi.ui.HBackgroundConfigTemplate}
     * object which both describes and
     * uniquely identifies this {@link
     * org.havi.ui.HBackgroundConfiguration}.
     */
    public HBackgroundConfigTemplate getConfigTemplate()
    {
        return (null);
    }

    /**
     * Obtain the current color of this background. This method may
     * be called without ownership of the resource. The value returned
     * is not guaranteed to be the value set in the last call to
     * {@link org.havi.ui.HBackgroundConfiguration#setColor}
     * since platforms may offer a reduced color space for
     * backgrounds and the actual value used will be returned.
     *
     * @return the current Color 
     */
    public java.awt.Color getColor()
    {
        return (null);
    }

    /**
     * Set the current color of this background. On platforms where
     * there is a sub-class of java.awt.Color supporting transparency
     * of any kind, passing an object representing a non-opaque color
     * is illegal.  Platforms with a limited color resolution for
     * backgrounds may approximate this value to the nearest
     * available. The {@link
     * org.havi.ui.HBackgroundConfiguration#getColor} method
     * will return the actual value used.
     *
     * @param color the color to be used for the background
     * @exception HPermissionDeniedException if the application has
     * not currently reserved the HBackgroundDevice associated with
     * this configuration or this configuration is not the current
     * configuration of that HBackgroundDevice.
     * @exception HConfigurationException if the color specified is illegal
     * for this platform.  
     */
    public void setColor(java.awt.Color color)
	throws org.havi.ui.HPermissionDeniedException,
	       org.havi.ui.HConfigurationException
    {
    }
}

