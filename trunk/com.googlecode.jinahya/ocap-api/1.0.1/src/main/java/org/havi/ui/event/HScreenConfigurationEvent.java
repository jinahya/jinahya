package org.havi.ui.event;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */


/**
   This event is sent to all registered {@link
   org.havi.ui.event.HScreenConfigurationListener
   HScreenConfigurationListeners} when an {@link
   org.havi.ui.HScreenDevice HScreenDevice} modifies its
   configuration.
   
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
   
   @see org.havi.ui.event.HScreenConfigurationListener
   @see org.havi.ui.HScreenDevice 

*/

public class HScreenConfigurationEvent 
    extends java.util.EventObject
{
    /**
     * Construct an {@link org.havi.ui.event.HScreenConfigurationEvent
     * HScreenConfigurationEvent}
     *
     * @param source the {@link org.havi.ui.HScreenDevice
     * HScreenDevice} whose configuration changed 
     */
    public HScreenConfigurationEvent(Object source)
    {
	super(source);
    }

}


