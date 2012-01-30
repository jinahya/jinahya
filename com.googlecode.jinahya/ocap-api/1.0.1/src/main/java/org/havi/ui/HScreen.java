package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
   This class describes the final output composition of a device.  It
   ties together all the (MPEG) video decoders, all the graphics
   sub-systems and backgrounds which are all combined together before
   finally being displayed. A platform with two independent displays
   would support two instances of this class. Where a device outputs
   audio closely bound with video, that audio output can also be
   represented through this class.

   <p>

   Since an HScreen represents a single video output signal from a
   device, all the devices which contribute to that signal must have
   certain properties in common. It is not possible to select
   conflicting configurations for different devices on the same
   HScreen - for example having a video device whose logical output
   has a 4:3 picture aspect ratio and a graphics device whose logical
   output has a 16:9 picture aspect ratio. This specification
   intentionally does not define configurations, or which
   configurations would be conflicting, since these are essentially
   region or market dependent.

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

*/

public class HScreen
{    
    /**
     * private constructor to stop javadoc generating one 
     */
    private HScreen()
    {
    }

    /**
     * Returns all {@link org.havi.ui.HScreen HScreens} in this
     * system.
     *
     * @return an array of {@link org.havi.ui.HScreen HScreens}
     * representing all {@link org.havi.ui.HScreen HScreens} in this
     * system.  
     */
    public static HScreen[] getHScreens()
    {
        return (null);
    }

    /**
     * Returns the default {@link org.havi.ui.HScreen HScreen} for
     * this application.  For systems where an application is
     * associated with audio or video which is started before the
     * application starts, this method will return the {@link
     * org.havi.ui.HScreen HScreen} where that associated audio /
     * video is being output.
     *
     * @return the default {@link org.havi.ui.HScreen HScreen} for
     * this application.  
     */
    public static HScreen getDefaultHScreen()
    {
        return (null);
    }

    /**
     * Returns a list of video device for this screen. For systems
     * where an application is associated with video started before
     * the application starts, the first entry in the array returned
     * will be the video device where that video is being output.
     *
     * @return an array of {@link org.havi.ui.HVideoDevice
     * HVideoDevice} objects or null if none exist.  
     */
    public HVideoDevice[] getHVideoDevices()
    {
        return (null);
    }

    /**
     * Return the default video device for this screen. Note that the
     * {@link org.havi.ui.HVideoDevice HVideoDevice} is the default
     * device for rendering video, but it may not be capable of
     * displaying graphics / mixing it with graphics concurrently.
     *
     * @return an {@link org.havi.ui.HVideoDevice HVideoDevice} object
     * or null if none exist. 
     */
    public HVideoDevice getDefaultHVideoDevice()
    {
        return (null);
    }

     /**
     * Returns an {@link org.havi.ui.HVideoConfiguration
     * HVideoConfiguration} from an {@link org.havi.ui.HVideoDevice
     * HVideoDevice} which is present on this {@link
     * org.havi.ui.HScreen HScreen} that best matches at least one of
     * the specified {@link org.havi.ui.HVideoConfigTemplate
     * HVideoConfigTemplates}.  
     * If this is not possible, null is returned.
     * <p>
     * Equally best in this sense means that the configurations
     * satisfy an equal number of preferences with priorities {@link
     * org.havi.ui.HScreenConfigTemplate#PREFERRED PREFERRED} and
     * {@link org.havi.ui.HScreenConfigTemplate#PREFERRED_NOT
     * PREFERRED_NOT} and all preferences with priorities {@link
     * org.havi.ui.HScreenConfigTemplate#REQUIRED REQUIRED} and {@link
     * org.havi.ui.HScreenConfigTemplate#REQUIRED_NOT REQUIRED_NOT}.
     * If there are such equally best configurations, the one which is
     * returned by this method is an implementation dependent
     * selection from among those which are equally best.     
     * <p>
     * Configurations are chosen according to the following algorithm,
     * based on the priority as supplied to {@link
     * org.havi.ui.HScreenConfigTemplate#setPreference
     * setPreference}. Configurations must:
     * <p><ol>     
     * <li> satisfy ALL the preferences whose priority was {@link
     * HScreenConfigTemplate#REQUIRED REQUIRED}
     * <li> satisfy NONE of the preferences whose priority was
     * {@link HScreenConfigTemplate#REQUIRED_NOT REQUIRED_NOT}
     * <li> satisfy as many as possible of the preferences whose
     * priority was {@link HScreenConfigTemplate#PREFERRED PREFERRED}.
     * <li> satisfy as few as possible of the preferences whose
     * priority was {@link HScreenConfigTemplate#PREFERRED_NOT
     * PREFERRED_NOT}.
     * </ol><br>
     * Preferences whose priority was {@link
     * HScreenConfigTemplate#DONT_CARE DONT_CARE} are ignored. 
     * <p>
     * This method returns null if no configuration
     * exists that satisfies all {@link
     * org.havi.ui.HScreenConfigTemplate#REQUIRED REQUIRED} and {@link
     * org.havi.ui.HScreenConfigTemplate#REQUIRED_NOT REQUIRED_NOT}
     * priorities.

     * 
     * @param hvcta - the array of {@link
     * org.havi.ui.HVideoConfigTemplate HVideoConfigTemplate}
     * objects to choose from.    
     * @return an  {@link org.havi.ui.HVideoConfiguration
     * HVideoConfiguration} object that is the best matching configuration
     * possible, or null if no HVideoConfiguration 
     *  object passes the criteria.  
     */
    public HVideoConfiguration 
	getBestConfiguration(HVideoConfigTemplate[] hvcta)
    {
	return null;
    }

