package org.havi.ui.event;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import org.davic.resources.ResourceStatusEvent;

/** 
    This event informs an application that a device for this {@link
    org.havi.ui.HScreen HScreen} has been released by an application
    or other entity in the system.
   
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

public class HScreenDeviceReleasedEvent 
    extends ResourceStatusEvent
{
    /**
     * Creates an {@link org.havi.ui.event.HScreenDeviceReleasedEvent
     * HScreenDeviceReleasedEvent} object.  See the class description for
     * details of constructor parameters and default values.  
     * 
     * @param source the {@link org.havi.ui.HScreenDevice HScreenDevice}
     * which has been released 
     */
    public HScreenDeviceReleasedEvent(Object source)
    {
	super(source);
    }

    /**
     * Returns the device that has been released
     *
     * @return the {@link org.havi.ui.HScreenDevice HScreenDevice}
     * object representing the device that has been released 
     */
    public Object getSource()
    {
	return(null);
    }

}

