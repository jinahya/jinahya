package org.havi.ui.event;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import org.davic.resources.ResourceStatusEvent;

/** 
   This event informs that a device on this {@link org.havi.ui.HScreen
   HScreen} has been reserved by an application or other entity in the
   system.
   
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

public class HScreenDeviceReservedEvent 
    extends ResourceStatusEvent
{
    /**
     * Creates an {@link org.havi.ui.event.HScreenDeviceReservedEvent
     * HScreenDeviceReservedEvent} object.  See the class description for
     * details of constructor parameters and default values.  
     *
     * @param source the {@link org.havi.ui.HScreenDevice HScreenDevice}
     * representing the device which has been reserved 
     */
    public HScreenDeviceReservedEvent(Object source)
    {
	super(source);
    } 

    /**
     * Returns the device that has been reserved
     *
     * @return an {@link org.havi.ui.HScreenDevice HScreenDevice}
     * representing the device that has been reserved 
     */
    public Object getSource()
    {
	return (null);
    }

}