    /**
     * Returns a list of graphics devices for this screen.
     *
     * @return an array of {@link org.havi.ui.HGraphicsDevice
     * HGraphicsDevices} or null if none exist. 
     */
    public HGraphicsDevice[] getHGraphicsDevices()
    {
        return (null);
    }

    /**
     * Return the default graphics device for this screen. Note that
     * the {@link org.havi.ui.HGraphicsDevice HGraphicsDevice} is the
     * default device for rendering graphics, but it may not be
     * capable of displaying video / mixing it with graphics
     * concurrently.
     *
     * @return the default graphics device for this screen or null if
     * none exist. 
     */
    public HGraphicsDevice getDefaultHGraphicsDevice()
    {
        return (null);
    }

    /**
     * Returns an {@link org.havi.ui.HGraphicsConfiguration
     * HGraphicsConfiguration} from an {@link org.havi.ui.HGraphicsDevice
     * HGraphicsDevice} which is present on this {@link
     * org.havi.ui.HScreen HScreen} that best matches at least one of
     * the specified {@link org.havi.ui.HGraphicsConfigTemplate
     * HGraphicsConfigTemplates}.  If this is not possible this method
     * will attempt to construct an {@link
     * org.havi.ui.HEmulatedGraphicsConfiguration
     * HEmulatedGraphicsConfiguration} where the emulated
     * configuration best matches one of the specified {@link
     * org.havi.ui.HGraphicsConfigTemplate HGraphicsConfigTemplates}.
     * If this is not possible, null is returned.
     * <p>
     * Equally best in this sense means that the configurations
     * satisfy an equal number of preferences with priorities {@link
     * org.havi.ui.HScreenConfigTemplate#PREFERRED PREFERRED} and
     * {@link org.havi.ui.HScreenConfigTemplate#PREFERRED_NOT
     * PREFERRED_NOT} and all preferences with priorities {@link
     * org.havi.ui.HScreenConfigTemplate#REQUIRED REQUIRED} and {@link
     * org.havi.ui.HScreenConfigTemplate#REQUIRED_NOT REQUIRED_NOT}.
     * If there are such equally best configurations, the one which is
     * returned by this method is an implementation dependent
     * selection from among those which are equally best.     
     * <p>
     * Configurations are chosen according to the following algorithm,
     * based on the priority as supplied to {@link
     * org.havi.ui.HScreenConfigTemplate#setPreference
     * setPreference}. Configurations must:
     * <p><ol>     
     * <li> satisfy ALL the preferences whose priority was {@link
     * HScreenConfigTemplate#REQUIRED REQUIRED}
     * <li> satisfy NONE of the preferences whose priority was
     * {@link HScreenConfigTemplate#REQUIRED_NOT REQUIRED_NOT}
     * <li> satisfy as many as possible of the preferences whose
     * priority was {@link HScreenConfigTemplate#PREFERRED PREFERRED}.
     * <li> satisfy as few as possible of the preferences whose
     * priority was {@link HScreenConfigTemplate#PREFERRED_NOT
     * PREFERRED_NOT}.
     * </ol><br>
     * Preferences whose priority was {@link
     * HScreenConfigTemplate#DONT_CARE DONT_CARE} are ignored. 
     * <p>
     * This method returns null if no configuration
     * exists that satisfies all {@link
     * org.havi.ui.HScreenConfigTemplate#REQUIRED REQUIRED} and {@link
     * org.havi.ui.HScreenConfigTemplate#REQUIRED_NOT REQUIRED_NOT}
     * priorities.

     * 
     * @param hgcta - the array of {@link
     * org.havi.ui.HGraphicsConfigTemplate HGraphicsConfigTemplate}
     * objects to choose from.    
     * @return an  {@link org.havi.ui.HGraphicsConfiguration
     * HGraphicsConfiguration} object that is the best matching configuration
     * possible, or null if no HGraphicsConfiguration or
     * HEmulatedGraphicsConfiguration object passes the criteria.  
     */
    public HGraphicsConfiguration 
	getBestConfiguration(HGraphicsConfigTemplate[] hgcta)
    {
	return (null);
    }

