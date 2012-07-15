package org.havi.ui.event;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

/**
   This class is used to describe the (basic) mouse capabilities of
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

public class HMouseCapabilities
{
    /**
     * It is not intended that applications should directly construct
     * {@link org.havi.ui.event.HMouseCapabilities HMouseCapabilities}
     * objects.
     * <p>
     * Creates an {@link org.havi.ui.event.HMouseCapabilities
     * HMouseCapabilities} object.  See the class description for
     * details of constructor parameters and default values.  
     * <p> 
     * This method is protected to allow the platform to override it
     * in a different package scope.  
     */
    protected HMouseCapabilities()
    {
    }

    /**
     * Determine if a mouse exists in the system.  
     *
     * @return true if a mouse exists in the system, false otherwise.
     */
    public static boolean getInputDeviceSupported()
    {
	return (true);
    }

}

