 
package org.havi.ui.event;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
   This class is used to describe the (basic) remote control
   capabilities of the platform. <p> This class is not intended to be
   constructed by applications.
   
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

public class HRcCapabilities 
    extends HKeyCapabilities
{
    /**
     * It is not intended that applications should directly construct
     * {@link org.havi.ui.event.HRcCapabilities HRcCapabilities}
     * objects.
     * <p>
     * Creates an {@link org.havi.ui.event.HRcCapabilities
     * HRcCapabilities} object. See the class description for details
     * of constructor parameters and default values.
     * <p> 
     * This method is protected to allow the platform to override it
     * in a different package scope.  
     */
    protected HRcCapabilities()
    {
    }

    /**
     * Get the <code>HEventRepresentation</code> object for a
     * specified key event <code>keyCode</code>.
     * 
     * @param aCode the key event <code>keyCode</code> for which the 
     * <code>HEventRepresentation</code> should be returned. 
     * @return an <code>HEventRepresentation</code>
     * object for the specified key event <code>keyCode</code>, or
     * <code>null</code> if there is no valid representation available.
     */
    public static HEventRepresentation getRepresentation(int aCode)
    {
	return (null);
    }

    /**
     * Determine if a physical remote control exists in the system.  
     *
     * @return true if a physical remote control exists in the system,
     * false otherwise.
     * @see HKeyCapabilities#getInputDeviceSupported 
     */
    public static boolean getInputDeviceSupported()
    {
	return (true);
    }

    /**
     * Queries whether the remote control can directly generate an event
     * of the given type. Note that this method will return
     * false for key codes which can only be generated on this system
     * via a virtual keyboard.
     *
     * @param keycode the keycode to query e.g. <code>VK_SPACE</code>
     * @return true if events with the given key code can be directly
     * generated on this system via a physical remote control, false
     * otherwise.
     * @see HKeyCapabilities#isSupported
     */
    public static boolean isSupported(int keycode)
    {
        return (false);
    }

}

