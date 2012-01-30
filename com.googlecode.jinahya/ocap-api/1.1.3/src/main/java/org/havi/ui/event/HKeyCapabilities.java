package org.havi.ui.event;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
   This class is used to describe the (basic) keyboard capabilities of
   the platform. <p> This class is not intended to be constructed by
   applications.
   
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

public class HKeyCapabilities
{
    /**
     * It is not intended that applications should directly construct
     * {@link org.havi.ui.event.HKeyCapabilities HKeyCapabilities}
     * objects.
     * <p>
     * Creates an {@link org.havi.ui.event.HKeyCapabilities
     * HKeyCapabilities} object.  See the class description for
     * details of constructor parameters and default values.  
     * <p> 
     * This method is protected to allow the platform to override it
     * in a different package scope.  
     */
    protected HKeyCapabilities()
    {
    }
    
    /**  
     * Determine if keyboard input functionality exists in the
     * system. Note that this functionality may be provided through a
     * &quot;virtual&quot; keyboard.
     * 
     * @return true if keyboard input functionality exists in the
     * system, false otherwise.  
     * @see HRcCapabilities#getInputDeviceSupported 
     */
    public static boolean getInputDeviceSupported()
    {
        return (true);
    }
    
    /**
     * Queries whether the system keyboard can generate an event of
     * the given type. Note that this method does not distinguish
     * between key codes which can only be generated via a virtual
     * keyboard and key codes generated as a result of
     * &quot;real&quot; key presses.
     *
     * @param keycode the keycode to query e.g. <code>VK_SPACE</code>
     * @return true if events with the given key code can (ever) be
     * generated on this system, false otherwise. 
     * @see HRcCapabilities#isSupported 
     */
    public static boolean isSupported(int keycode)
    {
        return (false);
    }
   
} 