    /**
     * Returns a list of background devices for this screen.
     *
     * @return an array of {@link org.havi.ui.HBackgroundDevice
     * HBackgroundDevices} or null if none exist. 
     */
    public HBackgroundDevice[] getHBackgroundDevices()
    {
        return (null);
    }

    /**
     * Return the default background device for this screen.
     *
     * @return the default background device for this screen or null if none
     * exist.
     */
    public HBackgroundDevice getDefaultHBackgroundDevice()
    {
        return (null);
    }

    /**
     * Returns an {@link org.havi.ui.HBackgroundConfiguration
     * HBackgroundConfiguration} from an {@link org.havi.ui.HBackgroundDevice
     * HBackgroundDevice} which is present on this {@link
     * org.havi.ui.HScreen HScreen} that best matches at least one of
     * the specified {@link org.havi.ui.HBackgroundConfigTemplate
     * HBackgroundConfigTemplates}, or null if this is not possible.
     * <p>
 
     * Equally best in this sense means that the configurations
     * satisfy an equal number of preferences with priorities {@link
     * org.havi.ui.HScreenConfigTemplate#PREFERRED PREFERRED} and
     * {@link org.havi.ui.HScreenConfigTemplate#PREFERRED_NOT
     * PREFERRED_NOT} and all preferences with priorities {@link
     * org.havi.ui.HScreenConfigTemplate#REQUIRED REQUIRED} and {@link
     * org.havi.ui.HScreenConfigTemplate#REQUIRED_NOT REQUIRED_NOT}.
     * If there are such equally best configurations, the one which is
     * returned by this method is an implementation dependent
     * selection from among those which are equally best.     
     * <p>
     * Configurations are chosen according to the following algorithm,
     * based on the priority as supplied to {@link
     * org.havi.ui.HScreenConfigTemplate#setPreference
     * setPreference}. Configurations must:
     * <p><ol>     
     * <li> satisfy ALL the preferences whose priority was {@link
     * HScreenConfigTemplate#REQUIRED REQUIRED}
     * <li> satisfy NONE of the preferences whose priority was
     * {@link HScreenConfigTemplate#REQUIRED_NOT REQUIRED_NOT}
     * <li> satisfy as many as possible of the preferences whose
     * priority was {@link HScreenConfigTemplate#PREFERRED PREFERRED}.
     * <li> satisfy as few as possible of the preferences whose
     * priority was {@link HScreenConfigTemplate#PREFERRED_NOT
     * PREFERRED_NOT}.
     * </ol><br>
     * Preferences whose priority was {@link
     * HScreenConfigTemplate#DONT_CARE DONT_CARE} are ignored. 
     * <p>
     * This method returns null if no configuration
     * exists that satisfies all {@link
     * org.havi.ui.HScreenConfigTemplate#REQUIRED REQUIRED} and {@link
     * org.havi.ui.HScreenConfigTemplate#REQUIRED_NOT REQUIRED_NOT}
     * priorities.

     * 
     * @param hbcta the array of {@link org.havi.ui.HBackgroundConfiguration
     * HBackgroundConfiguration} objects to choose from, represented as an
     * array of {@link org.havi.ui.HBackgroundConfiguration
     * HBackgroundConfiguration} objects.
     * @return an {@link org.havi.ui.HBackgroundConfiguration
     * HBackgroundConfiguration} object that is the best matching
     * configuration possible, or null if no HBackgroundConfiguration
     * passes the criteria.  
     */
    public HBackgroundConfiguration 
	getBestConfiguration(HBackgroundConfigTemplate[] hbcta)
    {
	return null;
    }

