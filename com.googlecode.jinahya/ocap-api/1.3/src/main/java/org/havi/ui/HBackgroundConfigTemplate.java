package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
   The {@link org.havi.ui.HBackgroundConfigTemplate}
   class is used to obtain a valid {@link
   org.havi.ui.HBackgroundConfiguration}. An
   application instantiates one of these objects and then sets all
   non-default attributes as desired. The {@link
   org.havi.ui.HBackgroundDevice#getBestConfiguration}
    method found in the {@link
   org.havi.ui.HBackgroundDevice} class is then
   called with this {@link org.havi.ui.HBackgroundConfigTemplate}
   .  A valid {@link
   org.havi.ui.HBackgroundConfiguration} is
   returned that meets or exceeds what was requested in the {@link
   org.havi.ui.HBackgroundConfigTemplate}.
   <p> 
   This class may be subclassed to support additional
   properties of background configurations which may be requested by applications.

   <p>
   The {@link org.havi.ui.HScreenConfigTemplate#ZERO_VIDEO_IMPACT}
   property may be used in instances of this class
   to discover whether displaying background stills will have any
   impact on already running video. Implementations supporting the
   {@link org.havi.ui.HBackgroundConfigTemplate#STILL_IMAGE}
   preference shall return an {@link
   org.havi.ui.HStillImageBackgroundConfiguration} when requested except as
   described below.

   <p><ul>
   <li>If displaying an {@link
   org.havi.ui.HBackgroundConfigTemplate#STILL_IMAGE}
   interrupts video transiently while the image is decoded then a
   configuration shall not be returned if the {@link
   org.havi.ui.HScreenConfigTemplate#ZERO_VIDEO_IMPACT}
   property is present with the priority {@link
   org.havi.ui.HScreenConfigTemplate#REQUIRED}.
   <li>If displaying an {@link
   org.havi.ui.HBackgroundConfigTemplate#STILL_IMAGE}
   interrupts video while the image is decoded and for the entire
   period while the image is displayed then a configuration shall not
   be returned if the {@link
   org.havi.ui.HScreenConfigTemplate#ZERO_VIDEO_IMPACT}
   property is present with either the priorities
   {@link org.havi.ui.HScreenConfigTemplate#REQUIRED} or
   {@link org.havi.ui.HScreenConfigTemplate#PREFERRED}.
   </ul>

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
 
   @see HScreenConfigTemplate
   @see HGraphicsConfigTemplate
   @see HVideoConfigTemplate

*/

public class HBackgroundConfigTemplate
    extends HScreenConfigTemplate
{
    /**
     * A value for use in the preference field of the {@link
     * org.havi.ui.HScreenConfigTemplate#setPreference setPreference}
     * and {@link
     * org.havi.ui.HScreenConfigTemplate#getPreferencePriority
     * getPreferencePriority} methods in the {@link
     * org.havi.ui.HBackgroundConfigTemplate
     * HBackgroundConfigTemplate} that indicates that a single color
     * background is requested where that single color can be changed
     * by applications.  
     */
    public static final int CHANGEABLE_SINGLE_COLOR = 0x0A;

    /**
     * A value for use in the preference field of the {@link
     * org.havi.ui.HScreenConfigTemplate#setPreference setPreference}
     * and {@link
     * org.havi.ui.HScreenConfigTemplate#getPreferencePriority
     * getPreferencePriority} methods in the {@link
     * org.havi.ui.HBackgroundConfigTemplate
     * HBackgroundConfigTemplate} that indicates that a background
     * which can support still images is requested. Where backgrounds
     * supporting this feature are returned, they are returned as
     * objects of the {@link
     * org.havi.ui.HStillImageBackgroundConfiguration
     * HStillImageBackgroundConfiguration} class.  
     */
    public static final int STILL_IMAGE             = 0x0B;

    /**
     * Creates an {@link org.havi.ui.HBackgroundConfigTemplate}
     * object. See the class description
     * for details of constructor parameters and default values.  
     */
    public HBackgroundConfigTemplate()
    {
    }

    /**
     * Returns a boolean indicating whether or not the specified
     * {@link org.havi.ui.HBackgroundConfiguration}
     * can be used to create a background
     * plane that supports the features set in this template.
     *
     * @param hbc - the {@link org.havi.ui.HBackgroundConfiguration}
     * object to test against this template. 
     * @return true if this {@link
     * org.havi.ui.HBackgroundConfiguration}
     * object can be used to create a background plane that supports
     * the features set in this template, false otherwise.  
     */
    public boolean isConfigSupported(HBackgroundConfiguration hbc)
    {
        return (true);
    }

    /**
     * Set the indicated preference to have the specified priority. If
     * the preference has been previously set, then the previous
     * priority for the preference shall be overwritten.
     * <p>
     * Attributes that are not filled in in a template (through {@link
     * org.havi.ui.HScreenConfigTemplate#setPreference}),
     * shall have the priority {@link
     * org.havi.ui.HScreenConfigTemplate#DONT_CARE}. Any
     * configuration always satisfies these attributes.
     *
     * @param preference the preference to be indicated. Valid values 
     * for an {@link org.havi.ui.HBackgroundConfigTemplate}
     * are:
     * {@link org.havi.ui.HScreenConfigTemplate#ZERO_BACKGROUND_IMPACT},
     * {@link org.havi.ui.HScreenConfigTemplate#ZERO_GRAPHICS_IMPACT},
     * {@link org.havi.ui.HScreenConfigTemplate#ZERO_VIDEO_IMPACT},
     * {@link org.havi.ui.HScreenConfigTemplate#INTERLACED_DISPLAY},
     * {@link org.havi.ui.HScreenConfigTemplate#FLICKER_FILTERING},
     * {@link org.havi.ui.HScreenConfigTemplate#VIDEO_GRAPHICS_PIXEL_ALIGNED},
     * {@link org.havi.ui.HBackgroundConfigTemplate#CHANGEABLE_SINGLE_COLOR}
     * and 
     * {@link org.havi.ui.HBackgroundConfigTemplate#STILL_IMAGE}.
     * <p>
     * Subclasses may add further valid values. An
     * IllegalArgumentException shall be thrown if the preference is
     * not a valid value for this instance of {@link
     * org.havi.ui.HBackgroundConfigTemplate}
     * @param priority the priority of the preference. Valid values are:
     * {@link org.havi.ui.HScreenConfigTemplate#REQUIRED},
     * {@link org.havi.ui.HScreenConfigTemplate#PREFERRED},
     * {@link org.havi.ui.HScreenConfigTemplate#DONT_CARE},
     * {@link org.havi.ui.HScreenConfigTemplate#PREFERRED_NOT} and
     * {@link org.havi.ui.HScreenConfigTemplate#REQUIRED_NOT}. 
     * <p>
     * If <code>priority</code> is not a valid priority as defined here a 
     * java.lang.IllegalArgumentException will be thrown. 
     */
    public void setPreference(int preference, int priority)
    {
    }

    /**
     * Return the priority for the specified preference.
     * <p>
     * By default the preferences in a template returned from the
     * system will have a {@link
     * org.havi.ui.HScreenConfigTemplate#DONT_CARE}
     * priority unless specified otherwise.  Any configuration always
     * satisfies these attributes.
     *
     * @param preference the preference to be indicated. Valid values 
     * for an {@link org.havi.ui.HBackgroundConfigTemplate}
     * are:
     * {@link org.havi.ui.HScreenConfigTemplate#ZERO_BACKGROUND_IMPACT},
     * {@link org.havi.ui.HScreenConfigTemplate#ZERO_GRAPHICS_IMPACT},
     * {@link org.havi.ui.HScreenConfigTemplate#ZERO_VIDEO_IMPACT},
     * {@link org.havi.ui.HScreenConfigTemplate#INTERLACED_DISPLAY},
     * {@link org.havi.ui.HScreenConfigTemplate#FLICKER_FILTERING},
     * {@link org.havi.ui.HScreenConfigTemplate#VIDEO_GRAPHICS_PIXEL_ALIGNED},
     * {@link org.havi.ui.HScreenConfigTemplate#PIXEL_ASPECT_RATIO},
     * {@link org.havi.ui.HScreenConfigTemplate#PIXEL_RESOLUTION},
     * {@link org.havi.ui.HScreenConfigTemplate#SCREEN_RECTANGLE},
     * {@link org.havi.ui.HBackgroundConfigTemplate#CHANGEABLE_SINGLE_COLOR}
     * and 
     * {@link org.havi.ui.HBackgroundConfigTemplate#STILL_IMAGE}.
     * <p>
     * Subclasses may add further valid values. An
     * IllegalArgumentException shall be thrown if the preference is
     * not a valid value for this instance of {@link
     * org.havi.ui.HBackgroundConfigTemplate}
     * @return the priority for the specified preference.  */
    public int getPreferencePriority(int preference)
    {
        return (REQUIRED);
    }
}

