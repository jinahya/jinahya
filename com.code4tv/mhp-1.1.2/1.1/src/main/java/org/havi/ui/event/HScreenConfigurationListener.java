package org.havi.ui.event;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.util.EventListener;
import org.havi.ui.event.HScreenConfigurationEvent;
                        
/**
   This listener is used to monitor when the configuration of an {@link
   org.havi.ui.HScreenDevice HScreenDevice} is modified.
   
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

   @see org.havi.ui.event.HScreenConfigurationEvent
   @see org.havi.ui.HScreenDevice
   
*/

public interface HScreenConfigurationListener 
    extends java.util.EventListener
{ 
    
    /** This method is called when the configuration of an {@link
      org.havi.ui.HScreenDevice HScreenDevice} is modified.
      @param gce The event notifying the listener of the modification.
    */
    public abstract void report(HScreenConfigurationEvent gce);
}

