package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Dimension;
import org.havi.ui.event.*;
import org.davic.resources.*;

/**
   An instance of the {@link org.havi.ui.HScreen HScreen} class
   represents a single independent video output signal from a
   device. Devices with multiple independent video output signals
   should support multiple instances of this class. A video output
   signal is created by adding together the contributions from the
   devices represented by a number of objects inheriting from the
   {@link org.havi.ui.HScreenDevice HScreenDevice} class. These can be
   {@link org.havi.ui.HGraphicsDevice HGraphicsDevice} objects, {@link
   org.havi.ui.HVideoDevice HVideoDevice} objects and {@link
   org.havi.ui.HBackgroundDevice HBackgroundDevice} objects.  A given
   {@link org.havi.ui.HScreen HScreen} may support any number of any
   of these objects as far as the API is concerned however some form
   of profiling may restrict this. In reality right now, one instance
   of each is all that may reasonably expected to be present.

   <p>   
   Each {@link org.havi.ui.HScreenDevice HScreenDevice} can have
   multiple settings ({@link org.havi.ui.HScreenConfiguration
   HScreenConfigurations}) but only one &quot;setting&quot; ({@link
   org.havi.ui.HScreenConfiguration HScreenConfiguration}) can be
   active at any point in time. The current configuration can be
   determined on the {@link org.havi.ui.HScreenDevice HScreenDevice}
   subclasses using their specific getCurrentConfiguration
   methods. The current configuration can be modified on the {@link
   org.havi.ui.HScreenDevice HScreenDevice} subclasses using their
   specific setCurrentConfiguration methods (assuming sufficient
   rights, etc.).

   <p>  
   Applications may select the best of these configurations for them

   by creating an instance of {@link org.havi.ui.HScreenConfigTemplate
   HScreenConfigTemplate} and populating that with a number
   preferences each with a priority. The implementation then matches
   this template against the range of possible configurations and
   attempts to find one which matches the template
   provided. Priorities {@link HScreenConfigTemplate#REQUIRED
   REQUIRED} and {@link HScreenConfigTemplate#REQUIRED_NOT
   REQUIRED_NOT} must be respected. If they cannot be respected then
   the method call shall fail and not return any
   configuration. Priorities {@link HScreenConfigTemplate#PREFERRED
   PREFERRED} and {@link HScreenConfigTemplate#PREFERRED_NOT
   PREFERRED_NOT} should be respected as much as possible.

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

public class HScreenDevice
    implements org.davic.resources.ResourceProxy, 
	       org.davic.resources.ResourceServer
{     
    /**
     * package level constructor to stop javadoc making a public one 
     */
    HScreenDevice()
    {
    }

    /**
     * Returns the identification string associated with this {@link
     * org.havi.ui.HScreenDevice HScreenDevice}.
     *
     * @return an identification string 
     */
    public String getIDstring()
    {
        return (null);
    }

    /**
     * Add an {@link org.havi.ui.event.HScreenConfigurationListener
     * HScreenConfigurationListener} to this device, which is notified
     * whenever the device's configuration is modified.  If the
     * listener has already been added further calls will add further
     * references to the listener, which will then receive multiple
     * copies of a single event.
     *
     * @param hscl the {@link
     * org.havi.ui.event.HScreenConfigurationListener
     * HScreenConfigurationListener} to be added to this device.  
     */
    public void addScreenConfigurationListener(HScreenConfigurationListener hscl)
    {
    }

    /**
     * Add an {@link org.havi.ui.event.HScreenConfigurationListener
     * HScreenConfigurationListener} to this device, which is notified
     * when the device's configuration is further modified so that it
     * is no longer compatible with the specified {@link
     * org.havi.ui.HScreenConfigTemplate HScreenConfigTemplate}. If
     * the listener has already been added further calls will add
     * further references to the listener, which will then receive
     * multiple copies of a single event.
     * <p>
     * Note that if the device configuration does not match the
     * specified template, then the listener should be added and a
     * {@link org.havi.ui.event.HScreenConfigurationEvent
     * HScreenConfigurationEvent} immediately generated for the
     * specified {@link org.havi.ui.event.HScreenConfigurationListener
     * HScreenConfigurationListener}.
     *
     * @param hscl the {@link
     * org.havi.ui.event.HScreenConfigurationListener
     * HScreenConfigurationListener} to be added to this device.
     * @param hsct the {@link org.havi.ui.HScreenConfigTemplate
     * HScreenConfigTemplate} which is to be used to determine
     * compatibility with the device configuration.  
     */
    public void addScreenConfigurationListener(HScreenConfigurationListener hscl, 
					       HScreenConfigTemplate hsct)
    {
    }

    /**
     * Remove an {@link org.havi.ui.event.HScreenConfigurationListener
     * HScreenConfigurationListener} from this device. if the
     * specified listener is not registered, the method has no effect.
     * If multiple references to a single listener have been
     * registered it should be noted that this method will only remove
     * one reference per call.
     *
     * @param hscl the {@link
     * org.havi.ui.event.HScreenConfigurationListener
     * HScreenConfigurationListener} to be removed from this device.
     */
    public void removeScreenConfigurationListener(HScreenConfigurationListener hscl)
    {
    }

    /**
     * Return the aspect ratio of the screen as far as is known.  i.e.
     * 4:3, 16:9, etc.
     * <p>
     * This Dimension may be used to determine the pixel aspect ratio
     * for given {@link org.havi.ui.HScreenConfiguration
     * HScreenConfigurations}.
     *
     * @return a Dimension object specifying the aspect ratio of the
     * screen 
     */
    public Dimension getScreenAspectRatio()
    {
        return (null);
    }

    /**
     * Requests the right to call any method which may otherwise throw
     * an HPermissionDeniedException.  If this method returns true this
     * exception will never be thrown until this right is revoked as
     * notified by methods on {@link
     * org.davic.resources.ResourceClient ResourceClient}.  The policy
     * by which the platform decides whether or not to grant this
     * right is not defined in this specification.
     * <p>
     * Note that the word &quot;right&quot; in this context has
     * nothing to do with security. See the description of {@link
     * org.havi.ui.HPermissionDeniedException
     * HPermissionDeniedException}.
     * <p>
     * Once the right to control this device has been granted and not
     * removed in the intervening period further calls to this method
     * re-using the current resource client shall have no effect and
     * return true.
     * 
     * @param client a representation of the intended owner of the
     * resource
     * @return true if the right is granted, otherwise false 
     */
    public boolean reserveDevice(ResourceClient client)
    {
        return (false);
    }

    /**
     * Release the right to control of this device.  If this
     * application doesn't have this right then this method has no
     * effect. It is not specified whether any device configuration
     * set by this application will be removed from display
     * immediately or whether it will remain on display until a
     * subsequent application obtains the device and sets its own
     * configuration. Applications wishing to ensure a configuration
     * they have installed is removed must actively remove it before
     * calling this method. 
     */
    public void releaseDevice()
    {
    }

    /**
     * Return the last {@link org.davic.resources.ResourceClient
     * ResourceClient} passed to the last successful call to the
     * {@link org.havi.ui.HScreenDevice#reserveDevice reserveDevice}
     * method of this instance of {@link org.havi.ui.HScreenDevice
     * HScreenDevice}, or null if this method has not been called on
     * this instance.
     *
     * @return a representation of the intended owner of the resource
     * or null if none has been set.
     */
    public ResourceClient getClient()
    {
        return (null);
    }

    /**
     * Register a listener for events about changes in the state of
     * the ownership of this device. If the
     * listener has already been added further calls will add further
     * references to the listener, which will then receive multiple
     * copies of a single event.
     * 
     * @param listener the object to be informed of state changes
     * @see org.havi.ui.event.HScreenDeviceReleasedEvent
     * @see org.havi.ui.event.HScreenDeviceReservedEvent 
     */
    public void addResourceStatusEventListener(ResourceStatusListener listener)
    {
    }

    /**
     * Remove a listener for events about changes in the state of the
     * ownership of this device. This method has no effect if the
     * listener specified is not registered.
     *
     * @param listener the object which is no longer interested
     * @see org.havi.ui.event.HScreenDeviceReleasedEvent
     * @see org.havi.ui.event.HScreenDeviceReservedEvent
     */
    public void removeResourceStatusEventListener(ResourceStatusListener listener)
    {
    }
}