    /**
     * Return a coherent set of {@link
     * org.havi.ui.HScreenConfiguration HScreenConfigurations}
     * matching a set of templates. One {@link
     * org.havi.ui.HScreenConfiguration HScreenConfiguration} will be
     * returned for each {@link org.havi.ui.HScreenConfigTemplate
     * HScreenConfigTemplate} provided as input. The class of the
     * returned objects will correspond to the class of the templates
     * provided as input - where an {@link
     * org.havi.ui.HGraphicsConfigTemplate HGraphicsConfigTemplate} is
     * provided as input, an {@link org.havi.ui.HGraphicsConfiguration
     * HGraphicsConfiguration} shall be returned. Where an {@link
     * org.havi.ui.HVideoConfigTemplate HVideoConfigTemplate} is
     * provided as input, an {@link org.havi.ui.HVideoConfiguration
     * HVideoConfiguration} shall be returned. If more than one
     * template of the same type is provided then the configurations
     * returned must be on different devices but presented on the same
     * screen.  If more templates of one type are provided than there
     * are devices of that type in the system, this function will
     * return null.
     * <p>
     * Coherent means that all the required properties are respected
     * in all of the templates provided and that a configuration can
     * be returned for each template provided.
     * <p>
     * Conflicts between templates are resolved as discussed in the
     * description of {@link org.havi.ui.HScreenConfigTemplate
     * HScreenConfigTemplate}.
     * 
     * @param hscta an array of objects describing desired / required
     * configurations. If a zero-length array is passed this
     * function will throw a java.lang.IllegalArgumentException.
     * @return an array of non-null objects describing a coherent set
     * of screen device configurations or null if no such coherent set
     * is possible.
     */
    public HScreenConfiguration[] 
	getCoherentScreenConfigurations(HScreenConfigTemplate[] hscta)
    {
        return (null);
    }

    /**
     * Modify the settings for a set of {@link HScreenDevice
     * HScreenDevices}, based on their {@link HScreenConfiguration
     * HScreenConfigurations} supplied. Settings should be modified
     * atomically (where possible) or should not be modified if the
     * {@link HScreenConfiguration HScreenConfigurations} can be
     * determined to be conflicting a priori, i.e. are not "coherent",
     * or would cause an exception to be thrown.
     *
     * @param hsca the array of configurations that should be applied
     * atomically (where possible). If the length of this array is
     * zero a java.lang.IllegalArgumentException will be thrown.
     * @return A boolean indicating whether all {@link
     * HScreenConfiguration HScreenConfigurations} could be applied
     * successfully. If all of the {@link HScreenConfiguration
     * HScreenConfigurations} could not be applied successfully, the
     * configuration after this method may not match the configuration
     * of the devices prior to this method being called ---
     * applications should take steps to determine whether a partial
     * change of settings has been made on each device.
     * @exception java.lang.SecurityException if the application does
     * not have sufficient rights to set the {@link
     * HScreenConfiguration HScreenConfiguration} for any of the
     * devices.
     * @exception HPermissionDeniedException if the
     * application does not currently have the right to set the
     * configuration for any of the devices.
     * @exception HConfigurationException if the
     * specified {@link HScreenConfiguration HScreenConfiguration[]}
     * array is not valid for any of the devices.  
     */
    public boolean setCoherentScreenConfigurations(HScreenConfiguration[] hsca)
	throws java.lang.SecurityException, 
	       org.havi.ui.HPermissionDeniedException,
	       org.havi.ui.HConfigurationException
    {
        return (true);
    }
}

