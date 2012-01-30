package org.havi.ui.event;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.util.EventListener;
import org.havi.ui.event.HScreenLocationModifiedEvent;

/**
   This listener is used to monitor when a component, such as an
   {@link org.havi.ui.HVideoComponent HVideoComponent's} on-screen
   location is modified.

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
      
   @see org.havi.ui.event.HScreenLocationModifiedEvent
   @see org.havi.ui.HVideoComponent
   
*/

public interface HScreenLocationModifiedListener 
    extends java.util.EventListener
{
    /**
    This method is called when the component's on-screen
   location is modified.
    */
    public abstract void report(HScreenLocationModifiedEvent gce);
}

